package com.dentech.engineeringapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.database.DatabaseHelper;
import com.dentech.engineeringapp.model.Project;
import com.dentech.engineeringapp.model.User;
import com.dentech.engineeringapp.viewholders.ProjectViewHolder;
import com.dentech.engineeringapp.viewholders.UserProfileViewHolder;
import com.dentech.engineeringapp.viewholders.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserProfileViewHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> list){
        this.userList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile, parent, false);
        return new UserProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileViewHolder holder, int position) {
        User data  = userList.get(position);
        holder.username.setText(data.getName());
        holder.eContact.setText(data.getEmail());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void clear(){
        userList.clear();
        notifyDataSetChanged();

    }
}
