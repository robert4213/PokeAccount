package android.cmpe277.pokeaccount.Login;

import android.cmpe277.pokeaccount.R;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewPasscodeFragment extends LoginBasicFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        final TextView loginText = view.findViewById(R.id.text_login);
        TextView loginTextBtn = view.findViewById(R.id.text_login_btn);

        loginText.setText("Please enter new passcode");
        loginTextBtn.setText("Cancel");

        loginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_login, new EnterFragment()).commit();
            }
        });

        getPinView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == LENGTH){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_login, new ReenterFragment(charSequence.toString())).commit();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

}
