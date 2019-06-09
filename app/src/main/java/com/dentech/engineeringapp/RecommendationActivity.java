package com.dentech.engineeringapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dentech.engineeringapp.common.CommonUtils;

public class RecommendationActivity extends AppCompatActivity {
    private TextView panel_rated, no_of_panels;
    private TextView battery_rated, no_of_batteries;
    private TextView controller_rated;
    private TextView inverter_rated;

    Button saveInfo;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        setTitle("Recommendation");

        dialog = new ProgressDialog(this);
        dialog.setTitle("");
        dialog.setMessage("Loading data..");
        dialog.show();

        panel_rated = (TextView) findViewById(R.id.tvPanelsRated);
        no_of_panels = (TextView) findViewById(R.id.tvPanelNumber);
        battery_rated = (TextView) findViewById(R.id.tvBatteryRated);
        no_of_batteries = (TextView) findViewById(R.id.tvBatteryNumber);
        controller_rated = (TextView) findViewById(R.id.tvControllerRated);
        inverter_rated = (TextView) findViewById(R.id.tvInverterRated);

        //show recommendation
        loadRecommendation();

        saveInfo = (Button) findViewById(R.id.btnSaveInfo);
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecommendation();
            }
        });
    }

    private void loadRecommendation() {
        dialog.dismiss();
        //for solars
        double panelRated = CommonUtils.round(CommonUtils.panelsRating, 2);
        double numberPanels = CommonUtils.round(CommonUtils.number_of_panels, 0);
        int mNoPanels = (int) numberPanels;
        panel_rated.setText(String.valueOf(panelRated) + " Wph");
        no_of_panels.setText(String.valueOf(mNoPanels) + " panels rated");

        //for batteries
        double batteryRated = CommonUtils.round(CommonUtils.batteryRating, 2);
        double numberBatteries= CommonUtils.round(CommonUtils.number_of_batteries, 0);
        int mNoBatteries = (int) numberBatteries;
        battery_rated.setText(String.valueOf(batteryRated) + " Ah");
        no_of_batteries.setText(String.valueOf(mNoBatteries) + " batteries rated");

        //for charge controller
        double controllerRated = CommonUtils.round(CommonUtils.controllerRating, 2);
        controller_rated.setText(String.valueOf(controllerRated) + " A");

        //for inverter
        double inverterRated = CommonUtils.round(CommonUtils.inverterRating, 2);
        inverter_rated.setText(String.valueOf(inverterRated) + " A");
    }


    private void saveRecommendation() {
        startActivity(new Intent(RecommendationActivity.this, HomeActivity.class));
        Toast.makeText(this, "Recommendation saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
