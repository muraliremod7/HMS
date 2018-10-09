package com.pronix.android.hmssample.model;

public class Hospitallist {
    public Hospitallist(){

    }

    public String getHopId() {
        return hopId;
    }

    public void setHopId(String hopId) {
        this.hopId = hopId;
    }

    public String getHopName() {
        return hopName;
    }

    public void setHopName(String hopName) {
        this.hopName = hopName;
    }

    public String getHosAddress() {
        return hosAddress;
    }

    public void setHosAddress(String hosAddress) {
        this.hosAddress = hosAddress;
    }

    private String hopId,hopName,hosAddress;
}
