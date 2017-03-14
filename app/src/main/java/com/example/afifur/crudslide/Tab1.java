package com.example.afifur.crudslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;

public class Tab1 extends Fragment {
    private EditText nim;
    private EditText nama;
    private Button save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab1, container, false);
        nim=(EditText)view.findViewById(R.id.nim);
        nama=(EditText)view.findViewById(R.id.nama);
        this.save=(Button)view.findViewById(R.id.button);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        return view;
    }
    public void save() {
        DbHandler db = new DbHandler(getContext());

        db.addMahasiswa(new Mahasiswa(Integer.parseInt(nim.getText().toString()),nama.getText().toString()));
    }
}
