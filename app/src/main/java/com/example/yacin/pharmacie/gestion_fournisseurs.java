package com.example.yacin.pharmacie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.net.URLConnection;

public class gestion_fournisseurs extends AppCompatActivity {

    EditText cf;
    EditText nom;
    EditText adr;
    EditText ville;
    EditText tel;
    Button ajt;
    Button aff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_fournisseurs);

        cf=(EditText) findViewById(R.id.nf);
        nom=(EditText) findViewById(R.id.nom);
        adr=(EditText) findViewById(R.id.adr);
        ville=(EditText) findViewById(R.id.ville);
        tel=(EditText) findViewById(R.id.tel);
        ajt=(Button) findViewById(R.id.ajt);
        aff=(Button) findViewById(R.id.aff);


       ajt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String get_cf=cf.getText().toString();
               String get_nom=nom.getText().toString();
               String get_adr=adr.getText().toString();
               String get_ville=ville.getText().toString();
               String get_tel=tel.getText().toString();

               new insert().execute("http://192.168.43.41/pharmacie/insert_frns.php?cf="+get_cf+"&nom="+get_nom+"&adr="+get_adr+"&ville="+get_ville+"&tel="+get_tel);
           }
       });

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
                    Toast.makeText(getApplicationContext(),"le fournisseur est ajoute",Toast.LENGTH_LONG).show();
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
