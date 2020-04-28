package com.example.uiucscheduling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddCourseActivity extends AppCompatActivity {

    private String courseCtg;
    private String startingDate;
    private String endingDate;
    private String startingTime;
    private String endingTime;

    Course course;

    Button btnSetStartDate;
    Button btnSetEndDate;
    Button btnSetStartTime;
    Button btnSetEndTime;

    private TextView txvCourseName;
    private TextView txvCourseNum;
    private TextView txvStartDate;
    private TextView txvEndDate;
    private TextView txvStartTime;
    private TextView txvEndTime;
    private TextView txvLocation;
    private TextView txvCredit;
    private TextView txvProf;
    private TextView txvSection;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    Button addCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Toolbar toolBar = findViewById(R.id.newCourseToolbar);
        setSupportActionBar(toolBar);

        txvCourseName = findViewById(R.id.editCourseName);
        txvCourseNum = findViewById(R.id.editCourseNum);
        txvLocation = findViewById(R.id.editLocation);
        txvCredit = findViewById(R.id.editCredit);
        txvProf = findViewById(R.id.editProf);
        txvSection = findViewById(R.id.editSection);

        addCourse = findViewById(R.id.btnAdd);

        /* Set course name and number. */
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    course = new Course();
                    course.setCourseName(txvCourseName.getText().toString());
                    course.setCourseNum(txvCourseNum.getText().toString());
                    course.setCourseCtg(courseCtg);
                    course.setCredit(Integer.parseInt(txvCredit.getText().toString()));
                    course.setLocation(txvLocation.getText().toString());
                    course.setProfName(txvProf.getText().toString());
                    course.setSectionNum(txvSection.getText().toString());
                    course.setStartingTime(startingTime);
                    course.setEndingTime(endingTime);
                    course.setStartingDate(startingDate);
                    course.setEndingDate(endingDate);

                    Map<String, String> item = new HashMap<>();
                    item.put("level", course.getCourseCtg() + " " + course.getCourseNum());
                    item.put("name", course.getCourseName());
                    CourseActivity.courseList.add(item);
                    CourseActivity.adapter.notifyDataSetChanged();

                    finish();
                }
            }
        });

        /* Set course category. */
        String[] ctg = this.getResources().getStringArray(R.array.subjects_abbrev);
        Spinner category = findViewById(R.id.spinCtg);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ctg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                courseCtg = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddCourseActivity.this, "Select the corresponding subject", Toast.LENGTH_LONG).show();
            }
        });



        txvStartDate = findViewById(R.id.txvStartDate);
        txvEndDate = findViewById(R.id.txvEndDate);
        txvStartTime = findViewById(R.id.txvStartTime);
        txvEndTime = findViewById(R.id.txvEndTime);

        btnSetStartDate = findViewById(R.id.btnPickStartDate);
        btnSetEndDate = findViewById(R.id.btnPickEndDate);
        btnSetStartTime = findViewById(R.id.btnPickStartTime);
        btnSetEndTime = findViewById(R.id.btnPickEndTime);

        /* Set start date. */
        btnSetStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int pickYear, int pickMonth, int pickDay) {
                        startingDate = (pickMonth + 1) + "/" + pickDay + "/" + pickYear;
                        txvStartDate.setText(startingDate);
                    }
                }, month, day, year);
                datePickerDialog.show();
            }
        });

        /* Set end date. */
        btnSetEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddCourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int pickYear, int pickMonth, int pickDay) {
                        endingDate = (pickMonth + 1) + "/" + pickDay + "/" + pickYear;
                        txvEndDate.setText(endingDate);
                    }
                }, month, day, year);
                datePickerDialog.show();
            }
        });

        /* Set start time. */
        btnSetStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int pickHour, int pickMinute) {
                        startingTime = pickHour + ":" + pickMinute;
                        if (pickMinute < 10) {
                            startingTime = pickHour + ":0" + pickMinute;
                        }
                        txvStartTime.setText(startingTime);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        /* Set end time. */
        btnSetEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int pickHour, int pickMinute) {
                        endingTime = pickHour + ":" + pickMinute;
                        if (pickMinute < 10) {
                            endingTime = pickHour + ":0" + pickMinute;
                        }
                        txvEndTime.setText(endingTime);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
    }

    /**
     * Check validity of the input info.
     * @return The validity of the input.
     */
    private boolean isValid() {
        // Check if anything is left blank
        if (TextUtils.isEmpty(txvCourseName.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter the course's name", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(txvCourseNum.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter the course's number", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(txvCredit.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter the credit hour(s)", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(txvLocation.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter the location", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(txvProf.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter professor's name", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(txvSection.getText().toString())) {
            Toast.makeText(AddCourseActivity.this, "Please enter the section code", Toast.LENGTH_LONG).show();
            return false;
        }
        if (startingDate == null) {
            Toast.makeText(AddCourseActivity.this, "Please pick a starting date", Toast.LENGTH_LONG).show();
            return false;
        }
        if (endingDate == null) {
            Toast.makeText(AddCourseActivity.this, "Please pick an ending date", Toast.LENGTH_LONG).show();
            return false;
        }
        if (startingTime == null) {
            Toast.makeText(AddCourseActivity.this, "Please pick a starting time", Toast.LENGTH_LONG).show();
            return false;
        }
        if (endingTime == null) {
            Toast.makeText(AddCourseActivity.this, "Please pick an ending time", Toast.LENGTH_LONG).show();
            return false;
        }

        // Check if the selected time is valid
        String[] temp = startingTime.split(":");
        int startingHour = Integer.parseInt(temp[0]);
        int startingMinute = Integer.parseInt(temp[1]);
        temp = endingTime.split(":");
        int endingHour = Integer.parseInt(temp[0]);
        int endingMinute = Integer.parseInt(temp[1]);

        if (endingHour < startingHour) {
            Toast.makeText(AddCourseActivity.this, "Invalid time", Toast.LENGTH_LONG).show();
            return false;
        } else if (endingHour == startingHour && endingMinute <= startingMinute) {
            Toast.makeText(AddCourseActivity.this, "Invalid time", Toast.LENGTH_LONG).show();
            return false;
        }

        // Check if the selected date is valid
        temp = startingDate.split("/");
        int startingDay = Integer.parseInt(temp[1]);
        int startingMonth = Integer.parseInt(temp[0]);
        int startingYear = Integer.parseInt(temp[2]);
        temp = endingDate.split("/");
        int endingDay = Integer.parseInt(temp[1]);
        int endingMonth = Integer.parseInt(temp[0]);
        int endingYear = Integer.parseInt(temp[2]);

        if (endingYear < startingYear) {
            Toast.makeText(AddCourseActivity.this, "Invalid date", Toast.LENGTH_LONG).show();
            return false;
        } else if (endingYear == startingYear && endingMonth < startingMonth) {
            Toast.makeText(AddCourseActivity.this, "Invalid date", Toast.LENGTH_LONG).show();
            return false;
        } else if (endingYear == startingYear && endingMonth == startingMonth && endingDay <= startingDay) {
            Toast.makeText(AddCourseActivity.this, "Invalid date", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
