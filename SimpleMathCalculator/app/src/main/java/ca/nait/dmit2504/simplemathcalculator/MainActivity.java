package ca.nait.dmit2504.simplemathcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mNum1EditText;
    private EditText mNum2EditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNum1EditText = findViewById(R.id.num1_edit_text);
        mNum2EditText = findViewById(R.id.num2_edit_text);
        mResultTextView = findViewById(R.id.result_text_view);

    }

    public void addNumbers(View view) {
        String num1Text = mNum1EditText.getText().toString();
        String num2Text = mNum2EditText.getText().toString();

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);
        double result = num1 + num2;

        mResultTextView.setText(result + "");

    }

    public void subtractNumbers(View view) {
        String num1Text = mNum1EditText.getText().toString();
        String num2Text = mNum2EditText.getText().toString();

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);
        double result = num1 - num2;

        mResultTextView.setText(result + "");
    }

    public void multiplyNumbers(View view) {
        String num1Text = mNum1EditText.getText().toString();
        String num2Text = mNum2EditText.getText().toString();

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);
        double result = num1 * num2;

        mResultTextView.setText(result + "");
    }

    public void divideNumbers(View view) {
        String num1Text = mNum1EditText.getText().toString();
        String num2Text = mNum2EditText.getText().toString();

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);
        double result = 0;
        if (num2 != 0 ) {
            result = num1 / num2;
        }

        String resultString = result + "";
        mResultTextView.setText(resultString);
    }

}
