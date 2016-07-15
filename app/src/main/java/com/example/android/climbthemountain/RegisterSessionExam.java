package com.example.android.climbthemountain;

import android.content.Intent;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android.climbthemountain.user_data.ExamData;

public class RegisterSessionExam extends AppCompatActivity {

    //QUESTA ACTIVITY VIENE RICHIAMATA DOPO CHE L'UTENTE HA PREMUTO SUL TASTO + NELLA 03 DUNQUE AGGIUNGE SOLO UN ESAME

    // elements
    EditText etExamName;
    EditText etCFU;
    DatePicker dpExamDate;
    Button btColor;

    // toolbar
    Toolbar tbRegistration;

    //Oggetto esame
    ExamData esame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_04);

        // element instance
        etExamName = (EditText) findViewById(R.id.etSessionExam_examName);
        etCFU = (EditText) findViewById(R.id.etSessionExam_cfu);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline);
        btColor = (Button) findViewById(R.id.btSessionExam_colorPck);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar);
        setSupportActionBar(tbRegistration);

        esame = new ExamData();


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


        //VERIFICA DELL'INPUT


        //recupero i dati e popolo l'oggetto esame da passare all'activity 03
        esame.setNome(etExamName.getText().toString());
        esame.setCfu(Integer.parseInt(etCFU.getText().toString()));
        esame.setGiorno(dpExamDate.getDayOfMonth());
        esame.setMese(dpExamDate.getMonth());
        esame.setAnno(dpExamDate.getYear());

        //Ancora non ho implementato il colorPicker, uso un colore Standar
        esame.setColore("#123456");


        //Ho recuperato i dati dell'esame, lo passo all'activity 03


        Intent intent = new Intent(this, RegisterSessionExamSelection.class);
        intent.putExtra("esame", esame);
        intent.putExtra("codice", "aggiungiEsame");

        startActivity(intent);


    }




}
