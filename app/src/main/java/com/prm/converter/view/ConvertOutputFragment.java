package com.prm.converter.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prm.converter.R;
import com.prm.converter.entity.ConvertHistory;

public class ConvertOutputFragment extends Fragment implements MainScreenComponentProvider.ConvertOutputScreen {
    private TextView txtResult;
    private Button btnShowHistory;

    public static ConvertOutputFragment newInstance() {
        return new ConvertOutputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_output, container, false);
        txtResult = view.findViewById(R.id.txtValue);
        btnShowHistory = view.findViewById(R.id.btnShowHistory);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnShowHistory.setOnClickListener(v -> startActivity(new Intent(getActivity(), ConvertHistoryActivity.class)));
    }

    @Override
    public void showOutput(double value) {
        txtResult.setText(value + "");
    }
}