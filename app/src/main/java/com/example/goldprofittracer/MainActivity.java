package com.example.goldprofittracer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText reservedateedittext,withdrawdateedittext,amountedittext,rateedittext;
    Button calculate;
    TextView finaltextview,alltextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reservedateedittext = findViewById(R.id.editTextReservationDate);
        withdrawdateedittext = findViewById(R.id.editTextWithdrawalDate);
        amountedittext = findViewById(R.id.editTextAmount);
        rateedittext = findViewById(R.id.editTextRate);

        calculate = findViewById(R.id.calculatebuttonid);
        finaltextview = findViewById(R.id.finaltextid);
        alltextview = findViewById(R.id.allid);


        calculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String reserve_date = reservedateedittext.getText().toString().trim();
                String withdraw_date = withdrawdateedittext.getText().toString().trim();
                String amount = amountedittext.getText().toString().trim();
                String rate = rateedittext.getText().toString().trim();

                int taka = Integer.parseInt(amount);
                float dor = Float.parseFloat(rate);

                String[] x = reserve_date.split("/");
                String[] y = withdraw_date.split("/");

                String r_year = x[0];
                String r_month = x[1];
                String r_day = x[2];

                String w_year = y[0];
                String w_month = y[1];
                String w_day = y[2];

                int r_y = Integer.parseInt(r_year);
                int r_m = Integer.parseInt(r_month);
                int r_d = Integer.parseInt(r_day);

                int w_y = Integer.parseInt(w_year);
                int w_m = Integer.parseInt(w_month);
                int w_d = Integer.parseInt(w_day);


                LocalDate d1 = LocalDate.of(r_y,r_m,r_d);
                LocalDate d2 = LocalDate.of(w_y,w_m,w_d);


                LocalDateTime dateTime1 = LocalDateTime.of(d1, LocalTime.MIDNIGHT);
                LocalDateTime dateTime2 = LocalDateTime.of(d2, LocalTime.MIDNIGHT);
                long days = ChronoUnit.DAYS.between(dateTime1, dateTime2);

                if(days<0){
                    days = days*-1;
                }


                System.out.println("Reservarion year : "+r_y);
                System.out.println("Reservarion month : "+r_m);
                System.out.println("Reservarion day : "+r_d);

                System.out.println("Withdrawn year : "+w_y);
                System.out.println("Withdrawn month : "+w_m);
                System.out.println("Withdrawn day : "+w_d);

                System.out.println("Total days : "+days);

                dor = dor/30;
                System.out.println("Dor : "+dor);
                float profit_in_a_day = (dor* taka) / 1000;
                float profit = profit_in_a_day*days;

                String showprofit = String.valueOf(profit);
                finaltextview.setText("Profit : "+showprofit);
                alltextview.setText("Total(Capital + Interest) : "+((float)taka+profit));

            }

        });
    }



    public void showReservationDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date to the EditText
                        reservedateedittext = findViewById(R.id.editTextReservationDate);
                        reservedateedittext.setText(year + "/" + (month + 1) + "/" + day);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void showWithdrawalDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Set the selected date to the EditText
                        withdrawdateedittext = findViewById(R.id.editTextWithdrawalDate);
                        withdrawdateedittext.setText(year + "/" + (month + 1) + "/" + day);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }


}