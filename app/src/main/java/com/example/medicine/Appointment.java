package com.example.medicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    EditText patname,pid,pcontact,pdate,ptime;
    Button btnapadd,btnapdelete,btnapupdate,btnapview;
    Calendar calendar=Calendar.getInstance();
    DBhelperAppointment DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        patname = findViewById(R.id.txtappPname);
        pid = findViewById(R.id.txtappPid);
        pcontact =findViewById(R.id.txtappCnumber);
        pdate = findViewById(R.id.txtappdate);
        ptime = findViewById(R.id.txtapptime);
        DB = new DBhelperAppointment(this);

        btnapadd = findViewById(R.id.btnappadd);
        btnapdelete = findViewById(R.id.btnappdelete);
        btnapupdate = findViewById(R.id.btnappupdate);
        btnapview = findViewById(R.id.btnappview);


        DatePickerDialog.OnDateSetListener listener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                GettDate();
            }
        };

        pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Appointment.this,listener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute= calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;



                timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        ptime.setText(hourOfDay +":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.setTitle("Select Appointment time");
                timePickerDialog.show();
                    }


        });




        btnapadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientnameTXT = patname.getText().toString();
                String patientIDTXT = pid.getText().toString();
                String contactTXT = pcontact.getText().toString();
                String DateTXT =pdate.getText().toString();
                String timeTXT =ptime.getText().toString();


                Boolean checkinsertmedi = DB.insertAppointment(patientnameTXT,patientIDTXT,contactTXT, DateTXT,timeTXT );

                if(checkinsertmedi==true){
                    Toast.makeText(Appointment.this,"New Appointment Added",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Appointment.this,"New Appointment not Added",Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnapupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientnameTXT = patname.getText().toString();
                String patientIDTXT = pid.getText().toString();
                String contactTXT = pcontact.getText().toString();
                String DateTXT =pdate.getText().toString();
                String timeTXT =ptime.getText().toString();



                Boolean checkinsertmedi = DB.updateAppointment(patientnameTXT,patientIDTXT,contactTXT, DateTXT,timeTXT );

                if(checkinsertmedi==true){
                    Toast.makeText(Appointment.this,"New Appointment Added",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Appointment.this,"New Appointment not Added",Toast.LENGTH_SHORT).show();
                }


            }
        });




        btnapdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientnameTXT = patname.getText().toString();


                Boolean checkinsertmedi = DB.deleteAppointment(patientnameTXT);

                if(checkinsertmedi==true){
                    Toast.makeText(Appointment.this," Appointment Deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Appointment.this," Appointment not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor ans = DB.ViewdataAppointment();

                if (ans.getCount()==0){
                    Toast.makeText(Appointment.this,"List Empty",Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (ans.moveToNext()){
                    buffer.append("Patient Name :" + ans.getString(0)+"\n");
                    buffer.append("Patient Number :" + ans.getString(0)+"\n");
                    buffer.append("Patient Contact :" + ans.getString(0)+"\n");
                    buffer.append("Appointment Date :" + ans.getString(0)+"\n");
                    buffer.append("Appointment Time :" + ans.getString(1)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Appointment.this);
                builder.setCancelable(true);
                builder.setTitle("Appointment Informations");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });



            }





    private void GettDate() {
        String date = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(date, Locale.UK);
        pdate.setText(format.format(calendar.getTime()));
    }
}