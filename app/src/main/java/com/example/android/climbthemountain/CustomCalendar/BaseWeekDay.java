package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage.DailyHours;
import com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage.WeeklyDays;
import com.example.android.climbthemountain.CustomDialogFragment.TutorialDialogFragment;
import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;
import com.example.android.climbthemountain.RegisterSessionData;
import com.example.android.climbthemountain.RegisterSessionExamSelection;
import com.example.android.climbthemountain.RegisterSessionSummary;
import com.example.android.climbthemountain.user_data.UserData;

import java.util.ArrayList;

/**
 * Created by Marco on 11/07/16.
 */


public abstract class BaseWeekDay extends AppCompatActivity {



    // field
    TextView tvEightOClock;
    TextView tvNineOCLock;
    TextView tvTenOCLock;
    TextView tvElevenOClock;
    TextView tvTwelveOClock;
    TextView tvThirteenOClock;
    TextView tvFourteenOClock;
    TextView tvFifteenOClock;
    TextView tvSixteenOClock;
    TextView tvSeventeenOClock;
    TextView tvEighteenOClock;
    TextView tvNineteenOClock;
    TextView tvTwentyOClock;
    TextView tvTwentyOneOClock;
    TextView tvTwentyTwoOClock;
    TextView tvTwentyThreeOClock;
    TextView tvDayName;

    Toolbar tbRegistration;

    Button btGoForward;
    Button btGoBackward;

    ArrayList<TextView> hoursTextViews = new ArrayList<>();


    UserData accountData;
    UserData back_accountData;

    @Override
    public void onBackPressed() {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_day);

        Intent intent = getIntent();





        if(intent != null){
            if (intent.getParcelableExtra(Login.USER_OBJ) != null) {

                // possible javaNullException
                accountData = intent.getParcelableExtra(Login.USER_OBJ);
                back_accountData = accountData;
                if(accountData.getNeedTutorial().equals("si")) {
                    TutorialDialogFragment dialogFragment = TutorialDialogFragment.newInstance();
                    dialogFragment.show(getFragmentManager(), "tutorial");
                }
                accountData.setNeedTutorial("no");

            }


        }
        if(accountData.getNeedTutorial().equals("si")) {
            TutorialDialogFragment dialogFragment = TutorialDialogFragment.newInstance();
            dialogFragment.show(getFragmentManager(), "tutorial");
            accountData.setNeedTutorial("no");
        }

        tvEightOClock = (TextView) findViewById(R.id.eightOClock);
        tvNineOCLock = (TextView) findViewById(R.id.nineOClock);
        tvTenOCLock = (TextView) findViewById(R.id.tenOClock);
        tvElevenOClock = (TextView) findViewById(R.id.elevenOClock);
        tvTwelveOClock = (TextView) findViewById(R.id.twelveOClock);
        tvThirteenOClock = (TextView) findViewById(R.id.thirteenOClock);
        tvFourteenOClock = (TextView) findViewById(R.id.fourteenOClock);
        tvFifteenOClock = (TextView) findViewById(R.id.fifteenOClock);
        tvSixteenOClock = (TextView) findViewById(R.id.sixteenOClock);
        tvSeventeenOClock = (TextView) findViewById(R.id.seventeenOClock);
        tvEighteenOClock = (TextView) findViewById(R.id.eighteenOClock);
        tvNineteenOClock = (TextView) findViewById(R.id.nineteenOClock);
        tvTwentyOClock = (TextView) findViewById(R.id.twentyOClock);
        tvTwentyOneOClock = (TextView) findViewById(R.id.twentyOneOClock);
        tvTwentyTwoOClock = (TextView) findViewById(R.id.twentyTwoOClock);
        tvTwentyThreeOClock = (TextView) findViewById(R.id.twentyThreeOClock);

        tvDayName = (TextView) findViewById(R.id.tv_day_name);

        btGoBackward = (Button) findViewById(R.id.bt_go_backward);
        btGoForward = (Button) findViewById(R.id.bt_go_forward);


        hoursTextViews.add(tvEightOClock);
        hoursTextViews.add(tvNineOCLock);
        hoursTextViews.add(tvTenOCLock);
        hoursTextViews.add(tvElevenOClock);
        hoursTextViews.add(tvTwelveOClock);
        hoursTextViews.add(tvThirteenOClock);
        hoursTextViews.add(tvFourteenOClock);
        hoursTextViews.add(tvFifteenOClock);
        hoursTextViews.add(tvSixteenOClock);
        hoursTextViews.add(tvSeventeenOClock);
        hoursTextViews.add(tvEighteenOClock);
        hoursTextViews.add(tvNineteenOClock);
        hoursTextViews.add(tvTwentyOClock);
        hoursTextViews.add(tvTwentyOneOClock);
        hoursTextViews.add(tvTwentyTwoOClock);
        hoursTextViews.add(tvTwentyThreeOClock);


        setDayName();
        setAllHoursGray();
        setUserSelectedHours();


        tvEightOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(8);
            }
        });

        tvNineOCLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(9);
            }
        });

        tvTenOCLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(10);
            }
        });

        tvElevenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(11);
            }
        });

        tvTwelveOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(12);
            }
        });

        tvThirteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(13);
            }
        });

        tvFourteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(14);
            }
        });

        tvFifteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(15);
            }
        });

        tvSixteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(16);
            }
        });

        tvSeventeenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(17);
            }
        });

        tvEighteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(18);
            }
        });

        tvNineteenOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(19);
            }
        });

        tvTwentyOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(20);
            }
        });

        tvTwentyOneOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(21);
            }
        });

        tvTwentyTwoOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(22);
            }
        });

        tvTwentyThreeOClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHourOnClick(23);
            }
        });


        // buttons Listeners

        btGoForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNextDay();
            }
        });

        btGoBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePreviousDay();
            }
        });



        // toolbar registration
        //tbRegistration = (Toolbar) findViewById(R.id.tbReg_toolbar);
        //setSupportActionBar(tbRegistration);

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
                saveSelectedWeekHours();
                break;

        }

        return super.onOptionsItemSelected(item);

    }


    // method to save user details
    private void saveSelectedWeekHours() {

        // we have to dispatch the next activity
        Intent nextIntent = new Intent(getApplicationContext(), RegisterSessionData.class);
        nextIntent.putExtra(Login.USER_OBJ, accountData);
        

        startActivity(nextIntent);


    }


    // abstract methods to implement
    protected abstract void setDayName();
    protected abstract void setHourOnClick(int hour);
    protected abstract void setUserSelectedHours();
    protected abstract void movePreviousDay();
    protected abstract void moveNextDay();

    // custom method
    protected void setAllHoursGray(){
        for (int i = 0; i < hoursTextViews.size(); i++){

            hoursTextViews.get(i).setTextColor(getResources().getColor(R.color.grey_700));

        }
    }

    // static method to calculate the number of hours chosen
    public static int countHours(WeeklyDays weeklyDays){

        int count = 0;

        for (DailyHours dailyHours : weeklyDays.weekDaysList){
            for( boolean hour : dailyHours.getDayHours()){

                if(hour) count++;

            }
        }

        return count;
    }

    private void backAction(){

        Intent intent = new Intent(this, RegisterSessionData.class);
        intent.putExtra(Login.USER_OBJ, back_accountData);

        startActivity(intent);
    }

}
