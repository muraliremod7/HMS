package com.pronix.android.hmssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pronix.android.hmssample.adapters.BillinfoAdapter;
import com.pronix.android.hmssample.model.BillHistory;
import com.pronix.android.hmssample.model.MyIpModel;

import java.util.ArrayList;

public class GetBillinfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<BillHistory> billHistories;
    private BillinfoAdapter billinfoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_billinfo);
        recyclerView = (RecyclerView)findViewById(R.id.billinfo_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        billHistories = new ArrayList<>();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        billHistories = (ArrayList<BillHistory>) args.getSerializable("ARRAYLIST");
        billinfoAdapter = new BillinfoAdapter(this,billHistories);
        recyclerView.setAdapter(billinfoAdapter);
    }
}
