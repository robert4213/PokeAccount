package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.AccountBaseHelper;
import android.bignerdranch.a277test.database.AccountCursorWrapper;
import android.bignerdranch.a277test.database.AccountDbSchema.AccountTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountLab {
    private static AccountLab lab;
    private List<Account> accounts;
    private Context mContext;
    private SQLiteDatabase mDataBase;

    public static AccountLab get(Context context){
        if(lab == null){
            lab = new AccountLab(context);
        }
        return lab;
    }

    private AccountLab(Context context){
        accounts = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDataBase = new AccountBaseHelper(mContext).getWritableDatabase();

    }

    public void addAccount(Account account){
        ContentValues values = getContentValue(account);
        mDataBase.insert(AccountTable.NAME,null,values);
    }

    public void updateAccount(Account account){
        String id = account.getid().toString();
        ContentValues values = getContentValue(account);

        mDataBase.update(AccountTable.NAME,values,AccountTable.Cols.UUID + "=?",
                new String[]{id});
    }

    public void deleteAccount(Account account){
        String id = account.getid().toString();
        ContentValues values = getContentValue(account);
        mDataBase.delete(AccountTable.NAME, AccountTable.Cols.UUID + "=?", new String[]{id});
    }

    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        AccountCursorWrapper cursor = queryAccount(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                accounts.add(cursor.getAccount());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return accounts;
    }

    public Account getAccount(UUID id){
        List<Account> accounts = new ArrayList<>();
        AccountCursorWrapper cursor = queryAccount(AccountTable.Cols.UUID + "=?",new String[]{id.toString()});
        try{
            if(cursor.getCount() == 0) return null;
            cursor.moveToFirst();
            return  cursor.getAccount();
        }finally {
            cursor.close();
        }
    }

    public Boolean newAccount(UUID id){
        if(getAccount(id).getCompany() == null || getAccount(id).getType() == null) return true;
        else return false;
    }

    private static ContentValues getContentValue(Account account){
        ContentValues values = new ContentValues();
        values.put(AccountTable.Cols.UUID, account.getid().toString());
        values.put(AccountTable.Cols.Type,account.getType());
        values.put(AccountTable.Cols.BALANCE,account.getBalance());
        values.put(AccountTable.Cols.COMPANY,account.getCompany());
        return values;
    }

    private AccountCursorWrapper queryAccount(String whereClause, String[] whereArgs){
        Cursor cursor = mDataBase.query(
                AccountTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new AccountCursorWrapper(cursor);
    }



}
