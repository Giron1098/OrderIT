package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ACT_RegistroPedido extends AppCompatActivity {

    Bundle datos;

    String ip="192.168.1.5";

    int ID_Platillo;
    String nombrePlatillo;
    String nombreRestaurante;
    Double costo_platillo;
    String nombre_usuario;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro_pedido);

        SharedPreferences userDetails = ACT_RegistroPedido.this.getSharedPreferences("preferenciasLogin", MODE_PRIVATE);

        String email = userDetails.getString("email", "");
        String password = userDetails.getString("password", "");

        System.out.println(email);
        System.out.println(password);

        Calendar c = Calendar.getInstance();
        String dia, mes, annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        annio = Integer.toString(c.get(Calendar.YEAR));

        TextView tv_nombreRestaurante = findViewById(R.id.TV_NombreRestaurante);
        TextView tv_nombrePlatillo = findViewById(R.id.TV_NombrePlatillo);
        TextView tv_costoPlatillo = findViewById(R.id.TV_CostoPlatillo);
        TextView tv_totalPedido = findViewById(R.id.TV_TotalPedido);
        TextView tv_idPlatillo = findViewById(R.id.TV_IDPlatillo);
        TextView tv_datePedido = findViewById(R.id.TV_DatePedido);
        TextView tv_etiqueta = findViewById(R.id.TV_Etiqueta);


        EditText et_cantidadPedido = findViewById(R.id.ET_CantidadPedido);

        Button btn_hacerPedido = findViewById(R.id.BTN_HacerPedido);
        Button btn_calcularTotal = findViewById(R.id.BTN_CalcularTotal);

        datos = getIntent().getExtras();

        ID_Platillo = datos.getInt("ID_Platillo");
        nombrePlatillo = datos.getString("Platillo");
        nombreRestaurante = datos.getString("Restaurante");
        costo_platillo = datos.getDouble("Costo_Platillo");
        nombre_usuario = datos.getString("nombreUsuario");



        tv_nombreRestaurante.setText(""+nombreRestaurante);
        tv_nombrePlatillo.setText(""+nombrePlatillo);
        tv_costoPlatillo.setText("$"+costo_platillo);
        tv_idPlatillo.setText(""+ID_Platillo);
        tv_datePedido.setText(dia + "/" + mes +"/" + annio);




        obtenerDatos("http://"+ip+"/orderit/recuperaDatosUsuario.php?email="+email+"&password="+password);


        btn_calcularTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double total=0.0;
                String S_cantidad = et_cantidadPedido.getText().toString();

                Double cantidad = Double.parseDouble(S_cantidad);

                total = costo_platillo * cantidad;


                tv_totalPedido.setText(""+total);



            }
        });


    }

    private void obtenerDatos(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        TextView tv_idusuario = findViewById(R.id.tv_IDuser);
                        jsonObject = response.getJSONObject(i);
                        System.out.println(jsonObject.getInt("idUsuario"));
                        System.out.println(jsonObject.getString("nombreUsuario"));
                        tv_idusuario.setText(""+jsonObject.getInt("idUsuario"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}