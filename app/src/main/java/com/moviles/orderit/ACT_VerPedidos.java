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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ACT_VerPedidos extends AppCompatActivity {

    List<Pedido> pedidoList;

    RecyclerView recyclerView;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_act_ver_pedidos);

        recyclerView = findViewById(R.id.RV_VerPedidos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pedidoList = new ArrayList<>();

        SharedPreferences userDetails = ACT_VerPedidos.this.getSharedPreferences("preferenciasLogin", MODE_PRIVATE);

        String email = userDetails.getString("email", "");
        String password = userDetails.getString("password", "");





        obtenerDatos("http://"+ip+"/orderit/recuperaDatosUsuario.php?email="+email+"&password="+password);



    }

    private void obtenerDatos(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        TextView tv_idusuario = findViewById(R.id.TV_IdUsuarioPedido);
                        jsonObject = response.getJSONObject(i);
                        System.out.println(jsonObject.getInt("idUsuario"));
                        System.out.println(jsonObject.getString("nombreUsuario"));
                        tv_idusuario.setText(""+jsonObject.getInt("idUsuario"));
                        tv_idusuario.setVisibility(View.INVISIBLE);
                        verPedidos("http://"+ip+"/orderit/consultaPedidos.php?idUsuario="+tv_idusuario.getText().toString());
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void verPedidos(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                System.out.println(array.length());

                                JSONObject pedido = array.getJSONObject(i);


                                pedidoList.add(new Pedido(
                                        pedido.getInt("idPedidos"),
                                        pedido.getString("fecha"),
                                        pedido.getDouble("total"),
                                        pedido.getDouble("cantidad"),
                                        pedido.getString("nombrePlatillo"),
                                        pedido.getDouble("precio"),
                                        pedido.getString("nombreRest"),
                                        pedido.getInt("costoEntrega")
                                ));
                            }

                            PedidoAdapter pedidoAdapter = new PedidoAdapter(ACT_VerPedidos.this, pedidoList);
                            recyclerView.setAdapter(pedidoAdapter);

                        } catch (JSONException e) {

                            TextView tv_idusuario = findViewById(R.id.TV_IdUsuarioPedido);
                            tv_idusuario.setVisibility(View.VISIBLE);
                            tv_idusuario.setText("SIN PEDIDOS REALIZADOS");

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