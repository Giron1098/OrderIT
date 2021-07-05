package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ACT_Registro extends AppCompatActivity {

    EditText et_nombre,et_apPaterno,et_apMaterno,et_email,et_password;
    Button btn_registroUsuario;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_registro);

        et_nombre=findViewById(R.id.ET_NombreRegister);
        et_apPaterno=findViewById(R.id.ET_ApPaternoRegister);
        et_apMaterno=findViewById(R.id.ET_ApMaternoRegister);
        et_email=findViewById(R.id.ET_EmailRegister);
        et_password=findViewById(R.id.ET_PasswordRegister);
        btn_registroUsuario=findViewById(R.id.BTN_SubmitRegister);

        btn_registroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_nombre.getText().toString().isEmpty() && !et_apPaterno.getText().toString().isEmpty() && !et_apMaterno.getText().toString().isEmpty()
                        && !et_email.getText().toString().isEmpty() && !et_password.getText().toString().isEmpty() ){
                    ejecutarServicio("http://"+ip+"/orderit/registroUsuario.php");
                }else{
                    Toast.makeText(getApplicationContext(), "No se premiten campos vacios", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
                et_nombre.setText("");
                et_apPaterno.setText("");
                et_apMaterno.setText("");
                et_email.setText("");
                et_password.setText("");

                Intent int_act_loginForm= new Intent(ACT_Registro.this   , ACT_Login.class);
                startActivity(int_act_loginForm);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("nombreUsuario",et_nombre.getText().toString());
                parametros.put("apPaterno",et_apPaterno.getText().toString());
                parametros.put("apMaterno",et_apMaterno.getText().toString());
                parametros.put("email",et_email.getText().toString());
                parametros.put("password",et_password.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}