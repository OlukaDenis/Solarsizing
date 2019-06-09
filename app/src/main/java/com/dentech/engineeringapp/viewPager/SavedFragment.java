package com.dentech.engineeringapp.viewPager;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.adapters.ProjectAdapter;
import com.dentech.engineeringapp.adapters.UserAdapter;
import com.dentech.engineeringapp.database.DatabaseHelper;
import com.dentech.engineeringapp.model.Project;
import com.dentech.engineeringapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Project> projectList;
    DatabaseHelper db;
    ProjectAdapter projectAdapter;


    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        projectList = new ArrayList<>();
        db = new DatabaseHelper(getActivity());
        projectAdapter = new ProjectAdapter(getActivity(), projectList);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_project_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(projectAdapter);

        retrieveProjects();

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void retrieveProjects() {

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

    @Override
    public void onResume() {
        super.onResume();
        retrieveProjects();
    }
}
