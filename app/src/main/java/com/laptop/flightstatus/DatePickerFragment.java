package com.laptop.flightstatus;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);



        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dateDialog.getDatePicker().setMinDate(new Date().getTime() - (2 * 1000 * 60*60*24));
        dateDialog.getDatePicker().setMaxDate(new Date().getTime() + (1000 * 60*60*24));

        return dateDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        String smonth = null;
        String sday = null;

        if(month < 10){

            smonth = "0" + (month + 1);
        }
        else {smonth = new Integer(month).toString();}
        if(day < 10){
            sday  = "0" + day ;
        }
        else {sday = new Integer(day).toString();}

        Button myView = (Button)  this.getActivity().findViewById(R.id.dateButton);
        String newDate = new String(year + "-" + smonth + "-" + sday);
        myView.setText(newDate);
        //pass date to fragments activity src. http://stackoverflow.com/questions/9343241/passing-data-between-a-fragment-and-its-container-activity
        ((ScrollingActivity) getActivity()).setDate(newDate);



    }
}
