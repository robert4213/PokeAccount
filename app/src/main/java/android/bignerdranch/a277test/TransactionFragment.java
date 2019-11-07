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
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vivian.timelineitemdecoration.itemdecoration.DotItemDecoration;
import com.vivian.timelineitemdecoration.itemdecoration.SpanIndexListener;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    private Button plus;
    private TransactionLab lab;
    private ArrayList<Transaction> transactions;

    RecyclerView mRecyclerView;
    List<Event> mList = new ArrayList<>();
    DotTimeLineAdapter mAdapter;
    DotItemDecoration mItemDecoration;

//    long[] times = {
//            1497229200,
//            1497240000,
//            1497243600,
//            1497247200,
//            1497249000,
//            1497252600
//    };
//    String[] events = new String[]{
//            "去小北门拿快递",
//            "跟同事一起聚餐",
//            "写文档",
//            "和产品开会",
//            "整理开会内容",
//            "提交代码到git上"
//    };

    public static TransactionFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        TransactionFragment fragment = new TransactionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plus=view.findViewById(R.id.plus);


        lab=TransactionLab.getMtransaction(getContext());
        transactions=lab.getmTransactions();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mItemDecoration = new DotItemDecoration
                .Builder(getContext())
                .setOrientation(DotItemDecoration.VERTICAL)//if you want a horizontal item decoration,remember to set horizontal orientation to your LayoutManager
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(20)//dp
                .setItemInterVal(10)//dp
                .setItemPaddingLeft(20)//default value equals to item interval value
                .setItemPaddingRight(20)//default value equals to item interval value
                .setDotColor(Color.WHITE)
                .setDotRadius(2)//dp
                .setDotPaddingTop(0)
                .setDotInItemOrientationCenter(false)//set true if you want the dot align center
                .setLineColor(Color.RED)
                .setLineWidth(1)//dp
                .setEndText("END")
                .setTextColor(Color.WHITE)
                .setTextSize(10)//sp
                .setDotPaddingText(2)//dp.The distance between the last dot and the end text
                .setBottomDistance(40)//you can add a distance to make bottom line longer
                .create();
        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                Log.i("Info","view:"+view+"  span:"+spanIndex);
                //   view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
        });
        mRecyclerView.addItemDecoration(mItemDecoration);

        for (int i = 0; i < transactions.size(); i++) {
            Event event = new Event();

            event.setTime((transactions.get(i).getDATE()) );
            event.setEvent(transactions.get(i).getTYPE()+"  "+transactions.get(i).getVALUE());
            mList.add(event);
        }

        mAdapter = new DotTimeLineAdapter(getContext(), mList);
        mRecyclerView.setAdapter(mAdapter);
        //Dao

        //拿数据库 Transaction记录

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
