package com.pronix.android.hmssample.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.adapters.MyIpListAdapter;
import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.model.BillHistory;
import com.pronix.android.hmssample.model.MyIpModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyIpListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyIpListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyIpListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private ArrayList<BillHistory> billHistories;
    private ArrayList<MyIpModel> myIpModels;

    //private ArrayList<MyIpModel> myIpModels;
    private Toolbar toolbar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyIpListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyIpListFragment newInstance() {
        MyIpListFragment fragment = new MyIpListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_ip_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.myiprecycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My IpList");
        //getList();
        myIpModels = new ArrayList<>();

        getListt();
        return view;
    }

    private void getListt() {
        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());
            for(int i=0;i<obj.length();i++){
                billHistories = new ArrayList<>();
                JSONObject jsonObject = obj.getJSONObject(i);
                JSONObject jsonObject1 = jsonObject.getJSONObject("patientEscalateDetails");
                MyIpModel myIpModel = new MyIpModel();
                myIpModel.setDoctorName(jsonObject1.getString("doctorId"));
                myIpModel.setHospitalName(jsonObject1.getString("hospitalId"));
                myIpModel.setModeofjoining(jsonObject1.getString("modeOfJoining"));
                myIpModel.setNoofdays(jsonObject1.getString("noOfDays"));
                myIpModel.setDischargeDate(jsonObject1.getString("date"));
                myIpModel.setAppointmentId(jsonObject1.getString("appointmentId"));
                if(jsonObject.isNull("hospitalJoiningInfo")){

                }else {
                    JSONObject jsonObject2 = jsonObject.getJSONObject("hospitalJoiningInfo");
                    myIpModel.setBedno(jsonObject2.getString("bedNo"));
                    myIpModel.setRoomno(jsonObject2.getString("roomNo"));
                }
                    JSONArray jsonArray = jsonObject.getJSONArray("billInfoList");
                    for(int j=0;j<jsonArray.length();j++){
                        JSONObject jsonObject3 = jsonArray.getJSONObject(j);
                        BillHistory billHistory = new BillHistory();
                        billHistory.setBillid(jsonObject3.getString("billId"));
                        billHistory.setBillamount(jsonObject3.getString("billAmount"));
                        billHistory.setBilldetails(jsonObject3.getString("billDetails"));
                        billHistory.setBillissueddate(jsonObject3.getString("billIssuedDate"));
                        billHistory.setBillissuedby(jsonObject3.getString("billIssuedBy"));
                        billHistory.setBillIssuedAuthority(jsonObject3.getString("billIssuedAuthority"));
                        billHistory.setReceiptId(jsonObject3.getString("receiptId"));
                        billHistory.setPaidBillAmount(jsonObject3.getString("paidBillAmount"));
                        billHistory.setDiffBillAmount(jsonObject3.getString("diffBillAmount"));
                        billHistory.setBillPaidDate(jsonObject3.getString("billPaidDate"));
                        billHistory.setBillPaidBy(jsonObject3.getString("billPaidBy"));
                        billHistory.setReceiptGeneratedBy(jsonObject3.getString("receiptGeneratedBy"));
                        billHistories.add(billHistory);
                    }
                    myIpModel.setBillHistories(billHistories);
                    myIpModels.add(myIpModel);
            }

            MyIpListAdapter myIpListAdapter = new MyIpListAdapter(getActivity(),myIpModels);
            recyclerView.setAdapter(myIpListAdapter);
            myIpListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("document.json");
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
//    private void getList() {
//        Ion.with(getActivity())
//                .load(Constants.URLBase+Constants.REQUEST_MYIPLIST+"/"+Constants.userDetails.userId)
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        if(e!=null){
//
//                        }else {
//                            try {
//                                JSONArray jsonArray = new JSONArray(result);
//                                for(int i=0;i<jsonArray.length();i++){
//                                    MyIpModel myIpModel = new MyIpModel();
//                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    myIpModel.setDoctorName(jsonObject.getString("doctorName"));
//                                    myIpModel.setHospitalName(jsonObject.getString("hospitalName"));
//                                    myIpModel.setHospitalAddress(jsonObject.getString("hospitalAddress"));
//                                    myIpModel.setDischargeDate(jsonObject.getString("dischargeDate"));
//                                    myIpModels.add(myIpModel);
//                                }
//                            MyIpListAdapter myIpListAdapter = new MyIpListAdapter(getActivity(),myIpModels);
//                            recyclerView.setAdapter(myIpListAdapter);
//                            myIpListAdapter.notifyDataSetChanged();
//                            } catch (JSONException e1) {
//                                e1.printStackTrace();
//                            }
//                        }
//                    }
//                });
//    }

    private void getList() {
        Ion.with(getContext())
                .load(Constants.URLBase+Constants.REQUEST_MYIPLIST+"/"+Constants.userDetails.userId)
                .as(new TypeToken<ArrayList<MyIpModel>>(){})
                .setCallback(new FutureCallback<ArrayList<MyIpModel>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<MyIpModel> tweets) {
                        if(e!=null){

                        }else {
                            myIpModels = tweets;
                            MyIpListAdapter myIpListAdapter = new MyIpListAdapter(getActivity(),myIpModels);
                            recyclerView.setAdapter(myIpListAdapter);
                            myIpListAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
