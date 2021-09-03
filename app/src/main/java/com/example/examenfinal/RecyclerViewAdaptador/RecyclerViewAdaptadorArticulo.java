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
import com.example.examenfinal.Secciones.Articulo;

import java.util.List;

public class RecyclerViewAdaptadorArticulo extends RecyclerView.Adapter<RecyclerViewAdaptadorArticulo.ViewHolder>implements View.OnClickListener{

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
        private TextView issue_id, volume,number,year, date_published,title, doi;
        ImageView imagenarticulo;
        public ViewHolder(View itemView) {
            super(itemView);
            issue_id =(TextView)itemView.findViewById(R.id.txtissue);
            volume =(TextView)itemView.findViewById(R.id.txtvolume);
            number =(TextView)itemView.findViewById(R.id.txtnumber);
            year =(TextView)itemView.findViewById(R.id.txtyear);
            date_published =(TextView)itemView.findViewById(R.id.txtautores);
            title =(TextView)itemView.findViewById(R.id.txttitle);
            doi =(TextView)itemView.findViewById(R.id.txtdoi);
            imagenarticulo = itemView.findViewById(R.id.ivusuario);
        }
    }
    private Context articulocon;
    public List<Articulo> articuloListist;
    private View.OnClickListener listener;
    public RecyclerViewAdaptadorArticulo(Context context, List<Articulo> articuloList) {
        articulocon=context;
        articuloListist = articuloList;
    }
    @Override
    public RecyclerViewAdaptadorArticulo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(articulocon);
        View view = inflater.inflate(R.layout.item_articulos,parent,false);
        RecyclerViewAdaptadorArticulo.ViewHolder viewHolder= new RecyclerViewAdaptadorArticulo.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptadorArticulo.ViewHolder holder, int position) {
        holder.issue_id.setText((articuloListist.get(position).getissue_id()));
        holder.volume.setText((articuloListist.get(position).getvolume()));
        holder.number.setText((articuloListist.get(position).getnumber()));
        holder.year.setText((articuloListist.get(position).getyear()));
        holder.date_published.setText((articuloListist.get(position).getdate_published()));
        holder.title.setText((articuloListist.get(position).gettitle()));
        holder.doi.setText((articuloListist.get(position).getdoi()));
        Glide.with(holder.imagenarticulo.getContext()).load(articuloListist.get(position).getcover()).into(holder.imagenarticulo);
    }
    @Override
    public int getItemCount() {
        return articuloListist.size();
    }
}

