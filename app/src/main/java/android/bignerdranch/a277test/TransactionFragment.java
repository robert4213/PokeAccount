package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.TransactionLab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vivian.timelineitemdecoration.itemdecoration.DotItemDecoration;
import com.vivian.timelineitemdecoration.itemdecoration.SpanIndexListener;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    private Button plus;
    private TransactionLab lab;
    private ArrayList<Transaction> transactions;

    public static TransactionFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        TransactionFragment fragment = new TransactionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DotItemDecoration mItemDecoration = new DotItemDecoration
                .Builder(getContext())
                .setOrientation(DotItemDecoration.VERTICAL)//如果LayoutManager设置了横向，那么这里也要设置成横向
                .setItemStyle(DotItemDecoration.STYLE_DRAW)//选择dot使用图片资源或者用canvas画
                .setTopDistance(20)//单位dp
                .setItemInterVal(10)//单位dp
                .setItemPaddingLeft(10)//如果不设置，默认和item间距一样
                .setItemPaddingRight(10)//如果不设置，默认和item间距一样
                .setDotColor(Color.WHITE)
                .setDotRadius(2)//单位dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(false)//设置dot居中
                .setLineColor(Color.RED)//设置线的颜色
                .setLineWidth(1)//单位dp
                .setEndText("END")//设置结束的文字
                .setTextColor(Color.WHITE)
                .setTextSize(10)//单位sp
                .setDotPaddingText(2)//单位dp.设置最后一个点和文字之间的距离
                .setBottomDistance(40)//设置底部距离，可以延长底部线的长度
                .create();

        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plus=view.findViewById(R.id.plus);
        //Dao

        //拿数据库 Transaction记录
      lab=TransactionLab.getMtransaction(getContext());
      transactions=lab.getmTransactions();
        Log.d("TransactionFragment",String.valueOf(transactions.size()));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Expense_Income_Activity.class);
                startActivity(intent);
            }
        });

    }
}
