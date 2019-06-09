package com.dentech.engineeringapp.viewPager;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dentech.engineeringapp.ProjectActivity;
import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.adapters.ProjectAdapter;
import com.dentech.engineeringapp.database.DatabaseHelper;
import com.dentech.engineeringapp.model.Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    private ImageView close;
    private FancyButton btn_add;
    FloatingActionButton floatingActionButton;
    Dialog projectDialog;
    Calendar calendar;
    int Day, Month, Year;
    Project project;
    DatabaseHelper db;
    List<Project> projectList;
    ProjectAdapter projectAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        projectDialog = new Dialog(getActivity());
        db = new DatabaseHelper(getActivity());
        project = new Project();
        projectList = new ArrayList<>();
        projectAdapter = new ProjectAdapter(getActivity(), projectList);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_add_project);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                //showAddDialog();
               // floatingActionButton.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getContext(), ProjectActivity.class));
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_project);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(projectAdapter);

        showRecentProjects();

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void showRecentProjects() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                projectList.clear();
                projectList.addAll(db.getAllProjects());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                projectAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


}
