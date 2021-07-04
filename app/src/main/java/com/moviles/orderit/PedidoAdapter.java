package com.moviles.orderit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>{

    private Context mCtx;
    private List<Pedido> pedidoList;

    public PedidoAdapter(Context mCtx, List<Pedido> pedidoList)
    {
        this.mCtx = mCtx;
        this.pedidoList = pedidoList;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lay_lista_item_pedidos, null);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder holder, int position) {
        Pedido pedido = pedidoList.get(position);

        int total_iVal= (int)pedido.getTotal();
        int cantidad_iVal = (int)pedido.getCantidad();

        holder.TV_RestaurantePedido.setText(pedido.getNombreRest());
        holder.TV_FechaPedido.setText(pedido.getFecha());
        holder.TV_PlatilloPedido.setText(pedido.getNombrePlatillo());
        holder.TV_CostoPlatilloPedido.setText("$"+pedido.getPrecio());
        holder.TV_CantidadPlatilloPedido.setText(""+total_iVal);
        holder.TV_TotalPlatilloPedido.setText("$"+cantidad_iVal);
        holder.TV_CostoEntregaPedido.setText("$"+pedido.getCostoEntrega());

    }

    @Override
    public int getItemCount() {
        return pedidoList.size();
    }

    class PedidoViewHolder extends RecyclerView.ViewHolder{

        TextView TV_RestaurantePedido, TV_FechaPedido, TV_PlatilloPedido, TV_CostoPlatilloPedido, TV_CantidadPlatilloPedido, TV_TotalPlatilloPedido, TV_CostoEntregaPedido;

        public PedidoViewHolder(View itemView){

            super(itemView);

            TV_RestaurantePedido = itemView.findViewById(R.id.TV_RestaurantePedido);
            TV_FechaPedido = itemView.findViewById(R.id.TV_FechaPedido);
            TV_PlatilloPedido = itemView.findViewById(R.id.TV_PlatilloPedido);
            TV_CostoPlatilloPedido = itemView.findViewById(R.id.TV_CostoPlatilloPedido);
            TV_CantidadPlatilloPedido = itemView.findViewById(R.id.TV_CantidadPlatilloPedido);
            TV_TotalPlatilloPedido = itemView.findViewById(R.id.TV_TotalPlatilloPedido);
            TV_CostoEntregaPedido = itemView.findViewById(R.id.TV_CostoEntregaPedido);

        }

    }

}
