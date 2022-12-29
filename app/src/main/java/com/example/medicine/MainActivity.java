package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname,password;
    Button loginbtn ,goRegbtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.txtloginname);
        password = findViewById(R.id.txtloginpass);
        loginbtn = findViewById(R.id.btnlogin);
        goRegbtn = findViewById(R.id.btngoReg);

        preferences = getSharedPreferences("Userinfo",0);

        goRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(i);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamevalue = uname.getText().toString();
                String passwordvalue = password.getText().toString();

                String registeredUsername = preferences.getString("name","");
                String registeredPassword = preferences.getString("passw","");
                String registerUser = preferences.getString("utype","");


                if(usernamevalue.equals(registeredUsername) && passwordvalue.equals(registeredPassword)
                        && registerUser.equals("Customer")){

                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                    finish();

                } else if(usernamevalue.equals(registeredUsername) && passwordvalue.equals(registeredPassword)
                        && registerUser.equals("Cleaner")) {

                    Intent i = new Intent(getApplicationContext(), Bill.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Please Register first", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}