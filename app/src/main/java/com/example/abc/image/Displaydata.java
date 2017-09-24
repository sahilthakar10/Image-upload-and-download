package com.example.abc.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class Displaydata extends AppCompatActivity {

    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata);
        l1 = (ListView)findViewById(R.id.l1);
        DABASEHANDLER db = new DABASEHANDLER(this);
        List<Registration> contacts = db.getAllData();
        String[] notification = new String[contacts.size()];
        String[] contact = new String[contacts.size()];
        int i = 0;
        for (Registration cn : contacts) {
            notification[i] = cn.getphone();
            contact[i] = cn.getPhoto();
            Log.e("contactt", contact[i]);
            i++;
        }
      datalist datalist = new datalist(Displaydata.this, contact, notification);
        l1 = (ListView) findViewById(R.id.l1);
        l1.setAdapter(datalist);
    }
}
