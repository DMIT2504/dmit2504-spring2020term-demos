package ca.nait.dmit2504.servicesbroadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // write code to start a service to retrieve messages from http://www.youcode.ca/Week05Servlet
        // and displays the result on the listview

        IntentFilter postDataIntentFilter = new IntentFilter();
        postDataIntentFilter.addAction(JittersIntentService.ACTION_POST_COMPLETE);
        registerReceiver(mFinishedPostingBroadcastReceiver, postDataIntentFilter);
}

    public void sendData(View view) {
        // write code to start a service to post a message to http://www.youcode.ca/JitterServlet

        // Retrieve the message from the EditText
        String name = "sampleUserSam";
        String message = "Sample Message";
        final String urlString = "http://www.youcode.ca/JitterServlet";

        JittersIntentService.startActionPost(this, urlString, name, message);

//        // Create a NetworkAPI object
//        NetworkAPI networkAPI = new NetworkAPI();
//        // Create a Map with the data to send
//        Map<String, String> formData = new HashMap<>();
//        formData.put("LOGIN_NAME", name);
//        formData.put("DATA", message);
//        try {
//            networkAPI.postFormData(urlString, formData);
//        } catch (Exception e) {
//            e.printStackTrace();
//            String errorMessage = e.toString();
//            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
//        }

    }

    // Define a BroadcastReceiver to listen for finishedPostingData message - empty text in the EditText
    private FinishedPostingBroadcastReceiver mFinishedPostingBroadcastReceiver = new FinishedPostingBroadcastReceiver();

    private class FinishedPostingBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            String result = intent.getStringExtra("post_result");
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }

    // Define a BroadcastReceiver to listen for finishedReadingData message - refresh contents of ListView
}