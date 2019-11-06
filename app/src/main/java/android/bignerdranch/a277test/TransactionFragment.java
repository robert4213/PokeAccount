package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.TransactionLab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    private Button plus;
    private TransactionLab lab;
    private ArrayList<Transaction> transactions;

    public static TransactionFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        TransactionFragment fragment = new TransactionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plus=view.findViewById(R.id.plus);
        //Dao

        //拿数据库 Transaction记录
      lab=TransactionLab.getMtransaction(getContext());
      transactions=lab.getmTransactions();
        Log.d("TransactionFragment",String.valueOf(transactions.size()));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Expense_Income_Activity.class);
                startActivity(intent);
            }
        });

    }
}
