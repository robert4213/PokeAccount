package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.AccountLab;
import android.bignerdranch.a277test.database.TransactionLab;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Income_Fragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private MenuItem menuItem;
    TextView textView,type;
    private Button clear, delete, percentage, divide, add, subtract, multiply, equal, minusValue;
    private  Button one, two, three, four, five, six, seven, eight, nine, point, zero ;
    private ImageButton salary,refund,rewards,red_packet,cash,others;
    private Date date;
    private String ans="";
    private ImageView image;
    private Spinner spinner;
    private UUID accountId;

    ArrayList<Integer> a = new ArrayList<Integer>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView)view.findViewById(R.id.textView1);
        type=(TextView)view.findViewById(R.id.type);
        salary=(ImageButton)view.findViewById(R.id.salary);
        refund=(ImageButton)view.findViewById(R.id.refund);
        rewards=(ImageButton)view.findViewById(R.id.rewards);
        red_packet=(ImageButton)view.findViewById(R.id.red_packet);
        cash=(ImageButton)view.findViewById(R.id.cash);
        others=(ImageButton)view.findViewById(R.id.others);

        image=(ImageView)view.findViewById(R.id.imageicon);

        //textView.setMovementMethod(new ScrollingMovementMethod());
        //textView.setSelected(true);
        clear = (Button)view.findViewById(R.id.buttonClearText);
        delete = (Button)view.findViewById(R.id.buttonDelete);
        percentage = (Button)view.findViewById(R.id.buttonPercentage);
        divide = (Button)view.findViewById(R.id.buttonDivide);
        add = (Button)view.findViewById(R.id.buttonAdd);
        subtract = (Button)view.findViewById(R.id.buttonSubtraction);
        multiply = (Button)view.findViewById(R.id.buttonMultiply);
        equal = (Button)view.findViewById(R.id.buttonEqual);
        minusValue = (Button)view.findViewById(R.id.buttonMinusValue);

        one = (Button)view.findViewById(R.id.button1);
        two = (Button)view.findViewById(R.id.button2);
        three = (Button)view.findViewById(R.id.button3);
        four = (Button)view.findViewById(R.id.button4);
        five = (Button)view.findViewById(R.id.button5);
        six = (Button)view.findViewById(R.id.button6);
        seven = (Button)view.findViewById(R.id.button7);
        eight = (Button)view.findViewById(R.id.button8);
        nine = (Button)view.findViewById(R.id.button9);
        point = (Button)view.findViewById(R.id.buttonPoint);
        zero = (Button)view.findViewById(R.id.buttonZero);


        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("Salary");
                image.setImageResource(R.drawable.salary);
            }
        });
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("Refund");
                image.setImageResource(R.drawable.refund);
            }
        });


        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("Rewards");
                image.setImageResource(R.drawable.rewards);
            }
        });

        red_packet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("Red_packet");
                image.setImageResource(R.drawable.red_packet);
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("Cash");
                image.setImageResource(R.drawable.cash);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.setText("others");
                image.setImageResource(R.drawable.others);
            }
        });



        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="1";
                textView.setText(textView.getText() + "1");


            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="2";

                textView.setText(textView.getText() + "2");

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="3";

                textView.setText(textView.getText() + "3");

            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="4";

                textView.setText(textView.getText() + "4");

            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="5";

                textView.setText(textView.getText() + "5");

            }
        });


        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="6";

                textView.setText(textView.getText() + "6");

            }
        });


        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="7";

                textView.setText(textView.getText() + "7");

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="8";

                textView.setText(textView.getText() + "8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="9";

                textView.setText(textView.getText() + "9");

            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.')
                    {
                        ans+=".";
                        textView.setText(textView.getText() + ".");
                    }

                }


            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans+="0";

                textView.setText(textView.getText() + "0");

            }
        });


        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ans="";
                textView.setText(null);
                a.clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    //ans=ans.substring(0,ans.length());
                    String s1="";
                    for(int i=0;i<s.length()-1;i++)
                    {
                        s1+=s.charAt(i);
                    }
                    //ans=ans.substring(0,s.length()-1);
                    ans=s1;
                    textView.setText(s1);
                }

            }
        });

        minusValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = textView.getText().toString();
                double res = Double.parseDouble(s + "");
                res*=-1;
                ans=String.valueOf(res);
                textView.setText(res + "");
            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.')
                    {
                        a.add(s.length()-1);
                        ans+="%";
                        textView.setText(textView.getText() + "%");
                    }


                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.') {
                        a.add(s.length()-1);
                        ans+="/";
                        textView.setText(textView.getText() + "/");
                    }
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.') {
                        a.add(s.length()-1);
                        ans+="+";
                        textView.setText(textView.getText() + "+");
                    }
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.') {
                        a.add(s.length()-1);
                        ans+="-";
                        textView.setText(textView.getText() + "-");
                    }
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = textView.getText().toString();
                    char ch=s.charAt(s.length()-1);
                    if(ch!='+' && ch!='-' && ch!='%' && ch!='*' && ch!='/' && ch!='.') {
                        a.add(s.length()-1);
                        ans+="*";
                        textView.setText(textView.getText() + "*");
                    }


                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().isEmpty())
                {
                    String s = ans;
                    char ch = s.charAt(s.length() - 1);
                    if (ch == '+' || ch == '-' || ch == '%' || ch == '*' || ch == '/' || ch=='.')
                    {
                        Toast.makeText(getContext(), "Invalid", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        a.add(s.length()-1);
                        s+= "=";
                        //String info="";

                        //boolean div=false,mult=false,sub=false,perc=false,add1=false;
                        double res=Double.parseDouble(s.substring(0,a.get(0)+1));

                        for(int i=0;i<a.size()-1;i++)
                        {
                            double answ = Double.parseDouble(s.substring(a.get(i)+2,a.get(i+1)+1));
                            if(s.charAt(a.get(i)+1)=='+')
                            {
                                res+=answ;
                            }
                            else if(s.charAt(a.get(i)+1)=='-')
                            {
                                res-=answ;
                            }
                            else if(s.charAt(a.get(i)+1)=='/')
                            {
                                res/=answ;
                            }
                            else if(s.charAt(a.get(i)+1)=='*')
                            {
                                res*=answ;
                            }
                            else if(s.charAt(a.get(i)+1)=='%')
                            {
                                res=(res)*(answ)/10000;
                            }
                        }
                        try {
//                            Account account = AccountLab.get(getContext()).getAccounts().get(0);

                            Transaction transaction = new Transaction();
                            date = new Date();
                            transaction.setDATE(date.toString());
                            transaction.setACCOUNTID(accountId);
                            transaction.setINCOME_EXPENSE("Income");
                            transaction.setTYPE(String.valueOf(type.getText()));
                            transaction.setVALUE(String.valueOf(res));
                            TransactionLab.getMtransaction(getContext()).addTransaction(transaction);
                            String answer = String.valueOf(res);
                            a.clear();
                            textView.setText(answer );
                            //a.add(answer.length()-1);
                            ans=answer;
                            Account account = AccountLab.get(getContext()).getAccount(accountId);
                            account.setBalance(account.getBalance()+ res);
                            AccountLab.get(getActivity()).updateAccount(account);
                        }catch (Exception e){
                            Toast.makeText(getContext(),"No Account Created", Toast.LENGTH_LONG).show();
                        }
                       //



                        //int check=0; //first time
                        //String answ= String.valueOf(a.size());




                    }


                }
            }
        });

        final List<Account> accounts = AccountLab.get(getContext()).getAccounts();
        spinner = view.findViewById(R.id.income_spinner);
        ArrayList<String> strs = new ArrayList<>();
        for(Account account: accounts){
            strs.add(account.getType()+": $"+account.getBalance());
        }
        String[] strA = strs.toArray(new String[strs.size()]);
        Log.i("Spinner",strA.toString());
        ArrayAdapter<String> charAdapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.simple_spinner_dropdown_item,strA);

        spinner.setAdapter(charAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){
                if(position == 0) return;
                accountId = accounts.get(position).getid();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                return;
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_income,container,false);

        return view;
    }

}
