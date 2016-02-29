package com.example.android.climbthemountain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Dichiaro i bottoni.
    Button button_login;
    Button button_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.PrimoAvvio);

        //Inizializzo i bottoni trovando le view corrispondenti
        button_login = (Button) findViewById(R.id.button_login);
        button_create = (Button) findViewById(R.id.button_create);

        //Stabilisco il clickListener dei due bottoni
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Avvio la schermata di login
                Intent loginIntent = new Intent(v.getContext(), LogIn.class);
                startActivity(loginIntent);
            }
        });

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Avvio la procedura di creazione del profilo
                Intent creazioneProfiloIntent = new Intent(v.getContext(), CreazioneProflo_DatiPersonali.class);
                startActivity(creazioneProfiloIntent);
            }
        });
    }
}
