package com.example.android.climbthemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.climbthemountain.CustomAdapter.ExamAdapter;
import com.example.android.climbthemountain.CustomCalendar.BaseWeekDay;
import com.example.android.climbthemountain.CustomCalendar.Monday;
import com.example.android.climbthemountain.user_data.ExamData;
import com.example.android.climbthemountain.user_data.UserData;

import java.util.ArrayList;

public class RegisterSessionData extends AppCompatActivity{


    // elements
    DatePicker dpSessionStart;
    Button btAddStudyHours;
    Button btAddExams;
    TextView tvAddExams;

    //Eventuale intent proveniente da activity
    Intent intent;

    // toolbar
    Toolbar tbRegistration;

    // NOTE: no use to have a variable handling dispatching towards Summary if it's the default action

    UserData accountData = new UserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_03);

        intent = getIntent();
        //Recupero i dati dall'intent
        accountData = intent.getParcelableExtra(Login.USER_OBJ);
        String codice = intent.getStringExtra("codice");




        // element instance
        dpSessionStart = (DatePicker) findViewById(R.id.dpSession_date);
        btAddStudyHours = (Button) findViewById(R.id.btSession_addStudyHours);
        tvAddExams = (TextView) findViewById(R.id.tvSession_ErrorExams);
        btAddExams = (Button) findViewById(R.id.btSession_addExam);


        //Inizialzzo i componenti con i dati dell'utente
        fillUserData();


        //Verifico che l'utente abbia aggiunto esami
        if(codice.equals("visualizzaLista")){
            //L'utente ha già aggiunto almeno un esame, modifico il bottone
            btAddExams.setText("Lista Esami");
            tvAddExams.setVisibility(View.GONE);

            btAddExams.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);

                    //Passo l'utente
                    nextIntent.putExtra(Login.USER_OBJ, accountData);

                    startActivity(nextIntent);
                }
            });

        }else{
            btAddExams.setText("Add Exam");
            tvAddExams.setVisibility(View.VISIBLE);

            btAddExams.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExam.class);

                    //Carico l'utente nell'intent
                    nextIntent.putExtra(Login.USER_OBJ, accountData);

                    startActivity(nextIntent);

                }
            });
        }


        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        setSupportActionBar(tbRegistration);

        btAddStudyHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), Monday.class);
                registerDate();
                nextIntent.putExtra(Login.USER_OBJ, accountData);

                startActivity(nextIntent);

            }
        });

    }

    // inflate the menu for App Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        registerDate();
        inflater.inflate(R.menu.register_account_data, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // manage menu's items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        saveSessionData();
        return super.onOptionsItemSelected(item);
    }




    // method to save user details
    private void saveSessionData(){

        // Dany!! I've deleted the variable you created here, just to let u know :)

        Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionSummary.class);
        registerDate();
        nextIntent.putExtra(Login.USER_OBJ, accountData);

        startActivity(nextIntent);
    }


    // method to insert previous values in OnCreate
    private void fillUserData(){
        dpSessionStart.updateDate(accountData.getSession_year(),
                accountData.getSession_month(), accountData.getSession_day());

    }

    private void registerDate(){
        accountData.setSession_day(dpSessionStart.getDayOfMonth());
        accountData.setSession_month(dpSessionStart.getMonth());
        accountData.setSession_year(dpSessionStart.getYear());
    }


}



