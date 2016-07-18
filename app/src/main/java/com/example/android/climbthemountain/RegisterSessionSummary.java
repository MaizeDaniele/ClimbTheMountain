package com.example.android.climbthemountain;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.climbthemountain.CustomAdapter.ExamAdapter1;
import com.example.android.climbthemountain.CustomCalendar.BaseWeekDay;
import com.example.android.climbthemountain.CustomCalendar.Monday;
import com.example.android.climbthemountain.user_data.UserData;

public class RegisterSessionSummary extends AppCompatActivity{

    // elements
    TextView tvName;
    TextView tvSurname;
    TextView tvUsername;
    TextView tvWeeklyHour;
    TextView tvSessionstart;
    ImageButton btEditAccount;
    ImageButton btEditSession;

    ImageButton btEditExamsList;
    ActionBar actionBar;

    ListView listView;
    ExamAdapter1 adattatore1;

    // toolbar


    UserData userData = new UserData();

    boolean isFromList;
    boolean isFromAccount;


    @Override
    public void onBackPressed() {
        backAction();
    }

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
        btEditAccount = (ImageButton) findViewById(R.id.btSessionSum_editUser);
        btEditSession = (ImageButton) findViewById(R.id.btSessionSum_editSession);

        btEditExamsList = (ImageButton) findViewById(R.id.btSessionSum_editListExams);


        Intent intent = getIntent();

        if(intent != null){
            if (intent.getParcelableExtra(Login.USER_OBJ) != null) {
                userData = intent.getParcelableExtra(Login.USER_OBJ);
                isFromList = intent.getBooleanExtra(Login.isFROM_LIST, false);
            }
        }




        btEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), RegisterAccountData.class);
                nextIntent.putExtra(Login.USER_OBJ, userData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                startActivity(nextIntent);

            }
        });



        btEditSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionData.class);
                nextIntent.putExtra(Login.USER_OBJ, userData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                nextIntent.putExtra("codice", "visualizzaLista");
                startActivity(nextIntent);

            }
        });

        btEditExamsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
                nextIntent.putExtra(Login.USER_OBJ, userData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                startActivity(nextIntent);
            }
        });


        fillContent();


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

                break;

        }



        // Start to the END :P
        return super.onOptionsItemSelected(item);

    }

    private void fillContent(){

        tvName.setText(userData.getName());
        tvSurname.setText(userData.getSurname());
        tvUsername.setText(userData.getUsername());
        tvSessionstart.setText(String.format("%d %d %d", userData.getSession_day(),
                userData.getSession_month(), userData.getSession_year()));
        tvWeeklyHour.setText(String.valueOf( BaseWeekDay.countHours(userData.userSelectedHours)));

        listView = (ListView) findViewById(R.id.lvSessionSum_list);
        //Sono nel summary, il tasto modifica delle view si comporta in maniera diversa
        adattatore1 = new ExamAdapter1(userData, this, true);
        impostaListView(listView, adattatore1);

    }

    private void impostaListView(ListView listView, ExamAdapter1 adattatore){
        int numeroEsami = userData.userExams.size();
        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = numeroEsami * 680;
        listView.setLayoutParams(p);
        listView.setAdapter(adattatore);
    }


    private void backAction(){


        Intent intent;

        if (isFromList){

            intent = new Intent(this, RegisterSessionExamSelection.class);
        } else if (isFromAccount){

            intent = new Intent(this, RegisterAccountData.class);
        } else {

            intent = new Intent(this, RegisterSessionData.class);
        }

        intent.putExtra(Login.USER_OBJ, userData);
        intent.putExtra(Login.isSUMMARY, true);
        startActivity(intent);

    }


}
