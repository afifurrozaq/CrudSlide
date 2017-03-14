package com.example.afifur.crudslide;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Edit extends AppCompatActivity {
EditText text1, text2,text3;
     Button ton;  protected Cursor cursor;
    DbHandler dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit);
            final DbHandler dab = new DbHandler(this);
            text1 = (EditText) findViewById(R.id.nim);
            text2 = (EditText) findViewById(R.id.nama);
            int nim =Integer.parseInt( getIntent().getStringExtra("nama"));
            cursor = dab.getCursor();
            cursor.moveToFirst();
            if (cursor.getCount()>0)
            {
                cursor.moveToPosition(0);
                text1.setText(cursor.getString(0).toString());
                text2.setText(cursor.getString(1).toString());
            }
            ton = (Button) findViewById(R.id.button);
            // daftarkan even onClick pada btnSimpan
            ton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    int nim = Integer.parseInt(text1.getText().toString());
                    dab.updateMahasiswa(text2.getText().toString(), nim);
                }
            });

        }
}
