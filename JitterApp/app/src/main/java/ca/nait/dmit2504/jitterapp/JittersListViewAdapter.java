package ca.nait.dmit2504.jitterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class JittersListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Jitter> jittersList;
    private LayoutInflater layoutInflator;

    public JittersListViewAdapter(final Context context, final List<Jitter> jittersList) {
        this.context = context;
        this.jittersList = jittersList;

        layoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return jittersList.size();
    }

    @Override
    public Jitter getItem(final int position) {
        return jittersList.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // Inflate the XML custom listview item layout file into a Java View object
        View rowView = layoutInflator.inflate(R.layout.custom_listview_item, null);

        // Find the individual views in the custom listview layout
        TextView dateTextView = rowView.findViewById(R.id.custom_listview_item_date);
        TextView loginNameTextView = rowView.findViewById(R.id.custom_listview_item_loginname);
        TextView dataTextView = rowView.findViewById(R.id.custom_listview_item_data);

        // Change the text for each item
        Jitter currentJitter = jittersList.get(position);
        dateTextView.setText(currentJitter.getDate());
        loginNameTextView.setText(currentJitter.getLoginName());
        dataTextView.setText(currentJitter.getData());

        return rowView;
    }
}
