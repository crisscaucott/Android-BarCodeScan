package com.example.ccaucott.barcodescan;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;


public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Carga el fragment principal en la actividad.
        setupLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupLayout(){
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));

        MainFragment mainFragment = MainFragment.newInstance();
        mainFragment.setReenterTransition(slideTransition);
        mainFragment.setExitTransition(slideTransition);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mainFragment)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCameraButtonClick(boolean click) {
        if (click){
            Slide slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

            CameraFragment cameraFragment = CameraFragment.newInstance();
            cameraFragment.setReenterTransition(slideTransition);
            cameraFragment.setExitTransition(slideTransition);
            cameraFragment.setAllowEnterTransitionOverlap(false);
            cameraFragment.setAllowReturnTransitionOverlap(false);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, cameraFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
