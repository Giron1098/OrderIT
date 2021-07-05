package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ACT_Login extends AppCompatActivity {

    EditText et_email, et_password;
    Button btn_login;
    String email, password;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_login);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.BTN_Login);

        recuperarPreferencias();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=et_email.getText().toString();
                password=et_password.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()){
                    validarUsuario("http://"+ip+"/orderit/validarUsuario.php");
                }else{
                    Toast.makeText(ACT_Login.this,"No se permiten campos vacios",Toast.LENGTH_LONG).show();
                }

            }
        });

        //RUTAS

        RutaRegistrar();
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    guardarPreferencias();
                    Intent intent=new Intent(getApplicationContext(),EsperaActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ACT_Login.this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_Login.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("email",email);
                parametros.put("password",password);
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.apply();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        et_email.setText(preferences.getString("email",""));
        et_password.setText(preferences.getString("password",""));
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