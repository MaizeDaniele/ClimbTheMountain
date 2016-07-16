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
import android.widget.TextView;

import com.example.android.climbthemountain.CustomCalendar.BaseWeekDay;
import com.example.android.climbthemountain.CustomCalendar.Monday;
import com.example.android.climbthemountain.user_data.UserData;

import java.util.Calendar;

public class RegisterSessionData extends AppCompatActivity{


    // elements
    DatePicker dpSessionStart;
    Button btAddStudyHours;
    Button btAddExams;


    TextView tvErrorHours;
    TextView tvErrorExams;

    //Eventuale intent proveniente da activity
    Intent intent;

    // toolbar
    Toolbar tbRegistration;

    // NOTE: no use to have a variable handling dispatching towards Summary if it's the default action

    UserData userData = new UserData();

    boolean isSummary;
    boolean isFromList;

    // control field for inputErrors
    boolean flag = false;
    boolean fromCalendar = false;
    boolean fromExams = false;

    UserData back_userData;

    @Override
    public void onBackPressed() {

        Intent intent;

        if (isSummary){

            intent = new Intent(this, RegisterSessionSummary.class);

        } else {

            intent = new Intent(getApplicationContext(), RegisterAccountData.class);
        }

        intent.putExtra(Login.USER_OBJ, back_userData);

        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_03);

        intent = getIntent();
        //Recupero i dati dall'intent
        userData = intent.getParcelableExtra(Login.USER_OBJ);
        isFromList = intent.getBooleanExtra(Login.isFROM_LIST, false);
        isSummary = intent.getBooleanExtra(Login.isSUMMARY, false);

        back_userData = userData;

        // element instance
        dpSessionStart = (DatePicker) findViewById(R.id.dpSession_date);
        btAddStudyHours = (Button) findViewById(R.id.btSession_addStudyHours);
        btAddExams = (Button) findViewById(R.id.btSession_addExam);

        // for show errors
        tvErrorExams = (TextView) findViewById(R.id.tvSession_ErrorExams);
        tvErrorHours = (TextView) findViewById(R.id.tvSession_ErrorHours);

        //Inizialzzo i componenti con i dati dell'utente


        setDatePicker();
        fillUserData();

        clearMessages();
        checkOnCreate();



        //Verifico che l'utente abbia aggiunto esami
        if(isFromList || isSummary){
            //L'utente ha già aggiunto almeno un esame, modifico il bottone
            btAddExams.setText("Lista Esami");

            btAddExams.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);

                    //Passo l'utente
                    nextIntent.putExtra(Login.USER_OBJ, userData);

                    startActivity(nextIntent);
                }
            });

        }else{
            btAddExams.setText("Add Exam");

            btAddExams.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExam.class);

                    //Carico l'utente nell'intent
                    nextIntent.putExtra(Login.USER_OBJ, userData);

                    startActivity(nextIntent);

                }
            });
        }


        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        //setSupportActionBar(tbRegistration);

        btAddStudyHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), Monday.class);
                registerDate();
                nextIntent.putExtra(Login.USER_OBJ, userData);

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
        flag = true;

        if(controlExamsMoreThanOne() && controlHoursMoreThanOne()){

            saveSessionData();

        } else if(controlHoursMoreThanOne() && !controlExamsMoreThanOne()){

            setMessageExam();

        } else if(controlExamsMoreThanOne() && !controlHoursMoreThanOne()) {

            setMessageHours();
        }else{

            setMessageExam();
            setMessageHours();
        }
        return super.onOptionsItemSelected(item);
    }




    // method to save user details
    private void saveSessionData(){

        // Dany!! I've deleted the variable you created here, just to let u know :)

        Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionSummary.class);
        registerDate();
        nextIntent.putExtra(Login.USER_OBJ, userData);

        startActivity(nextIntent);
    }


    // method to insert previous values in OnCreate
    private void fillUserData(){
        dpSessionStart.updateDate(userData.getSession_year(),
                userData.getSession_month(), userData.getSession_day());

    }

    private void registerDate(){
        userData.setSession_day(dpSessionStart.getDayOfMonth());
        userData.setSession_month(dpSessionStart.getMonth());
        userData.setSession_year(dpSessionStart.getYear());
    }

    private boolean controlHoursMoreThanOne(){
        return BaseWeekDay.countHours(userData.userSelectedHours) > 0;

    }

    private boolean controlExamsMoreThanOne(){

        return userData.userExams.size() > 0;
    }

    private void setMessageExam(){

        tvErrorExams.setVisibility(View.VISIBLE);
        btAddExams.setBackgroundColor(getResources().getColor(R.color.cyan_200));
        btAddExams.setTextColor(getResources().getColor(R.color.cyan_900));
    }

    private void clearMessageExam(){

        tvErrorHours.setVisibility(View.GONE);
        btAddExams.setBackgroundColor(getResources().getColor(R.color.grey_300));
        btAddExams.setTextColor(getResources().getColor(R.color.grey_800));
    }

    private void setMessageHours(){

        tvErrorHours.setVisibility(View.VISIBLE);
        btAddStudyHours.setBackgroundColor(getResources().getColor(R.color.cyan_200));
        btAddStudyHours.setTextColor(getResources().getColor(R.color.cyan_900));
    }

    private void clearMessageHours(){

        tvErrorHours.setVisibility(View.GONE);
        btAddStudyHours.setBackgroundColor(getResources().getColor(R.color.grey_300));
        btAddStudyHours.setTextColor(getResources().getColor(R.color.grey_800));
    }

    private void clearMessages(){

        tvErrorHours.setVisibility(View.GONE);
        tvErrorExams.setVisibility(View.GONE);
        tvErrorExams.setTextColor(getResources().getColor(R.color.cyan_600));
        tvErrorHours.setTextColor(getResources().getColor(R.color.cyan_600));
        btAddStudyHours.setBackgroundColor(getResources().getColor(R.color.grey_300));
        btAddExams.setBackgroundColor(getResources().getColor(R.color.grey_300));
        btAddStudyHours.setTextColor(getResources().getColor(R.color.grey_800));
        btAddExams.setTextColor(getResources().getColor(R.color.grey_800));


    }

    private void checkOnCreate(){
        if (!controlHoursMoreThanOne() && (flag || fromCalendar)) setMessageHours();
        if (!controlExamsMoreThanOne() && (flag || fromExams)) setMessageExam();
    }

    private void setDatePicker(){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        dpSessionStart.updateDate(mYear,mMonth,mDay);
    }

}