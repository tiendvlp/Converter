package com.prm.converter.view.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prm.converter.R;
import com.prm.converter.dao.ConvertHistoryDao;
import com.prm.converter.database.AppDatabase;

public class ConvertHistoryActivity extends AppCompatActivity {
    private AppDatabase db;
    private ConvertHistoryDao historyDao;
    private ListView lvHistories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_history);
        addControls();
        bindData();
    }

    private void bindData() {
        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
        .allowMainThreadQueries().build();
        historyDao = db.create();
        lvHistories.setAdapter(new ArrayAdapter<>(this, android.R
                .layout.simple_list_item_1, historyDao.getConvertHistories()));
    }

    private void addControls() {
        lvHistories = findViewById(R.id.lvHistories);
    }
}