package ca.nait.dmit2504.canadianprovinceslistactivity;

//import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

// Step 1: Modify the activity class to inherit from ListActivity
public class MainActivity extends ListActivity {

    // Step 2: Define and create/get the data source
    final String[] provinces = {"British Columbia", "Alberta", "Saskatchewan", "Manitoba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // Step 3: Define a data adapter that provides the data fro the adapter view.
        ListAdapter provincesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinces);
        // Step 4: Set the adapter for the adapter view
        setListAdapter(provincesAdapter);

        // Step 5: Optional handle item click event
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                String message = ((TextView) view).getText() + " " + position;
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
