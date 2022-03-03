package com.prm.converter.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.prm.converter.dao.ConvertHistoryDao;
import com.prm.converter.entity.ConvertHistory;

@Database(entities = {ConvertHistory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public final static String DB_NAME = "converter.db";
    public abstract ConvertHistoryDao create();
}
