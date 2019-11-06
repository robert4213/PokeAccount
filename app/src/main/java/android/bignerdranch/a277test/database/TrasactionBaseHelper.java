package android.bignerdranch.a277test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.bignerdranch.a277test.database.TransactionDbSchema.Transactions.NAME;

public class TrasactionBaseHelper extends SQLiteOpenHelper {
    private  static final int VERSION=1;
    private static final String DATABASE_NAME="transactionBase.db";
    TrasactionBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MainActivity","table created!!!!!!!!!!!!!!!!!!");
        db.execSQL("create table "+ NAME+"(" +
                TransactionDbSchema.Transactions.Cols.ACCOUNTID+","+
                TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE+","+
                        TransactionDbSchema.Transactions.Cols.Type+","+
                        TransactionDbSchema.Transactions.Cols.Value+","+
                        TransactionDbSchema.Transactions.Cols.Date+","+
                        TransactionDbSchema.Transactions.Cols.CARD_NUMBER+","+
                "PRIMARY KEY "+ "("+ TransactionDbSchema.Transactions.Cols.ACCOUNTID+ ","+ TransactionDbSchema.Transactions.Cols.CARD_NUMBER+
                ","+ TransactionDbSchema.Transactions.Cols.Date+","+ TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE+
                ")"+")"

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
