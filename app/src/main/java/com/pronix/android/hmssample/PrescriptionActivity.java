package com.pronix.android.hmssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class PrescriptionActivity extends AppCompatActivity {

    private TextView doctor_name,prescription;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Prescription");
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        doctor_name = findViewById(R.id.doctor_name);
        prescription = findViewById(R.id.prescription);

        Intent intent = getIntent();

        doctor_name.setText("Dr. "+intent.getStringExtra("doctor_name"));
        prescription.setText(intent.getStringExtra("prexcription"));
    }
}
