package com.example.myapplication;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePicker extends DialogFragment {

    static int flag=0;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        int hours=calendar.get(Calendar.HOUR_OF_DAY);
        int min=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        TimeChange timeChange=new TimeChange(getActivity());
        timePickerDialog=new TimePickerDialog(getActivity(),timeChange,hours,min,
                android.text.format.DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
        return timePickerDialog;
    }
}
