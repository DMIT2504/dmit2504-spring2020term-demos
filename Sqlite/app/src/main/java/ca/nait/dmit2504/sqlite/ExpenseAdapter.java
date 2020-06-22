package ca.nait.dmit2504.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Expense> mExpenses;

    public ExpenseAdapter(final Context context) {
        mContext = context;
        mExpenses = new ArrayList<>();
    }

    public void addItem(Expense newExpense) {
        mExpenses.add(newExpense);
        notifyDataSetChanged();
    }

    public void removeItem(Expense existingExpense) {
        mExpenses.remove(existingExpense);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mExpenses.size();
    }

    @Override
    public Expense getItem(final int position) {
        return mExpenses.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return mExpenses.get(position).getId();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItemView = inflater.inflate(R.layout.listview_item, parent, false);
        TextView expenseIdTextView = listItemView.findViewById(R.id.listview_item_expenseId);
        TextView descriptionTextView = listItemView.findViewById(R.id.listview_item_description);
        TextView amountTextView = listItemView.findViewById(R.id.listview_item_amount);
        TextView dateTextView = listItemView.findViewById(R.id.listview_item_date);

        Expense currentExpense = mExpenses.get(position);
        expenseIdTextView.setText(String.valueOf(currentExpense.getId()));
        descriptionTextView.setText(currentExpense.getDescription());
        amountTextView.setText(currentExpense.getAmount());
        dateTextView.setText(currentExpense.getDate());

        return listItemView;
    }
}
