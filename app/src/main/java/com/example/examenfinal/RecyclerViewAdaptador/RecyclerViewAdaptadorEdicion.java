package com.example.examenfinal.RecyclerViewAdaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examenfinal.R;
import com.example.examenfinal.Secciones.Edicion;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdaptadorEdicion extends RecyclerView.Adapter<RecyclerViewAdaptadorEdicion.ViewHolder>implements View.OnClickListener {

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tittle, doi,section,autores;
        private Button btnPDF;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            section = itemView.findViewById(R.id.txtSection);
            tittle= itemView.findViewById(R.id.txtitle);
            autores= itemView.findViewById(R.id.txtautores);
        }
    }
    private Context edicioncon ;
    public List<Edicion> edicions;
    private View.OnClickListener listener;
    public RecyclerViewAdaptadorEdicion(Context context, List<Edicion> edicionListist){
        edicioncon= context;
        edicions = edicionListist;
    }

    @Override
    public RecyclerViewAdaptadorEdicion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(edicioncon);
        View view = inflater.inflate(R.layout.item_sub,parent,false);
        RecyclerViewAdaptadorEdicion.ViewHolder viewHolder= new RecyclerViewAdaptadorEdicion.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptadorEdicion.ViewHolder holder, int position) {
        holder.section.setText(edicions.get(position).getsection());
        holder.tittle.setText(edicions.get(position).gettittle());
        holder.autores.setText(edicions.get(position).getautores());
//        holder.doi.setText(edicions.get(position).getdoi());
    }

    @Override
    public int getItemCount() {
        return edicions.size();
    }


}
