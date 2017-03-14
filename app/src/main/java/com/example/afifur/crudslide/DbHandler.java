package com.example.afifur.crudslide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import static com.example.afifur.crudslide.PAPBLContract.PAPBL.*;



public class DbHandler extends SQLiteOpenHelper {

    private Context context;
    private static final String CREATE_TABLE_MAHASISWA="CREATE TABLE "+_TABLE_MAHASISWA+"("+_ID+" INTEGER PRIMARY KEY , "+_NAME+" VARCHAR(255));";
    private static final String DROP_TABLE_MAHASISWA="DROP TABLE  IF EXISTS "+_TABLE_MAHASISWA;
    private static final String selectMahasiswaQuery = "SELECT * FROM " + _TABLE_MAHASISWA;


    public DbHandler (Context context) {
        super(context,_DB_NAME,null,_DATABASE_VERSION);
        this.context=context;
        Message.message(context,"Constructor dipanggil");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_MAHASISWA);
            Message.message(context,"onCreate dipanggil");
        } catch (SQLiteException e){
            Message.message(context,""+e);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            Message.message(context,"onDowngrade dipanggil");
            db.execSQL(DROP_TABLE_MAHASISWA);
            onCreate(db);
        }catch (SQLiteException e){
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            Message.message(context,"onUpgrade dipanggil");
            db.execSQL(DROP_TABLE_MAHASISWA);
            onCreate(db);
        }catch (SQLiteException e){
            Message.message(context,""+e);
        }
    }

    public void addMahasiswa(Mahasiswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(_NAME, siswa.get_nama()); // Shop Name
        values.put(_ID, siswa.get_id()); // Shop Phone Number

        // Inserting Row
        db.insert(_TABLE_MAHASISWA, null, values);
        db.close(); // Closing database connection
    }

    // Deleting a siswa
    public void deleteMahasiswa(Mahasiswa siswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(_TABLE_MAHASISWA, _ID + "=?",
                new String[] { String.valueOf(siswa.get_id())}
        );
        db.close();
    }

    public void updateMahasiswa(String nama,int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues isi = new ContentValues();
        isi.put(_NAME, nama); // Shop Name
        db.update(_TABLE_MAHASISWA, isi, "WHERE "+_ID+ " = "+id,null);
        db.close();
    }

    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectMahasiswaQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mahasiswa siswa = new Mahasiswa();
                siswa.set_id(Integer.parseInt(cursor.getString(0)));
                siswa.set_nama(cursor.getString(1));
                // Adding siswa to list
                mahasiswaList.add(siswa);
            } while (cursor.moveToNext());
        }
        // return contact list
        return mahasiswaList;
    }

    public Cursor getCursor() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectMahasiswaQuery, null);

        return cursor;
    }
    public Cursor getCursor(int nama) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + _TABLE_MAHASISWA+ " WHERE"+ _ID +" = "+nama, null);

        return cursor;
    }
}
