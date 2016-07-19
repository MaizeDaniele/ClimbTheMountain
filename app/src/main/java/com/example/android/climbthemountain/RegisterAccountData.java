package com.example.android.climbthemountain;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.climbthemountain.user_data.UserData;

import java.util.ArrayList;

public class RegisterAccountData extends AppCompatActivity {

    int flagButton01 = 0;
    int flagButton02 = 0;

    // elements
    EditText etUsername;
    EditText etPassword;
    EditText etPasswordRepeat;
    EditText etName;
    EditText etSurname;

    TextView tvUsername;
    TextView tvPassword;
    TextView tvPasswordRepeat;
    TextView tvName;
    TextView tvSurname;

    ImageButton btShowPass01;
    ImageButton btShowPass02;

    ImageView imWarn1;
    ImageView imWarn2;

    UserData accountData = new UserData();

    ArrayList<EditText> listEt = new ArrayList<>();
    ArrayList<TextView> listTv = new ArrayList<>();

    // toolbar
    Toolbar tbRegistration;

    // variable to know where dispatch an intent to next Activity
    boolean isSummary;


    boolean flag = false;
    boolean match1 = false;
    boolean match2 = false;


    @Override
    public void onBackPressed() {
        backAction();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_account_02);


        // elements instance
        etName = (EditText) findViewById(R.id.etReg_name);
        etSurname = (EditText) findViewById(R.id.etReg_surname);
        etUsername = (EditText) findViewById(R.id.etReg_username);
        etPassword = (EditText) findViewById(R.id.etReg_pass);
        etPasswordRepeat = (EditText) findViewById(R.id.etReg_passRep);

        tvName = (TextView) findViewById(R.id.tvReg_name);
        tvSurname = (TextView) findViewById(R.id.tvReg_surname);
        tvPassword = (TextView) findViewById(R.id.tvReg_pass);
        tvUsername = (TextView) findViewById(R.id.tvReg_username);
        tvPasswordRepeat = (TextView) findViewById(R.id.tvReg_passRep);

        imWarn1 = (ImageView) findViewById(R.id.image_warningPass01);
        imWarn2 = (ImageView) findViewById(R.id.image_warningPass02);

        btShowPass01 = (ImageButton) findViewById(R.id.showPass01);
        btShowPass02 = (ImageButton) findViewById(R.id.showPass02);



        btShowPass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flagButton01 == 0) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    flagButton01 = 1;
                }else{
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    flagButton01 = 0;
                }
            }
        });

        btShowPass02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flagButton02 == 0) {
                    etPasswordRepeat.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    flagButton02 = 1;
                }else{
                    etPasswordRepeat.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    flagButton02 = 0;
                }
            }
        });


        listEt.add(etName);
        listEt.add(etSurname);
        listEt.add(etUsername);
        listEt.add(etPassword);
        listEt.add(etPasswordRepeat);

        listTv.add(tvName); // get(0)
        listTv.add(tvSurname); // get(1)
        listTv.add(tvUsername); // get(2)
        listTv.add(tvPassword); // get(3)
        listTv.add(tvPasswordRepeat); // get(4)




        hintSetOnCreate();

        // check if a previous instance of UserData was created and passed with intent
        Intent intent = getIntent();

        if(intent != null){
            if (intent.getParcelableExtra(Login.USER_OBJ) != null) {
                accountData = intent.getParcelableExtra(Login.USER_OBJ);

                // control variable
                isSummary = intent.getBooleanExtra(Login.isSUMMARY,false);
                fillUserData();

            }
        }


        for(final EditText editText : listEt){

            int i = 0;

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkUserDetailMessage();
                    realTimePasswordMatching();
                    passwordEmptyMessage();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }


        for (int i = 0; i < 5; i++){


            listEt.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if(hasFocus) listTv.get(listEt.indexOf(v)).setTextColor(getResources().getColor(R.color.purple_400));
                    else listTv.get(listEt.indexOf(v)).setTextColor(getResources().getColor(R.color.grey_900));
                }
            });
        }


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

        flag = true;



        switch (item.getItemId()){

            case (R.id.btBack):
                backAction();

                break;

            case (R.id.btConfirm):


                if(anyFieldEmpty()) {

                    View v = getCurrentFocus();
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    checkUserDetailMessage();
                    passwordUserMessage();
                }
                else {

                    if(passwordMatching() && !anyFieldEmpty()) saveRegisterData(isSummary);
                    else{

                        View v = getCurrentFocus();
                        if (v != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }

                        checkUserDetailMessage();
                        passwordUserMessage();

                    }
                }


                break;

        }





        return super.onOptionsItemSelected(item);
    }


    // method to save user details
    private void saveRegisterData(boolean toSummary){

        // CONTROL MUST BE APPLIED
        accountData.setName(listEt.get(0).getText().toString());
        accountData.setSurname(listEt.get(1).getText().toString());
        accountData.setUsername(listEt.get(2).getText().toString());
        accountData.setPassword(listEt.get(3).getText().toString());
        accountData.setNeedTutorial("si");

        Intent intent;

        if(toSummary){
            intent= new Intent(getApplicationContext(), RegisterSessionSummary.class);
            intent.putExtra(Login.USER_OBJ, accountData);
            intent.putExtra(Login.getIsFROM_AD, true);
            startActivity(intent);


        } else {
            intent= new Intent(getApplicationContext(), RegisterSessionData.class);
            intent.putExtra(Login.USER_OBJ, accountData);
            intent.putExtra("codice", ""); // non toccare
            startActivity(intent);

        }


    }

    // method to insert previous values in OnCreate
    private void fillUserData(){

        listEt.get(0).setText(accountData.getName());
        listEt.get(1).setText(accountData.getUsername());
        listEt.get(2).setText(accountData.getSurname());
        listEt.get(3).setText(accountData.getPassword());
        // it's supposed was previous checked if it matches
        listEt.get(4).setText(accountData.getPassword());

    }


    private boolean passwordMatching(){

        if(listEt.get(3).getText().toString().equals("") && listEt.get(4).getText().toString().equals("")){
            return false;
        } else{

            return listEt.get(3).getText().toString().equals(listEt.get(4).getText().toString());
        }


    }

    private boolean[] controllerFieldEmpty(){

        boolean[] controller = new boolean[5];

        controller[0] = !listEt.get(0).getText().toString().equals("");
        controller[1] = !listEt.get(1).getText().toString().equals("");
        controller[2] = !listEt.get(2).getText().toString().equals("");
        controller[3] = !listEt.get(3).getText().toString().equals("");
        controller[4] = !listEt.get(4).getText().toString().equals("");

        return controller;
    }

    private boolean anyFieldEmpty(){

        boolean temp = true;

        for (boolean bool : controllerFieldEmpty()){
            temp = bool && temp;
        }

        return !temp;
    }

    private void passwordUserMessage() {



        if(passwordMatching() && !passwordBothEmpty() && !flag ){

            listEt.get(3).setHint(R.string.hint_general_password);
            listEt.get(4).setHint(R.string.hint_general_password_rep);

        } else if(passwordBothEmpty() && flag){

            listEt.get(3).setHint(R.string.hint_error_password);
            listEt.get(4).setHint(R.string.hint_error_password_rep_match);


        } else if(!passwordMatching()){

            match1 = true;
            match2 = true;

            listEt.get(3).setText("");
            listEt.get(4).setText("");

            listEt.get(3).setHint(R.string.hint_error_password_rep);
            listEt.get(4).setHint(R.string.hint_error_password_rep);

        } else {

            listEt.get(3).setHint(R.string.hint_general_password);
            listEt.get(4).setHint(R.string.hint_general_password_rep);
        }

    }

    private void passwordEmptyMessage(){


        if(match1 || match2){

            if (!listEt.get(3).getText().toString().equals("") || !listEt.get(4).getText().toString().equals("")){

                if (!listEt.get(3).getText().toString().equals("")){
                    listEt.get(3).setHint(R.string.hint_error_password);
                    match1 = false;
                }

                if(!listEt.get(4).getText().toString().equals("")){
                    listEt.get(4).setHint(R.string.hint_error_password_rep_match);
                    match2 = false;
                }

            }
        }

    }

    public boolean passwordBothEmpty(){

        if(listEt.get(3).getText().toString().equals("") && listEt.get(4).getText().toString().equals("")) return  true;
        else return false;

    }

    private void checkUserDetailMessage(){

        for(int i = 0; i < 5; i++){

            if(!controllerFieldEmpty()[i] && flag){

                listTv.get(i).setTextColor(getResources().getColor(R.color.grey_800));
                listEt.get(i).setHintTextColor(getResources().getColor(R.color.orange_700));

                switch (i) {
                    case 0:
                        listEt.get(i).setHint(R.string.hint_error_name);
                        break;
                    case 1:
                        listEt.get(i).setHint(R.string.hint_error_surname);
                        break;
                    case 2:
                        listEt.get(i).setHint(R.string.hint_error_username);
                        break;

                }

            } else {

                //listTv.get(i).setTextColor(getResources().getColor(R.color.grey_900));
                listEt.get(i).setHintTextColor(getResources().getColor(R.color.grey_600));

                switch (i) {
                    case 0:
                        listEt.get(i).setHint(R.string.hint_error_name);
                        break;
                    case 1:
                        listEt.get(i).setHint(R.string.hint_general_surname);
                        break;
                    case 2:
                        listEt.get(i).setHint(R.string.hint_general_username);
                        break;

                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void realTimePasswordMatching(){

        if (listEt.get(3).getText().toString().equals("") || listEt.get(4).toString().equals("")) {

            imWarn1.setVisibility(View.GONE);
            imWarn2.setVisibility(View.GONE);


            /*if(flag){
                listTv.get(3).setTextColor(getResources().getColor(R.color.grey_900));
                listTv.get(4).setTextColor(getResources().getColor(R.color.orange_700));

            } else {
                listTv.get(3).setTextColor(getResources().getColor(R.color.grey_900));
                listTv.get(4).setTextColor(getResources().getColor(R.color.grey_900));
            }*/

        } else if (passwordMatching()) {

            // INSERT HERE FOR PASSWORD MATCHING CONTROL IMAGE

            //imWarn1.setVisibility(View.VISIBLE);
            //imWarn1.setImageDrawable(getDrawable(R.drawable.vector_drawable_ic_done_green___px));

            imWarn2.setVisibility(View.VISIBLE);
            imWarn2.setImageDrawable(getDrawable(R.drawable.vector_drawable_ic_done_green___px));



            /*listTv.get(3).setTextColor(getResources().getColor(R.color.green_600));
            listTv.get(4).setTextColor(getResources().getColor(R.color.green_600));*/

        } else {

            if(!etPasswordRepeat.getText().toString().equals("")){

                //imWarn1.setVisibility(View.VISIBLE);
                //imWarn1.setImageDrawable(getDrawable(R.drawable.vector_drawable_ic_warning_black___px));

                imWarn2.setVisibility(View.VISIBLE);
                imWarn2.setImageDrawable(getDrawable(R.drawable.vector_drawable_ic_warning_black___px));
            }



            /*listTv.get(3).setTextColor(getResources().getColor(R.color.red_600));
            listTv.get(4).setTextColor(getResources().getColor(R.color.red_600));*/

        }
    }

    private  void hintSetOnCreate(){

        for(int i = 0; i < 5; i++) {

            listTv.get(i).setTextColor(getResources().getColor(R.color.grey_900));
            listEt.get(i).setHintTextColor(getResources().getColor(R.color.grey_600));

            imWarn1.setVisibility(View.GONE);
            imWarn2.setVisibility(View.GONE);

            switch (i) {
                case 0:
                    listEt.get(i).setHint(R.string.hint_error_name);
                    break;
                case 1:
                    listEt.get(i).setHint(R.string.hint_general_surname);
                    break;
                case 2:
                    listEt.get(i).setHint(R.string.hint_general_username);
                    break;
                case 3:
                    listEt.get(i).setHint(R.string.hint_general_password);
                    break;
                case 4:
                    listEt.get(i).setHint(R.string.hint_general_password_rep);
                    break;
            }
        }
    }

    private void backAction(){



        Intent intent;

        if (isSummary){

            intent = new Intent(this, RegisterSessionSummary.class);
            intent.putExtra(Login.USER_OBJ, accountData);

        } else {

            intent = new Intent(this, Login.class);
        }

        startActivity(intent);
    }

}