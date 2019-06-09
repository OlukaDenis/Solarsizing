package com.dentech.engineeringapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dentech.engineeringapp.database.DatabaseHelper;
import com.dentech.engineeringapp.model.Project;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class ProjectActivity extends AppCompatActivity {
    private FancyButton btn_add;
    Calendar calendar;
    int Day, Month, Year;
    Project project;
    DatabaseHelper db;
    List<Project> projectList;
    TextView getDate, Date;
    MaterialEditText project_name, project_location, project_technician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        setTitle("Add Project");

        db = new DatabaseHelper(this);
        project = new Project();

        getDate = (TextView) findViewById(R.id.getDate);
        Date = (TextView) findViewById(R.id.date);
        btn_add = (FancyButton) findViewById(R.id.btnAdd_project);
        project_name= (MaterialEditText) findViewById(R.id.etProjectName);
        project_location= (MaterialEditText) findViewById(R.id.etProjectLocation);
        project_technician= (MaterialEditText) findViewById(R.id.etProjectTechnician);

        addProject();
    }

    public void addProject(){

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                Day = calendar.get(Calendar.DAY_OF_MONTH);
                Month = calendar.get(Calendar.MONTH);
                Year = calendar.get(Calendar.YEAR);
                final DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(ProjectActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                Date.setText(day + "/" + month + "/" + year);
                            }
                        }, Year, Month, Day);
                datePickerDialog.show();
            }
        });

        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                Day = calendar.get(Calendar.DAY_OF_MONTH);
                Month = calendar.get(Calendar.MONTH);
                Year = calendar.get(Calendar.YEAR);
                final DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(ProjectActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                Date.setText(day + "/" + month + "/" + year);
                            }
                        }, Year, Month, Day);
                datePickerDialog.show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                String mDate = Date.getText().toString();
                String mLocation = project_location.getText().toString();
                String mTechnician = project_technician.getText().toString();
                String mProjectName = project_name.getText().toString();
                if (mProjectName.isEmpty()) {
                    project_name.setError("Please provide project title!");
                    project_name.requestFocus();
                }
                else if (mLocation.isEmpty()) {
                    project_location.setError("Please provide project location!");
                    project_location.requestFocus();
                }
                else if (mTechnician.isEmpty()) {
                    project_technician.setError("Please provide project technician!");
                    project_technician.requestFocus();
                }
                else {
                    project.setProjectname(mProjectName);
                    project.setProjectDate(mDate);
                    project.setLocation(mLocation);
                    project.setTechnician(mTechnician);

                    db.addProject(project);
                   // Toast.makeText(getApplicationContext(), "New project added", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ProjectActivity.this, ProjectParameters.class));
                    finish();
                }

            }
        });
    }
}
