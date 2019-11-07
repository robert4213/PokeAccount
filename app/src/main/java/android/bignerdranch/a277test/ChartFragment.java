package android.bignerdranch.a277test;

import android.bignerdranch.a277test.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class ChartFragment extends Fragment {
    private RadioRealButtonGroup rrbg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.chart_container,new IncomeChartFragment()).commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart,container,false);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.chart_container);

        if(fragment == null){
            Log.i("Chart","Fragment is null");
            fm.beginTransaction().add(R.id.chart_container,new IncomeChartFragment()).commit();
        }else{
            Log.i("Chart","Fragment is not null");

        }

        rrbg= view.findViewById(R.id.chart_rrbg);

        rrbg.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            @Override
            public void onClickedButton(RadioRealButton button, int position) {
                switch (position){
                    case 0:
                        fm.beginTransaction().replace(R.id.chart_container,new IncomeChartFragment()).commit();
                        break;
                    case 1:
                        fm.beginTransaction().replace(R.id.chart_container,new ExpenseChartFragment()).commit();
                        break;
                    case 2:
                        fm.beginTransaction().replace(R.id.chart_container,new BalanceChartFragment()).commit();
                        break;
                }
            }
        });

        return view;
    }




}
