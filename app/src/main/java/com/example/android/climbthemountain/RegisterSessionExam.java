package com.example.android.climbthemountain;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RegisterSessionExam extends AppCompatActivity {


    // elements
    EditText etExamName;
    EditText etCFU;
    DatePicker dpExamDate;
    Button btColor;

    // toolbar
    Toolbar tbRegistration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_exam_04);

        // element instance
        etExamName = (EditText) findViewById(R.id.etSessionExam_examName);
        etCFU = (EditText) findViewById(R.id.etSessionExam_cfu);
        dpExamDate = (DatePicker) findViewById(R.id.dpSessionExam_deadline);
        btColor = (Button) findViewById(R.id.btSessionExam_colorPck);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionExam_toolbar);
        setSupportActionBar(tbRegistration);



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
        startActivity(new Intent(getApplicationContext(), RegisterSessionData.class));
    }


}
