package com.example.medicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Bill extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();


    EditText pprice, quntity, stottal,additional,ttotal;
    Button btnadd,btntotal,btnviewmedicine;
    DBhelperMedinfo DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        pprice =findViewById(R.id.txtbillprice);
        quntity =findViewById(R.id.txtbillQuntity);
        stottal = findViewById(R.id.txtbillstotal);
        additional = findViewById(R.id.txtbilladdition);
        ttotal = findViewById(R.id.txtbilltotal);

        btnadd = findViewById(R.id.btnbilladd);
        btntotal = findViewById(R.id.btnbilltotal);
        btnviewmedicine = findViewById(R.id.btnviewavailmedi);
        DB = new DBhelperMedinfo(this);




        btnviewmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor ans = DB.ViewdataMedicine();

                if (ans.getCount()==0){
                    Toast.makeText(Bill.this,"List Empty",Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (ans.moveToNext()){
                    buffer.append("Medicine Name :" + ans.getString(0)+"\n");
                    buffer.append("Medicine Quntity :" + ans.getString(1)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Bill.this);
                builder.setCancelable(true);
                builder.setTitle("Medicine Informations");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                add();



            }
        });


        btntotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sprice =  Integer.parseInt(stottal.getText().toString());
                int addi =  Integer.parseInt(additional.getText().toString());
                int toti = sprice + addi;

                ttotal.setText(String.valueOf(toti));



            }
        });



    }


    public void add()
    {
        int tot;
        int price = Integer.parseInt(pprice.getText().toString());
        int qty = Integer.parseInt(quntity.getText().toString());
        tot = price * qty;

        data.add(String.valueOf(price));
        data1.add(String.valueOf(qty));
        data2.add(String.valueOf(tot));
        String total;
        int sum = 0;
        for(int i = 0; i< data.size(); i ++)
        {

            String prc = data.get(i);
            String qtyy = data1.get(i);
            total = data2.get(i);
            sum = sum + Integer.parseInt(data2.get(i).toString());
        }

        stottal.setText(String.valueOf(sum));
        pprice.setText("");
        quntity.setText("");
        pprice.requestFocus();
    }


}