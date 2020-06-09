package ca.nait.dmit2504.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Step 1: Create a Java class that extends SQLiteOpenHelper
public class ExpenseDatabase extends SQLiteOpenHelper {

    // Step 2: Define constants for database name, database version, table name, column names
    private static final String DATABASE_NAME = "expenses.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_EXPENSE = "expenses";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_DATE = "date";

    // Step 3: Create a constructor with a Context parameter and implement
    // the onCreate() and onUpgrade() methods
    public ExpenseDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        // execute SQL statements to create required database tables
        db.execSQL("CREATE TABLE " + TABLE_EXPENSE
            + "(_id INTEGER PRIMARY KEY, "
                + COLUMN_DESCRIPTION + " TEXT, "
            +   COLUMN_AMOUNT + " TEXT, "
            +  COLUMN_DATE + " TEXT);");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // SQL code to execute when database schema changes (database version)
        db.execSQL("DROP TABLE " + TABLE_EXPENSE);
        onCreate(db);
    }

    // Step 4: Code methods to perform database operations
    public long createExpense(String description, String amount, String date) {
        // Get a writeable database
        SQLiteDatabase db = getWritableDatabase();
        // Create a ContentValue with the values to insert
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_DATE, date);
        // Call the insert() method
        return db.insert(TABLE_EXPENSE, null, values);
    }

    public Cursor getAllExpenses() {
        // Create a readable database
        SQLiteDatabase db = getReadableDatabase();
        // Construct a SQL query statement
        String queryStatement = "SELECT _id, "

                + COLUMN_DESCRIPTION + ", "
                + COLUMN_AMOUNT + ", "
                + COLUMN_DATE
                + " FROM " + TABLE_EXPENSE
                + " ORDER BY " + COLUMN_DATE + " DESC";
        // Execute the raw query
        return db.rawQuery(queryStatement, null);
    }

}
