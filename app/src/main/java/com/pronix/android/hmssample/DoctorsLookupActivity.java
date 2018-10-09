package com.pronix.android.hmssample;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.pronix.android.hmssample.adapters.MyAdapter;
import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.CustomItemClickListener;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.Doctors;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.renderer.DoctorsListAdapter;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoctorsLookupActivity extends AppCompatActivity implements CustomItemClickListener, OnTaskCompleted {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    ArrayList<Doctors> arrayList = new ArrayList<>();
    String specialization,hospid,patid = null;
    Dialog dialog;
    TextView tv_EmptyView;
    private SwipeRefreshLayout scrollIndicatorUp;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_lookup);
        Intent intent = getIntent();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(specialization);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
//        specialization = "Cardiology";
        mRecyclerView = findViewById(R.id.my_recycler_view);
        scrollIndicatorUp = findViewById(R.id.scrollIndicatorUp);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        tv_EmptyView = findViewById(R.id.emptyView);
        specialization = intent.getStringExtra("SPECIALIZATION");
        hospid = intent.getStringExtra("hosId");
        patid = intent.getStringExtra("patid");
        if(hospid!=null){
            callProfileWebServices( Constants.REQUEST_doctorListInHospital,hospid);
        }else if(specialization!=null){
            callProfileWebServices( Constants.REQUEST_DOCTORSLOOKUP,specialization);
        }else if(patid!=null){
            callProfileWebServices( Constants.REQUEST_myDoctors,patid);
        }
        try{

        }catch (NullPointerException e){

        }



        scrollIndicatorUp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                scrollIndicatorUp.setRefreshing(false);
                DoctorsListAdapter adapter = new DoctorsListAdapter(arrayList,DoctorsLookupActivity.this);
                mRecyclerView.setAdapter(adapter);
            }
        });
    }

    public void open_doctor(View view) {
        startActivity(new Intent(this, DoctorProfileActivity.class));
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent in = new Intent(this, DoctorProfileActivity.class);
        in.putExtra("DoctorsDetails", arrayList.get(position));
        startActivity(in);
    }

    public void callProfileWebServices(String request,String type) {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "DETAILS";
            new AsyncTask(DoctorsLookupActivity.this, DoctorsLookupActivity.this, Constants.URLBase + request+"/" + type, "GET", null).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                Type listType = new TypeToken<List<Doctors>>() {}.getType();
                arrayList = new Gson().fromJson(webServiceDO.responseContent, listType);
                DoctorsListAdapter adapter = new DoctorsListAdapter(arrayList, this);
                mRecyclerView.setAdapter(adapter);
                if(arrayList.size() == 0)
                {
                    tv_EmptyView.setVisibility(View.VISIBLE);
                }
            } else {
                Utils.showalert(DoctorsLookupActivity.this, "Alert", webServiceDO.responseContent);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void progressDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressview);
        dialog.show();
    }

}
