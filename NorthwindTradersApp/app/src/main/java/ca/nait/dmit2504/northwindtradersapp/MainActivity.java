package ca.nait.dmit2504.northwindtradersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Define data fields to be access from your methods
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mCategoryNameEditText;
    private EditText mDescriptionEditText;
    private Button mSaveButton;
    private Spinner mCategoriesSpinner;
    private NorthwindTradersDatabase mNorthwindDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all the views in your layout
        mCategoryNameEditText = findViewById(R.id.activity_main_category_name);
        mDescriptionEditText = findViewById(R.id.activity_main_description);
        mSaveButton = findViewById(R.id.activity_main_addcategory);
        mCategoriesSpinner = findViewById(R.id.activity_main_categories_spinner);

        mNorthwindDB = new NorthwindTradersDatabase(this);

        // Create a new Category in our database when the save button is clicked
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Retrieve the categoryName and description from their corresponding views
                String categoryName = mCategoryNameEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                mNorthwindDB.createCategory(categoryName, description);
                // Clear all input views
                mCategoryNameEditText.setText("");
                mDescriptionEditText.setText("");
                bindDataToCategoriesSpinner();
            }
        });

        bindDataToCategoriesSpinner();

        mCategoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                ArrayList<Category> categories = mNorthwindDB.getAllCategoryPOJO();
                Category selectedCategory = categories.get(position);
                mCategoryNameEditText.setText(selectedCategory.categoryName);
                mDescriptionEditText.setText(selectedCategory.description);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }

        });
    }

    private void bindDataToCategoriesSpinner() {
        ArrayList<Category> categories = mNorthwindDB.getAllCategoryPOJO();
        ArrayList<String> categoryNames = new ArrayList<>();
        for (Category singleCategory : categories) {
            categoryNames.add(singleCategory.categoryName);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categoryNames
        );
        mCategoriesSpinner.setAdapter(spinnerAdapter);

    }
}