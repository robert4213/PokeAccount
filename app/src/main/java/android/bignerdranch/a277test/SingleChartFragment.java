package android.bignerdranch.a277test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public abstract class SingleChartFragment extends Fragment {
    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_single_chart,container,false);

        setChart(view);

        return view;
    }

    protected abstract void setChart(View view);

    protected void setNullBackground(){
        view.findViewById(R.id.chart_null_background).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.no_contents_background));
    }
}
