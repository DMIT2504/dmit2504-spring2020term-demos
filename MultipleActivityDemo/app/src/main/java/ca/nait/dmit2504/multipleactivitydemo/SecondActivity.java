package ca.nait.dmit2504.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Check if an intent was used to start this activity and if the intent has an extra named "username"
        if (getIntent() != null && getIntent().hasExtra("username")) {
            TextView usernameTextView = findViewById(R.id.activity_second_username_text_view);
            // Retrieve the String EXTRA named username
            String username = getIntent().getStringExtra("username");
            usernameTextView.setText(username);
        }
    }

    public void onCloseActivity(View view) {
        finish();
    }
}
