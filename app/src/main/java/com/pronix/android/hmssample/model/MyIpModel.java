package com.pronix.android.hmssample.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MyIpModel {
    public MyIpModel(){
    }
    public MyIpModel(String doctorName,String appointmentId,ArrayList<BillHistory> billHistories, String roomno,String bedno,String hospitalName, String hospitalAddress, String dischargeDate,String modeofjoining,String noofdays) {
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.dischargeDate = dischargeDate;
        this.modeofjoining = modeofjoining;
        this.noofdays = noofdays;
        this.roomno = roomno;
        this.bedno = bedno;
        this.billHistories = billHistories;
        this.appointmentId = appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    private String doctorName;
    private String hospitalName;
    private String hospitalAddress;
    private String dischargeDate;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    private String appointmentId;

    public String getModeofjoining() {
        return modeofjoining;
    }

    public void setModeofjoining(String modeofjoining) {
        this.modeofjoining = modeofjoining;
    }

    public String getNoofdays() {
        return noofdays;
    }

    public void setNoofdays(String noofdays) {
        this.noofdays = noofdays;
    }

    private String modeofjoining;
    private String noofdays;

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    private String roomno,bedno;

    public ArrayList<BillHistory> getBillHistories() {
        return billHistories;
    }

    public void setBillHistories(ArrayList<BillHistory> billHistories) {
        this.billHistories = billHistories;
    }

    private ArrayList<BillHistory> billHistories;

}

