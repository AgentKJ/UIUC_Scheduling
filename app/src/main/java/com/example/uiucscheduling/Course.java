package com.example.uiucscheduling;

public class Course {
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
