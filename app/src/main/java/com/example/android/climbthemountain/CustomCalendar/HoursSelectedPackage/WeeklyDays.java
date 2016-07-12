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
    ArrayList<DailyHours> weekDaysList = new ArrayList<>();
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

    public void setMonday(DailyHours monday) {
        this.weekDaysList.set(0, monday);
    }

    public DailyHours getTuesday() {
        return this.weekDaysList.get(1);
    }

    public void setTuesday(DailyHours tuesday) {
        this.weekDaysList.set(1, tuesday);
    }

    public DailyHours getWednesday() {
        return this.weekDaysList.get(2);
    }

    public void setWednesday(DailyHours wednesday) {
        this.weekDaysList.set(2, wednesday);
    }

    public DailyHours getThursday() {
        return this.weekDaysList.get(3);
    }

    public void setThursday(DailyHours thursday) {
        this.weekDaysList.set(3, thursday);
    }

    public DailyHours getFriday() {
        return this.weekDaysList.get(4);
    }

    public void setFriday(DailyHours friday) {
        this.weekDaysList.set(4,friday);
    }

    public DailyHours getSaturday() {
        return this.weekDaysList.get(5);
    }

    public void setSaturday(DailyHours saturday) {
        this.weekDaysList.set(5, saturday);
    }

    public DailyHours getSunday() {
        return this.weekDaysList.get(6);
    }

    public void setSunday(DailyHours sunday) {
        this.weekDaysList.set(6, sunday);
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
