package ca.nait.dmit2504.northwindtradersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

// Step 1: Create a class that extends SQLiteOpenHelper
public class NorthwindTradersDatabase extends SQLiteOpenHelper {

    // Step 2: Define data fields for database name, table name, and column names
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "NorthwindTraders.db";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_CATEGORY_COLUMN_ID = BaseColumns._ID;
    public static final String TABLE_CATEGORY_COLUMN_NAME = "categoryName";
    public static final String TABLE_CATEGORY_COLUMN_DESCRIPTION = "description";

    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_PRODUCT_COLUMN_ID = BaseColumns._ID;   // "_id"
    public static final String TABLE_PRODUCT_COLUMN_PRODUCT_NAME = "productName";
    public static final String TABLE_PRODUCT_COLUMN_UNIT_PRICE = "unitPrice";
    public static final String TABLE_PRODUCT_COLUMN_CATEGORYID = "categoryID";


    // Step 3: Define a constructor with a Context parameter
    public NorthwindTradersDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Step 4: Execute DDL statements to create tables
    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE category (_id integer primary key, categoryName TEXT, description TEXT);");
        db.execSQL("CREATE TABLE product (_id integer primary key, productName TEXT, unitPrice TEXT, categoryID integer REFERENCES category(_id))");

        // Create sample data
        db.execSQL("INSERT INTO category(categoryName) VALUES('Seafood');");
        db.execSQL("INSERT INTO category(categoryName) VALUES('Meat');");
        db.execSQL("INSERT INTO category(categoryName) VALUES('Soft Drinks');");

        db.execSQL("INSERT INTO product(productName,unitPrice,categoryID) VALUES('Lobster','100', 1);");
        db.execSQL("INSERT INTO product(productName,unitPrice,categoryID) VALUES('Crab','200', 1);");
        db.execSQL("INSERT INTO product(productName,unitPrice,categoryID) VALUES('Fish','50', 1);");
        db.execSQL("INSERT INTO product(productName,unitPrice,categoryID) VALUES('Coca-Cola','5', 3);");
        db.execSQL("INSERT INTO product(productName,unitPrice,categoryID) VALUES('Pepsi','4', 3);");


    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE product;");
        db.execSQL("DROP TABLE category;");
        onCreate(db);
    }

    public int updateProduct(int productID, String productName, String unitPrice) {
        // Get a writeable database
        SQLiteDatabase db = getWritableDatabase();

        // Construct a ContentValues to update
        ContentValues values = new ContentValues();
        values.put(TABLE_PRODUCT_COLUMN_ID, productID);
        values.put(TABLE_PRODUCT_COLUMN_PRODUCT_NAME, productName);
        values.put(TABLE_PRODUCT_COLUMN_UNIT_PRICE, unitPrice);

        String whereClause = " WHERE _id = ?";
        String[] whereArgs = {String.valueOf(productID)};
        return db.update(TABLE_PRODUCT, values, whereClause, whereArgs);
    }

    public Product findProductByIndex(int productIndex, int categoryID) {
        Product singleResult = null;

        Cursor productCursor = findProductByCategory(categoryID);
        int foundIndex = 0;
        while (productCursor.moveToNext()) {
            if (productIndex == foundIndex) {
                singleResult = new Product();
                singleResult.productID = productCursor.getInt(0);
                singleResult.productName = productCursor.getString(1);
                singleResult.unitPrice = productCursor.getString(2);
                break;
            }
        }
        productCursor.close();

        return singleResult;
    }

    public int deleteProduct(int productID) {
        // Get a writeable database
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = " WHERE _id = ?";
        String[] whereArgs = {String.valueOf(productID)};
        return db.delete(TABLE_PRODUCT, whereClause, whereArgs);
    }


    // Step 5: Add methods to perform CRUD operations
    public long createCategory(String categoryName, String description) {
        // Get a writeable database
        SQLiteDatabase db = getWritableDatabase();

        // Construct a ContentValues object and set the key-value pairs to update
        ContentValues values = new ContentValues();
        values.put(TABLE_CATEGORY_COLUMN_NAME, categoryName);
        values.put(TABLE_CATEGORY_COLUMN_DESCRIPTION, description);

        // Call the insert method
        return db.insert(TABLE_CATEGORY, null, values);
    }

    // Return a cursor of product for a given category
    public Cursor findProductByCategory(int categoryID) {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();
        // Construct SQL statement to return product for a given category
        String queryStatement = "SELECT _id, productName, unitPrice FROM product WHERE categoryID = ?";
        String[] selectArgs = {String.valueOf(categoryID)};

        // Execute and return the query results
        return db.rawQuery(queryStatement, selectArgs);
    }

    // Return a cursor with all categories
    public Cursor getAllCategories() {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();

        // Constructor SQL query statement
        String queryStatement = "SELECT _id, categoryName, description FROM category ORDER BY categoryName";

        // Call the rawQuery method
        return db.rawQuery(queryStatement, null);
    }

    // Return a list of Category POJO
    public ArrayList<Category> getAllCategoryPOJO() {
        ArrayList<Category> categories = new ArrayList<>();
        Cursor resultListCursor = getAllCategories();
        while (resultListCursor.moveToNext()) {
            Category singleCategory = new Category();
            singleCategory.categoryID = resultListCursor.getInt(resultListCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_ID));
            singleCategory.categoryName = resultListCursor.getString(resultListCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_NAME));
            singleCategory.description = resultListCursor.getString(resultListCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_DESCRIPTION));
            // add POJO to list
            categories.add(singleCategory);
        }
        return  categories;
    }

    // Return a single category for a given id
    public Category findCategory(int categoryID) {
        // Get a readable database
        SQLiteDatabase db = getReadableDatabase();

        // Construct a SQL query statement
        String queryStatement = "SELECT _id, categoryName, description FROM category WHERE _id = ?";
        String[] selectionArgs = {String.valueOf(categoryID)};

        // Execute the query statement
        Cursor singleResultCursor = db.rawQuery(queryStatement, selectionArgs);

        // Copy the first row to a Category POJO
        Category foundCategory = null;
        if (singleResultCursor.getCount() == 1) {
            singleResultCursor.moveToFirst();
            // Copy the column values from the cursor to our POJO
            foundCategory = new Category();
//            foundCategory.categoryID = singleResultCursor.getInt(0);
//            foundCategory.categoryName = singleResultCursor.getString(1);
//            foundCategory.description = singleResultCursor.getString(2);
            foundCategory.categoryID = singleResultCursor.getInt(singleResultCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_ID));
            foundCategory.categoryName = singleResultCursor.getString(singleResultCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_NAME));
            foundCategory.description = singleResultCursor.getString(singleResultCursor.getColumnIndex(TABLE_CATEGORY_COLUMN_DESCRIPTION));
        }
        singleResultCursor.close();

        return foundCategory;
    }

}
