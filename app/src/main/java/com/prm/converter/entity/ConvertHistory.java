package com.prm.converter.entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class ConvertHistory {
    @PrimaryKey
    @NonNull
    String id;
    @NonNull
    Long date;
    @NonNull
    Double value;
    @NonNull
    String fromMetric;
    @NonNull
    String toMetric;
    @NonNull
    Double result;

    public ConvertHistory(@NonNull String id, @NonNull Long date, @NonNull Double value, @NonNull String fromMetric, @NonNull String toMetric, @NonNull Double result) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.fromMetric = fromMetric;
        this.toMetric = toMetric;
        this.result = result;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public Long getDate() {
        return date;
    }

    public void setDate(@NonNull Long date) {
        this.date = date;
    }

    @NonNull
    public Double getValue() {
        return value;
    }

    public void setValue(@NonNull Double value) {
        this.value = value;
    }

    @NonNull
    public String getFromMetric() {
        return fromMetric;
    }

    public void setFromMetric(@NonNull String fromMetric) {
        this.fromMetric = fromMetric;
    }

    @NonNull
    public String getToMetric() {
        return toMetric;
    }

    public void setToMetric(@NonNull String toMetric) {
        this.toMetric = toMetric;
    }

    @NonNull
    public Double getResult() {
        return result;
    }

    public void setResult(@NonNull Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        Date date=new Date(getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm dd/mm/yyyy");

        return  "Date: " + formatter.format(date) + "\n" +
                "Convert: " + value + "\n" +
                "From '" + fromMetric + '\'' +
                " to '" + toMetric + '\'' + "\n" +
                "Result: " + result;
    }
}
