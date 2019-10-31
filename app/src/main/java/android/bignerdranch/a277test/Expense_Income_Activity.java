package android.bignerdranch.a277test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

//注意MainActivity是继承自FragmentActivity类
public class Expense_Income_Activity extends AppCompatActivity implements OnClickListener,
        ViewPager.OnPageChangeListener {

    // 布局文件中自己的myvirwpager
    private ViewPager myvirwpager;
    // 选项卡中的三个Button
    private Button one, two;
    // 指示标签的ImageView
    private ImageView cursor;
    // 指示标签的横坐标
    private float cursorX = 0;
    // 定义获取所有按钮的宽度数组
    private int[] WidrhArgs;
    // 定义所有标题按钮的数组
    private Button[] ButtonArgs;
    // fragment的集合
    private ArrayList<Fragment> list;
    // viewpage适配器
    private ViewPagerAdapter adapter;
    FragmentManager fm=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_income);
        Init();
    }

    private void Init() {
        // 获取控件
        myvirwpager = (ViewPager) findViewById(R.id.myviewpager);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);

        // 初始化按钮数组
        ButtonArgs = new Button[] { one, two };
        // 设置指示标签颜色为红色
        cursor = (ImageView) findViewById(R.id.cursor);
        cursor.setBackgroundColor(Color.RED);
        // 按钮单机事件
        one.setOnClickListener(this);
        two.setOnClickListener(this);


        // 将fragment放进集合，并初始化适配器
        list = new ArrayList<Fragment>();
        list.add(new Expense_Fragment());
        list.add(new Income_Fragment());

        adapter = new ViewPagerAdapter(fm);
        adapter.setList(list);
        myvirwpager.setAdapter(adapter);
        // viewpage监听事件，重写onPageSelected()方法，实现左右滑动页面
        myvirwpager.setOnPageChangeListener(this);
        // 初始按钮颜色
        resetButtonColor();
        // 默认第一页
        one.setTextColor(Color.RED);
    }

    // 设置按钮颜色
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
                cursorAnim(0);
                break;
            case R.id.two:
                myvirwpager.setCurrentItem(1);
                cursorAnim(1);
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

        if (WidrhArgs == null) {
            WidrhArgs = new int[] { one.getWidth(), two.getWidth(),
                    };
        }

        // 根据每次选中的按钮，重置颜色
        resetButtonColor();
        // 将滑动到当前的标签下，改动标签颜色
        ButtonArgs[arg0].setTextColor(Color.RED);
        cursorAnim(arg0);

    }

    // 指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        // 每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        // 再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor
                .getLayoutParams();
        // 减去边距*2，以对齐标题栏文字
        lp.width = WidrhArgs[curItem] - ButtonArgs[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        // 循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + ButtonArgs[i].getWidth();
        }
        // 再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX + ButtonArgs[curItem].getPaddingLeft());
    }
}