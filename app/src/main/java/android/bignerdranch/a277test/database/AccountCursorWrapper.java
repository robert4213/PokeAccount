package android.bignerdranch.a277test.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import android.bignerdranch.a277test.Account;
import android.bignerdranch.a277test.database.AccountDbSchema.AccountTable;

import java.util.UUID;

public class AccountCursorWrapper extends CursorWrapper {
    public AccountCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Account getAccount(){
        String uuidStr = getString(getColumnIndex(AccountTable.Cols.UUID));
        String type = getString(getColumnIndex(AccountTable.Cols.CASH));
        String company = getString(getColumnIndex(AccountTable.Cols.PAYPAL));
        Double balance = getDouble(getColumnIndex(AccountTable.Cols.BALANCE));

        Account account = new Account(UUID.fromString(uuidStr));
        account.setBalance(balance);
        account.setType(type);
        account.setCompany(company);
        return account;
    }

}
