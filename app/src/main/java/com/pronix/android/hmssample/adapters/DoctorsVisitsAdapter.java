package com.pronix.android.hmssample.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pronix.android.hmssample.PrescriptionActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.model.DoctorVisits;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DoctorsVisitsAdapter extends RecyclerView.Adapter<DoctorsVisitsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<DoctorVisits> doctorVisitsList;
    private int count = 0;
    private int currentDatePosition;

    public DoctorsVisitsAdapter(List<DoctorVisits> doctorVisitsList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.doctorVisitsList = doctorVisitsList;
    }

    @NonNull
    @Override
    public DoctorsVisitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.doctor_appointments, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsVisitsAdapter.ViewHolder holder, final int position) {
        holder.doctorName.setText(doctorVisitsList.get(position).getFullName());


        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(doctorVisitsList.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        holder.date.setText(dateString);

        holder.fee.setText(doctorVisitsList.get(position).getConsultationFee());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (position > 0) {
            holder.heading.setVisibility(View.GONE);
            if (dateString.equals(dateFormat.format(new Date()))) {
                if (count == 0) {
                    holder.heading.setVisibility(View.VISIBLE);
                    holder.heading.setText("Today Appointments");
                    count++;
                    currentDatePosition = position;
                }
            }else {
                if(position > currentDatePosition && count == 1){
                    holder.heading.setVisibility(View.VISIBLE);
                    holder.heading.setText("Old Appointments");
                    count++;
                }
            }
        }
        if(doctorVisitsList.get(position).getPrescription() == null)
            holder.prescription_d.setVisibility(View.GONE);
        else{
            holder.prescription_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(context, PrescriptionActivity.class);
                   intent.putExtra("doctor_name",doctorVisitsList.get(position).getFullName());
                   intent.putExtra("prexcription",doctorVisitsList.get(position).getPrescription());
                   context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return doctorVisitsList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName,date,fee,heading;
        TextView prescription_d;
        ViewHolder(View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.doctorName);
            date = itemView.findViewById(R.id.date);
            fee = itemView.findViewById(R.id.fee);
            prescription_d = itemView.findViewById(R.id.prescription_d);
            heading = itemView.findViewById(R.id.heading);

        }
    }
}
