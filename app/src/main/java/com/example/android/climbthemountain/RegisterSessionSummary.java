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
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterSessionSummary extends AppCompatActivity implements View.OnClickListener{

    // elements
    TextView tvName;
    TextView tvSurname;
    TextView tvUsername;
    TextView tvWeeklyHour;
    TextView tvSessionstart;
    Button btEditAccount;
    Button btEditSession;

    // toolbar
    Toolbar tbRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_session_summary_05);

        // element instance
        tvName = (TextView) findViewById(R.id.tvSessionSum_nameShow);
        tvSurname = (TextView) findViewById(R.id.tvSessionSum_surnameShow);
        tvUsername = (TextView) findViewById(R.id.tvSessionSum_usernameShow);
        tvWeeklyHour = (TextView) findViewById(R.id.tvSessionSum_hoursShow);
        tvSessionstart = (TextView) findViewById(R.id.tvSessionSum_sessionStartShow);

        // buttons
        btEditAccount = (Button) findViewById(R.id.btSessionSum_editUser);
        btEditSession = (Button) findViewById(R.id.btSessionSum_editSession);


        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionSum_toolbar);

        // button registration
        btEditSession.setOnClickListener(this);
        btEditAccount.setOnClickListener(this);


        // little debug

        tvName.setText("Marco");

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
        return super.onOptionsItemSelected(item);
    }

    // manage buttons


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSessionSum_editUser:
                startActivity(new Intent(this, RegisterAccountData.class));

                /* we need a way to return from the account data to this summary.
                * note: if the user press the done button in the app bar, he goes back to the register
                * session page.
                * we need a way to bring him back to this one, changing the way the button acts or
                * adding a new ( previously hidden) button
                 */

                break;

            case R.id.btSessionSum_editSession:
                startActivity(new Intent(this, RegisterSessionData.class));
                break;
        }
    }
}
