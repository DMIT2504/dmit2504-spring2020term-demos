package ca.nait.dmit2504.androidthreads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    public void runLongTask(View view) {
        // double the counter value using a worker thread
        // Two ways to create a thread
        // 1) extends the "Thread" class
        // 2) Implement the "Runnable" interface
        // Implement the "run()" method in both cases

        Thread doubleCounterThread = new Thread(() -> {
            // this is the run method
            try {
                Thread.sleep(30 * 1000);
                mCounter *= 2;

//                mCounterTextView.setText(String.valueOf(mCounter));

                mCounterTextView.post(new Thread(()-> {
                    mCounterTextView.setText(String.valueOf(mCounter));
                }));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        doubleCounterThread.start();

    }
}
