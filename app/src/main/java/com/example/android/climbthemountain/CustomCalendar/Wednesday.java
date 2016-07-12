package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Wednesday extends BaseWeekDay{

    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_wednesday);

    }

    @Override
    protected void setHourOnClick(int hour) {

        weeklyDays.getWednesday().setDayHours(hour);

        if(weeklyDays.getWednesday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(Color.BLUE);
        } else{
            hoursTextViews.get(hour-8).setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(weeklyDays.getWednesday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(Color.BLUE);
            }
        }
    }

    @Override
    protected void movePreviousDay() {

        Intent specificIntent = new Intent(this, Tuesday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);
    }

    @Override
    protected void moveNextDay() {

        Intent specificIntent = new Intent(this, Thursday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);
    }
}
