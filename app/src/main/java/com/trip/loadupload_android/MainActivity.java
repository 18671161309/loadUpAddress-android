package com.trip.loadupload_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.trip.loadupload_android.service.BootStrapService;

import butterknife.ButterKnife;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startService(new Intent(this, BootStrapService.class));
//        finish();
    }

}
