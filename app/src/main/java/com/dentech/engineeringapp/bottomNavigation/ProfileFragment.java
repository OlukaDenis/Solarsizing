package com.dentech.engineeringapp.bottomNavigation;

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
import com.dentech.engineeringapp.adapters.UserAdapter;
import com.dentech.engineeringapp.common.CommonUtils;
import com.dentech.engineeringapp.database.DatabaseHelper;
import com.dentech.engineeringapp.model.User;
import com.dentech.engineeringapp.viewholders.UserProfileViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    User user;
    DatabaseHelper db;
    List<User> userList;
    UserAdapter userAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String phone;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Profile");

        db = new DatabaseHelper(getActivity());
        user = new User();
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(getActivity(), userList);
        phone = CommonUtils.current_user_phone;


        recyclerView = (RecyclerView) view.findViewById(R.id.rvProfile);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(userAdapter);

        showUserProfile();
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void showUserProfile() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                userList.clear();
                userList.addAll(db.getOneUser(phone));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                userAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


}
