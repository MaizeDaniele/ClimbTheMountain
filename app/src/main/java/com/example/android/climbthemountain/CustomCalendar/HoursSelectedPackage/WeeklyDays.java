package com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Marco on 11/07/16.
 */
public class WeeklyDays implements Parcelable{

    public static final Creator<WeeklyDays> CREATOR = new Creator<WeeklyDays>() {
        @Override
        public WeeklyDays createFromParcel(Parcel in) {
            return new WeeklyDays(in);
        }

        @Override
        public WeeklyDays[] newArray(int size) {
            return new WeeklyDays[size];
        }
    };

    public ArrayList<DailyHours> weekDaysList = new ArrayList<>();


    private DailyHours monday = new DailyHours();
    private DailyHours tuesday = new DailyHours();
    private DailyHours wednesday = new DailyHours();
    private DailyHours thursday = new DailyHours();
    private DailyHours friday = new DailyHours();
    private DailyHours saturday = new DailyHours();
    private DailyHours sunday = new DailyHours();


    public WeeklyDays(){
        this.weekDaysList.add(monday);
        this.weekDaysList.add(tuesday);
        this.weekDaysList.add(wednesday);
        this.weekDaysList.add(thursday);
        this.weekDaysList.add(friday);
        this.weekDaysList.add(saturday);
        this.weekDaysList.add(sunday);
    }

    protected WeeklyDays(Parcel in) {
        weekDaysList = in.createTypedArrayList(DailyHours.CREATOR);
        monday = in.readParcelable(DailyHours.class.getClassLoader());
        tuesday = in.readParcelable(DailyHours.class.getClassLoader());
        wednesday = in.readParcelable(DailyHours.class.getClassLoader());
        thursday = in.readParcelable(DailyHours.class.getClassLoader());
        friday = in.readParcelable(DailyHours.class.getClassLoader());
        saturday = in.readParcelable(DailyHours.class.getClassLoader());
        sunday = in.readParcelable(DailyHours.class.getClassLoader());
    }

    public DailyHours getMonday() {
        return this.weekDaysList.get(0);
    }

    public void setMonday(int hour) {
        this.weekDaysList.get(0).setDayHours(hour);
    }

    public DailyHours getTuesday() {
        return this.weekDaysList.get(1);
    }

    public void setTuesday(int hour) {
        this.weekDaysList.get(1).setDayHours(hour);
    }

    public DailyHours getWednesday() {
        return this.weekDaysList.get(2);
    }

    public void setWednesday(int hour) {
        this.weekDaysList.get(2).setDayHours(hour);
    }

    public DailyHours getThursday() {
        return this.weekDaysList.get(3);
    }

    public void setThursday(int hour) {
        this.weekDaysList.get(3).setDayHours(hour);
    }

    public DailyHours getFriday() {
        return this.weekDaysList.get(4);
    }

    public void setFriday(int hour) {
        this.weekDaysList.get(4).setDayHours(hour);
    }

    public DailyHours getSaturday() {
        return this.weekDaysList.get(5);
    }

    public void setSaturday(int hour) {
        this.weekDaysList.get(5).setDayHours(hour);
    }

    public DailyHours getSunday() {
        return this.weekDaysList.get(6);
    }

    public void setSunday(int hour) {
        this.weekDaysList.get(6).setDayHours(hour);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(weekDaysList);
        dest.writeParcelable(monday, flags);
        dest.writeParcelable(tuesday, flags);
        dest.writeParcelable(wednesday, flags);
        dest.writeParcelable(thursday, flags);
        dest.writeParcelable(friday, flags);
        dest.writeParcelable(saturday, flags);
        dest.writeParcelable(sunday, flags);
    }
}
