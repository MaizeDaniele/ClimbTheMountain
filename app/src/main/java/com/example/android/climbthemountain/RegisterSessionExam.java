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

import java.util.Calendar;
import java.util.Objects;

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


    TextView tvCFU;
    TextView tvExamName;
    TextView tvColorError;

    //DialogFragment
    ColorPickerDialogFragment dialogFragment;
    UserData userData;
    Intent intent;

    // control for user input
    boolean flag = false;
    boolean control = false;

    // variable to know where dispatch an intent to next Activity
    boolean isSummary;

    UserData back_userData;
    boolean back_fromList;

    @Override
    public void onBackPressed() {
        backAction();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_04);

        //Recupero l'utente
        intent = getIntent();
        userData = intent.getParcelableExtra(Login.USER_OBJ);
        isSummary = intent.getBooleanExtra(Login.isSUMMARY, false);

        back_fromList = intent.getBooleanExtra(Login.isFROM_LIST, false);

        // BackUP for button press

        back_userData = userData;


        // element instance
        etExamName = (EditText) findViewById(R.id.etSessionExam_examName);
        etCFU = (EditText) findViewById(R.id.etSessionExam_cfu);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline);
        btColor = (Button) findViewById(R.id.btSessionExam_colorPck);

        // toolbar
        //tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar);
        //setSupportActionBar(tbRegistration);

        // adding references
        tvCFU = (TextView) findViewById(R.id.tvSessionExam_cfu);
        tvExamName = (TextView) findViewById(R.id.tvSessionExam_examName);
        tvColorError = (TextView) findViewById(R.id.tvSessionExam_errorColor);


        btColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = ColorPickerDialogFragment.newInstance();
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });


        etCFU.addTextChangedListener(new TextWatcher() {
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


        etExamName.addTextChangedListener(new TextWatcher() {
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

        etCFU.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) tvCFU.setTextColor(getResources().getColor(R.color.purple_400));
                else tvCFU.setTextColor(getResources().getColor(R.color.grey_900));
            }
        });

        etExamName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) tvExamName.setTextColor(getResources().getColor(R.color.purple_400));
                else tvExamName.setTextColor(getResources().getColor(R.color.grey_900));
            }

        });


        hintSetOnCreate();
        setDatePicker();
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


        switch (item.getItemId()){

            case (R.id.btBack):
                backAction();

                break;

            case (R.id.btConfirm):

                saveExamData();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //Implemento i metodi dell'interfaccia
    @Override
    public void onPositiveButtonClick(String colore){
        //Recupera il colore e lo aggiunge all'esame

        if(!Objects.equals(colore, "#000000")) this.colore = colore;
        TextView txt = (TextView) findViewById(R.id.tvSessionExam_colorSquare);
        if(this.colore == null){
            //txt.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            txt.setBackgroundColor(Color.parseColor(this.colore));
        }

        //setMessageColor();

    }


    // need to handle content returning to display inserted exams in the list view
    private void saveExamData() {

        flag = true;
        setMessageColor();

        if (isCFUaValidNumb() && !anyFieldEmpty() && colorIsChosen()){

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
            /*else {
                esame.setColore("#000000");
            }*/

            //Ho recuperato i dati dell'esame, lo passo all'activity 03
            Intent intent = new Intent(this, RegisterSessionExamSelection.class);

            //Aggiorno elenco esami
            userData.userExams.add(esame);

            //Passo l'elenco esami all'intent
            intent.putExtra(Login.USER_OBJ, userData);
            intent.putExtra(Login.isSUMMARY, isSummary);

            startActivity(intent);
        } else if( !etCFU.getText().toString().equals("") && !isCFUaValidNumb()){
            setMessageColor();
            controlValidCFU();

        } else if(anyFieldEmpty()){

            checkUserDetailMessage();
        }

    }

    private void hintSetOnCreate() {

        etExamName.setHint(R.string.hint_general_exam_name);
        etCFU.setHint(R.string.hint_general_exam_cfu);

        etExamName.setHintTextColor(getResources().getColor(R.color.grey_600));
        etCFU.setHintTextColor(getResources().getColor(R.color.grey_600));

        tvExamName.setHintTextColor(getResources().getColor(R.color.grey_900));
        tvCFU.setHintTextColor(getResources().getColor(R.color.grey_900));

        tvColorError.setVisibility(View.GONE);
        tvColorError.setTextColor(getResources().getColor(R.color.orange_700));

        btColor.setBackgroundColor(getResources().getColor(R.color.grey_300));
        btColor.setTextColor(getResources().getColor(R.color.grey_800));
    }


    private boolean isCFUaValidNumb() {

        try {

            if (!etCFU.getText().toString().equals("")) {
                int dummy = Integer.parseInt(etCFU.getText().toString());
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

        controller[0] = etExamName.getText().toString().equals("");
        controller[1] = etCFU.getText().toString().equals("");

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

            //tvExamName.setTextColor(getResources().getColor(R.color.grey_900));
            etExamName.setHintTextColor(getResources().getColor(R.color.orange_700));

            etExamName.setHint(R.string.hint_error_examName_noName);

        } else {

            //tvExamName.setTextColor(getResources().getColor(R.color.grey_900));


        }

        if (controllerFieldEmpty()[1] && flag) {

            if (!control) {

//                tvCFU.setTextColor(getResources().getColor(R.color.grey_900));
                etCFU.setHintTextColor(getResources().getColor(R.color.orange_700));

                etCFU.setHint(R.string.hint_error_examCfu_noCfu);

            } else {
//
//                tvCFU.setTextColor(getResources().getColor(R.color.grey_900));
                etCFU.setHintTextColor(getResources().getColor(R.color.red_600));
            }

        } else {

//            tvCFU.setTextColor(getResources().getColor(R.color.grey_900));

        }


        if (control && !etCFU.getText().toString().equals("")) {

            control = false;

        }

    }

    private void controlValidCFU() {

        if (!isCFUaValidNumb() && !etCFU.getText().toString().equals("")) {

            control = true;

//            tvCFU.setTextColor(getResources().getColor(R.color.grey_900));
            etCFU.setHintTextColor(getResources().getColor(R.color.red_600));

            etCFU.setHint(R.string.hint_error_examCfu_noChar);

            etCFU.setText("");
        }
    }



    private void setMessageColor(){

        if (colorIsChosen() ) tvColorError.setVisibility(View.GONE);
        else if (flag && !colorIsChosen()){

            tvColorError.setVisibility(View.VISIBLE);
            btColor.setBackgroundColor(getResources().getColor(R.color.orange_300));
            btColor.setTextColor(getResources().getColor(R.color.white));
        }
        else{

            tvColorError.setVisibility(View.GONE);
            btColor.setBackgroundColor(getResources().getColor(R.color.grey_300));
            btColor.setTextColor(getResources().getColor(R.color.grey_800));
        }
    }

    private boolean colorIsChosen(){

        return this.colore != null;
    }

    private void setDatePicker(){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        dpExamDate.updateDate(mYear,mMonth,mDay);
    }

    private void backAction(){

        Intent intent;

        if(back_fromList){

            intent = new Intent(this, RegisterSessionExamSelection.class);
        } else {

            // like if the user wants to cancel the action to insert a new exam
            intent = new Intent(this, RegisterSessionData.class);
        }


        intent.putExtra(Login.USER_OBJ, back_userData);
        intent.putExtra(Login.isSUMMARY, isSummary);
        startActivity(intent);
    }


}
