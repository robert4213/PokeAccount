package android.bignerdranch.a277test;

import android.bignerdranch.a277test.database.TransactionLab;
import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class IncomeChartFragment extends SingleChartFragment {
    private AnyChartView anyChartView;
    @Override
    protected void setChart(View view) {
        anyChartView = view.findViewById(R.id.chart_container);
        anyChartView.setProgressBar(view.findViewById(R.id.chart_progress_bar));

        anyChartView.setChart(createIncomePie());
    }

    private Pie createIncomePie(){
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        ArrayList<Transaction> transactions = TransactionLab.getMtransaction(getContext()).getmTransactions();
        for( Transaction transaction : transactions){
            if(transaction.getINCOME_EXPENSE().equalsIgnoreCase("Income")) {
                if (hashMap.get(transaction.getTYPE()) == null){
                    hashMap.put(transaction.getTYPE(),transaction.getVALUE());
                }else{
                    hashMap.put(transaction.getTYPE(),Double.toString(Double.parseDouble(transaction.getVALUE())+Double.parseDouble(hashMap.get(transaction.getTYPE()))));
                }
            }
        }
        Iterator iterator = hashMap.keySet().iterator();
        if(hashMap.isEmpty()){
            setNullBackground();
        }
        while (iterator.hasNext()){
            String key = (String)iterator.next();
            data.add(new ValueDataEntry(key, Double.parseDouble(hashMap.get(key))));
        }

        pie.data(data);

        pie.labels().position("outside");

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        return pie;
    }
}
