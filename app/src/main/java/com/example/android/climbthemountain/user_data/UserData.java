package com.example.android.climbthemountain.user_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.climbthemountain.CustomCalendar.HoursSelectedPackage.WeeklyDays;

import java.util.ArrayList;

/**
 * Created by Marco on 30/03/16.
 */
public class UserData implements Parcelable {

    private String username;
    private String password;
    private String name;
    private String surname;

    private int session_day;
    private int session_month;
    private int session_year;



    // keep both into an user object
    public ArrayList<ExamData> userExams = new ArrayList<>();
    public WeeklyDays userSelectedHours = new WeeklyDays();

    public UserData(){

    }

    public UserData(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected UserData(Parcel in) {
        username = in.readString();
        password = in.readString();
        name = in.readString();
        surname = in.readString();
        userExams = in.createTypedArrayList(ExamData.CREATOR);
        userSelectedHours = in.readParcelable(WeeklyDays.class.getClassLoader());
        session_day = in.readInt();
        session_month = in.readInt();
        session_year = in.readInt();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSession_day() {
        return session_day;
    }

    public void setSession_day(int session_day) {
        this.session_day = session_day;
    }

    public int getSession_month() {
        return session_month;
    }

    public void setSession_month(int session_month) {
        this.session_month = session_month;
    }

    public int getSession_year() {
        return session_year;
    }

    public void setSession_year(int session_year) {
        this.session_year = session_year;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeTypedList(userExams);
        dest.writeParcelable(userSelectedHours, flags);
        dest.writeInt(session_day);
        dest.writeInt(session_month);
        dest.writeInt(session_year);
    }
}
