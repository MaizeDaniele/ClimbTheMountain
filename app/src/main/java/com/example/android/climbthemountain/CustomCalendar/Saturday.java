package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

import com.example.android.climbthemountain.R;

/**
 * Created by Marco on 12/07/16.
 */
public class Saturday extends BaseWeekDay {

    @Override
    protected void setDayName() {
        tvDayName.setText(R.string.tv_saturday);

    }

    @Override
    protected void setHourOnClick(int hour) {

        weeklyDays.getSaturday().setDayHours(hour);

        if(weeklyDays.getSaturday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(Color.BLUE);
        } else{
            hoursTextViews.get(hour-8).setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(weeklyDays.getSaturday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(Color.BLUE);
            }
        }
    }

    @Override
    protected void movePreviousDay() {
        Intent specificIntent = new Intent(this, Friday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);


    }

    @Override
    protected void moveNextDay() {
        Intent specificIntent = new Intent(this, Sunday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);


    }
}
