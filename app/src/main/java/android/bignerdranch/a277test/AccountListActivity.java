package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.AccountLab;
import android.content.Context;

import androidx.fragment.app.Fragment;

public class AccountListActivity extends SingleFragmentActivity {
    Context mContext;

//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        mContext =
//    }
    @Override
    protected Fragment createFragment(){
        if(AccountLab.get(this).getAccounts().size() == 0) {
            System.out.println("No data");
            return new NoContentFragment();
        }
        return new AccountListFragment();
    }
}
