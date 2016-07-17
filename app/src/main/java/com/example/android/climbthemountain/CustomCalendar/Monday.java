package com.example.android.climbthemountain.CustomCalendar;


import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage.DailyHours;
import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;
import com.example.android.climbthemountain.RegisterSessionSummary;

/**
 * Created by Marco on 12/07/16.
 */
public class Monday extends BaseWeekDay{


    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_monday);
    }

    @Override
    protected void setHourOnClick(int hour) {

        accountData.userSelectedHours.setMonday(hour);

        if(accountData.userSelectedHours.getMonday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.purple_400));
        } else{
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.grey_700));
        }

    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(accountData.userSelectedHours.getMonday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(getResources().getColor(R.color.purple_400));
            }
        }
    }

    @Override
    protected void movePreviousDay() {

        Intent specificIntent = new Intent(this, Sunday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);



    }

    @Override
    protected void moveNextDay() {

        Intent specificIntent = new Intent(this, Tuesday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);
    }
}
