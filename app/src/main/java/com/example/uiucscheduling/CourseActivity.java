package com.example.uiucscheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseActivity extends AppCompatActivity {

    public static List<Course> courses = new ArrayList<>();
    public static List<Map<String, String>> courseList = new ArrayList<>();
    public static SimpleAdapter adapter;

    public String[][] data = {
            {"CS 125","Intro to Computer Science"},
            {"Math 347","Fundamental Mathematics"},
            {"Math 241","Calculus III"},
            {"ECON 302","Inter Microeconomics Theory"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        // Passing the list and setting up the list view
        adapter = new SimpleAdapter(this, courseList, R.layout.style_listview,
                new String[]{"number", "name"}, new int[]{R.id.tvLocal, R.id.tvName}
        );
        ListView listview = findViewById(R.id.courseListView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onClickListView);


        // Creating the upper toolbar
        Toolbar toolBar = findViewById(R.id.courseToolbar);
        setSupportActionBar(toolBar);


        // Creating the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.courses);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.courses:
                        return true;
                    case R.id.schedule:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.assignments:
                        startActivity(new Intent(getApplicationContext(), AssignmentActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }


    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(), CourseInfoActivity.class);
            Map<String, String> temp = courseList.get(position);
            String courseNum = temp.get("number");
            intent.putExtra("COURSE_NUM", courseNum);
            startActivity(intent);
            // Toast.makeText(CourseActivity.this,"點選第 " + (position + 1) + " 個 \n內容："+ data[position][1], Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            startActivity(new Intent(this, AddCourseActivity.class));
        } else if (id == R.id.remove) {
            startActivity(new Intent(this, RemoveCourseActivity.class));
        }
        return true;
    }
}
