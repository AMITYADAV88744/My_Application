package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BookingConfirmation extends AppCompatActivity {

    EditText email,vname,vehino,drivername,driverno,traveldate,traveltime,status;
    Button button;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.show();


        email = findViewById(R.id.custemail);
        vname = findViewById(R.id.vname);
        vehino = findViewById(R.id.vnumber);
        drivername = findViewById(R.id.drivername);
        driverno = findViewById(R.id.drivernumber);
        traveldate = findViewById(R.id.traveldate);
        traveltime = findViewById(R.id.traveltime);
        status = findViewById(R.id.status);
        button = findViewById(R.id.confirmsend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                checknullfields();
                if (flag == 0) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Booking Status:" + status.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, "Details:" +
                            "\nCar Name:" + vname.getText().toString() +
                            "\nCar Number:" + vehino.getText().toString() +
                            "\nDriver Name:" + drivername.getText().toString() +
                            "\nDriver Number:" + driverno.getText().toString() +
                            "\nTravel Date:" + traveldate.getText().toString() +
                            "\nTravel Time:" + traveltime.getText().toString()
                    );
                    intent.setType("message/rfc822");
                    Toast.makeText(BookingConfirmation.this, "Sending mail to customer..", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(BookingConfirmation.this, "Fill Form Completely", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checknullfields() {
        if (TextUtils.isEmpty(email.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(vehino.toString()))
            flag = 1;
        else if (TextUtils.isEmpty(vname.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(driverno.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(driverno.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(traveltime.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(traveldate.getText().toString()))
            flag = 1;
        else if (TextUtils.isEmpty(status.getText().toString()))
            flag = 1;
    }
}
