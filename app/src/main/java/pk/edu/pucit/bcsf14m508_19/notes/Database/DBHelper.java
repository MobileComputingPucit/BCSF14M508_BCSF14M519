package pk.edu.pucit.bcsf14m508_19.notes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import pk.edu.pucit.bcsf14m508_19.notes.Globals;
import pk.edu.pucit.bcsf14m508_19.notes.Models.UserInfo;

/**
 * Created by abc on 1/18/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Database
 * @project Database
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "note";

    //COLUMN NAMES
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String DATE = "date";

    //COLUMN TYPES
    public static final String TYPE_TEXT = " TEXT ";
    public static final String TYPE_INT = " INTEGER ";
    public static final String SEPERATOR = ", ";
    private static final String DATABASE_NAME = "proj.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "Create table " + TABLE_NAME + " ("
                //need to make primary key NOT NULL
                // and AUTOINCREMENT instead of AUTO_INCREMENT
                + ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT NOT NULL " + SEPERATOR
                + TITLE + TYPE_TEXT + SEPERATOR
                + BODY + TYPE_TEXT + SEPERATOR
                + DATE + TYPE_TEXT + ");";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            String drop_query = "drop database if exists " + DATABASE_NAME;
            db.execSQL(drop_query);
            onCreate(db);
        }
    }

    /**
     * This function inserts data into database
     *
     * @param title    takes a string user name
     * @param body   takes a string type email
     * @param date takes a string type address
     * @return true when data inserted false when failed
     */
    public long insert(String title, String body, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(BODY, body);
        cv.put(DATE, date);
        long i = db.insert(TABLE_NAME, null, cv);
        Log.d("Database_helper", String.valueOf(i));
        //be sure to close database after work is done
        db.close();
        return i;
    }

    public long edit(int id,String title, String body, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, id);
        cv.put(TITLE, title);
        cv.put(BODY, body);
        cv.put(DATE, date);

        int count =db.update(TABLE_NAME, cv, "id="+id, null);
        //be sure to close database after work is done
        db.close();
        return count;
    }

    /*
        tableColumns"
        null for all columns as in SELECT * FROM ...
                new String[] { "column1", "column2", ... } for specific columns as in SELECT column1, column2 FROM ... - you can also put complex expressions here:
                new String[] { "(SELECT max(column1) FROM table1) AS max" } would give you a column named max holding the max value of column1
        whereClause:
        the part you put after WHERE without that keyword, e.g. "column1 > 5"
        should include ? for things that are dynamic, e.g. "column1=?" -> see whereArgs

        whereArgs:
        specify the content that fills each ? in whereClause in the order they appear

        Remaining params:
        just like whereClause the statement after the keyword or null if you don't use it.
    */
    public Cursor read(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{ID, TITLE}, null, null, null, null, null);//"SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id);
        return c;
    }

    /**
     * Deletes Record Based On Email Address
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=" + id, null);
        db.close();
        return false;
    }


    public ArrayList getAll() {
        ArrayList<UserInfo> ui = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[0] + ":" + cursor.getString(cursor.getColumnIndex(ID)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[1] + ":" + cursor.getString(cursor.getColumnIndex(TITLE)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[2] + ":" + cursor.getString(cursor.getColumnIndex(BODY)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[3] + ":" + cursor.getString(cursor.getColumnIndex(DATE)));

            ui.add(new UserInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(TITLE)),
                    cursor.getString(cursor.getColumnIndex(BODY)),
                    cursor.getString(cursor.getColumnIndex(DATE))));
        }
        return ui;
    }
}
