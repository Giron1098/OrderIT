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

public class ACT_EditarPerfil extends AppCompatActivity {

    Bundle datos;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    int ID_Usuario;
    String nombre, ApPaterno, ApMaterno, Email, Password;

    EditText ET_NombrePerfil, ET_ApPaternoPerfil, ET_ApMaternoPerfil, ET_EmailPerfil, ET_PasswordPerfil;

    Button BTN_GuardarCambiosPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_editar_perfil);

        ET_NombrePerfil = findViewById(R.id.ET_NombrePerfil);
        ET_ApPaternoPerfil = findViewById(R.id.ET_ApPaternoPerfil);
        ET_ApMaternoPerfil = findViewById(R.id.ET_ApMaternoPerfil);
        ET_EmailPerfil = findViewById(R.id.ET_EmailPerfil);
        ET_PasswordPerfil = findViewById(R.id.ET_PasswordPerfil);

        BTN_GuardarCambiosPerfil = findViewById(R.id.BTN_GuardarCambiosPerfil);

        datos = getIntent().getExtras();

        ID_Usuario = datos.getInt("ID_Usuario");
        nombre= datos.getString("Nombre");
        ApPaterno = datos.getString("ApPaterno");
        ApMaterno = datos.getString("ApMaterno");
        Email = datos.getString("Email");
        Password = datos.getString("Password");

        System.out.println("DATOS DEL BUNDLE");

        System.out.println(ID_Usuario);
        System.out.println(nombre);
        System.out.println(ApPaterno);
        System.out.println(ApMaterno);
        System.out.println(Email);
        System.out.println(Password);

        ET_NombrePerfil.setText(nombre);
        ET_ApPaternoPerfil.setText(ApPaterno);
        ET_ApMaternoPerfil.setText(ApMaterno);
        ET_EmailPerfil.setText(Email);
        ET_PasswordPerfil.setText(Password);


        BTN_GuardarCambiosPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreET, apPaternoET, apMaternoET, emailET, passwordET;

                nombreET = ET_NombrePerfil.getText().toString();
                apPaternoET = ET_ApPaternoPerfil.getText().toString();
                apMaternoET = ET_ApMaternoPerfil.getText().toString();
                emailET = ET_EmailPerfil.getText().toString();
                passwordET= ET_PasswordPerfil.getText().toString();


                if(nombreET.length() != nombre.length() || apPaternoET.length() != ApPaterno.length() || apMaternoET.length() != ApMaterno.length() || emailET.length() != Email.length() || passwordET.length() != Password.length() ) {
                    System.out.println("HUBO CAMBIOS");

                    actualizarDatosUsuario("http://"+ip+"/orderit/updateDatosUsuario.php?nombreUsuario="+nombreET+"&apPaterno="+apPaternoET+"&apMaterno="+apMaternoET+"&email="+emailET+"&password="+passwordET+"&idUsuario="+ID_Usuario);

                }else
                {
                    Toast.makeText(getApplicationContext(), "SIN CAMBIOS EN SUS DATOS", Toast.LENGTH_LONG).show();

                    Intent int_act_registerForm= new Intent(ACT_EditarPerfil.this, ACT_VerPerfil.class);
                    startActivity(int_act_registerForm);

                    System.out.println("NO HUBO CAMBIOS");
                }
            }
        });

    }

    private void actualizarDatosUsuario(String URL) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACIÓN ÉXITOSA, POR FAVOR VUELVA A INICIAR SESIÓN PARA APLICAR LOS CAMBIOS", Toast.LENGTH_LONG).show();

                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent=new Intent(getApplicationContext(), EsperaActivityCS.class);
                startActivity(intent);
                finish();

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
                parametros.put("nombreUsuario", ET_NombrePerfil.getText().toString());
                parametros.put("apPaterno", ET_ApPaternoPerfil.getText().toString());
                parametros.put("apMaterno", ET_ApMaternoPerfil.getText().toString());
                parametros.put("email", ET_EmailPerfil.getText().toString());
                parametros.put("password", ET_PasswordPerfil.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}