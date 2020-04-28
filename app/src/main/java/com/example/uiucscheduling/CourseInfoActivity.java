package com.example.uiucscheduling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CourseInfoActivity extends AppCompatActivity {

    private Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        String courseNum = getIntent().getStringExtra("COURSE_NUM");
        TextView textView = findViewById(R.id.textView);
        String courseName = "";
        for (Course c : CourseActivity.courses) {
            if (courseNum.equals(c.getCourseCtg() + " " + c.getCourseNum())) {
                course = c;
            }
        }
        String courseInfo = "Subject: " + course.getCourseCtg() +
                "\nCourse Number: " + course.getCourseNum() +
                "\nCourse Title: " + course.getCourseName() +
                "\nSection Number: " + course.getSectionNum() +
                "\nCredit Hours: " + course.getCredit() +
                "\nLocation: " + course.getLocation() +
                "\nMeeting Time: " + course.getStartingTime() + " - " + course.getEndingTime() +
                "\nMeeting Date: " + course.getStartingDate() + " - " + course.getEndingDate();
        textView.setText(courseInfo);
    }
}
