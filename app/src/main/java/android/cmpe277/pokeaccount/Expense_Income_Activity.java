package android.cmpe277.pokeaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Expense_Income_Activity extends AppCompatActivity implements OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager myvirwpager;
    private Button one, two;
    private ImageView cursor;
    private float cursorX = 0;
    private int[] WidrhArgs;
    private Button[] ButtonArgs;
    private ArrayList<Fragment> list;
    private ViewPagerAdapter adapter;
    FragmentManager fm=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_income);
        Init();
    }

    private void Init() {
        myvirwpager = (ViewPager) findViewById(R.id.myviewpager);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);

        ButtonArgs = new Button[] { one, two };
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        onPageSelected(1);

        list = new ArrayList<Fragment>();
        list.add(new Expense_Fragment());
        list.add(new Income_Fragment());

        adapter = new ViewPagerAdapter(fm);
        adapter.setList(list);
        myvirwpager.setAdapter(adapter);
        myvirwpager.setOnPageChangeListener(this);
        resetButtonColor();
        one.setTextColor(Color.RED);


        ViewPagerScroller scroller = new ViewPagerScroller(this);// 设置动画转场时间
        scroller.setScrollDuration(1500);
        scroller.initViewPagerScroll(myvirwpager);
    }

    public void resetButtonColor() {
        one.setBackgroundColor(Color.parseColor("#DCDCDC"));
        two.setBackgroundColor(Color.parseColor("#DCDCDC"));
        one.setTextColor(Color.BLACK);
        two.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                myvirwpager.setCurrentItem(0);
                break;
            case R.id.two:
                myvirwpager.setCurrentItem(1);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        resetButtonColor();
        ButtonArgs[arg0].setTextColor(Color.RED);
    }
}