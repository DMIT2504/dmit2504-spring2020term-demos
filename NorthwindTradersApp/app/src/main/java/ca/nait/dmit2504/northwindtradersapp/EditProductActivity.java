package ca.nait.dmit2504.northwindtradersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProductActivity extends AppCompatActivity {

    TextView mProductIDTextView;
    EditText mProductNameEditText;
    EditText mUnitPriceEditText;
    Button mUpdateButton;
    Button mDeleteButton;
    NorthwindTradersDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        mProductIDTextView = findViewById(R.id.activity_edit_product_productid);
        mProductNameEditText = findViewById(R.id.activity_edit_product_productname);
        mUnitPriceEditText = findViewById(R.id.activity_edit_product_unitprice);
        mUpdateButton = findViewById(R.id.activity_edit_update);
        mDeleteButton = findViewById(R.id.activity_edit_delete);

        mDatabase = new NorthwindTradersDatabase(this);

        // Check if the "productID" extra is passed into this activity
        if (getIntent() != null && getIntent().hasExtra("productID")) {
            // Retrieve the productID extra value
            int productID = getIntent().getIntExtra("productID", 0);
            // Read from the database a Product with the productID value
            Product singleResult = mDatabase.findProduct(productID);
            if (singleResult != null) {
                // Update our views with data from singleResult
                mProductIDTextView.setText( String.valueOf(singleResult.productID) );
                mProductNameEditText.setText( singleResult.productName );
                mUnitPriceEditText.setText( singleResult.unitPrice );
            }
        }

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Read the productId, productName, unitPrice from the views
                int productId = Integer.parseInt(mProductIDTextView.getText().toString());
                String productName = mProductNameEditText.getText().toString();
                String unitPrice = mUnitPriceEditText.getText().toString();
                mDatabase.updateProduct(productId, productName, unitPrice);
                // close the current activity
                finish();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int productId = Integer.parseInt(mProductIDTextView.getText().toString());
                mDatabase.deleteProduct(productId);
                finish();
            }
        });

    }
}