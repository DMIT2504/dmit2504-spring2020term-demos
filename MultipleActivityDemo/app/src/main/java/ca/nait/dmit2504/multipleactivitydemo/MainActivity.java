package ca.nait.dmit2504.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLaunchMoodle(View view) {
        // Create an intent to view a website
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://moodle.nait.ca"));
        startActivity(intent);
    }

    public void onClickCallSam(View view) {
        // Create an intent to dial a number
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7803785286"));
        startActivity(intent);
    }

    public void onClickStartSecondActivity(View view) {
        // Retrieve the username from the EditText
//        EditText usernameEditText = findViewById(R.id.username_edit_text);
//        String username = usernameEditText.getText().toString();

        String username = ((EditText) findViewById(R.id.username_edit_text)).getText().toString();
        // Create an explicit intent to start the SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        // Pass the username value as an EXTRA to the intent
        intent.putExtra("username", username);
        // Start the SecondActivity
        startActivity(intent);

    }
}
