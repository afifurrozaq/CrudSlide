package com.example.afifur.crudslide;

/**
 * Created by afifur on 09/03/17.
 */


        import android.content.Context;
        import android.widget.Toast;

/**
 * Created by MuhammadAminul on 3/6/2017.
 */
public class Message {

    public static void message (Context context, String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}

