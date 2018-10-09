package com.pronix.android.hmssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pronix.android.hmssample.adapters.HospitalLookUpAdapter;
import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.model.Doctors;
import com.pronix.android.hmssample.model.Hospitallist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HospitalsLookupActivity extends AppCompatActivity {
    RecyclerView rv_ImpPateientList;
    ArrayList<Hospitallist> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_lookup);
        initializeControls();
        loadHospitals();
    }

    private void loadHospitals() {
        Ion.with(HospitalsLookupActivity.this)
                .load(Constants.URLBase+Constants.REQUEST_HOSPITAL_LIST)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try {
                                JSONArray jsonArray = new JSONArray(result);
                                for(int i=0;i<jsonArray.length();i++){
                                    Hospitallist hospitallist = new Hospitallist();
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    hospitallist.setHopId(jsonObject.getString("hospitalId"));
                                    hospitallist.setHopName(jsonObject.getString("hospitalName"));
                                    hospitallist.setHosAddress(jsonObject.getString("hospitalAddress"));
                                    list.add(hospitallist);
                                }
                                HospitalLookUpAdapter hospitalLookUpAdapter = new HospitalLookUpAdapter(HospitalsLookupActivity.this,list);
                                rv_ImpPateientList.setAdapter(hospitalLookUpAdapter);
                                hospitalLookUpAdapter.notifyDataSetChanged();
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
    }

    public void initializeControls() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hospital List");

        rv_ImpPateientList = (RecyclerView) findViewById(R.id.hospital_List_patient);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_ImpPateientList.setLayoutManager(llm);
        rv_ImpPateientList.setHasFixedSize(true);
        rv_ImpPateientList.setItemAnimator(new DefaultItemAnimator());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
