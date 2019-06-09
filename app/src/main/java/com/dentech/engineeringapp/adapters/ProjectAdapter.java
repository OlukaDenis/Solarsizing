package com.dentech.engineeringapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.model.Project;
import com.dentech.engineeringapp.viewholders.ProjectViewHolder;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {
    private Context context;
    private List<Project> projectList;

    public ProjectAdapter(Context context, List<Project> list){
        this.projectList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_projects, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project data  = projectList.get(position);
        holder.mProjectName.setText(data.getProjectname());
        holder.mProjectDate.setText(data.getProjectDate());
        holder.mLocation.setText(data.getLocation());
        holder.mTechnician.setText(data.getTechnician());

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public void clear(){
        projectList.clear();
        notifyDataSetChanged();

    }
}
