package ru.mirea.komanda.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Clic(View view) {
        Class dialogFragment = new Class ();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }
    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void Picker(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Выбери действие").setMessage("Самостоялка").setNegativeButton("TimePicker", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");

            }
        }).setNeutralButton("DatePicker", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogFragment datePricker = new DataPickerFragment();
                datePricker.show(getSupportFragmentManager(),"date picker");
            }
        }).setPositiveButton("ProgressBar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progress =new ProgressDialog(MainActivity.this);
                progress.setMax(100);
                progress.setTitle("Download");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();
            }
        });
        AlertDialog alert = b.create();
        alert.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String text=(hourOfDay + ":" + minute);
        Intent timeIntent = new Intent(this,Time.class);
        timeIntent.putExtra("Time",text);
        startActivity(timeIntent);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Intent timeIntent = new Intent(this,Date.class);
        timeIntent.putExtra("Date",currentDateString);
        startActivity(timeIntent);
    }
}
