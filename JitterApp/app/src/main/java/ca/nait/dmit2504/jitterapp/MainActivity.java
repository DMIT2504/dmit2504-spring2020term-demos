package ca.nait.dmit2504.jitterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();   // simpleName = "MainActivity"

    private TextView mJitterText;

    private ListView mJitterListView;

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_list_jitters:
                Intent listJittersIntent = new Intent(this, MainActivity.class);
                startActivity(listJittersIntent);
                return true;
            case R.id.menu_item_post_jitter:
                Intent postJitterIntent = new Intent(this, SendJitterActivity.class);
                startActivity(postJitterIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the textview in the layout
//        mJitterText = findViewById(R.id.jitters_text);
        mJitterListView = findViewById(R.id.jittersListView);

        // Generate an implementation of the Retrofit interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.youcode.ca")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        YoucodeService youcodeService = retrofit.create(YoucodeService.class);

        // Call a method in your service
        Call<String> getCall = youcodeService.listJSONServlet();
        getCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                Log.i(TAG,"success getting data ");
                String responseBodyText = response.body();
                String[] responseArray = responseBodyText.split("\r\n");

                ArrayAdapter<String> jittersAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, responseArray);
                mJitterListView.setAdapter(jittersAdapter);
//                mJitterText.setText(response.body());

                // Split the response.body() string values into an array of single jitter
                // and populate the listview
            }

            @Override
            public void onFailure(final Call<String> call, final Throwable t) {
                Log.e(TAG, "failure to get data");
            }
        });

    }
}
