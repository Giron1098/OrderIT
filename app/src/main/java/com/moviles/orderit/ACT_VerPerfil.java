package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ACT_VerPerfil extends AppCompatActivity {

    List<Usuario> usuarioList;

    RecyclerView recyclerView;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_ver_perfil);

        recyclerView = findViewById(R.id.RV_Perfil);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        usuarioList = new ArrayList<>();

        SharedPreferences userDetails = ACT_VerPerfil.this.getSharedPreferences("preferenciasLogin", MODE_PRIVATE);

        String email = userDetails.getString("email", "");
        String password = userDetails.getString("password", "");





        obtenerDatosUsuario("http://"+ip+"/orderit/obtenerDatosUsuario.php?email="+email+"&password="+password);

    }

    private void obtenerDatosUsuario(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                System.out.println(array.length());

                                JSONObject usuario = array.getJSONObject(i);


                                usuarioList.add(new Usuario(
                                        usuario.getInt("idUsuario"),
                                        usuario.getString("nombreUsuario"),
                                        usuario.getString("apPaterno"),
                                        usuario.getString("apMaterno"),
                                        usuario.getString("email"),
                                        usuario.getString("password")
                                ));
                            }

                            UsuarioAdapter usuarioAdapter = new UsuarioAdapter(ACT_VerPerfil.this, usuarioList);
                            recyclerView.setAdapter(usuarioAdapter);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}