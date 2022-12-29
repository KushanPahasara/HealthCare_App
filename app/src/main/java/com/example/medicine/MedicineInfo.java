package com.example.medicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedicineInfo extends AppCompatActivity {

    EditText mediname, mediquntity;
    Button btnadd, btnview, btndelete, btnupdate;
    DBhelperMedinfo DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        mediname = findViewById(R.id.txtmediname);
        mediquntity = findViewById(R.id.txtmediqut);
        btnadd = findViewById(R.id.btnmediadd);
        btnview = findViewById(R.id.btnmediview);
        btndelete = findViewById(R.id.btnmedidelete);
        btnupdate = findViewById(R.id.btnmediupdate);
        DB = new DBhelperMedinfo(this);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicinenametxt = mediname.getText().toString();
                String medicinequntitytxt = mediquntity.getText().toString();

                Boolean checkinsertmedi = DB.insertmedicine(medicinenametxt,medicinequntitytxt);

                if(checkinsertmedi==true){
                    Toast.makeText(MedicineInfo.this,"New Medicine Added",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MedicineInfo.this,"New Medicine not Added",Toast.LENGTH_SHORT).show();
                }



            }
        });



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicinenametxt = mediname.getText().toString();
                String medicinequntitytxt = mediquntity.getText().toString();

                Boolean checkinsertmedi = DB.updatemedicine(medicinenametxt,medicinequntitytxt);

                if(checkinsertmedi==true){
                    Toast.makeText(MedicineInfo.this,"New Medicine Updated",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MedicineInfo.this,"New Medicine not Updated",Toast.LENGTH_SHORT).show();
                }

            }
        });



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String medicinenametxt = mediname.getText().toString();


                Boolean checkinsertmedi = DB.deletemedicine(medicinenametxt);

                if(checkinsertmedi==true){
                    Toast.makeText(MedicineInfo.this," Medicine Deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MedicineInfo.this," Medicine not Deleted",Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor ans = DB.ViewdataMedicine();

                if (ans.getCount()==0){
                    Toast.makeText(MedicineInfo.this,"List Empty",Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (ans.moveToNext()){
                    buffer.append("Medicine Name :" + ans.getString(0)+"\n");
                    buffer.append("Medicine Quntity :" + ans.getString(1)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MedicineInfo.this);
                builder.setCancelable(true);
                builder.setTitle("Medicine Informations");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}