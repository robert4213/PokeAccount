package android.bignerdranch.a277test;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;


public class AccountActivity extends SingleFragmentActivity {
    private static final String EXTRA_ACCOUNT_ID = "com.shiyan.android.accountintent.account_id";

    @Override
    protected Fragment createFragment(){
        UUID accountId = (UUID) getIntent().getSerializableExtra(EXTRA_ACCOUNT_ID);
        return AccountFragment.newInstance(accountId);
    }

    public static Intent newIntent(Context packageContext, UUID account_id){
        Intent intent = new Intent(packageContext,AccountActivity.class);
        intent.putExtra(EXTRA_ACCOUNT_ID,account_id);
        return intent;
    }

}
