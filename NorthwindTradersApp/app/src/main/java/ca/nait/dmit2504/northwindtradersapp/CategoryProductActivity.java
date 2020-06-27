package ca.nait.dmit2504.northwindtradersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CategoryProductActivity extends AppCompatActivity {

    // Define views to access
    Spinner mCategorySpinner;
    ListView mCategoryProductsListView;
    // Define the database to use
    NorthwindTradersDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        // Create an instance of our DB helper
        mDatabase = new NorthwindTradersDatabase(this);
        // Find the Spinner view
        mCategorySpinner = findViewById(R.id.category_spinner);
        // Find the ListView
        mCategoryProductsListView = findViewById(R.id.categor_product_listview);

        // Get a cursor of the Category
        Cursor categoryCursor = mDatabase.getAllCategories();
        // Construct a SimpleCursorAdapter for the spinner
        String[] columnNames = {NorthwindTradersDatabase.TABLE_CATEGORY_COLUMN_NAME};
        int[] viewIds = {android.R.id.text1};
        SimpleCursorAdapter categoryCursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoryCursor,
                columnNames,
                viewIds,
                0
        );
        categoryCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCategorySpinner.setAdapter(categoryCursorAdapter);
        // Update the Products ListView when an item is selected in a spinner
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
//                Toast.makeText(getApplicationContext(), "Position: " + position + ", Id:" + id, Toast.LENGTH_SHORT).show();
                int categoryID = (int) id;
                Cursor productCursor = mDatabase.findProductByCategory(categoryID);
                String[] productColumnNames = {
                        NorthwindTradersDatabase.TABLE_PRODUCT_COLUMN_PRODUCT_NAME,
                        NorthwindTradersDatabase.TABLE_PRODUCT_COLUMN_UNIT_PRICE
                };
                int[] productViewIds = {R.id.list_item_product_productname, R.id.list_item_product_unitprice};
                SimpleCursorAdapter productAdapter = new SimpleCursorAdapter(
                        getApplicationContext(),
                        R.layout.list_item_product,
                        productCursor,
                        productColumnNames,
                        productViewIds,
                        0
                );
                mCategoryProductsListView.setAdapter(productAdapter);

            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });


    }
}