package com.pronix.android.hmssample.model;

import java.util.Date;

public class DoctorVisits {

    private String date;
    private String fullName;
    private String hospitalName;
    private String specialization;
    private String exp;
    private String qualification;
    private String timings;
    private String consultationFee;
    private String prescription;

    public DoctorVisits() {
        super();
    }

    public DoctorVisits(String date, String fullName, String hospitalName, String specialization, String exp,
                        String qualification, String timings, String consultationFee,String prescription) {
        super();
        this.date = date;
        this.fullName = fullName;
        this.hospitalName = hospitalName;
        this.specialization = specialization;
        this.exp = exp;
        this.qualification = qualification;
        this.timings = timings;
        this.consultationFee = consultationFee;
        this.prescription = prescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
