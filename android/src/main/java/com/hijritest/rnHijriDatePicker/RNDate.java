package com.hijritest.rnHijriDatePicker;

import android.os.Bundle;

//import com.alrajhiretailapp.rnHijriDatePicker.calendar.UmmalquraCalendar;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import java.util.Calendar;

public class RNDate {

    private UmmalquraCalendar now;

    private long value;
    private long min;
    private long max;
    private String language;


    public RNDate(Bundle args) {

        if (args != null && args.containsKey(RNConstants.ARG_VALUE)) {
            value = args.getLong(RNConstants.ARG_VALUE) ;
        }

        if (args != null && args.containsKey(RNConstants.ARG_MINDATE)) {
            min = args.getLong(RNConstants.ARG_MINDATE) ;
        }

        if (args != null && args.containsKey(RNConstants.ARG_MAXDATE)) {
            max = args.getLong(RNConstants.ARG_MAXDATE) ;
        }
        if (args != null && args.containsKey(RNConstants.LANGUAGE)) {
            language = args.getString(RNConstants.LANGUAGE) ;
        }
    }
    public String getLanguage() {
        return language;
    }

    public void set(long value) {
        now = new UmmalquraCalendar();
        now.setTimeInMillis(value);
    }
    public UmmalquraCalendar getNow() {
        return now;
    }

    public int year() { return now.get(Calendar.YEAR); }
    public int month() { return now.get(Calendar.MONTH); }
    public int day() { return now.get(Calendar.DAY_OF_MONTH); }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }
}

