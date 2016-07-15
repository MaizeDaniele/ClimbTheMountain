package com.example.android.climbthemountain.CustomCalendar;


import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Friday extends BaseWeekDay {
    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_friday);

    }

    @Override
    protected void setHourOnClick(int hour) {

        accountData.userSelectedHours.setFriday(hour);

        if(accountData.userSelectedHours.getFriday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(Color.BLUE);
        } else{
            hoursTextViews.get(hour-8).setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(accountData.userSelectedHours.getFriday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(Color.BLUE);
            }
        }

    }

    @Override
    protected void movePreviousDay() {
        Intent specificIntent = new Intent(this, Thursday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);

    }

    @Override
    protected void moveNextDay() {
        Intent specificIntent = new Intent(this, Saturday.class);

        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);

    }
}
