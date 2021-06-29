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
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                boolean sesion=preferences.getBoolean("sesion",false);
                if(sesion){
                    Intent intent=new Intent(getApplicationContext(),ACT_Restaurantes.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(getApplicationContext(),ACT_Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}