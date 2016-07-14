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

import com.example.android.climbthemountain.CustomAdapter.ExamAdapter;
import com.example.android.climbthemountain.user_data.ExamData;

import java.util.ArrayList;

public class RegisterSessionData extends AppCompatActivity implements View.OnClickListener{


    // elements
    DatePicker dpSessionStart;
    Button btAddStudyHours;


    // toolbar
    Toolbar tbRegistration;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_03);

        // element instance
        dpSessionStart = (DatePicker) findViewById(R.id.dpSession_date);
        btAddStudyHours = (Button) findViewById(R.id.btSession_addStudyHours);







        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbSession_toolbar);
        setSupportActionBar(tbRegistration);

        // button registration

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

        }

    }



    // method to save user details
    private void saveSessionData(){
        Intent intent = new Intent(this, RegisterSessionExamSelection.class);
        intent.putExtra("codice", "continua");
        startActivity(intent);
    }



}



