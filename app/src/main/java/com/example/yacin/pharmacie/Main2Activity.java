package com.example.yacin.pharmacie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button gcom;
    Button gfrn;
    Button gmed;
    Button gvnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gcom=(Button) findViewById(R.id.gcom);
        gfrn=(Button) findViewById(R.id.gfrn);
        gmed=(Button) findViewById(R.id.gmed);
        gvnt=(Button) findViewById(R.id.gvnt);


        gcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getion commonde

                Intent in=new Intent(getApplicationContext(),gestion_commandes.class);
                startActivity(in);

            }
        });

        gfrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getion fourniseur
                Intent in=new Intent(getApplicationContext(),gestion_fournisseurs.class);
                startActivity(in);
            }
        });

        gmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getion medicament
                Intent in=new Intent(getApplicationContext(),gestion_medicaments.class);
                startActivity(in);
            }
        });

        gvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getion venter
                Intent in=new Intent(getApplicationContext(),gestion_ventes.class);
                startActivity(in);
            }
        });

    }
}
