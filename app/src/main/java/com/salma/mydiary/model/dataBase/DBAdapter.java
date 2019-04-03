package com.salma.mydiary.model.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.salma.mydiary.model.Note;


public class DBAdapter {

    public static SQLiteDatabase sqLiteDatabase;
    private Context context;
    private Helper helper;

    public DBAdapter(Context _context) {
        context = _context;
        helper=new Helper(context);
    }

    public void insert(Note note){
        sqLiteDatabase=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.NAME_COLUMN,note.getNoteName());
        contentValues.put(helper.BODY_COLUMN,note.getNoteBody());
        sqLiteDatabase.insert(helper.TABLE_NAME, null ,contentValues);


    }

    public Note retrieve (String noteName){
        Note retrievedNote=new Note();
        sqLiteDatabase=helper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT "+helper.NAME_COLUMN+", "+helper.BODY_COLUMN+" FROM "+helper.TABLE_NAME+" WHERE "+ helper.NAME_COLUMN+" LIKE '"+noteName+"'",null );
        while (cursor.moveToNext()){
            retrievedNote.setNoteName(cursor.getString(0));
            retrievedNote.setNoteBody(cursor.getString(1));
        }
        return  retrievedNote;
    }

    private class Helper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "notesDataBase.db";
        private static final String TABLE_NAME = "notes";
        private static final String NAME_COLUMN = "NoteName";
        private static final String BODY_COLUMN = "NoteBody";
        private static final String PRIMARY_KEY = "_id";

        private String Create_query = "CREATE TABLE " + TABLE_NAME + " (" + PRIMARY_KEY + " Integer PRIMARY KEY AUTOINCREMENT, " + NAME_COLUMN + " varchar(30), " + BODY_COLUMN + " varchar(200) );";

        public Helper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Create_query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}
