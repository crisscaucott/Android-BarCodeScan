package com.example.ccaucott.barcodescan;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainFragment extends Fragment implements View.OnClickListener{
    private Button scanBtn;
    private TextView tvScanFormat, tvScanContent;
    private LinearLayout llSearch;
    OnFragmentInteractionListener mListener;

    interface OnFragmentInteractionListener{
        void onCameraButtonClick(boolean click);
    }

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        scanBtn = (Button) view.findViewById(R.id.scan_button);
        tvScanFormat = (TextView) view.findViewById(R.id.tvScanFormat);
        tvScanContent = (TextView) view.findViewById(R.id.tvScanContent);
        llSearch = (LinearLayout) view.findViewById(R.id.llSearch);

        scanBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            // handle scan result
            llSearch.setVisibility(View.VISIBLE);
            tvScanContent.setText(scanResult.getContents());
            tvScanFormat.setText(scanResult.getFormatName());
        }else{
            llSearch.setVisibility(View.GONE);
        }
        // else continue with any other code you need in the method
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        //mListener.onCameraButtonClick(true);

        /*
        FragmentIntentIntegrator integrator = new FragmentIntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
        *7

        /*
        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        CameraFragment cameraFragment = CameraFragment.newInstance();
        cameraFragment.setReenterTransition(slideTransition);
        cameraFragment.setExitTransition(slideTransition);
        cameraFragment.setAllowEnterTransitionOverlap(false);
        cameraFragment.setAllowReturnTransitionOverlap(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, cameraFragment)
                .addToBackStack(null)
                .commit();
        */



        llSearch.setVisibility(View.GONE);
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("Scan a barcode or QRcode");
        integrator.setOrientationLocked(true);
        integrator.initiateScan();


//        Use this for more customization
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
//        integrator.setPrompt("Scan a barcode");
//        integrator.setCameraId(0);  // Use a specific camera of the device
//        integrator.setBeepEnabled(false);
//        integrator.setBarcodeImageEnabled(true);
//        integrator.initiateScan();
    }

}
