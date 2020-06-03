package ca.nait.dmit2504.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListExpensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenses);

        ExpenseAdapter expenseAdapter = new ExpenseAdapter(this);
        ListView expensesListView = findViewById(R.id.activity_list_expenses_listview);
        expensesListView.setAdapter(expenseAdapter);

        Expense expense1 = new Expense();
        expense1.setId(66);
        expense1.setDescription("Order 66");
        expense1.setAmount("1000000");
        expense1.setDate("2020-06-01");

        expenseAdapter.addItem(expense1);
    }
}
