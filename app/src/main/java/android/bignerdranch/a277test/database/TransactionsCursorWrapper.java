package android.bignerdranch.a277test.database;

import android.bignerdranch.a277test.Transaction;
import android.database.Cursor;
import android.database.CursorWrapper;

public class TransactionsCursorWrapper extends CursorWrapper {
    public TransactionsCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Transaction getTransaction(){
        String ACCOUNTID=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.ACCOUNTID));
        String INCOME_EXPENSE=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE));
        String CARD_NUMBER=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.CARD_NUMBER));
        String Date=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Date));
        String Type=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Type));
        String Value=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Value));

        Transaction transaction=new Transaction();

        transaction.setACCOUNTID(ACCOUNTID);
        transaction.setINCOME_EXPENSE(INCOME_EXPENSE);
        transaction.setVALUE(Value);
        transaction.setTYPE(Type);
        transaction.setCARD_NUMBER(CARD_NUMBER);
        transaction.setDATE(Date);

        return transaction;
    }
}
