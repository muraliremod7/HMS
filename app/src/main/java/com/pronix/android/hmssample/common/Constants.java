package com.pronix.android.hmssample.common;

import com.pronix.android.hmssample.model.UserDetails;

public class Constants {

    public static int DATABASE_VERSION = 1;
    public static UserDetails userDetails;
    public static String URLBase = "http://192.168.0.131:1234/hms/patient/";
    //public static String URLBase = "http://env-0687861.cloud.cms500.com/hms/patient/";

    //RequestMethods
    public static String REQUEST_REGISTER = "register";
    public static String REQUEST_LOGIN = "login";
    public static String REQUEST_PROFILE = "viewProfile";
    public static String REQUEST_UPDATEPROFILE = "updateProfile";
    public static String REQUEST_DOCTORSLOOKUP = "doctorLookup";
    public static String REQUEST_APPOINTMENT = "bookAppointment";
    public static String REQUEST_myAppointments = "myAppointments";
    public static String REQUEST_HOSPITAL_LIST = "getAllHospitals";
    public static String REQUEST_doctorListInHospital = "doctorListInHospital";
    public static String REQUEST_myDoctors = "myDoctors";
    public static String REQUEST_MYIPLIST = "myIpDetails";
    //Service
    public static String SUCCESS = "SUCCESS";
    public static String FAILED = "FAILED";
    public static String EXCEPTION = "EXCEPTION";
    public static String SENT = "SENT";
}
