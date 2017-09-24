package com.example.abc.image;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ABC on 17-08-2017.
 */

public class GetALLImages {
    public static String[] imageURLs;
    public static Bitmap[] bitmaps;
    public static final String JSON_ARRAY="result";
    public static final String IMAGE_URL = "url";
    private String json;
    private JSONArray urls;
    private Context context;
        public GetALLImages(String json,Context c){
        this.context=c;
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
        private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(IMAGE_URL));
            Log.e("URL","getImage="+url);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }
        public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];
        imageURLs = new String[urls.length()];
        Log.e("imageurls" , String.valueOf(imageURLs));
        int i;
        for(i=0;i<urls.length();i++){

            imageURLs[i] = urls.getJSONObject(i).getString(IMAGE_URL);
            Log.e("imageurls" , String.valueOf(imageURLs[i]));
            JSONObject jsonObject = urls.getJSONObject(i);
            Log.e("URL","getallimages="+jsonObject.toString());
            bitmaps[i]=getImage(jsonObject);
            Log.e("bitmap" , String.valueOf(bitmaps[i]));
            saveToInternalStorage(bitmaps[i],imageURLs[i].substring(imageURLs[i].lastIndexOf("/")+1));
        }
}
         private String saveToInternalStorage(Bitmap bitmapImage,String name){

             ContextWrapper cw = new ContextWrapper(context);
                Log.e("String name",name);
                // path to /data/data/yourapp/app_data/imageDir
                File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/incisive/");
             if (!directory.exists()) {
                 Log.e("hhh","helloo");
                 try {
                     directory.mkdir();
                 }catch(Exception ex){Log.e("ERROR",ex.getMessage());}
             }
             Log.e("New Path",directory.getPath());
        // Create imageDir
        File mypath=new File(directory,name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("error2",e.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                //e.printStackTrace();
                Log.e("error",e.getMessage());
            }
        }
        return directory.getAbsolutePath();
    }
}
