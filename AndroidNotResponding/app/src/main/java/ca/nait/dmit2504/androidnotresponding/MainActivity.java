package ca.nait.dmit2504.androidnotresponding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mCounter = 0;
    TextView mCounterTextView;
    static final String COUNTER_KEY = "counter_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounterTextView = findViewById(R.id.counter_text);
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, mCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCounter = savedInstanceState.getInt(COUNTER_KEY);
        mCounterTextView.setText(String.valueOf(mCounter));
    }

    public void incrementCounter(View view) {
        mCounter++;
        mCounterTextView.setText( String.valueOf(mCounter));
    }

    public void runLongTask(View view) { // throws InterruptedException {

//        Thread.sleep( 15 * 1000);

        try {
            Thread.sleep( 15 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
