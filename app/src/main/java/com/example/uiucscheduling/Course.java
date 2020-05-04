package com.example.uiucscheduling;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    private String courseName;
    private String courseCtg;
    private String courseNum;
    private String sectionNum;
    private String location;
    private String profName;
    private int credit;
    private String startingDate;
    private String endingDate;
    private String startingTime;
    private String endingTime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseName);
        parcel.writeString(courseCtg);
        parcel.writeString(courseNum);
        parcel.writeString(sectionNum);
        parcel.writeString(location);
        parcel.writeString(profName);
        parcel.writeString(startingDate);
        parcel.writeString(endingDate);
        parcel.writeString(startingTime);
        parcel.writeString(endingTime);
        parcel.writeInt(credit);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {
        public Course createFromParcel(Parcel in) {
            return new Course();
        }
        public Course[] newArray(int size)
        {
            return new Course[size];
        }
    };

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCtg(String courseCtg) {
        this.courseCtg = courseCtg;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public void setSectionNum(String sectionNum) {
        this.sectionNum = sectionNum;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public int getCredit() {
        return credit;
    }

    public String getCourseCtg() {
        return courseCtg;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public String getLocation() {
        return location;
    }

    public String getProfName() {
        return profName;
    }

    public String getSectionNum() {
        return sectionNum;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getStartingTime() {
        return startingTime;
    }
}
