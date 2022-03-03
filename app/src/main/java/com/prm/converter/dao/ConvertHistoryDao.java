package com.prm.converter.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.prm.converter.entity.ConvertHistory;
import java.util.List;

@Dao
public interface ConvertHistoryDao {
    @Insert
    void insert (ConvertHistory... history);

    @Query("SELECT * FROM ConvertHistory ORDER BY date DESC")
    List<ConvertHistory> getConvertHistories ();
}
