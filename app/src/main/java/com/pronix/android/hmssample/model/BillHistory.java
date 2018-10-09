package com.pronix.android.hmssample.model;

import java.io.Serializable;

public class BillHistory implements Serializable{
    public BillHistory(){

    }

    public BillHistory(String billid, String billamount, String billdetails, String billissueddate, String billissuedby, String billIssuedAuthority, String receiptId, String paidBillAmount, String diffBillAmount, String billPaidDate, String billPaidBy, String receiptGeneratedBy) {
        this.billid = billid;
        this.billamount = billamount;
        this.billdetails = billdetails;
        this.billissueddate = billissueddate;
        this.billissuedby = billissuedby;
        this.billIssuedAuthority = billIssuedAuthority;
        this.receiptId = receiptId;
        this.paidBillAmount = paidBillAmount;
        this.diffBillAmount = diffBillAmount;
        this.billPaidDate = billPaidDate;
        this.billPaidBy = billPaidBy;
        this.receiptGeneratedBy = receiptGeneratedBy;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
    }

    public String getBilldetails() {
        return billdetails;
    }

    public void setBilldetails(String billdetails) {
        this.billdetails = billdetails;
    }

    public String getBillissueddate() {
        return billissueddate;
    }

    public void setBillissueddate(String billissueddate) {
        this.billissueddate = billissueddate;
    }

    public String getBillissuedby() {
        return billissuedby;
    }

    public void setBillissuedby(String billissuedby) {
        this.billissuedby = billissuedby;
    }

    public String getBillIssuedAuthority() {
        return billIssuedAuthority;
    }

    public void setBillIssuedAuthority(String billIssuedAuthority) {
        this.billIssuedAuthority = billIssuedAuthority;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getPaidBillAmount() {
        return paidBillAmount;
    }

    public void setPaidBillAmount(String paidBillAmount) {
        this.paidBillAmount = paidBillAmount;
    }

    public String getDiffBillAmount() {
        return diffBillAmount;
    }

    public void setDiffBillAmount(String diffBillAmount) {
        this.diffBillAmount = diffBillAmount;
    }

    public String getBillPaidDate() {
        return billPaidDate;
    }

    public void setBillPaidDate(String billPaidDate) {
        this.billPaidDate = billPaidDate;
    }

    public String getBillPaidBy() {
        return billPaidBy;
    }

    public void setBillPaidBy(String billPaidBy) {
        this.billPaidBy = billPaidBy;
    }

    public String getReceiptGeneratedBy() {
        return receiptGeneratedBy;
    }

    public void setReceiptGeneratedBy(String receiptGeneratedBy) {
        this.receiptGeneratedBy = receiptGeneratedBy;
    }

    private String billid,billamount,billdetails,billissueddate,billissuedby,billIssuedAuthority,receiptId,paidBillAmount,diffBillAmount,billPaidDate,billPaidBy,receiptGeneratedBy;
}
