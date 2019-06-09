package com.dentech.engineeringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dentech.engineeringapp.R;
import com.dentech.engineeringapp.common.CommonUtils;
import com.dentech.engineeringapp.model.ProjectParams;
import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class ProjectParameters extends AppCompatActivity {
    private MaterialEditText psh, dod, doa, sysEff, sysVolt, safetyFactor;
    private ProjectParams params;
    FancyButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_parameters);
        setTitle("Project Parameters");

        btnContinue = (FancyButton) findViewById(R.id.btnContinue);
        psh = (MaterialEditText) findViewById(R.id.etPSH);
        dod = (MaterialEditText) findViewById(R.id.etDOD);
        doa = (MaterialEditText) findViewById(R.id.etDOA);
        sysEff = (MaterialEditText) findViewById(R.id.etSystemEff);
        sysVolt = (MaterialEditText) findViewById(R.id.etSysVoltage);
        safetyFactor = (MaterialEditText) findViewById(R.id.etSafetyFactor);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveParameters();

            }
        });
    }

    private void saveParameters() {
        String mpsh = psh.getText().toString();
        String mdod = dod.getText().toString();
        String mdoa = doa.getText().toString();
        String meff = sysEff.getText().toString();
        String mvolt = sysVolt.getText().toString();
        String msafety = safetyFactor.getText().toString();

        if (mpsh.isEmpty()){
            psh.setError("Require Field");
            psh.requestFocus();
        }
        else {
            params = new ProjectParams(
                    mpsh,
                    mdod,
                    meff,
                    mdoa,
                    mvolt,
                    msafety
            );

            CommonUtils.currentParameters = params;

            startActivity(new Intent(ProjectParameters.this, AppliancesActivity.class));
            finish();
        }
    }
}
