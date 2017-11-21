package com.example.android.networkconnect;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DataPicker extends AppCompatActivity implements View.OnClickListener{

    //TextView currentDateTime;
    Calendar dateFrom=Calendar.getInstance();
    Calendar dateTo=Calendar.getInstance();
    Button from;
    Button to;
    Button update;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //currentDateTime=(TextView)findViewById(R.id.currentdate);
        from = (Button) findViewById(R.id.dateButton);
        to = (Button) findViewById(R.id.dateButtonto);
        from.setOnClickListener(this);
        to.setOnClickListener(this);

        viewGroup = (ViewGroup) findViewById(R.id.informview);
        viewGroup.setVisibility(View.GONE);

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == from || view == to) setDate(view);
        else if (view == update) updateStatistic();
    }

    private void updateStatistic() {
        viewGroup.setVisibility(View.GONE);

        System.out.println("from " + from.getText().toString());
        System.out.println("to " + to.getText().toString());
    }


    public void setDate(View v) {
        if (v == from) {
            new DatePickerDialog(DataPicker.this, d,
                    dateFrom.get(Calendar.YEAR),
                    dateFrom.get(Calendar.MONTH),
                    dateFrom.get(Calendar.DAY_OF_MONTH))
                    .show();
        } if (v == to) {
            new DatePickerDialog(DataPicker.this, d1,
                    dateTo.get(Calendar.YEAR),
                    dateTo.get(Calendar.MONTH),
                    dateTo.get(Calendar.DAY_OF_MONTH))
                    .show();
        }
    }


    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //тут можем получить выбранный год-месяц-день из datePicker
            //System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);
            String s = year + "-" + monthOfYear + "-" + dayOfMonth;
            from.setText(s);
            dateFrom.set(Calendar.YEAR, year);
            dateFrom.set(Calendar.MONTH, monthOfYear);
            dateFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (viewGroup.getVisibility() == View.GONE) viewGroup.setVisibility(View.VISIBLE);

        }
    };


    DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //тут можем получить выбранный год-месяц-день из datePicker
            System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);
            String s = year + "-" + monthOfYear + "-" + dayOfMonth;
            to.setText(s);
            dateTo.set(Calendar.YEAR, year);
            dateTo.set(Calendar.MONTH, monthOfYear);
            dateTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (viewGroup.getVisibility() == View.GONE) viewGroup.setVisibility(View.VISIBLE);
        }
    };
}
