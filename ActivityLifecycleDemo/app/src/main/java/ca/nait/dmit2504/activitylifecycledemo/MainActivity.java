package ca.nait.dmit2504.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onCreate() \n");
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onStart() \n");
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onResume() \n");
    }

    @Override
    protected void onPause() {
        super.onPause();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onPause() \n");
    }

    @Override
    protected void onStop() {
        super.onStop();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onStop() \n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onRestart() \n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        TextView stateTextView = findViewById(R.id.text_view_state);
        stateTextView.append("onDestroy() \n");
    }


}
