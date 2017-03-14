package com.example.afifur.crudslide;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.content.DialogInterface;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.database.Cursor;

public class Tab2 extends Fragment {
    Button refresh;
    ListView lv;
    ArrayAdapter<Mahasiswa> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tab2,  null);
        initializeViews(view);
        load();

        return view;

    }



    private void initializeViews(View rootView)
    {
        lv= (ListView) rootView.findViewById(R.id.viewsql);
        refresh= (Button) rootView.findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
    }
    public void load() {

        final DbHandler db = new DbHandler(getContext());;
        lv.setAdapter(new ArrayAdapter<Mahasiswa>(getActivity(), android.R.layout.simple_list_item_1,db.getAllMahasiswa()));
        lv.setSelected(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
        Cursor cursor;
            String[] daftar;

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                cursor = db.getCursor();
                daftar = new String[cursor.getCount()];
                cursor.moveToFirst();
                for (int cc=0; cc < cursor.getCount(); cc++){
                    cursor.moveToPosition(cc);
                    daftar[cc] = cursor.getString(0).toString();
                }
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = { "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){

                            case 0 :
                                Intent in = new Intent(getActivity().getApplicationContext(), Edit.class);
                                in.putExtra("nama",selection);
                                startActivity(in);
                                break;
                            case 1 :
                                DbHandler db = new DbHandler(getContext());
                                db.deleteMahasiswa(new Mahasiswa(Integer.parseInt(selection),null));
                                break;

                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)lv.getAdapter()).notifyDataSetInvalidated();
    }

}


