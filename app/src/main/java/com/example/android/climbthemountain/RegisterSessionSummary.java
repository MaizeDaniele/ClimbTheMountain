package com.example.android.climbthemountain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.climbthemountain.CustomAdapter.ExamAdapter;
import com.example.android.climbthemountain.CustomAdapter.ExamAdapter1;
import com.example.android.climbthemountain.CustomCalendar.BaseWeekDay;
import com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage.DailyHours;
import com.example.android.climbthemountain.CustomCalendar.Monday;
import com.example.android.climbthemountain.user_data.UserData;

import org.w3c.dom.Text;

public class RegisterSessionSummary extends AppCompatActivity{

    // elements
    TextView tvName;
    TextView tvSurname;
    TextView tvUsername;
    TextView tvWeeklyHour;
    TextView tvSessionstart;
    Button btEditAccount;
    Button btEditSession;
    Button btEditHours;
    Button btEditExamsList;

    ListView listView;
    ExamAdapter1 adattatore1;

    // toolbar
    Toolbar tbRegistration;

    UserData accountData = new UserData();


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
        btEditHours = (Button) findViewById(R.id.btSessionSum_editHours);
        btEditExamsList = (Button) findViewById(R.id.btSessionSum_editListExams);

        // toolbar
        tbRegistration = (Toolbar) findViewById(R.id.tbSessionSum_toolbar);



        Intent intent = getIntent();

        if(intent != null){
            if (intent.getParcelableExtra(Login.USER_OBJ) != null) {
                accountData = intent.getParcelableExtra(Login.USER_OBJ);
            }
        }




        btEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), RegisterAccountData.class);
                nextIntent.putExtra(Login.USER_OBJ, accountData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                startActivity(nextIntent);

            }
        });

        btEditHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), Monday.class);
                nextIntent.putExtra(Login.USER_OBJ, accountData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                startActivity(nextIntent);

            }
        });

        btEditSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionData.class);
                nextIntent.putExtra(Login.USER_OBJ, accountData);
                nextIntent.putExtra(Login.isSUMMARY, true);
                nextIntent.putExtra("codice", "visualizzaLista");
                startActivity(nextIntent);

            }
        });

        btEditExamsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionExamSelection.class);
                nextIntent.putExtra(Login.USER_OBJ, accountData);
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

        // Start to the END :P
        return super.onOptionsItemSelected(item);

    }

    private void fillContent(){

        tvName.setText(accountData.getName());
        tvSurname.setText(accountData.getSurname());
        tvUsername.setText(accountData.getUsername());
        tvSessionstart.setText(String.format("%d %d %d", accountData.getSession_day(),
                accountData.getSession_month(), accountData.getSession_year()));
        tvWeeklyHour.setText(String.valueOf( BaseWeekDay.countHours(accountData.userSelectedHours)));

        listView = (ListView) findViewById(R.id.lvSessionSum_list);
        //Sono nel summary, il tasto modifica delle view si comporta in maniera diversa
        adattatore1 = new ExamAdapter1(accountData, this, true);
        impostaListView(listView, adattatore1);

    }

    private void impostaListView(ListView listView, ExamAdapter1 adattatore){
        int numeroEsami = accountData.userExams.size();
        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = numeroEsami * 450;
        listView.setLayoutParams(p);
        listView.setAdapter(adattatore);
    }


}
