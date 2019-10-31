package android.bignerdranch.a277test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.bignerdranch.a277test.database.AccountDbSchema.AccountTable;

public class AccountBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "accountBase.db";

    public AccountBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ AccountTable.NAME + "("+
                "_id integer primary key autoincrement,"+
                AccountTable.Cols.UUID+","+
                AccountTable.Cols.Type+","+
                AccountTable.Cols.COMPANY+","+
                AccountTable.Cols.BALANCE+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}