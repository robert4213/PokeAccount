package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.TransactionLab;
import android.util.Log;
import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.MarkerType;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class BalanceChartFragment extends SingleChartFragment {
    private AnyChartView anyChartView;
    @Override
    protected void setChart(View view) {
        anyChartView = view.findViewById(R.id.chart_container);
        anyChartView.setProgressBar(view.findViewById(R.id.chart_progress_bar));

        anyChartView.setChart(createBalance());
    }

    private Cartesian createBalance(){
        Cartesian cartesian = AnyChart.line();
        List<DataEntry> seriesData = new ArrayList<>();
        Double[] income = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] expense = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        ArrayList<Transaction> transactions = TransactionLab.getMtransaction(getContext()).getmTransactions();

        for( Transaction transaction : transactions){

            try {
                DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
                Date date = format.parse(transaction.getDATE());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                Log.i("SQLData","Month: "+cal.get(Calendar.MONTH)+"");
                if (transaction.getINCOME_EXPENSE().equalsIgnoreCase("Income")) {
                    income[cal.get(Calendar.MONTH)] += Double.parseDouble(transaction.getVALUE());
                } else {
                    expense[cal.get(Calendar.MONTH)] += Double.parseDouble(transaction.getVALUE());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(int i = 0; i<income.length;i++){
            Log.i("SQLData","Month Value: "+i+": "+expense[i]+" "+ income[i]);
            seriesData.add(new CustomDataEntry(Integer.toString(i+1), expense[i], income[i]));
        }

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Expense");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(0d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Income");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(0d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        return cartesian;
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }

    }
}
