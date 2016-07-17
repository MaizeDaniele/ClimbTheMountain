package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Sunday extends BaseWeekDay {

    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_sunday);

    }

    @Override
    protected void setHourOnClick(int hour) {

        accountData.userSelectedHours.setSunday(hour);

        if(accountData.userSelectedHours.getSunday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.purple_400));
        } else{
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.grey_700));
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(accountData.userSelectedHours.getSunday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(getResources().getColor(R.color.purple_400));

            }
        }
    }

    @Override
    protected void movePreviousDay() {
        Intent specificIntent = new Intent(this, Saturday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);


    }

    @Override
    protected void moveNextDay() {
        Intent specificIntent = new Intent(this, Monday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);


    }
}
