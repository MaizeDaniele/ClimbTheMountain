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
import com.example.android.climbthemountain.CustomAdapter.ExamAdapter1;
import com.example.android.climbthemountain.user_data.ExamData;
import com.example.android.climbthemountain.user_data.UserData;

import java.util.ArrayList;

public class RegisterSessionExamSelection extends AppCompatActivity {

    Button bt_addExam;

    ListView lv_ExamsList;


    // toolbar
    Toolbar tbRegistration;


    //Adattatore per la visualizzazione della listView
    ExamAdapter adattatore;


    Intent intent;
    UserData userData = new UserData();
    boolean isSummary;


    UserData back_userData;

    @Override
    public void onBackPressed() {
        backAction();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_selection_07);

        //Per prima cosa recupero l'intent e l'user
        intent = getIntent();
        userData = intent.getParcelableExtra(Login.USER_OBJ);
        isSummary = intent.getBooleanExtra(Login.isSUMMARY, false);

        lv_ExamsList = (ListView) findViewById(R.id.lvSession_list);


        back_userData = userData;

        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        //setSupportActionBar(tbRegistration);

        bt_addExam = (Button) findViewById(R.id.btSession_addExam);
        bt_addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Passa all'activity per l'aggiunta esame
                Intent intent = new Intent(v.getContext(), RegisterSessionExam.class);

                intent.putExtra(Login.isFROM_LIST, true);
                //Passo anche l'utente così posso aggiungere direttamente un esame
                intent.putExtra(Login.USER_OBJ, userData);
                if(isSummary){
                    intent.putExtra(Login.isSUMMARY, isSummary);
                }

                startActivity(intent);
            }
        });

        //INIZIALIZZAZIONE LISTVIEW

        //Printo la listView, se la lista esami è vuota non verrà visualizzato nulla
        //in questo caso non sono nel riepilogo

        adattatore = new ExamAdapter(userData, this, isSummary);
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

    // need to handle content returning to display inserted exams in the list view
    private void saveExamData() {

        final Intent intent;

        //l'utente ha confermato
        if(isSummary) {
            intent = new Intent(this, RegisterSessionSummary.class);

        }
        else {
            intent = new Intent(this, RegisterSessionData.class);
        }

        intent.putExtra(Login.isFROM_LIST, true);
        intent.putExtra(Login.USER_OBJ, userData);
        startActivity(intent);

    }

    private void backAction(){


        Intent intent;

        if (isSummary){
            intent = new Intent(this, RegisterSessionSummary.class);

        }else {
            intent = new Intent(this, RegisterSessionData.class);
        }

        intent.putExtra(Login.USER_OBJ, userData);
        intent.putExtra(Login.isFROM_LIST, true);
        startActivity(intent);

    }


}