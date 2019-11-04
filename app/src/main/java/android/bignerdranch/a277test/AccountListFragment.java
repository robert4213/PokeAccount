package android.bignerdranch.a277test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountListFragment extends Fragment {
    private RecyclerView mAccountRecyclerView;
    private AccountAdapter adapter;
    private TextView totalText;
    private Button addAccount;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_account_list,container,false);

        mAccountRecyclerView = view.findViewById(R.id.account_recycler_view);
        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        totalText = view.findViewById(R.id.total_text_account_list);
        addAccount = view.findViewById(R.id.account_adding_button);

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                AccountLab.get(getActivity()).addAccount(account);
                Intent intent = AccountActivity.newIntent(getActivity(),account.getid());
                startActivity(intent);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        AccountLab accountLab = AccountLab.get(getActivity());
        List<Account> accs = accountLab.getAccounts();

        double total = 0.0;

        for(int i = 0; i < accs.size(); i++){
            total += accs.get(i).getBalance();
        }

        totalText.setText(String.format("$%.2f", total));

        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(adapter == null) {
            adapter = new AccountAdapter(accs);
            mAccountRecyclerView.setAdapter(adapter);
        }else{
            adapter.setCrimes(accs);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_account_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_account:
                Account account = new Account();
                AccountLab.get(getActivity()).addAccount(account);
                Intent intent = AccountActivity.newIntent(getActivity(),account.getid());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AccountHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView mBalanceTextView;
        public TextView mTypeTextView;
        public ImageView mCompanyImageView;

        private Account acc;

        public AccountHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mCompanyImageView = itemView.findViewById(R.id.list_item_account_company);
            mBalanceTextView = itemView.findViewById(R.id.list_item_account_balance);
            mTypeTextView = itemView.findViewById(R.id.list_item_account_type);
        }

        public void bindAccount(Account account){
            this.acc = account;
            try {
                mCompanyImageView.setImageResource(Integer.parseInt(acc.getCompany()));
                mTypeTextView.setText(acc.getType());
                mBalanceTextView.setText(String.format("$%.2f", acc.getBalance()));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void onClick(View V){
            Intent intent = AccountActivity.newIntent(getActivity(),acc.getid());
            startActivity(intent);
        }
    }

    private class AccountAdapter extends RecyclerView.Adapter<AccountHolder>{
        private List<Account> accs;

        public  AccountAdapter(List<Account> accs){
            this.accs = accs;
        }

        @Override
        public AccountHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_account,parent,false);
            return new AccountHolder(view);
        }

        @Override
        public void onBindViewHolder(AccountHolder holder, int position){
            Account acc = accs.get(position);
            holder.bindAccount(acc);

        }

        @Override
        public int getItemCount(){
            return accs.size();
        }

        public void setCrimes(List<Account> accounts){
            accs = accounts;
        }


    }
}