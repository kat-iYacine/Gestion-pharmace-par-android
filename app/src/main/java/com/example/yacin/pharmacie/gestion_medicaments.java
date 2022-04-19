package com.example.yacin.pharmacie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class gestion_medicaments extends AppCompatActivity {

    EditText cm;
    EditText nm;
    EditText prv;
    EditText pu;
    EditText ops;
    Spinner fm;
    Button ajtM;
    Button affM;

    public ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicaments);

        cm=(EditText) findViewById(R.id.cm1);
        nm=(EditText) findViewById(R.id.nm);
       // dm=(EditText) findViewById(R.id.dm);
        prv=(EditText) findViewById(R.id.prv);
        pu=(EditText) findViewById(R.id.pu);
        ops=(EditText) findViewById(R.id.ops);
        fm=(Spinner) findViewById(R.id.fm);
        ajtM=(Button) findViewById(R.id.ajtm);
        affM=(Button) findViewById(R.id.affM);

        list.add("list_famille");

        new MyAsyncTaskresources().execute("http://192.168.43.41/pharmacie/get_item.php?type=fm");

        ArrayAdapter<String> arry=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm.setAdapter(arry);


        ajtM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String get_cm=cm.getText().toString();
                String get_nm=nm.getText().toString();
                String get_prv=prv.getText().toString();
                String get_pu=pu.getText().toString();
                String get_ops=ops.getText().toString();
                String get_fm=fm.getSelectedItem().toString();

                new insert().execute("http://192.168.43.41/pharmacie/insert_med.php?cm="+get_cm+"&nm="+get_nm+"&prv="+get_prv+"&pu="+get_pu+"&ops="+get_ops+"&fm="+get_fm);
            }
        });

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
                java.net.URL url = new URL( URL);
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

                    list.add(json.getString("code"));}


            } catch (Exception e) {

// TODO: handle exception

                Log.e("log_tag", "Error Parsing Data "+e.toString());

            }
        }




    }
    String result1 = "";
    public class insert extends AsyncTask<String, String, String> {
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

                result1=sb.toString();

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

                JSONArray jArray = new JSONArray(result1);

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject json = jArray.getJSONObject(i);

                    s = s + "login info : " + json.getString("id") + " " + json.getString("login") + " " + json.getString("password");
                    break;}
                if(s.length()>0){
                    Toast.makeText(getApplicationContext(),"un nouvea medicament est ajoute",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"user name or password isnot correct",Toast.LENGTH_LONG).show();


            } catch (Exception e) {

// TODO: handle exception

                Log.e("log_tag", "Error Parsing Data "+e.toString());

            }
        }




    }
}
