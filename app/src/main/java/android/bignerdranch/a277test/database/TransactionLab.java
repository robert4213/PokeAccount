package android.bignerdranch.a277test.database;

import android.bignerdranch.a277test.Transaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class TransactionLab {
    private static TransactionLab mtransaction;
    private List<Transaction> mTransactions;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TransactionLab(Context mContext){
        mContext=mContext.getApplicationContext();
        mDatabase=new TrasactionBaseHelper(mContext).getWritableDatabase();
        Log.d("MainActivity","DB EXECUTED");
        mTransactions=new ArrayList<>();


    }

    public SQLiteDatabase getmDatabase() {
        return mDatabase;
    }

    public List<Transaction> getmTransactions() {
        return mTransactions;
    }

    public static TransactionLab getMtransaction(Context context) {
        mtransaction=new TransactionLab(context);
        return mtransaction;
    }

    private static ContentValues getContentValues(Transaction transaction){
        ContentValues values=new ContentValues();
        values.put(TransactionDbSchema.Transactions.Cols.ACCOUNTID,transaction.getACCOUNTID());
        values.put(TransactionDbSchema.Transactions.Cols.CARD_NUMBER,transaction.getCARD_NUMBER());
        values.put(TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE,transaction.getINCOME_EXPENSE());
        values.put(TransactionDbSchema.Transactions.Cols.Type,transaction.getTYPE());
        return values;
    }
    public void addTransaction(Transaction transaction){
        ContentValues values=getContentValues(transaction);
        mDatabase.insert(TransactionDbSchema.Transactions.NAME,null,values);
    }
}
