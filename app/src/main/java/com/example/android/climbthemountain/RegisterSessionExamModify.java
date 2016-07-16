package com.example.android.climbthemountain;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.example.android.climbthemountain.CustomDialogFragment.ColorPickerDialogFragment;
import com.example.android.climbthemountain.user_data.ExamData;
import com.example.android.climbthemountain.user_data.UserData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RegisterSessionExamModify extends AppCompatActivity implements ColorPickerDialogFragment.interazioneColorPicker {

    EditText etExamNameMod;
    EditText etCfuMod;
    DatePicker dpExamDate;
    Button colorExamPicker;
    Button cancel;
    Button delete;
    TextView colorPickerSelection;


    // toolbar
    Toolbar tbRegistration;

    ExamData esame = new ExamData();

    //Colore scelto dall'utente
    String colore;
    int position;
    boolean isSummary;

    //CustomDialogFragment
    ColorPickerDialogFragment dialogFragment;

    UserData userData = new UserData();

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_modify_06);

        //Recupero tutti i dati dall'intent
        intent = getIntent();
        userData = intent.getParcelableExtra(Login.USER_OBJ);
        position = intent.getIntExtra("position", 0);
        isSummary = intent.getBooleanExtra(Login.isSUMMARY, false);

        //Inizializzo le view
        etExamNameMod = (EditText) findViewById(R.id.etSessionExam_examName_Mod);
        etCfuMod = (EditText) findViewById(R.id.etSessionExam_cfu_Mod);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline_Mod);
        colorPickerSelection = (TextView) findViewById(R.id.tvSessionExam_colorSquare_Mod);
        colorExamPicker = (Button) findViewById(R.id.btSessionExam_colorPck_Mod);
        cancel = (Button) findViewById(R.id.btCancel);
        delete = (Button) findViewById(R.id.btDelete);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar_Mod);
        setSupportActionBar(tbRegistration);

        //Organizzo meglio con delle variabili
        esame = userData.userExams.get(position);
        colore = esame.getColore();

        //RECUPERO I DATI DA MODIFICARE DELL'ESAME
        etExamNameMod.setText(esame.getNome());
        etCfuMod.setText(String.format("%d",esame.getCfu()));
        colorPickerSelection.setBackgroundColor(Color.parseColor(colore));
        dpExamDate.updateDate(esame.getAnno(), esame.getMese(), esame.getGiorno());



        //Annulla la modifica dell'esame
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //L'utente vuole tornare indietro senza far nulla
                Intent intent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
                //non ho più bisogno della position
                intent.putExtra(Login.USER_OBJ, userData);
                intent.putExtra(Login.isSUMMARY, isSummary);
                startActivity(intent);
            }
        });

        //Elimina l'esame selezionato
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);

                //Aggiorno l'utente
                userData.userExams.remove(position);

                //Carico l'utente aggiornato sull'intent
                intent.putExtra(Login.USER_OBJ, userData);
                intent.putExtra(Login.isSUMMARY, isSummary);
                startActivity(intent);
            }
        });

        colorExamPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = ColorPickerDialogFragment.newInstance();
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });

    }

    //Implemento i metodi dell'interfaccia
    @Override
    public void onPositiveButtonClick(String colore){
        //Recupera il colore e lo aggiunge all'esame

        this.colore = colore;
        TextView txt = (TextView) findViewById(R.id.tvSessionExam_colorSquare_Mod);
        txt.setBackgroundColor(Color.parseColor(colore));

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

        //Aggiorno l'utente
        userData.userExams.remove(position);
        userData.userExams.add(position, esame);

        //In mancanza di un controllo più approfondito
        if(colore != null) {
            esame.setColore(colore);
        }
        else {
            esame.setColore("#000000");
        }


        //HO RECUPERATO TUTTI I DATI MODIFICATI, LI CARICO SULL'UTENTE

            Intent intent = new Intent(this, RegisterSessionExamSelection.class);
            //Carico l'utente sull'intent
            intent.putExtra(Login.USER_OBJ, userData);
            intent.putExtra(Login.isSUMMARY, isSummary);
            startActivity(intent);








    }


}
