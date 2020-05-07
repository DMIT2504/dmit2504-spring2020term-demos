package ca.nait.dmit2504.singlequestionquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void trueButtonClicked(View view) {
        Toast.makeText(MainActivity.this, R.string.incorrect_answer, Toast.LENGTH_LONG).show();
    }

    public void falseButtonClicked(View view) {
        Toast.makeText(MainActivity.this, R.string.correct_answer, Toast.LENGTH_LONG).show();
    }
}
