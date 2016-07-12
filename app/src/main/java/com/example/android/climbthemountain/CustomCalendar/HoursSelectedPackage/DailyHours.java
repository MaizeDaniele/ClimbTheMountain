package com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marco on 09/07/16.
 */
public class DailyHours implements Parcelable{

// Array of boolean values.
// MAP each hour with an element of the array.
//
// 8:00 dayHours[8]
// 9:00 dayHours[9]
// 10:00 dayHours[10]
// 11:00 dayHours[11]
// 12:00 dayHours[12]
// 13:00 dayHours[13]
// 14:00 dayHours[14]
// 15:00 dayHours[15]
// 16:00 dayHours[16]
// 17:00 dayHours[17]
// 18:00 dayHours[18]
// 19:00 dayHours[19]
// 20:00 dayHours[20]
// 21:00 dayHours[21]
// 22:00 dayHours[22]
// 23:00 dayHours[23]


    public static final Creator<DailyHours> CREATOR = new Creator<DailyHours>() {
        @Override
        public DailyHours createFromParcel(Parcel in) {
            return new DailyHours(in);
        }

        @Override
        public DailyHours[] newArray(int size) {
            return new DailyHours[size];
        }
    };
    private boolean[] dayHours= new boolean[24];

    public DailyHours(){
        for (int i=0; i < dayHours.length; i++){
            this.dayHours[i] = false;
        }
    }

    protected DailyHours(Parcel in) {
        dayHours = in.createBooleanArray();
    }

    public boolean[] getDayHours() {
        return this.dayHours;
    }

    public void setDayHours(int hour){
        this.dayHours[hour] = !this.dayHours[hour];
    }

    public void setDayHours(boolean[] dayHours) {
        this.dayHours = dayHours;
    }

    public boolean getDayHours(int hour){
        return this.dayHours[hour];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBooleanArray(dayHours);
    }
}
