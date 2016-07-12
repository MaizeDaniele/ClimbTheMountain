package com.example.android.climbthemountain.CustomCalendar;

import android.content.Intent;
import android.graphics.Color;

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

        weeklyDays.getThursday().setDayHours(hour);

        if(weeklyDays.getThursday().getDayHours(hour)){
            hoursTextViews.get(hour-8).setTextColor(Color.BLUE);
        } else{
            hoursTextViews.get(hour-8).setTextColor(Color.GRAY);
        }
    }

    @Override
    protected void setUserSelectedHours() {

        for (int i = 8; i < 24; i++ ){
            if(weeklyDays.getThursday().getDayHours(i)){
                hoursTextViews.get(i-8).setTextColor(Color.BLUE);
            }
        }

    }

    @Override
    protected void movePreviousDay() {
        Intent specificIntent = new Intent(this, Wednesday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);

    }

    @Override
    protected void moveNextDay() {
        Intent specificIntent = new Intent(this, Friday.class);
        specificIntent.putExtra(BaseWeekDay.key_intent_extra, weeklyDays );
        startActivity(specificIntent);

    }
}
