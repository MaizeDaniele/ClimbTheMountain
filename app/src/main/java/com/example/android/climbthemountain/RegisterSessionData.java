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
import android.widget.ListView;

public class RegisterSessionData extends AppCompatActivity implements View.OnClickListener{


    // elements
    DatePicker dpSessionStart;
    Button btAddStudyHours;
    Button btAddExam;
    ListView lvExams;

    // toolbar
    Toolbar tbRegistration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_03);

        // element instance
        dpSessionStart = (DatePicker) findViewById(R.id.dpSession_date);
        btAddStudyHours = (Button) findViewById(R.id.btSession_addStudyHours);
        btAddExam = (Button) findViewById(R.id.btSession_addExam);
        lvExams = (ListView) findViewById(R.id.lvSession_list);


        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        setSupportActionBar(tbRegistration);

        // button registration
        btAddExam.setOnClickListener(this);
        btAddStudyHours.setOnClickListener(this);

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
        saveSessionData();
        return super.onOptionsItemSelected(item);
    }

    // manage adding new exam
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btSession_addExam:
                startActivity(new Intent(this, RegisterSessionExam.class));
                return;
        }

    }


    // method to save user details
    private void saveSessionData(){
        startActivity(new Intent(getApplicationContext(), RegisterSessionSummary.class));
    }
}
