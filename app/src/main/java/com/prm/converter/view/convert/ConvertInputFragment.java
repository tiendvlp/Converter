package com.prm.converter.view.convert;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.prm.converter.R;
import com.prm.converter.dao.ConvertHistoryDao;
import com.prm.converter.database.AppDatabase;
import com.prm.converter.entity.ConvertHistory;

import java.util.UUID;

public class ConvertInputFragment extends Fragment implements MainScreenComponentProvider.ConvertInputScreen {
    public static ConvertInputFragment newInstance() {
        return new ConvertInputFragment();
    }

    private EditText edtInputValue;
    private Spinner spnFrom;
    private Spinner spnTo;
    private Button btnConvert;
    private String[] SUPPORTED_LIST = new String[3];
    private MainScreenComponentProvider componentProvider;
    private AppDatabase db;
    private ConvertHistoryDao historyDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SUPPORTED_LIST[0] = "Meter";
        SUPPORTED_LIST[1] = "Kilometer";
        SUPPORTED_LIST[2] = "Centimeter";
        componentProvider = (MainScreenComponentProvider) getActivity();
        db = Room.databaseBuilder(getContext(), AppDatabase.class, AppDatabase.DB_NAME)
            .allowMainThreadQueries().build();
        historyDao = db.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_input, container, false);
        edtInputValue = view.findViewById(R.id.edtInputValue);
        spnFrom = view.findViewById(R.id.spnFrom);
        spnTo = view.findViewById(R.id.spnTo);
        btnConvert = view.findViewById(R.id.btnConvert);
        SpinnerAdapter spnAdapter = new ArrayAdapter<>(getContext(), android.R
        .layout.simple_list_item_1, SUPPORTED_LIST);
        spnFrom.setAdapter(spnAdapter);
        spnTo.setAdapter(spnAdapter);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value = 0.0;
                try {
                    value = Double.parseDouble(edtInputValue.getText().toString());
                } catch (NumberFormatException e) {
                    edtInputValue.setText("");
                    Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_LONG).show();
                    value = 0.0;
                }

                Double result = convert(value);
                componentProvider.getConvertOutputScreen().showOutput(result);
            }
        });
    }

    private Double convert (Double value) {
        String from = spnFrom.getSelectedItem().toString();
        String to = spnTo.getSelectedItem().toString();
        double result = -1.0;
        if (from.equals(to)) {
            result = value;
        }


        if (from.equals(SUPPORTED_LIST[0])) { // From Meter
            if (to.equals(SUPPORTED_LIST[1])) { // to kilometer
                result = value / 1000;
            }
            if (to.equals(SUPPORTED_LIST[2])) { // to centimeter
                result = value * 1000;
            }
        }
        else if (from.equals(SUPPORTED_LIST[1])) { // from Kilometer
            if (to.equals(SUPPORTED_LIST[0])) { // to meter
                result = value * 1000;
            }
            if (to.equals(SUPPORTED_LIST[2])) { // to centimeter
                result = value * 1000 * 1000;
            }
        }
        else if (from.equals(SUPPORTED_LIST[2])) { // from centimeter
            if (to.equals(SUPPORTED_LIST[0])) { // to meter
                result = value / 1000;
            }
            if (to.equals(SUPPORTED_LIST[1])) { // to Kilometer
                result = (value / 1000) / 1000;
            }
        }

        saveHistory(from, to, value, result);
        return result;
    }

    private void saveHistory  (String from, String to, double value, double result) {
        String id = UUID.randomUUID().toString().substring(8);
        ConvertHistory newHistory = new ConvertHistory(id, System.currentTimeMillis(), value, from, to, result);
        historyDao.insert(newHistory);
    }
}




















