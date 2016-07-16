package com.example.android.climbthemountain;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

    // toolbar
    Toolbar tbRegistration;


    //Colore scelto dall'utente
    ExamData esame = new ExamData();
    String colore;
    int position;

    TextView colorPickerSelection;

    TextView tvCFU;
    TextView tvExamName;
    TextView tvColorError;

    //CustomDialogFragment
    ColorPickerDialogFragment dialogFragment;
    UserData userData;
    Intent intent;



    // control for user input
    boolean flag = false;
    boolean control = false;

    // variable to know where dispatch an intent to next Activity
    boolean isSummary;


    @Override
    public void onBackPressed() {

        //L'utente vuole tornare indietro senza far nulla
        Intent intent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
        //non ho più bisogno della position
        intent.putExtra(Login.USER_OBJ, userData);
        intent.putExtra(Login.isSUMMARY, isSummary);
        startActivity(intent);
    }

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


        // adding references
        tvCFU = (TextView) findViewById(R.id.tvSessionExam_cfu_Mod);
        tvExamName = (TextView) findViewById(R.id.tvSessionExam_examName_Mod);
        tvColorError = (TextView) findViewById(R.id.tvSessionExam_errorColor_Mod);

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

        etCfuMod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkUserDetailMessage();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etExamNameMod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkUserDetailMessage();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hintSetOnCreate();
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

        flag = true;
        setMessageColor();

        if (isCFUaValidNumb() && !anyFieldEmpty() && colorIsChosen() ){

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

        } else if (!etCfuMod.getText().toString().equals("") && !isCFUaValidNumb()){

            setMessageColor();
            controlValidCFU();
        } else if(anyFieldEmpty()){

            checkUserDetailMessage();
        }

    }



    private void hintSetOnCreate() {

        etExamNameMod.setHint(R.string.hint_general_exam_name);
        etCfuMod.setHint(R.string.hint_general_exam_cfu);

        etExamNameMod.setHintTextColor(getResources().getColor(R.color.grey_600));
        etCfuMod.setHintTextColor(getResources().getColor(R.color.grey_600));

        tvExamName.setHintTextColor(getResources().getColor(R.color.grey_900));
        tvCFU.setHintTextColor(getResources().getColor(R.color.grey_900));

        tvColorError.setVisibility(View.GONE);
        tvColorError.setTextColor(getResources().getColor(R.color.cyan_600));
    }


    private boolean isCFUaValidNumb() {

        try {

            if (!etCfuMod.getText().toString().equals("")) {
                int dummy = Integer.parseInt(etCfuMod.getText().toString());
                return true;
            } else {

                return false;
            }


        } catch (NumberFormatException e) {
            return false;
        }

    }

    private boolean[] controllerFieldEmpty() {

        boolean[] controller = new boolean[2];

        controller[0] = etExamNameMod.getText().toString().equals("");
        controller[1] = etCfuMod.getText().toString().equals("");

        return controller;
    }

    private boolean anyFieldEmpty() {

        boolean temp = false;

        for (boolean bool : controllerFieldEmpty()) {
            temp = bool || temp;
        }

        return temp;
    }

    private void checkUserDetailMessage() {


        if (controllerFieldEmpty()[0] && flag) {

            tvExamName.setTextColor(getResources().getColor(R.color.cyan_600));
            etExamNameMod.setHintTextColor(getResources().getColor(R.color.cyan_300));

            etExamNameMod.setHint(R.string.hint_error_examName_noName);

        } else {

            tvExamName.setTextColor(getResources().getColor(R.color.grey_900));


        }

        if (controllerFieldEmpty()[1] && flag) {

            if (!control) {

                tvCFU.setTextColor(getResources().getColor(R.color.cyan_600));
                etCfuMod.setHintTextColor(getResources().getColor(R.color.cyan_300));

                etCfuMod.setHint(R.string.hint_error_examCfu_noCfu);

            } else {

                tvCFU.setTextColor(getResources().getColor(R.color.red_900));
                etCfuMod.setHintTextColor(getResources().getColor(R.color.red_600));
            }

        } else {

            tvCFU.setTextColor(getResources().getColor(R.color.grey_900));

        }


        if (control && !etCfuMod.getText().toString().equals("")) {

            control = false;

        }

    }

    private void controlValidCFU() {

        if (!isCFUaValidNumb() && !etCfuMod.getText().toString().equals("")) {

            control = true;

            tvCFU.setTextColor(getResources().getColor(R.color.red_900));
            etCfuMod.setHintTextColor(getResources().getColor(R.color.red_600));

            etCfuMod.setHint(R.string.hint_error_examCfu_noChar);

            etCfuMod.setText("");
        }
    }



    private void setMessageColor(){

        if (colorIsChosen()) tvColorError.setVisibility(View.GONE);
        else tvColorError.setVisibility(View.VISIBLE);
    }

    private boolean colorIsChosen(){

        return this.colore != null;
    }


}
