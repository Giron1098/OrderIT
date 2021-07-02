package com.moviles.orderit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ACT_RegistroPedido extends AppCompatActivity {

    Bundle datos;

    int ID_Platillo;
    String nombrePlatillo;
    String nombreRestaurante;
    Double costo_platillo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro_pedido);

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


        tv_nombreRestaurante.setText(""+nombreRestaurante);
        tv_nombrePlatillo.setText(""+nombrePlatillo);
        tv_costoPlatillo.setText("$"+costo_platillo);
        tv_idPlatillo.setText(""+ID_Platillo);
        tv_datePedido.setText(dia + "/" + mes +"/" + annio);

        tv_costoPlatillo.setText("0.0");


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
}