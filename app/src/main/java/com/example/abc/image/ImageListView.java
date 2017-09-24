package com.example.abc.image;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageListView extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    public static final String GET_IMAGE_URL="http://10.0.2.2/Volleyupload/getALLImagess.php";
    public GetALLImages getAlImages;
    public static final String BITMAP_ID = "BITMAP_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view2);
        listView = (ListView) findViewById(R.id.l1);
        listView.setOnItemClickListener(this);
        getURLs();

        Intent i = new Intent(this , Displaydata.class);
        startActivity(i);

    }
    private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageListView.this,"Downloading images...","Please wait...",false,false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();
                String ur = GetALLImages.imageURLs.toString();
                int u = ur.length();
               for(int i=0;i<GetALLImages.imageURLs.length;i++) {
                   Log.e("URL SHORT", GetALLImages.imageURLs[i].substring(GetALLImages.imageURLs[i].lastIndexOf("i")));
                   DABASEHANDLER obj = new DABASEHANDLER(ImageListView.this);
                   obj.update(GetALLImages.imageURLs[i].substring(GetALLImages.imageURLs[i].lastIndexOf("i")));
               }
                CustomList customList = new CustomList(ImageListView.this,GetALLImages.imageURLs,GetALLImages.bitmaps);
                listView.setAdapter(customList);
            }
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlImages.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }
    private void getURLs() {
        class GetURLs extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ImageListView.this,"Loading...","Please Wait...",true,true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getAlImages = new GetALLImages(s,getApplicationContext());
                getImages();
            }
            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    Log.e("URL",sb.toString().trim());
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_IMAGE_URL);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ViewFullImage.class);
        intent.putExtra(BITMAP_ID,i);
        startActivity(intent);
    }
}
