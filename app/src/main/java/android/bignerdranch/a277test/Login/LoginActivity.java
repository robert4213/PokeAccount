package android.bignerdranch.a277test.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.bignerdranch.a277test.R;
import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container_login);

        if(fragment == null){
            fragment = new EnterFragment();
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
