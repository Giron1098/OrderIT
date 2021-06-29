package com.moviles.orderit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder> {

    private Context mCtx;
    private List<Restaurante> restauranteList;

    public RestauranteAdapter(Context mCtx, List<Restaurante> restauranteList)
    {
        this.mCtx = mCtx;
        this.restauranteList = restauranteList;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lay_lista_item_restaurante, null);
        return new RestauranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteAdapter.RestauranteViewHolder holder, int position) {
        Restaurante restaurante = restauranteList.get(position);

        holder.TV_NombreRest.setText(restaurante.getNombreRest());
        holder.TV_TiempoRes.setText("Tiempo estimado de entrega: "+restaurante.getTiempoEstimado());
        holder.TV_CostoRes.setText("Costo de entrega: $"+restaurante.getCostoEntrega());
    }

    @Override
    public int getItemCount() {
        return restauranteList.size();
    }

    class RestauranteViewHolder extends RecyclerView.ViewHolder{

        TextView TV_NombreRest, TV_TiempoRes, TV_CostoRes;

        public RestauranteViewHolder(View itemView)
        {
            super(itemView);

            TV_NombreRest = itemView.findViewById(R.id.TV_NombreRest);
            TV_TiempoRes = itemView.findViewById(R.id.TV_TiempoRes);
            TV_CostoRes = itemView.findViewById(R.id.TV_CostoRes);
        }

    }
}



