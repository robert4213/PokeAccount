package android.bignerdranch.a277test;

import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
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
        data.add(new ValueDataEntry("Apples", 6371664));
        data.add(new ValueDataEntry("Pears", 789622));

        pie.data(data);

        pie.labels().position("outside");

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        return pie;
    }
}
