package com.dentech.engineeringapp.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dentech.engineeringapp.R;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
    public TextView mProjectName, mProjectDate, mTechnician, mLocation;

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);
        mProjectName = (TextView) itemView.findViewById(R.id.tv_project_name);
        mProjectDate = (TextView) itemView.findViewById(R.id.tv_project_date);
        mTechnician = (TextView) itemView.findViewById(R.id.tv_project_technician);
        mLocation = (TextView) itemView.findViewById(R.id.tv_project_location);
    }
}
