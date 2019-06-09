package com.dentech.engineeringapp.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dentech.engineeringapp.R;

public class UserProfileViewHolder extends RecyclerView.ViewHolder {
    public TextView username, eContact;

    public UserProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        username = (TextView) itemView.findViewById(R.id.tv_sname);
        eContact = (TextView) itemView.findViewById(R.id.tv_contact);
    }
}
