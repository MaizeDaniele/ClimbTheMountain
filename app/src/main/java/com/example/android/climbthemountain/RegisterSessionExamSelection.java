package com.example.android.climbthemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.climbthemountain.CustomAdapter.ExamAdapter;
import com.example.android.climbthemountain.user_data.ExamData;

import java.util.ArrayList;

public class RegisterSessionExamSelection extends AppCompatActivity {

    Button bt_addExam;

    ListView lv_ExamsList;


    // toolbar
    Toolbar tbRegistration;

    //ListaEsami da mostrare nella listView
    static ArrayList<ExamData> listaEsami = new ArrayList<>();

    //Adattatore per la visualizzazione della listView
    ExamAdapter adattatore;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_selection_07);

        lv_ExamsList = (ListView) findViewById(R.id.lvSession_list);


        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        setSupportActionBar(tbRegistration);

        bt_addExam = (Button) findViewById(R.id.btSession_addExam);
        bt_addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passa all'activity per l'aggiunta esame
                Intent intent = new Intent(v.getContext(), RegisterSessionExam.class);
                startActivity(intent);
            }
        });

        //INIZIALIZZAZIONE LISTVIEW

        intent = getIntent();
        String codice = intent.getStringExtra("codice");



        verificaCodice(codice);

        //Printo la listView, se la lista esami è vuota non verrà visualizzato nulla
        adattatore = new ExamAdapter(listaEsami, this);
        lv_ExamsList.setAdapter(adattatore);


    }

    // inflate the menu for App Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register_account_data, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // manage menu's items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        saveExamData();
        return super.onOptionsItemSelected(item);
    }

    // need to handle content returning to display inserted exams in the list view
    private void saveExamData() {
        //l'utente ha confermato
        Intent intent = new Intent(this, RegisterSessionSummary.class);
        startActivity(intent);
    }

    //Funzione che modifica la listView in base all'intent ricevuto
    private void verificaCodice(String codice){

        int position = 0;
        ExamData esame = null;

        switch (codice) {

            case "continua":
                //Primo avvio dell'app, non fa nulla
                break;
            case "aggiungiEsame":
                //Aggiunta di un esame

                esame = intent.getParcelableExtra("esame");
                listaEsami.add(esame);
                break;

            case "modificaEsame":
                //modifica di un esame
                esame = intent.getParcelableExtra("esame");
                position = intent.getIntExtra("position", 0);

                //Rimuovo l'esame che l'utente ha cambiato
                listaEsami.remove(position);
                listaEsami.add(position, esame);
                break;

            case "eliminaEsame":
                //Elimina l'esame alla posizione data
                position = intent.getIntExtra("position", 0);
                listaEsami.remove(position);
                break;



        }
    }
}



