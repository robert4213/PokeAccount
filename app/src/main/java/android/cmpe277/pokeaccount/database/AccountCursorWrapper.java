package android.cmpe277.pokeaccount.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import android.cmpe277.pokeaccount.Account;
import android.cmpe277.pokeaccount.database.AccountDbSchema.AccountTable;

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
