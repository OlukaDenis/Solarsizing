package com.dentech.engineeringapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dentech.engineeringapp.common.CommonUtils;
import com.dentech.engineeringapp.model.Computer;
import com.dentech.engineeringapp.model.Fridge;
import com.dentech.engineeringapp.model.Lights;
import com.dentech.engineeringapp.model.Mobile;
import com.dentech.engineeringapp.model.OnMarket;
import com.dentech.engineeringapp.model.ProjectParams;
import com.dentech.engineeringapp.model.Radio;
import com.dentech.engineeringapp.model.Television;
import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class AppliancesActivity extends AppCompatActivity {
    private LinearLayout mobileAppl, mobileApplResult;
    private LinearLayout lightsAppl, lightsApplResult;
    private LinearLayout computerAppl, computerApplResult;
    private LinearLayout tvAppl, tvApplResult;
    private LinearLayout radioAppl, radioApplResult;
    private LinearLayout fridgeAppl, fridgeApplResult;

    private TextView mobileTotal1, mobileTotal2;
    private TextView computerTotal1, computerTotal2;
    private TextView lightsTotal1, lightsTotal2;
    private TextView radioTotal1, radioTotal2;
    private TextView tvTotal1, tvTotal2;
    private TextView fridgeTotal1, fridgeTotal2;

    private ImageView close;
    private FancyButton btn_save;
    Dialog ratingDialog;

    private Mobile mobile;
    private Computer computer;
    private Television television;
    private Lights lights;
    private Radio radio;
    private Fridge fridge;

    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances);

        ratingDialog = new Dialog(this);

        calculate = (Button) findViewById(R.id.btnCalculate);

        //all device cards
        mobileAppl = (LinearLayout) findViewById(R.id.llMobileAppl);
        mobileApplResult = (LinearLayout) findViewById(R.id.llMobileAppl_Result);
        lightsAppl = (LinearLayout) findViewById(R.id.llLightsAppl);
        lightsApplResult = (LinearLayout) findViewById(R.id.llLightsAppl_Result);
        computerAppl = (LinearLayout) findViewById(R.id.llComputerAppl);
        computerApplResult = (LinearLayout) findViewById(R.id.llComputerAppl_Result);
        tvAppl = (LinearLayout) findViewById(R.id.llTvAppl);
        tvApplResult = (LinearLayout) findViewById(R.id.llTvAppl_Result);
        fridgeAppl = (LinearLayout) findViewById(R.id.llFridgeAppl);
        fridgeApplResult = (LinearLayout) findViewById(R.id.llFridgeAppl_Result);
        radioAppl = (LinearLayout) findViewById(R.id.llRadioAppl);
        radioApplResult = (LinearLayout) findViewById(R.id.llRadioAppl_Result);

        //device totals
        mobileTotal1 = (TextView) findViewById(R.id.tvMobileTotal);
        mobileTotal2 = (TextView) findViewById(R.id.tvMobileTotal2);
        computerTotal1 = (TextView) findViewById(R.id.tvComputerTotal);
        computerTotal2 = (TextView) findViewById(R.id.tvComputerTotal2);
        tvTotal1 = (TextView) findViewById(R.id.tvTvTotal);
        tvTotal2 = (TextView) findViewById(R.id.tvTvTotal2);
        radioTotal1 = (TextView) findViewById(R.id.tvRadioTotal);
        radioTotal2 = (TextView) findViewById(R.id.tvRadioTotal2);
        lightsTotal1 = (TextView) findViewById(R.id.tvLightsTotal);
        lightsTotal2 = (TextView) findViewById(R.id.tvLightsTotal2);
        fridgeTotal1 = (TextView) findViewById(R.id.tvFridgeTotal);
        fridgeTotal2 = (TextView) findViewById(R.id.tvFridgeTotal2);


        //click listeners
        mobileAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMobileRatingsDialog();
            }
        });
        lightsAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLightsRatingsDialog();
            }
        });
        computerAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComputerRatingsDialog();
            }
        });
        radioAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRadioRatingsDialog();
            }
        });
        tvAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTvRatingsDialog();
            }
        });
        fridgeAppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFridgeRatingsDialog();
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCalculation();
            }
        });

    }

    private void addMobileRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
                else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    mobile = new Mobile(mRating, mLoads, mHours);

                    mobileAppl.setVisibility(View.GONE);
                    mobileApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) * Double.parseDouble(mLoads);
                    mobileTotal1.setText(String.valueOf(tot1));
                    mobileTotal2.setText(String.valueOf(tot2));
                    CommonUtils.mobileTotal1 = tot1;
                    CommonUtils.mobileTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void addFridgeRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
                else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    fridge = new Fridge(mRating, mLoads, mHours);

                    fridgeAppl.setVisibility(View.GONE);
                    fridgeApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) *  Double.parseDouble(mLoads);
                    fridgeTotal1.setText(String.valueOf(tot1));
                    fridgeTotal2.setText(String.valueOf(tot2));
                    CommonUtils.fridgeTotal1 = tot1;
                    CommonUtils.fridgeTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void addTvRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
                else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    television = new Television(mRating, mLoads, mHours);

                    tvAppl.setVisibility(View.GONE);
                    tvApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) *  Double.parseDouble(mLoads);
                    tvTotal1.setText(String.valueOf(tot1));
                    tvTotal2.setText(String.valueOf(tot2));
                    CommonUtils.tvTotal1 = tot1;
                    CommonUtils.tvTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void addRadioRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
                else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    radio = new Radio(mRating, mLoads, mHours);

                    radioAppl.setVisibility(View.GONE);
                    radioApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) *  Double.parseDouble(mLoads);
                    radioTotal1.setText(String.valueOf(tot1));
                    radioTotal2.setText(String.valueOf(tot2));
                    CommonUtils.radioTotal1 = tot1;
                    CommonUtils.radioTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void addComputerRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
               else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    computer = new Computer(mRating, mLoads, mHours);

                    computerAppl.setVisibility(View.GONE);
                    computerApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) *  Double.parseDouble(mLoads);
                    computerTotal1.setText(String.valueOf(tot1));
                    computerTotal2.setText(String.valueOf(tot2));
                    CommonUtils.computerTotal1 = tot1;
                    CommonUtils.computerTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void addLightsRatingsDialog() {
        ratingDialog.setContentView(R.layout.layout_add_ratings);
        ratingDialog.setCancelable(false);

        btn_save = (FancyButton) ratingDialog.findViewById(R.id.btnAdd_Ratings);
        close = (ImageView) ratingDialog.findViewById(R.id.closeImgIn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.dismiss();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"RestrictedApi", "StaticFieldLeak"})
            @Override
            public void onClick(View v) {
                final MaterialEditText rating = (MaterialEditText) ratingDialog.findViewById(R.id.etApplianceRating);
                final MaterialEditText hours = (MaterialEditText) ratingDialog.findViewById(R.id.etHoursNumber);
                final MaterialEditText loads = (MaterialEditText) ratingDialog.findViewById(R.id.etLoadNumber);

                String mRating = rating.getText().toString();
                String mHours = hours.getText().toString();
                String mLoads = loads.getText().toString();

                if (mRating.isEmpty()) {
                    rating.setError("Required field");
                    rating.requestFocus();
                }
                else if (mHours.isEmpty()) {
                    hours.setError("Required field");
                    hours.requestFocus();
                }
                else if (mLoads.isEmpty()) {
                    loads.setError("Required field");
                    loads.requestFocus();
                }
                else {
                    lights = new Lights(mRating, mLoads, mHours);

                    lightsAppl.setVisibility(View.GONE);
                    lightsApplResult.setVisibility(View.VISIBLE);
                    //get mobile totals
                    double tot1 = Double.parseDouble(mRating) * Double.parseDouble(mHours) * Double.parseDouble(mLoads);
                    double tot2 = Double.parseDouble(mRating) *  Double.parseDouble(mLoads);
                    lightsTotal1.setText(String.valueOf(tot1));
                    lightsTotal2.setText(String.valueOf(tot2));
                    CommonUtils.lightsTotal1 = tot1;
                    CommonUtils.lightsTotal2 = tot2;

                    ratingDialog.dismiss();
                }
            }
        });

        ratingDialog.show();
    }

    private void doCalculation() {

        //for solar panels
        double totalEnergyDemand = CommonUtils.mobileTotal1 + CommonUtils.computerTotal1
                                    + CommonUtils.fridgeTotal1 + CommonUtils.tvTotal1
                                    + CommonUtils.radioTotal1 + CommonUtils.lightsTotal1;
        double PSH = Double.parseDouble(CommonUtils.currentParameters.getPsh());
        double SysEff = Double.parseDouble(CommonUtils.currentParameters.getSysEff());
        double panelSize = Double.parseDouble(CommonUtils.current_on_market.getPanels());

        double dailyEnergyDemand = totalEnergyDemand / (PSH * SysEff);
        CommonUtils.panelsRating = dailyEnergyDemand;

        double no_panels = dailyEnergyDemand / panelSize;
        CommonUtils.number_of_panels = no_panels;

        //for batteries
        double batteriesSize = Double.parseDouble(CommonUtils.current_on_market.getBatteryCapacity());
        double doa = Double.parseDouble(CommonUtils.currentParameters.getDoa());
        double dod = Double.parseDouble(CommonUtils.currentParameters.getDod());
        double voltage = Double.parseDouble(CommonUtils.currentParameters.getSysVoltage());

        double batteryRating = (totalEnergyDemand * doa) / (SysEff * dod * voltage);
        CommonUtils.batteryRating = batteryRating;

        double no_batteries = batteryRating / batteriesSize;
        CommonUtils.number_of_batteries = no_batteries;


        //for charge controller
        double panelCurrent = Double.parseDouble(CommonUtils.current_on_market.getPanelCurrent());
        double controllerRating = panelCurrent * no_panels;
        CommonUtils.controllerRating = controllerRating;

        //for the inverter
        double totalPower = CommonUtils.mobileTotal2 + CommonUtils.computerTotal2
                                 + CommonUtils.fridgeTotal2 + CommonUtils.tvTotal2
                                + CommonUtils.radioTotal2 + CommonUtils.lightsTotal2;


        //go to the recommendation activity
        Intent intent = new Intent(AppliancesActivity.this, RecommendationActivity.class);
        startActivity(intent);


    }

}

