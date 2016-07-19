package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Thursday extends BaseWeekDay {
    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_thursday);

    }

    @Override
    protected void setHourOnClick(int hour) {

        accountData.userSelectedHours.setThursday(hour);

        if(accountData.userSelectedHours.getThursday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.purple_400));
            hoursTextViews.get(hour-8).setTextSize(18);
        } else{
            hoursTextViews.get(hour-8).setTextColor(getResources().getColor(R.color.grey_700));
            hoursTextViews.get(hour-8).setTextSize(16);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(accountData.userSelectedHours.getThursday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(getResources().getColor(R.color.purple_400));
                hoursTextViews.get(i-8).setTextSize(18);
            }
        }

    }

    @Override
    protected void movePreviousDay() {
        Intent specificIntent = new Intent(this, Wednesday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);

    }

    @Override
    protected void moveNextDay() {
        Intent specificIntent = new Intent(this, Friday.class);
        specificIntent.putExtra(Login.USER_OBJ, accountData);
        startActivity(specificIntent);

    }
}
