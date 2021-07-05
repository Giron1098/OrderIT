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

import com.android.volley.AuthFailureError;
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

import java.nio.DoubleBuffer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ACT_RegistroPedido extends AppCompatActivity {

    Bundle datos;

    // DIRECCIÓN IP

    Constantes constante = new Constantes();

    String ip = constante.IP;

    int ID_Platillo;
    String nombrePlatillo;
    String nombreRestaurante;
    Double costo_platillo;
    String nombre_usuario;
    int costo_entrega;
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
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));

        TextView tv_nombreRestaurante = findViewById(R.id.TV_NombreRestaurante);
        TextView tv_nombrePlatillo = findViewById(R.id.TV_NombrePlatillo);
        TextView tv_costoPlatillo = findViewById(R.id.TV_CostoPlatillo);
        TextView tv_totalPedido = findViewById(R.id.TV_TotalPedido);
        TextView tv_idPlatillo = findViewById(R.id.TV_IDPlatillo);
        TextView tv_datePedido = findViewById(R.id.TV_DatePedido);
        TextView tv_costo_entrega = findViewById(R.id.TV_CostoEntPedido);



        EditText et_cantidadPedido = findViewById(R.id.ET_CantidadPedido);



        Button btn_hacerPedido = findViewById(R.id.BTN_HacerPedido);
        Button btn_calcularTotal = findViewById(R.id.BTN_CalcularTotal);

        datos = getIntent().getExtras();

        ID_Platillo = datos.getInt("ID_Platillo");
        nombrePlatillo = datos.getString("Platillo");
        nombreRestaurante = datos.getString("Restaurante");
        costo_platillo = datos.getDouble("Costo_Platillo");
        nombre_usuario = datos.getString("nombreUsuario");
        costo_entrega = datos.getInt("Costo_Entrega");



        tv_nombreRestaurante.setText(""+nombreRestaurante);
        tv_nombrePlatillo.setText(""+nombrePlatillo);
        tv_costoPlatillo.setText("$"+costo_platillo);
        tv_idPlatillo.setText(""+ID_Platillo);
        tv_datePedido.setText(dia + "/" + mes +"/" + annio);
        tv_costo_entrega.setText(""+costo_entrega);


        tv_idPlatillo.setVisibility(View.INVISIBLE);
        tv_datePedido.setVisibility(View.INVISIBLE);




        obtenerDatos("http://"+ip+"/orderit/recuperaDatosUsuario.php?email="+email+"&password="+password);
        TextView tv_idusuario=findViewById(R.id.tv_IDuser);

        btn_calcularTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double total=0.0;
                String S_cantidad = et_cantidadPedido.getText().toString();

                Double cantidad = Double.parseDouble(S_cantidad);

                total = (costo_platillo * cantidad) + costo_entrega;


                tv_totalPedido.setText(""+total);



            }
        });

        btn_hacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarPedido("http://"+ip+"/orderit/registroPedido.php?fecha="+tv_datePedido.getText().toString()+"&cantidad="+et_cantidadPedido.getText().toString()+
                        "&total="+tv_totalPedido.getText().toString()+"&Platillos_idPlatillos="+tv_idPlatillo.getText().toString()+"&Usuario_idUsuario="+tv_idusuario.getText().toString());
            }
        });


    }

    private void ejecutarPedido(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "PEDIDO REALIZADO", Toast.LENGTH_LONG).show();

                Intent int_act_loginForm= new Intent(ACT_RegistroPedido.this   , ACT_Restaurantes.class);
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
                TextView tv_totalPedido = findViewById(R.id.TV_TotalPedido);
                TextView tv_idPlatillo = findViewById(R.id.TV_IDPlatillo);
                TextView tv_datePedido = findViewById(R.id.TV_DatePedido);
                TextView tv_idusuario = findViewById(R.id.tv_IDuser);
                EditText et_cantidadPedido = findViewById(R.id.ET_CantidadPedido);

                Map<String,String> parametros=new HashMap<String, String>();

                parametros.put("fecha",tv_datePedido.getText().toString());

                parametros.put("cantidad",et_cantidadPedido.getText().toString());

                parametros.put("total",tv_totalPedido.getText().toString());

                parametros.put("idPlatillos",tv_idPlatillo.getText().toString());

                parametros.put("idUsuario",tv_idusuario.getText().toString());


                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
                        tv_idusuario.setVisibility(View.INVISIBLE);
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
}