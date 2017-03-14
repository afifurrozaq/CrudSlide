package com.example.afifur.crudslide;

/**
 * Created by afifur on 09/03/17.
 */

public class Mahasiswa {

    private int _id;
    private String _nama;

    public Mahasiswa(){};

    public Mahasiswa(int _id, String _nama) {
        this._id = _id;
        this._nama = _nama;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nama() {
        return _nama;
    }

    public void set_nama(String _nama) {
        this._nama = _nama;
    }
    @Override
    public String toString() {
        return String.valueOf(_id) +" " +_nama;
    }
}