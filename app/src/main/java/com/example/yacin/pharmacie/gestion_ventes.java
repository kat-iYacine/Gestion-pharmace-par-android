package com.example.yacin.pharmacie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Date;

public class gestion_ventes extends AppCompatActivity {

    Spinner sm;
    Button vnt;
    Button vld;
    Button ord;
    Button ser;
    EditText qv;

    public double totale=0.0;
    public int q=0;
    public double p=0;
    public  String s;
    public ArrayList<String> list=new ArrayList<>();
    public String name="ERROR!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_ventes);

         sm=(Spinner) findViewById(R.id.sm);
        vnt=(Button) findViewById(R.id.vent);
        vld=(Button) findViewById(R.id.valider);
        ord=(Button) findViewById(R.id.ord);
        ser=(Button) findViewById(R.id.ser);
         qv=(EditText) findViewById(R.id.qv);


        list.add("List_Med");

        new MyAsyncTaskresources().execute("http://192.168.43.41/pharmacie/get_item.php?type=med");

        ArrayAdapter<String> arry=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sm.setAdapter(arry);


          ser.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 String get_sm= sm.getSelectedItem().toString();

                  new get_item().execute("http://192.168.43.41/pharmacie/get_rec.php?med="+get_sm);
              }
          });


        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date d=new Date();
                s= d.getTime()+"";

                name="nouveau ordonnance est ajouter";
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                new insert().execute("http://192.168.43.41/pharmacie/insert_ord.php?id="+s);

            }
        });


        vnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_sm= sm.getSelectedItem().toString();
                String get_qv=qv.getText().toString();
                int qt=Integer.valueOf(get_qv);
                name="OK!";
                if(qt<q){

                    new insert().execute("http://192.168.43.41/pharmacie/vent_med.php?sm="+get_sm+"&qv="+get_qv+"&idf="+s);
                    totale=totale+(qt*p);

                }else { Toast.makeText(getApplicationContext(),"Quntite unsifisont!!",Toast.LENGTH_LONG).show();}
            }
        });


        vld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"PRIX DE ORDONNACE:"+totale,Toast.LENGTH_LONG).show();
                new insert().execute("http://192.168.43.41/pharmacie/up_date.php?total="+totale+"&idf="+s);
                totale=0;
            }
        });


    }
     String result1="";
    public class insert extends AsyncTask<String, String, String> {
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
    public class get_item extends AsyncTask<String, String, String> {


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
                    String o2="DISPONIBLE:"+json.getString("libelle");
                    String o1="PRIX DE VENTRE:"+json.getString("prix");

                    q=Integer.valueOf(json.getString("libelle"));
                    p=Double.valueOf(json.getString("prix"));

                    Toast.makeText(getApplicationContext(),o1+"  "+o2,Toast.LENGTH_LONG).show();

                  }



            } catch (Exception e) {

// TODO: handle exception

                Log.e("log_tag", "Error Parsing Data "+e.toString());

            }
        }




    }

}
