package com.example.examenfinal.RecyclerViewAdaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.examenfinal.R;
import com.example.examenfinal.Secciones.Revistas;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener onClickListener;
    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListenerp){
        this.onClickListener=onClickListenerp;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
       private TextView nombre,descripcion;
        ImageView imagenrevista;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre =(TextView)itemView.findViewById(R.id.txtyear);
            descripcion =(TextView)itemView.findViewById(R.id.txtDescripcion);
            imagenrevista = itemView.findViewById(R.id.ivusuario);
        }


    }
    private Context revistacon;
    public List<Revistas> revistasListist;
    private View.OnClickListener listener;
      public RecyclerViewAdaptador(Context context, List<Revistas> revistasList) {
        revistacon=context;
        revistasListist = revistasList;
    }
    @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(revistacon);
            View view = inflater.inflate(R.layout.item_usuario,parent,false);
                 ViewHolder viewHolder= new ViewHolder(view);
        view.setOnClickListener(this);
            return viewHolder;
     }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptador.ViewHolder holder, int position) {
        holder.nombre.setText((revistasListist.get(position).getName()));
        holder.descripcion.setText((revistasListist.get(position).getDescription()));
         Glide.with(holder.imagenrevista.getContext()).load(revistasListist.get(position).getPortada()).into(holder.imagenrevista);
    }
    @Override
    public int getItemCount() {
        return revistasListist.size();
    }
}
