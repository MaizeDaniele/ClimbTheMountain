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
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android.climbthemountain.user_data.ExamData;

import java.util.ArrayList;

public class RegisterSessionExamModify extends AppCompatActivity {

    EditText etExamNameMod;
    EditText etCfuMod;
    DatePicker dpExamDate;
    Button colorExamPicker;
    Button cancel;
    Button delete;


    // toolbar
    Toolbar tbRegistration;

    ExamData esame;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_modify_06);

        etExamNameMod = (EditText) findViewById(R.id.etSessionExam_examName_Mod);
        etCfuMod = (EditText) findViewById(R.id.etSessionExam_cfu_Mod);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline_Mod);
        colorExamPicker = (Button) findViewById(R.id.btSessionExam_colorPck_Mod);
        cancel = (Button) findViewById(R.id.btCancel);
        delete = (Button) findViewById(R.id.btDelete);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar_Mod);
        setSupportActionBar(tbRegistration);

        intent = getIntent();
        esame = intent.getParcelableExtra("esame");



        //RECUPERO I DATI DA MODIFICARE DELL'ESAME
        etExamNameMod.setText(esame.getNome());
        etCfuMod.setText(String.format("%d",esame.getCfu()));

        //DEVO IMPOSTARE IL COLORE NEL COLORPICKER

        dpExamDate.updateDate(esame.getAnno(), esame.getMese(), esame.getGiorno());



        //Annulla la modifica dell'esame
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L'utente vuole tornare indietro senza far nulla
                Intent intent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
                intent.putExtra("codice", "continua");
                startActivity(intent);
            }
        });

        //Elimina l'esame selezionato
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
                intent1.putExtra("codice", "eliminaEsame");
                intent1.putExtra("position", intent.getIntExtra("position", 0));
                startActivity(intent1);
            }
        });

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
        esame.setNome(etExamNameMod.getText().toString());
        esame.setCfu(Integer.parseInt(etCfuMod.getText().toString()));
        esame.setGiorno(dpExamDate.getDayOfMonth());
        esame.setMese(dpExamDate.getMonth());
        esame.setAnno(dpExamDate.getYear());

        //Ancora non ho implementato il colorPicker, uso un colore Standar
        esame.setColore("#123456");


        //Ho recuperato i dati dell'esame, lo passo all'activity 03





        Intent intent = new Intent(this, RegisterSessionExamSelection.class);
        intent.putExtra("esame", esame);
        intent.putExtra("codice", "modificaEsame");
        intent.putExtra("position", this.intent.getIntExtra("position", 0));

        startActivity(intent);




    }
}
