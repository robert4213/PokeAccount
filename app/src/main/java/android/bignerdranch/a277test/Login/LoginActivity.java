package android.bignerdranch.a277test.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.bignerdranch.a277test.MainActivity;
import android.bignerdranch.a277test.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager fm;
    private String signature;
    public static final String TAG = "KeyStoreFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences( SecurityConstants.SHARED_PERFERENCE, Context.MODE_PRIVATE);
        signature = sharedPreferences.getString(SecurityConstants.SIGNATURE, "");
        Log.w(TAG, "Signature from memory: "+signature);

        fm = getSupportFragmentManager();

        fragment = fm.findFragmentById(R.id.fragment_container_login);

        if(fragment == null){
            if(signature != "") {
                fragment = new EnterFragment();
            }else{
                fragment = new NewPasscodeFragment();
//                TextView textView = findViewById(R.id.text_login);
//                textView.setText("Please set your passcode");
            }
            fm.beginTransaction().add(R.id.fragment_container_login,fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        fragment = fm.getFragments().get(0);
        if(fragment != null && !(fragment instanceof EnterFragment)){
            fm.beginTransaction().replace(R.id.fragment_container_login,new EnterFragment()).commit();
        }else {
            super.onBackPressed();
        }
    }

}
