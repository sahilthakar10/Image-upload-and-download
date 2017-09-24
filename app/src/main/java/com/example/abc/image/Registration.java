package com.example.abc.image;

import android.util.Log;

/**
 * Created by ABC on 20-08-2017.
 */

public class Registration {

    int id;
    String phone;
    String photo;
    public  Registration()
    {

    }
    public Registration(int id, String phone, String photo) {
        Log.e("eee" , phone);
        Log.e("id" , String.valueOf(id));
        this.id = id;
        this.phone = phone;
        this.photo = photo;

    }

    public Registration(String phone ) {

        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }



    public String getphone() {
        return phone;
    }


    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getPhoto(){ return photo;}
    public void setphoto(String photo){this.photo = photo;}
}
