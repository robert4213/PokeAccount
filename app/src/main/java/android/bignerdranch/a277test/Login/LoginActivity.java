package android.bignerdranch.a277test.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.bignerdranch.a277test.R;
import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_login);

        if(fragment == null){
            fragment = new Login_Basic_Fragment();
            fm.beginTransaction().add(R.id.fragment_container_login,fragment).commit();
        }
    }
}
