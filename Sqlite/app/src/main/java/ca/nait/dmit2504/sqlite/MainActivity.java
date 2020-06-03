package ca.nait.dmit2504.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare data fields for views where you retrieve and set data
    private EditText mDescriptionEditText;
    private EditText mAmountEditText;
    private EditText mDateEditText;
    private ListView mExpensesListView;

    //private SimpleCursorAdapter mExpensesCursorAdapter;
    private ExpenseDatabase mExpenseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and set the views in the activity
        mDescriptionEditText = findViewById(R.id.activity_main_description_edit);
        mAmountEditText = findViewById(R.id.activity_main_amount_edit);
        mDateEditText = findViewById(R.id.activity_main_date_edit);
        mExpensesListView = findViewById(R.id.activity_main_expenses_listview);

        mExpenseDatabase = new ExpenseDatabase(this);

        rebindListView();
    }

    private void rebindListView() {
        Cursor dbCursor = mExpenseDatabase.getAllExpenses();
        // Define an array of columns names used by the cursor
        String[] fromFields = {"_id", "description", "amount", "date"};
        // Define an array of resource ids in the listview item layout
        int[] toViews = new int[] {
                R.id.listview_item_expenseId,
                R.id.listview_item_description,
                R.id.listview_item_amount,
                R.id.listview_item_date
        };
        // Create a SimpleCursorAdapter for the ListView
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.listview_item,
                dbCursor,
                fromFields,
                toViews);
        mExpensesListView.setAdapter(cursorAdapter);
    }

    public void onAddExpense(View view) {
        // Retrieve the value from the input views for description, amount, date
        String description = mDescriptionEditText.getText().toString();
        String amount = mAmountEditText.getText().toString();
        String date = mDateEditText.getText().toString();
        // save the record to the database
        long primaryKeyId = mExpenseDatabase.createExpense(description, amount, date);
        Toast.makeText(this, R.string.create_new_record, Toast.LENGTH_SHORT ).show();
        // clear the text in the input views
        mDescriptionEditText.setText("");
        mAmountEditText.setText("");
        mDateEditText.setText("");

        rebindListView();
    }
}
