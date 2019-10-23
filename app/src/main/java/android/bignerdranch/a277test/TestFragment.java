package android.bignerdranch.a277test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class TestFragment extends Fragment {

    private TextView tv;

    public static TestFragment newInstance(String name) {

        Bundle args = new Bundle();
        args.putString("name", name);
        TestFragment fragment = new TestFragment();// 这个是例子，我们可以每个fragment建一个类。
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        tv=view.findViewById(R.id.fragment_test_tv);
        if (bundle != null) {
            String name = bundle.get("name").toString();
            tv.setText(name);
        }

    }

}