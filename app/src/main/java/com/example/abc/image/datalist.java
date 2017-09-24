package com.example.abc.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class datalist extends ArrayAdapter<String> {

    private String[] contact;
    private String[] notification;
    private Activity context;
    int b = 0;

    public datalist(Activity context, String[] contact, String[] notification) {
        super(context, R.layout.activity_datalist, contact);
        this.context = context;
        this.notification = notification;
        this.contact = contact;
        Log.e("notification", String.valueOf(this.notification));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_datalist, null, true);
        TextView textViewnotification = (TextView) listViewItem.findViewById(R.id.t2);
        TextView textViewContact = (TextView) listViewItem.findViewById(R.id.t1);
        ImageView i1=(ImageView) listViewItem.findViewById(R.id.i1);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/incisive/"+contact[position];
        Log.e("path",path);
        Bitmap bmp = BitmapFactory.decodeFile(path);

        if(bmp!=null)
        i1.setImageBitmap(bmp);
        //Uri url=new Uri();
        //i1.setImageURI(Environment.getExternalStorageDirectory() + ""));

        textViewContact.setText(contact[position]);
        textViewnotification.setText(notification[position]);

        return listViewItem;


    }

}