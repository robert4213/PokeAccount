package android.bignerdranch.a277test.database;

import android.bignerdranch.a277test.Transaction;
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
}
