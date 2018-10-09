package com.pronix.android.hmssample;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CardDetailsActivity extends AppCompatActivity {

    private TextInputEditText etCardNumber;
    private EditText etExpiryDate,etCvv;
    private Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        init();
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Write code here
            }
        });
    }

    private void init() {
        etCardNumber = findViewById(R.id.et_card_number);
        etExpiryDate = findViewById(R.id.et_expiry_date);
        etCvv = findViewById(R.id.et_cvv);
        btPay = findViewById(R.id.bt_pay);
    }
}
