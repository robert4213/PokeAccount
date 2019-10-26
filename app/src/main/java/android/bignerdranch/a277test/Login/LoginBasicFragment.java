package android.bignerdranch.a277test.Login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.bignerdranch.a277test.R;
import android.widget.Button;

import com.chaos.view.PinView;


public class LoginBasicFragment extends Fragment {
    PinView pinView;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnx;
    public static int LENGTH = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_basic, container, false);

        pinView = view.findViewById(R.id.PinView);

        btn0 = view.findViewById(R.id.btn_login_0);
        btn0.setOnClickListener(new NumListener(pinView,0));
        btn1 = view.findViewById(R.id.btn_login_1);
        btn1.setOnClickListener(new NumListener(pinView,1));
        btn2 = view.findViewById(R.id.btn_login_2);
        btn2.setOnClickListener(new NumListener(pinView,2));
        btn3 = view.findViewById(R.id.btn_login_3);
        btn3.setOnClickListener(new NumListener(pinView,3));
        btn4 = view.findViewById(R.id.btn_login_4);
        btn4.setOnClickListener(new NumListener(pinView,4));
        btn5 = view.findViewById(R.id.btn_login_5);
        btn5.setOnClickListener(new NumListener(pinView,5));
        btn6 = view.findViewById(R.id.btn_login_6);
        btn6.setOnClickListener(new NumListener(pinView,6));
        btn7 = view.findViewById(R.id.btn_login_7);
        btn7.setOnClickListener(new NumListener(pinView,7));
        btn8 = view.findViewById(R.id.btn_login_8);
        btn8.setOnClickListener(new NumListener(pinView,8));
        btn9 = view.findViewById(R.id.btn_login_9);
        btn9.setOnClickListener(new NumListener(pinView,9));
        btnx = view.findViewById(R.id.btn_login_x);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pinView.getText().length() > 0) {
                    pinView.setText(pinView.getText().delete(pinView.getText().length() - 1, pinView.getText().length()));
                }
            }
        });

        return view;
    }

    public PinView getPinView(){
        return pinView;
    }

    protected class NumListener implements View.OnClickListener{
        private PinView pinView;
        private int num;
        public NumListener(PinView pinView , int num){
            this.pinView = pinView;
            this.num = num;
        }
        @Override
        public void onClick(View view) {
            pinView.setText(pinView.getText().append(Integer.toString(num)));
        }
    }
}
