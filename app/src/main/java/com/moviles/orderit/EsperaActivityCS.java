package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class EsperaActivityCS extends AppCompatActivity {

    ProgressBar progressBarCS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_espera_activity_cs);

        progressBarCS=findViewById(R.id.progressBarCS);
        progressBarCS.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),ACT_Login.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}