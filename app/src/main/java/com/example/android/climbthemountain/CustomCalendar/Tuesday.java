package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Tuesday extends BaseWeekDay {




    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_tuesday);
    }

    @Override
    protected void setHourOnClick(int hour) {

        accountData.userSelectedHours.setTuesday(hour);

        if(accountData.userSelectedHours.getTuesday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(Color.BLUE);
        } else{
            hoursTextViews.get(hour-8).setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(accountData.userSelectedHours.getTuesday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(Color.BLUE);
            }
        }
    }

    @Override
    protected void movePreviousDay() {

        Intent specificIntent = new Intent(this, Monday.class);

        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);
    }

    @Override
    protected void moveNextDay() {

        Intent specificIntent = new Intent(this, Wednesday.class);

        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);


    }
}
