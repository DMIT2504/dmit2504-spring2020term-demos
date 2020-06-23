package ca.nait.dmit2504.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

    private boolean mEditMode = false;
    private long mEditId = 0;
    private Button mAddButton;
    private Button mUpdateButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and set the views in the activity
        mDescriptionEditText = findViewById(R.id.activity_main_description_edit);
        mAmountEditText = findViewById(R.id.activity_main_amount_edit);
        mDateEditText = findViewById(R.id.activity_main_date_edit);
        mExpensesListView = findViewById(R.id.activity_main_expenses_listview);

        mAddButton = findViewById(R.id.activity_main_add_expense_button);
        mUpdateButton = findViewById(R.id.activity_main_update_expense_Button);
        mCancelButton = findViewById(R.id.activity_main_cancel_button);
        mUpdateButton.setVisibility(View.GONE);
        mCancelButton.setVisibility(View.GONE);

        // Allow user to edit an expense when the list item is clicked
        mExpensesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                Expense singleResult = mExpenseDatabase.findExpense(id);
                if (singleResult != null) {
                    mEditId = id;
                    mAddButton.setVisibility(View.GONE);
                    mUpdateButton.setVisibility(View.VISIBLE);
                    mCancelButton.setVisibility(View.VISIBLE);

                    mDescriptionEditText.setText(singleResult.getDescription());
                    mAmountEditText.setText(singleResult.getAmount());
                    mDateEditText.setText(singleResult.getDate());
                }
            }
        });

        // Allow user to delete an expense when the list item is long clicked
        mExpensesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                mExpenseDatabase.deleteExpense(id);
                rebindListView();
                cancelEditMode(view);
                return true;
            }
        });

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
                toViews,
                0);
        mExpensesListView.setAdapter(cursorAdapter);
    }

    public void onAddExpense(View view) {
        // Retrieve the value from the input views for description, amount, date
        String description = mDescriptionEditText.getText().toString();
        String amount = mAmountEditText.getText().toString();
        String date = mDateEditText.getText().toString();

        // Check that all fields have values
        StringBuilder stringBuilder = new StringBuilder();
        if (description.isEmpty()) {
            stringBuilder.append("Descripton value is required. \n");
        }
        if (amount.isEmpty()) {
            stringBuilder.append("Amount value is required. \n");
        }
        if (date.isEmpty()) {
            stringBuilder.append("Date value is required.");
        }
        if (stringBuilder.length() > 0) {
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        } else {
            // save the record to the database
            long primaryKeyId = mExpenseDatabase.createExpense(description, amount, date);
            Toast.makeText(this, getResources().getString(R.string.create_new_record, primaryKeyId), Toast.LENGTH_SHORT ).show();
            // clear the text in the input views
            mDescriptionEditText.setText("");
            mAmountEditText.setText("");
            mDateEditText.setText("");

            rebindListView();
        }

    }

    public void onUpdateExpense(View view) {
        // Retrieve the value from the input views for description, amount, date
        String description = mDescriptionEditText.getText().toString();
        String amount = mAmountEditText.getText().toString();
        String date = mDateEditText.getText().toString();

        StringBuilder stringBuilder = new StringBuilder();
        if (description.isEmpty()) {
            stringBuilder.append("Descripton value is required. \n");
        }
        if (amount.isEmpty()) {
            stringBuilder.append("Amount value is required. \n");
        }
        if (date.isEmpty()) {
            stringBuilder.append("Date value is required.");
        }
        if (stringBuilder.length() > 0) {
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        } else {
            // save the record to the database
            long rowsUpdated = mExpenseDatabase.updateExpense(mEditId, description, amount, date);
            if (rowsUpdated == 1) {
                Toast.makeText(this, getResources().getString(R.string.update_record, mEditId), Toast.LENGTH_SHORT ).show();
            } else {
                Toast.makeText(this, "Update was not successful", Toast.LENGTH_SHORT ).show();
            }
            // clear the text in the input views
            mDescriptionEditText.setText("");
            mAmountEditText.setText("");
            mDateEditText.setText("");

            rebindListView();
            cancelEditMode(view);
        }

    }

    public void cancelEditMode(View view) {
        mEditMode = false;
        mEditId = 0;
        mUpdateButton.setVisibility(View.GONE);
        mCancelButton.setVisibility(View.GONE);

        mDescriptionEditText.setText("");
        mAmountEditText.setText("");
        mDateEditText.setText("");
        mAddButton.setVisibility(View.VISIBLE);
    }
}
