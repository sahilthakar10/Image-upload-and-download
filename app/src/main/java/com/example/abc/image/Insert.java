package com.example.abc.image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Insert extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        b1 = (Button) findViewById(R.id.b1);
        e1 = (EditText)findViewById(R.id.e1);
        Button b2 = (Button)findViewById(R.id.b2);
        b1.setOnClickListener(this );
        b2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.b1) {
            String e = e1.getText().toString();

            DABASEHANDLER db = new DABASEHANDLER(Insert.this);
            Registration obj = new Registration(e);
            db.addDATA(obj);
        }
        if(v.getId() == R.id.b2)
        {
            Intent i = new Intent(getApplicationContext() , MainActivity.class);
            startActivity(i);
        }
    }
}
