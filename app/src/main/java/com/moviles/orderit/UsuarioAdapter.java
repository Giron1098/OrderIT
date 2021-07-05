package com.moviles.orderit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>{

    private Context mCtx;
    private List<Usuario> usuarioList;

    public UsuarioAdapter(Context mCtx, List<Usuario> usuarioList)
    {
        this.mCtx = mCtx;
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lay_lista_item_ver_perfil, null);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);

        holder.TV_NombrePerfil.setText(usuario.getNombreUsuario());
        holder.TV_ApPaternoPerfil.setText(usuario.getApPaterno());
        holder.TV_ApMaternoPerfil.setText(usuario.getApMaterno());
        holder.TV_EmailPerfil.setText(usuario.getEmail());
        holder.TV_PasswordPerfil.setText(usuario.getPassword());
        holder.TV_IDUsuarioPerfil.setText(""+usuario.getIdUsuario());

        holder.TV_PasswordPerfil.setVisibility(View.INVISIBLE);
        holder.TV_IDUsuarioPerfil.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder{

        TextView TV_NombrePerfil, TV_ApPaternoPerfil, TV_ApMaternoPerfil, TV_EmailPerfil, TV_PasswordPerfil, TV_IDUsuarioPerfil;
        Button BTN_EditarPerfil;

        public UsuarioViewHolder(View itemView){

            super(itemView);

            TV_NombrePerfil = itemView.findViewById(R.id.TV_NombrePerfil);
            TV_ApPaternoPerfil = itemView.findViewById(R.id.TV_ApPaternoPerfil);
            TV_ApMaternoPerfil = itemView.findViewById(R.id.TV_ApMaternoPerfil);
            TV_EmailPerfil = itemView.findViewById(R.id.TV_EmailPerfil);
            TV_PasswordPerfil = itemView.findViewById(R.id.TV_PasswordPerfil);
            TV_IDUsuarioPerfil = itemView.findViewById(R.id.TV_IDUsuarioPerfil);

            BTN_EditarPerfil = itemView.findViewById(R.id.BTN_EditarPerfil);

            BTN_EditarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ACT_EditarPerfil.class);

                    intent.putExtra("ID_Usuario", usuarioList.get(getAdapterPosition()).getIdUsuario());
                    intent.putExtra("Nombre", usuarioList.get(getAdapterPosition()).getNombreUsuario());
                    intent.putExtra("ApPaterno", usuarioList.get(getAdapterPosition()).getApPaterno());
                    intent.putExtra("ApMaterno", usuarioList.get(getAdapterPosition()).getApMaterno());
                    intent.putExtra("Email", usuarioList.get(getAdapterPosition()).getEmail());
                    intent.putExtra("Password", usuarioList.get(getAdapterPosition()).getPassword());

                    context.startActivity(intent);
                }
            });

        }

    }

}
