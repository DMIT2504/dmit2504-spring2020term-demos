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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NorthwindTraders.db";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_CATEGORY_COLUMN_ID = BaseColumns._ID;
    public static final String TABLE_CATEGORY_COLUMN_NAME = "categoryName";
    public static final String TABLE_CATEGORY_COLUMN_DESCRIPTION = "description";

    // Step 3: Define a constructor with a Context parameter
    public NorthwindTradersDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Step 4: Execute DDL statements to create tables
    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE category (_id integer primary key, categoryName TEXT, description TEXT);");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE category;");
        onCreate(db);
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
