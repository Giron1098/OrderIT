package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ACT_Restaurantes extends AppCompatActivity {

    Button btn_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_restaurantes);
        btn_cerrarSesion=findViewById(R.id.btn_cerrarSesion);

        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent=new Intent(getApplicationContext(), ACT_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}