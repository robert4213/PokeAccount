package android.cmpe277.pokeaccount.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.cmpe277.pokeaccount.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


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
