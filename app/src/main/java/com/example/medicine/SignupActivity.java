package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {


    EditText uname,fname,lname,gender, passw,email,utype;
    Button signupbtn;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uname =findViewById(R.id.txtsname);
        fname=findViewById(R.id.txtFname);
        lname=findViewById(R.id.txtLname);

        passw =findViewById(R.id.txtspass);
        email = findViewById(R.id.txtsemail);
        utype=findViewById(R.id.txtUtype);
        signupbtn = findViewById(R.id.btnsignup);

        preferences = getSharedPreferences("Userinfo",0);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernamevalue = uname.getText().toString();
                String userfnamevalue = fname.getText().toString();
                String userlnamevalue = lname.getText().toString();

                String passwordvalue = passw.getText().toString();
                String emailvalue = email.getText().toString();
                String utypevalue= utype.getText().toString();

                if(usernamevalue.length()>1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("uname", usernamevalue);
                    editor.putString("fname", userfnamevalue);
                    editor.putString("lname", userlnamevalue);

                    editor.putString("passw", passwordvalue);
                    editor.putString("email", emailvalue);
                    editor.putString("utype", utypevalue);
                    editor.apply();
                    Toast.makeText(SignupActivity.this, "user registered", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);


                }else{
                    Toast.makeText(SignupActivity.this, "Please enter the values", Toast.LENGTH_SHORT).show();
                }


            }
        });





    }
}