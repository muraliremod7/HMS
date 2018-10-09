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

public class BillinfoAdapter extends RecyclerView.Adapter<BillinfoAdapter.MyViewHolder> {

    private ArrayList<BillHistory> patientList;
    private String status;
    private Context context;
    public static String updatedate;

    public BillinfoAdapter(Context context, ArrayList<BillHistory> list) {
        this.patientList = list;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView billId, billAmount,billDetails,billIssuedDate,billIssuedBy,receiptId,paidBillAmount,diffBillAmount,billPaidDate,billPaidBy;
        Button but_Start;
        CardView cv;
        LinearLayout roomdetails;
        Button paybill;
        public MyViewHolder(View view) {
            super(view);
            billId = (TextView) view.findViewById(R.id.billinfo_billid);
            billAmount = (TextView) view.findViewById(R.id.billinfo_billamount);
            billDetails = (TextView)view.findViewById(R.id.billinfo_billdetails);
            billIssuedDate = (TextView)view.findViewById(R.id.billinfo_billissueddate);
            billIssuedBy = (TextView)view.findViewById(R.id.billinfo_billissuedby);
            receiptId = (TextView)view.findViewById(R.id.billinfo_recieptid);
            paidBillAmount = (TextView)view.findViewById(R.id.billinfo_paidamount);
            diffBillAmount = (TextView)view.findViewById(R.id.billinfo_dueamount);
            billPaidDate = (TextView)view.findViewById(R.id.billinfo_billpaiddate);
            billPaidBy = (TextView)view.findViewById(R.id.billinfo_billpaidby);

            paybill = (Button)view.findViewById(R.id.billinfo_paybill);
            cv = (CardView)view.findViewById(R.id.myip);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_billinfi_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final BillHistory myIpModel = patientList.get(position);
        holder.billId.setText(myIpModel.getBillid());
        holder.billAmount.setText(myIpModel.getBillamount());
        holder.billDetails.setText(myIpModel.getBilldetails());
        holder.billIssuedDate.setText(myIpModel.getBillissueddate());
        holder.billIssuedBy.setText(myIpModel.getBillissuedby());
        holder.receiptId.setText(myIpModel.getReceiptId());
        holder.paidBillAmount.setText(myIpModel.getPaidBillAmount());
        if(myIpModel.getDiffBillAmount().equals("0")){
            holder.paybill.setVisibility(View.GONE);
            holder.diffBillAmount.setText(myIpModel.getDiffBillAmount());
            holder.billPaidDate.setText(myIpModel.getBillPaidDate());
            holder.billPaidBy.setText(myIpModel.getBillPaidBy());
        }else {
            holder.paybill.setVisibility(View.VISIBLE);
            holder.diffBillAmount.setText(myIpModel.getDiffBillAmount());
            holder.billPaidDate.setText(myIpModel.getBillPaidDate());
            holder.billPaidBy.setText(myIpModel.getBillPaidBy());
        }
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}
