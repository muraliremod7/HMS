package com.pronix.android.hmssample.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pronix.android.hmssample.DoctorsLookupActivity;
import com.pronix.android.hmssample.GetBillinfoActivity;
import com.pronix.android.hmssample.HospitalsLookupActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.model.BillHistory;
import com.pronix.android.hmssample.model.Doctors;
import com.pronix.android.hmssample.model.Hospitallist;
import com.pronix.android.hmssample.model.MyIpModel;

import java.io.Serializable;
import java.util.ArrayList;

public class MyIpListAdapter extends RecyclerView.Adapter<MyIpListAdapter.MyViewHolder> {

    private ArrayList<MyIpModel> patientList;
    private String status;
    private Context context;
    public static String updatedate;

    public MyIpListAdapter(Context context, ArrayList<MyIpModel> list) {
        this.patientList = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorname, hospitalname,date,modeofjoining,noofdays,roomno,bedno;
        Button but_Start;
        CardView cv;
        LinearLayout roomdetails;
        Button interestedtojoin,getbillinfo;
        public MyViewHolder(View view) {
            super(view);
            doctorname = (TextView) view.findViewById(R.id.myip_doctor_name);
            hospitalname = (TextView) view.findViewById(R.id.myip_hospital_name);
            date = (TextView)view.findViewById(R.id.myip_date);
            modeofjoining = (TextView)view.findViewById(R.id.myip_hospital_joinmode);
            noofdays = (TextView)view.findViewById(R.id.myip_hospital_noofdays);
            roomno = (TextView)view.findViewById(R.id.ipRoomNo);
            bedno = (TextView)view.findViewById(R.id.ipBedNo);
            roomdetails = (LinearLayout)view.findViewById(R.id.roomdetails);
            interestedtojoin = (Button)view.findViewById(R.id.ipInterestedJoin);
            getbillinfo = (Button)view.findViewById(R.id.ipgetBillinfo);
            cv = (CardView)view.findViewById(R.id.myip);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_myip_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final MyIpModel myIpModel = patientList.get(position);
        holder.doctorname.setText(myIpModel.getDoctorName());
        holder.hospitalname.setText(myIpModel.getHospitalName());
        holder.date.setText(myIpModel.getDischargeDate());
        holder.noofdays.setText(myIpModel.getNoofdays());
        holder.modeofjoining.setText(myIpModel.getModeofjoining());
        if(myIpModel.getBedno()==null){
            holder.roomdetails.setVisibility(View.GONE);
            holder.interestedtojoin.setVisibility(View.VISIBLE);
        }else {
            holder.interestedtojoin.setVisibility(View.GONE);
            holder.roomdetails.setVisibility(View.VISIBLE);
            holder.roomno.setText(myIpModel.getRoomno());
            holder.bedno.setText(myIpModel.getBedno());
        }
        holder.getbillinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetBillinfoActivity.class);
                Bundle args = new Bundle();
                ArrayList<BillHistory> fileList = new ArrayList<BillHistory>();
                fileList = patientList.get(position).getBillHistories();
                args.putSerializable("ARRAYLIST",(Serializable)fileList);
                intent.putExtra("BUNDLE",args);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
