package com.moviles.orderit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
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
import java.util.Calendar;
import java.util.List;

public class ACT_Restaurantes extends AppCompatActivity {

    Toolbar toolbar_menu;

    List<Restaurante> restauranteList;

    RecyclerView recyclerView;

    //PONGAN SU PROPIA IP gggg
    String ip="192.168.1.5";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_restaurantes);

        toolbar_menu = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_menu);

        recyclerView = findViewById(R.id.RV_ListaRestaurantes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restauranteList = new ArrayList<>();

        obtenerFecha();


        ejecutarServicio("http://"+ip+"/orderit/consultaRestaurantePedido.php");
        
    }

    private void obtenerFecha() {

        Calendar c = Calendar.getInstance();
        TextView tv_titulo = findViewById(R.id.TV_Titulo);

        String dia, mes, annio;

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        annio = Integer.toString(c.get(Calendar.YEAR));

        System.out.println (dia + "/" + mes +"/" + annio);

        //tv_titulo.setText(dia + "/" + mes +"/" + annio);

    }

    private void ejecutarServicio(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject restaurante = array.getJSONObject(i);



                        restauranteList.add(new Restaurante(
                                restaurante.getInt("idRestaurante"),
                                restaurante.getString("nombreRest"),
                                restaurante.getString("direccion"),
                                restaurante.getString("horario"),
                                restaurante.getString("tiempoEstimado"),
                                restaurante.getInt("costoEntrega"),
                                restaurante.getInt("idPlatillos"),
                                restaurante.getString("nombrePlatillo"),
                                restaurante.getDouble("precio")
                        ));
                    }

                    RestauranteAdapter restauranteAdapter = new RestauranteAdapter(ACT_Restaurantes.this,restauranteList);
                    recyclerView.setAdapter(restauranteAdapter);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lay_menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.op_ver_perfil)
        {
            Toast.makeText(ACT_Restaurantes.this,"Ver perfil",Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op_ver_pedidos)
        {
            Toast.makeText(ACT_Restaurantes.this,"Ver pedidos",Toast.LENGTH_SHORT).show();
        } else if (id==R.id.op_cerrar_sesion)
        {
            SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            preferences.edit().clear().commit();
            Intent intent=new Intent(getApplicationContext(), EsperaActivityCS.class);
            startActivity(intent);
            finish();
        }

        return true;
    }
}