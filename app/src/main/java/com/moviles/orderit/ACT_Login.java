package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ACT_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_login);

        //RUTAS

        RutaRegistrar();
    }

    private void RutaRegistrar() {

        Button btn_FormRegister = findViewById(R.id.BTN_Register);

        btn_FormRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_act_registerForm= new Intent(ACT_Login.this, ACT_Registro.class);
                startActivity(int_act_registerForm);
            }
        });

    }
}