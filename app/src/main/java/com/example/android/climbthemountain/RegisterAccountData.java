package com.example.android.climbthemountain;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class RegisterAccountData extends AppCompatActivity {

    // elements
    EditText etUsername;
    EditText etPassword;
    EditText etPasswordRepeat;
    EditText etName;
    EditText etSurname;



    // toolbar
    Toolbar tbRegistration;

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

        // toolbar registration
        tbRegistration = (Toolbar) findViewById(R.id.tbReg_toolbar);
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
        saveRegisterData();
        return super.onOptionsItemSelected(item);
    }



    // method to save user details
    private void saveRegisterData(){
        startActivity(new Intent(getApplicationContext(), RegisterSessionData.class));
    }


}

