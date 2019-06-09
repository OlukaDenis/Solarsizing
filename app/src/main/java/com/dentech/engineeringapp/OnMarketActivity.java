package com.dentech.engineeringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dentech.engineeringapp.common.CommonUtils;
import com.dentech.engineeringapp.model.OnMarket;
import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class OnMarketActivity extends AppCompatActivity {
    private MaterialEditText solar, panelCurrent, batteryCapacity;
    FancyButton save;
    private OnMarket marketParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_market);
        setTitle("Current Market");

        solar = (MaterialEditText) findViewById(R.id.etPanelMarket);
        panelCurrent = (MaterialEditText) findViewById(R.id.etPanelCurrent);
        batteryCapacity = (MaterialEditText) findViewById(R.id.etBatteryCapacity);

        save = (FancyButton) findViewById(R.id.btnAddtoMarket);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMarketParameters();
            }
        });
    }

    private void addMarketParameters() {
        String mSolar = solar.getText().toString();
        String mPanelCurrent = panelCurrent.getText().toString();
        String mBatteryCapacity = batteryCapacity.getText().toString();

        if(mSolar.isEmpty()){
            solar.setError("Required field");
            solar.requestFocus();
        }
        else {
            marketParams = new OnMarket(mSolar, mPanelCurrent, mBatteryCapacity);
            CommonUtils.current_on_market = marketParams;

            solar.setText(marketParams.getPanels());
            panelCurrent.setText(marketParams.getPanelCurrent());
            batteryCapacity.setText(marketParams.getBatteryCapacity());

            startActivity(new Intent(OnMarketActivity.this, HomeActivity.class));
        }

    }
}
