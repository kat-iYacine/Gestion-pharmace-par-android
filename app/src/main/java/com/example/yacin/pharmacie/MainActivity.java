package com.example.yacin.pharmacie;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bulogin(View view) {
        //get user name and password
        EditText txtusername=(EditText)findViewById(R.id.user);
        EditText txtpassword=(EditText)findViewById(R.id.psw);
        new MyAsyncTaskresources().execute("http://192.168.43.41/pharmacie/login.php?username="+txtusername.getText().toString()+"&password=" +txtpassword.getText().toString());
        //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
    }


    String result = "";
    public class MyAsyncTaskresources extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected String  doInBackground(String... params) {


            InputStream isr = null;

            try{
                String URL=params[0];
                URL url = new URL( URL);
                URLConnection urlConnection = url.openConnection();
                isr  = new BufferedInputStream(urlConnection.getInputStream());

            }

            catch(Exception e){

                Log.e("log_tag", "Error in http connection " + e.toString());



            }

//convert response to string

            try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");

                }

                isr.close();

                result=sb.toString();

            }

            catch(Exception e){

                Log.e("log_tag", "Error  converting result " + e.toString());

            }

//parse json data


            return null;
        }

        protected void onPostExecute(String  result2){
            try {

                String s = "";

                JSONArray jArray = new JSONArray(result);

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject json = jArray.getJSONObject(i);

                    s = s + "login info : " + json.getString("id") + " " + json.getString("login") + " " + json.getString("password");
                    break;}
                if(s.length()>0){

                   Intent in=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(in);}
                else
                    Toast.makeText(getApplicationContext(),"user name or password isnot correct",Toast.LENGTH_LONG).show();


            } catch (Exception e) {

// TODO: handle exception

                Log.e("log_tag", "Error Parsing Data "+e.toString());

            }
        }




    }
}
