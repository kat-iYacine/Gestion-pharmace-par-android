package com.example.yacin.pharmacie;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class gestion_commandes extends AppCompatActivity {

    Button aj;
    Button aff;
    Button ter;
    EditText nc;
    EditText prx;
    EditText qnt;
    EditText mnt;
    Spinner cf;
    Spinner cm;
    public   ArrayList<String> list=new ArrayList<>();
    public   ArrayList<String> listA=new ArrayList<>();

    public String name="error !";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_commandes);

        aj=(Button) findViewById(R.id.ajoute);
        aff=(Button) findViewById(R.id.afficher);
        ter=(Button) findViewById(R.id.ter);
        nc=(EditText) findViewById(R.id.nc);
        prx=(EditText) findViewById(R.id.prx);
        qnt=(EditText) findViewById(R.id.qnt);
        mnt=(EditText) findViewById(R.id.mnt);
        cf=(Spinner) findViewById(R.id.cf);
        cm=(Spinner) findViewById(R.id.cm);


         list.add("List_Med");
         listA.add("list_frnc");

        new MyAsyncTaskresources().execute("http://192.168.43.41/pharmacie/get_item.php?type=med");

        ArrayAdapter<String> arry=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cm.setAdapter(arry);


        new MyAsyncTaskresources1().execute("http://192.168.43.41/pharmacie/get_item.php?type=frnc");

        ArrayAdapter<String> arry1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listA);
        arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cf.setAdapter(arry1);



          aj.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  name="medicament est ajoute";
                  String get_cm=cm.getSelectedItem().toString();
                  String get_nc=nc.getText().toString();
                  String get_px=prx.getText().toString();
                  String get_qnt=qnt.getText().toString();

                  new insert().execute("http://192.168.43.41/pharmacie/insert_data.php?nc="+get_nc+"&cm="+get_cm+"&qnt="+get_qnt+"&prx="+get_px);
              }
          });

        ter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name="facteur est ajoute";
                String get_nc=nc.getText().toString();
                String get_cf=cf.getSelectedItem().toString();
                String get_mnt=mnt.getText().toString();
                new insert().execute("http://192.168.43.41/pharmacie/insert_fctr.php?nc="+get_nc+"&cf="+get_cf+"&mnt="+get_mnt);
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

                    list.add(json.getString("codemedicament"));}


            } catch (Exception e) {

// TODO: handle exception

                Log.e("log_tag", "Error Parsing Data "+e.toString());

            }
        }




    }
    String result0 = "";
    public class MyAsyncTaskresources1 extends AsyncTask<String, String, String> {


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

                result0=sb.toString();

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

                JSONArray jArray = new JSONArray(result0);

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject json = jArray.getJSONObject(i);
                    listA.add(json.getString("codefournisseur"));
                }

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
                    Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
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
