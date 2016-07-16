package com.example.android.climbthemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    static final public String USER_OBJ= "com.example.Intent.USER";
    static final public String isSUMMARY = "com.example.Intent.SUMMARY_VALUE";
    static final public String isFROM_LIST = "com.example.Intent.isFROM_LIST";
    static final public String getIsFROM_AD = "com.example.Intent.isFROM_AD";

    // elements
    Button btLogin;
    EditText etUsername;
    EditText etPassword;
    TextView tvNewUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_register_01);



        // elements instance
        btLogin = (Button) findViewById(R.id.btLogin_login);
        etUsername = (EditText) findViewById(R.id.etLogin_username);
        etPassword = (EditText) findViewById(R.id.etLogin_password);
        tvNewUser = (TextView) findViewById(R.id.tvLogin_newUser);



        // login button
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               authentication();
            }
        });

        // new user
        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterAccountData.class));
            }
        });


    }

    // authentication method to verify credential
    private void authentication() {

    }
}
