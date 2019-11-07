package android.bignerdranch.a277test.database;

import android.bignerdranch.a277test.Transaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class TransactionLab {
    private static TransactionLab mtransaction;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TransactionLab(Context mContext){
        mContext=mContext.getApplicationContext();
        mDatabase=new TrasactionBaseHelper(mContext).getWritableDatabase();
        Log.d("MainActivity","DB EXECUTED");



    }

    public SQLiteDatabase getmDatabase() {
        return mDatabase;
    }

    public ArrayList<Transaction> getmTransactions() {

        ArrayList<Transaction> transactions=new ArrayList<>();
        TransactionsCursorWrapper cursor=queryTransactions(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                transactions.add(cursor.getTransaction());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return transactions;
    }

    public static TransactionLab getMtransaction(Context context) {
        mtransaction=new TransactionLab(context);
        return mtransaction;
    }

    private static ContentValues getContentValues(Transaction transaction){
        ContentValues values=new ContentValues();
        values.put(TransactionDbSchema.Transactions.Cols.ACCOUNTID,transaction.getACCOUNTID().toString());

        values.put(TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE,transaction.getINCOME_EXPENSE());
        values.put(TransactionDbSchema.Transactions.Cols.Type,transaction.getTYPE());
        values.put(TransactionDbSchema.Transactions.Cols.Date,transaction.getDATE());
        values.put(TransactionDbSchema.Transactions.Cols.Value,transaction.getVALUE());
        return values;
    }
    public void addTransaction(Transaction transaction){
        ContentValues values=getContentValues(transaction);
        mDatabase.insert(TransactionDbSchema.Transactions.NAME,null,values);
    }
    private  TransactionsCursorWrapper queryTransactions(String whereClause, String[] whereArgs){
        Cursor cursor=mDatabase.query(TransactionDbSchema.Transactions.NAME,null,whereClause,whereArgs,null,null,null);
        return new TransactionsCursorWrapper(cursor);
    }
}
