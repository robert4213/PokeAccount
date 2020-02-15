package android.cmpe277.pokeaccount.database;

import android.cmpe277.pokeaccount.Transaction;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class TransactionsCursorWrapper extends CursorWrapper {
    public TransactionsCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Transaction getTransaction(){
        String ACCOUNTID=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.ACCOUNTID));
        String INCOME_EXPENSE=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.INCOME_EXPENSE));
        String Date=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Date));
        String Type=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Type));
        String Value=getString(getColumnIndex(TransactionDbSchema.Transactions.Cols.Value));

        Transaction transaction=new Transaction();

        transaction.setACCOUNTID(UUID.fromString(ACCOUNTID));
        transaction.setINCOME_EXPENSE(INCOME_EXPENSE);
        transaction.setVALUE(Value);
        transaction.setTYPE(Type);

        transaction.setDATE(Date);

        return transaction;
    }
}
