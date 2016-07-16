package com.example.android.climbthemountain;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class RegisterSessionExam extends AppCompatActivity implements ColorPickerDialogFragment.interazioneColorPicker {

    //QUESTA ACTIVITY VIENE RICHIAMATA DOPO CHE L'UTENTE HA PREMUTO SUL TASTO + NELLA 03 DUNQUE AGGIUNGE SOLO UN ESAME

    // elements
    EditText etExamName;
    EditText etCFU;
    DatePicker dpExamDate;
    Button btColor;

    // toolbar
    Toolbar tbRegistration;



    //Oggetto esame
    ExamData esame = new ExamData();
    String colore;
    boolean isSummary;


    //DialogFragment
    ColorPickerDialogFragment dialogFragment;

    UserData userData = new UserData();

    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_04);

        //Recupero l'utente
        intent = getIntent();
        userData = intent.getParcelableExtra(Login.USER_OBJ);
        isSummary = intent.getBooleanExtra(Login.isSUMMARY, false);

        // element instance
        etExamName = (EditText) findViewById(R.id.etSessionExam_examName);
        etCFU = (EditText) findViewById(R.id.etSessionExam_cfu);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline);
        btColor = (Button) findViewById(R.id.btSessionExam_colorPck);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar);
        setSupportActionBar(tbRegistration);



        btColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = ColorPickerDialogFragment.newInstance();
                dialogFragment.show(getFragmentManager(), "dialog");
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

    //Implemento i metodi dell'interfaccia
    @Override
    public void onPositiveButtonClick(String colore){
        //Recupera il colore e lo aggiunge all'esame

        this.colore = colore;
        TextView txt = (TextView) findViewById(R.id.tvSessionExam_colorSquare);
        if(colore == null){
            txt.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            txt.setBackgroundColor(Color.parseColor(colore));
        }

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

        //In mancanza di un controllo più approfondito
        if(colore != null) {
            esame.setColore(colore);
        }
        else {
            esame.setColore("#000000");
        }

        //Ho recuperato i dati dell'esame, lo passo all'activity 03


        Intent intent = new Intent(this, RegisterSessionExamSelection.class);

        //Aggiorno elenco esami
        userData.userExams.add(esame);

        //Passo l'elenco esami all'intent
        intent.putExtra(Login.USER_OBJ, userData);
        intent.putExtra(Login.isSUMMARY, isSummary);

        startActivity(intent);


    }




}
