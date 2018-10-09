package com.pronix.android.hmssample.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pronix.android.hmssample.DoctorsLookupActivity;
import com.pronix.android.hmssample.ProfileActivity;
import com.pronix.android.hmssample.R;
import com.pronix.android.hmssample.common.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

public CardView cv_Profile,myAppointments,doctor_lookup;
    private Fragment selectedFragment;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        cv_Profile = (CardView) rootView.findViewById(R.id.Profile);
        cv_Profile.setOnClickListener(this);
        myAppointments = (CardView) rootView.findViewById(R.id.myAppointments);
        myAppointments.setOnClickListener(this);
        doctor_lookup = (CardView) rootView.findViewById(R.id.doctor_lookup);
        doctor_lookup.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Profile:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;
            case R.id.myAppointments:
                selectedFragment = ListFragment.newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.patient_frame_layout, selectedFragment);
                transaction.commit();
                break;
            case R.id.doctor_lookup:
                Intent intent = new Intent(getActivity(), DoctorsLookupActivity.class);
                intent.putExtra("patid", Constants.userDetails.userId);
                startActivity(intent);
                break;
        }
    }
}
