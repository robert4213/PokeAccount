package android.bignerdranch.a277test;

import android.app.AlertDialog;
import android.bignerdranch.a277test.database.AccountLab;
import android.bignerdranch.a277test.database.TransactionLab;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

public class AccountFragment extends Fragment {
    private Account acc;
    private EditText companyText,balanceText,typeText;
    private Button btnDelete,btnSave,paypal,boa,alipay,wallet;
    private Spinner spinner;
    private View v;
    private double originalValue;

    private static final String ACCOUNT_ID = "account_id";

    public static AccountFragment newInstance(UUID accountId){
        Bundle args = new Bundle();
        args.putSerializable(ACCOUNT_ID,accountId);

        AccountFragment accountFragment = new AccountFragment();
        accountFragment.setArguments(args);
        return accountFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID accountId = (UUID)getArguments().getSerializable(ACCOUNT_ID);
        acc = AccountLab.get(getActivity()).getAccount(accountId);
        originalValue = acc.getBalance();
    }

    @Override
    public void onPause(){
        if(acc.newAccount()) AccountLab.get(getActivity()).deleteAccount(acc);
//        else AccountLab.get(getActivity()).updateAccount(acc);
        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_account,container,false);

        paypal = setButton(v,R.id.paypal_button,R.drawable.paypal_logo,R.drawable.paypal_logo_selected);

        boa = setButton(v,R.id.boa_button,R.drawable.bank_of_america,R.drawable.bank_of_america_selected);

        wallet = setButton(v,R.id.wallet_button,R.drawable.wallet,R.drawable.wallet_selected);

        alipay = setButton(v,R.id.alipay_button,R.drawable.alipay_logo,R.drawable.alipay_logo_selected);


        spinner = v.findViewById(R.id.account_type);
        final ArrayAdapter<CharSequence> charAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.account_type,R.layout.spinner_item);
        charAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(charAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){
               if(position == 0) return;
               acc.setType(parent.getItemAtPosition(position).toString());
           }
           @Override
            public void onNothingSelected(AdapterView<?> parent){
                return;
           }
        });


        balanceText = v.findViewById(R.id.account_balance);
        try {
            balanceText.setText(Double.toString(acc.getBalance()));
        }catch (NullPointerException e){
            balanceText.setText("0.00");
        }
        balanceText.addTextChangedListener(new TextWatcher() {
            Double num;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    DecimalFormat decim = new DecimalFormat("0.00");
                    num = Double.parseDouble(decim.format(Double.parseDouble(charSequence.toString())));
                }catch (Exception e){
                    num = acc.getBalance();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                acc.setBalance(num);
            }
        });

        btnDelete = v.findViewById(R.id.cancel_delete_button);
        if(acc.newAccount()) btnDelete.setText("Cancel");
        else btnDelete.setText("Delete");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                if(acc.newAccount()) builder.setMessage("Do you want to leave?");
                else builder.setMessage("Do you want to delete this account?");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(btnDelete.getText().equals("Delete") && acc.getBalance()!= 0.0) {
                            Transaction transaction = new Transaction();
                            Date date = new Date();
                            transaction.setDATE(date.toString());
                            transaction.setACCOUNTID(acc.getid());
                            transaction.setTYPE("Account Modified");
                            transaction.setINCOME_EXPENSE("Expense");
                            transaction.setVALUE(String.valueOf(acc.getBalance()));
                            TransactionLab.getMtransaction(getContext()).addTransaction(transaction);
                        }
                        AccountLab.get(getActivity()).deleteAccount(acc);
                        returnPre();
                    }
                }).setNegativeButton("CANCEL",null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnSave =v.findViewById(R.id.save_button);
        btnSave.setText("Save");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!acc.newAccount()) {
                    AccountLab.get(getActivity()).updateAccount(acc);
                    Toast.makeText(getActivity(),"Succeed!",Toast.LENGTH_LONG).show();
                    double changeValue = acc.getBalance()- originalValue;
                    //Add Transaction
                    Transaction transaction=new Transaction();
                    Date date= new Date();
                    transaction.setDATE(date.toString());
                    transaction.setACCOUNTID(acc.getid());
                    transaction.setTYPE("Account Modified");
                    if(changeValue < 0){
                        transaction.setINCOME_EXPENSE("Expense");
                        transaction.setVALUE(String.valueOf(-1*changeValue));
                        TransactionLab.getMtransaction(getContext()).addTransaction(transaction);
                    }else if(changeValue > 0){
                        transaction.setINCOME_EXPENSE("Income");
                        transaction.setVALUE(String.valueOf(changeValue));
                        TransactionLab.getMtransaction(getContext()).addTransaction(transaction);
                    }
                    returnPre();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please fill all the message!").setPositiveButton("OK",null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        return v;
    }



    private void returnPre(){
        Intent intent = new Intent(getContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        onPause();
    }

    private Button setButton(View v , int btnId, final int logoId, final int pressed_id){
        final Button btn = v.findViewById(btnId);
        try {
            if (acc.getCompany().equals(Integer.toString(logoId))) {
                btn.setBackground(ContextCompat.getDrawable(getContext(), pressed_id));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acc.setCompany(Integer.toString(logoId));
                resetButtonImage();
                btn.setBackground(ContextCompat.getDrawable(getContext(), pressed_id));
            }
        });
        return btn;
    }
    private void resetButtonImage(){
        paypal.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.paypal_logo));
        boa.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bank_of_america));
        wallet.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.wallet));
        alipay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.alipay_logo));
    }
}
