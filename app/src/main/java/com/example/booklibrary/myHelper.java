package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myHelper extends SQLiteOpenHelper {
    Context context;
    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "myDatabase.db";

    public static final String TABLE_NAME = "BOOK_LIBRARY";
    public static final String KEY_ID = "_ID";
    public static final String KEY_TITLE = "TITLE";
    public static final String KEY_AUTHOR = "AUTHOR";
    public static final String KEY_PAGES = "PAGES";

     myHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " TEXT, "
                + KEY_AUTHOR + " TEXT, " +
                KEY_PAGES + " TEXT ) ; ";
        sqLiteDatabase.execSQL(table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
//         onCreate(sqLiteDatabase);
    }

    void addBook(String bookTitle, String bookAuthor, String bookPages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, bookTitle);
        values.put(KEY_AUTHOR, bookAuthor);
        values.put(KEY_PAGES, bookPages);
        Long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed to add in library " + bookTitle, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully added in library " + bookTitle, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor resultData() {
        // hum ne query le li aur database le liya kyun k yeh dono jaein gay cursir k ander L

        String query = "SELECT * FROM " + TABLE_NAME; // is ne jana hai cursor k rawquery methid mein
        SQLiteDatabase db = this.getReadableDatabase(); // is ne rawquery ko access karwana hai


        Cursor cursor = null; // main ya hi hai

        if (db != null) {

            cursor = db.rawQuery(query, null); // maybe given new String [] []

        }

        return cursor;
    }
    void updateData(String rowId,String bookTitle, String bookAuthor, String bookPages){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, bookTitle);
        values.put(KEY_AUTHOR, bookAuthor);
        values.put(KEY_PAGES, bookPages);
        long result= db.update(TABLE_NAME,values,"_id=? ",new String[]{rowId});

        if (result == -1){
            Toast.makeText(context, "Problem", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }

    }
    void rowDeletion(String row_id){
         SQLiteDatabase db =this.getWritableDatabase();
         long result =db.delete(TABLE_NAME,"_id =?",new String[]{row_id});

         if(result==-1){
             Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
         }else
             Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();

    }
    void deleteaAll(){
         SQLiteDatabase db =this.getWritableDatabase();
         db.execSQL("Delete  From "+TABLE_NAME);
    }
}


