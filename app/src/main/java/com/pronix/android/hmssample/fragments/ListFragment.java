package com.pronix.android.hmssample.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pronix.android.hmssample.DoctorsLookupActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.adapters.DoctorsVisitsAdapter;
import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.DoctorVisits;
import com.pronix.android.hmssample.model.Doctors;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.renderer.DoctorsListAdapter;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ListFragment extends Fragment implements OnTaskCompleted{


    private RecyclerView recyclerView;
    private TextView noData;
    private LinearLayoutManager layoutManager;
    private Dialog dialog;
    private List<DoctorVisits> doctorVisitsList;
    private SwipeRefreshLayout scrollIndicatorUp;
    private DoctorsVisitsAdapter adapter;
    private Toolbar toolbar;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Your Appointments");
        /*// add back arrow to toolbar
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        recyclerView = view.findViewById(R.id.my_recycler_view);
        scrollIndicatorUp = view.findViewById(R.id.scrollIndicatorUp);
        noData = view.findViewById(R.id.noData);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        doctorAppointmentsWebServices();

        scrollIndicatorUp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                scrollIndicatorUp.setRefreshing(false);
                doctorAppointmentsWebServices();
                adapter = new DoctorsVisitsAdapter(doctorVisitsList, getContext());
                recyclerView.setAdapter(adapter);
            }
        });

       /* doctorVisitsList = new Gson().fromJson(loadJSONFromAsset(),new TypeToken<List<DoctorVisits>>() {}.getType());
        adapter = new DoctorsVisitsAdapter(doctorVisitsList, getContext());
        recyclerView.setAdapter(adapter);*/

        return view;
    }

    public void doctorAppointmentsWebServices() {
        try {
            progressDialog(getActivity());
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "DETAILS";
            new AsyncTask(getContext(), ListFragment.this, Constants.URLBase  + Constants.REQUEST_myAppointments+"/" + Constants.userDetails.userId, "GET", null).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
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

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                Type listType = new TypeToken<List<DoctorVisits>>() {}.getType();
                doctorVisitsList = new Gson().fromJson(webServiceDO.responseContent, listType);
                adapter = new DoctorsVisitsAdapter(doctorVisitsList, getContext());
                recyclerView.setAdapter(adapter);
                if(doctorVisitsList.size() == 0)
                {
                    recyclerView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
            } else {
                Utils.showalert(getContext(), "Alert", webServiceDO.responseContent);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("yourList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
