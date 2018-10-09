package com.pronix.android.hmssample;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.pronix.android.hmssample.common.Constants;
import com.pronix.android.hmssample.common.Utils;
import com.pronix.android.hmssample.model.DoctorProfile;
import com.pronix.android.hmssample.model.Doctors;
import com.pronix.android.hmssample.model.WebServiceDO;
import com.pronix.android.hmssample.services.AsyncTask;
import com.pronix.android.hmssample.services.OnTaskCompleted;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoctorProfileActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private Toolbar mToolbar;
    private ViewFlipper viewFlipper;
    private TextView timings, doctor_name, qualification, specialization, experience, consultationFee;
    private ImageView doctor_profile;
    Doctors doctorsData;
    Dialog dialog;
    RelativeLayout appointmentDate;
    static TextView tv_AppointmentDate;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        init();
        doctorsData = (Doctors) getIntent().getSerializableExtra("DoctorsDetails");
        setValues(doctorsData);
       /* mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Doctor Name");*/

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(doctorsData.getDoctorName());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        /*viewFlipper = findViewById(R.id.viewFlipper);
        int[] images = new int[]{R.drawable.hospital_building, R.drawable.doctor_icon};
        for (int i=0; i < images.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            viewFlipper.addView(imageView);
        }*/
    }

    void init() {
        doctor_profile = findViewById(R.id.doctor_profile);
        doctor_name = findViewById(R.id.doctor_name);
        qualification = findViewById(R.id.qualification);
        specialization = findViewById(R.id.specialization);
        experience = findViewById(R.id.experience);
        consultationFee = findViewById(R.id.consultationFee);
        timings = findViewById(R.id.timings);
        appointmentDate = (RelativeLayout) findViewById(R.id.appointmentDate);
        appointmentDate.setOnClickListener(this);
        tv_AppointmentDate = findViewById(R.id.date);
        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void bookAppointment(View view) {

        if(!tv_AppointmentDate.getText().toString().trim().equals(""))
        {
            calWebService();
            Utils.showalert(DoctorProfileActivity.this, "Alert", "Appointment booked successfully");
            Intent intent = new Intent(this,MainActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Utils.showalert(this, "Alert", "Please select appointment date");
        }



    }

    public void setValues(Doctors doctors) {
        doctor_name.setText(doctors.getDoctorName());
        qualification.setText(doctors.getDoctorProfile().getQualification());
        specialization.setText(doctors.getDoctorProfile().getSpecialization());
        experience.setText(doctors.getDoctorProfile().getExp());
        consultationFee.setText(doctors.getDoctorProfile().getConsultationFee());
        timings.setText(doctors.getDoctorProfile().getTimings());
    }

    public void calWebService() {
        try {
            progressDialog(this);
            WebServiceDO webServiceDO = new WebServiceDO();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("patientId", Constants.userDetails.userId);
            jsonObject.put("doctorId", doctorsData.getDoctorProfile().getUserId());
            jsonObject.put("hospitalId", doctorsData.getDoctorProfile().getHospitalId());
            jsonObject.put("date",TimeStampConverter("dd-MM-yyyy", tv_AppointmentDate.getText().toString(), "yyyy-MM-dd") );
            webServiceDO.result = Constants.SENT;
            webServiceDO.request = "APPOINTMENT";
            new AsyncTask(DoctorProfileActivity.this, DoctorProfileActivity.this, Constants.URLBase + "" + Constants.REQUEST_APPOINTMENT, "POST", jsonObject.toString()).execute(webServiceDO);
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    public void progressDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressview);
        dialog.show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.appointmentDate:
                showDatePickerDialog();
                break;
        }

    }

    @Override
    public void onTaskCompletes(WebServiceDO webServiceDO) {
        try {
            Utils.hideProgress(dialog);
            if (webServiceDO.result.equals(Constants.SUCCESS)) {
                if (webServiceDO.request.equals("APPOINTMENT")) {
                    JSONObject jsonObject = new JSONObject(webServiceDO.responseContent);
                    if (jsonObject.getString("status").toUpperCase().equals("SUCCESS")) {
                        Utils.showalert(DoctorProfileActivity.this, "Alert", "Appointment booked successfully");
                        Intent intent = new Intent(this,MainActivity.class);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Utils.showalert(DoctorProfileActivity.this, "Alert", jsonObject.getString("errorDescription"));
                    }

                }
            } else {
                Utils.showalert(DoctorProfileActivity.this, "Alert", webServiceDO.responseContent);
            }
        } catch (Exception e) {
            e.getMessage();
            Utils.hideProgress(dialog);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private String inputTimeStamp;
        private String inputFormat;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            //final Calendar d = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            c.add(Calendar.DATE, 60);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int month1 = month+1;
            inputTimeStamp = year+"-"+month1+"-"+day;
            System.out.println("dateformat: "+ inputTimeStamp);
            inputFormat = "yyyy-MM-dd";
            final String outputFormat = "dd-MM-yyyy";
            try {
                tv_AppointmentDate.setText( TimeStampConverter(inputFormat, inputTimeStamp, outputFormat));

            } catch (ParseException e) {
                e.printStackTrace();
            };
        }
    }

    private static String TimeStampConverter(final String inputFormat,
                                             String inputTimeStamp, final String outputFormat)
            throws ParseException {
        return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(
                inputFormat).parse(inputTimeStamp));
    }
}
