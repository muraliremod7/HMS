package com.pronix.android.hmssample.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.pronix.android.hmssample.DoctorsLookupActivity;
import com.pronix.android.hmssample.HospitalsLookupActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.model.Hospitallist;

import java.util.ArrayList;

public class HospitalLookUpAdapter extends RecyclerView.Adapter<HospitalLookUpAdapter.MyViewHolder> {

    private ArrayList<Hospitallist> patientList;
    private String status;
    private Context context;
    public static String updatedate;

    public HospitalLookUpAdapter(Context context, ArrayList<Hospitallist> list) {
        this.patientList = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hospitalname, hospitaladdress,hospitalId;
        Button but_Start;
        CardView cv;
        public MyViewHolder(View view) {
            super(view);
            hospitalname = (TextView) view.findViewById(R.id.hospital_id_hopname);
            hospitaladdress = (TextView) view.findViewById(R.id.hospitalname_and_address);
            hospitalId = (TextView)view.findViewById(R.id.hospital_id);
            cv = (CardView)view.findViewById(R.id.hospital_lookup);
        }
    }

    @NonNull
    @Override
    public HospitalLookUpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_hospitalnames_singlerow, parent, false);
        return new HospitalLookUpAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalLookUpAdapter.MyViewHolder holder, int position) {
        final Hospitallist patientDO = patientList.get(position);
        holder.hospitalname.setText(patientDO.getHopName());
        holder.hospitaladdress.setText(patientDO.getHosAddress());


        holder.hospitalId.setText(patientDO.getHopId());
       holder.hospitalname.setText(patientDO.getHopName());
       holder.hospitaladdress.setText(patientDO.getHosAddress());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientid = patientDO.getHopId();
                Intent intent = new Intent(context, DoctorsLookupActivity.class);
                intent.putExtra("hosId",patientid);
                context.startActivity(intent);
                ((HospitalsLookupActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
