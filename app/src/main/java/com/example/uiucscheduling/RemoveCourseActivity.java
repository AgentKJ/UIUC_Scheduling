package com.example.uiucscheduling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.Map;

public class RemoveCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_course);

        // Passing the list and setting up the list view
        ListView listview = findViewById(R.id.courseListView);
        listview.setAdapter(CourseActivity.adapter);
        listview.setOnItemClickListener(onClickListView);


        // Creating the upper toolbar
        Toolbar toolBar = findViewById(R.id.courseToolbar);
        setSupportActionBar(toolBar);
    }

    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> temp = CourseActivity.courseList.get(position);
            String name = temp.get("name");
            for (Course c : CourseActivity.courses) {
                if (c.getCourseName().equals(name)) {
                    CourseActivity.courses.remove(c);
                }
            }
            CourseActivity.courseList.get(position).clear();
            CourseActivity.adapter.notifyDataSetChanged();
            finish();
        }

    };
}
