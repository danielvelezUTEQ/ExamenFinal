package com.example.examenfinal.RecyclerViewAdaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.R;
import com.example.examenfinal.Secciones.CategoriasCarreras;

import java.util.List;

public class RecyclerViewAdaptadorCategoria extends RecyclerView.Adapter<RecyclerViewAdaptadorCategoria.ItemViewHolder>implements View.OnClickListener{
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<CategoriasCarreras> itemList;
    private View.OnClickListener onClickListener;
    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView txttitle;
        private RecyclerView rvitemcategoria;
        ItemViewHolder(View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txttitulo);
            rvitemcategoria = itemView.findViewById(R.id.rv_editorial);
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListenerp){
        this.onClickListener=onClickListenerp;
    }
    public RecyclerViewAdaptadorCategoria(List<CategoriasCarreras> itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categoria_carrerra, viewGroup, false);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        CategoriasCarreras categoriasCarreras = itemList.get(i);
        itemViewHolder.txttitle.setText(categoriasCarreras.getsection());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.rvitemcategoria.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(categoriasCarreras.getedicionList().size());
        RecyclerViewAdaptadorSub subAdaptadorArticulo = new RecyclerViewAdaptadorSub(categoriasCarreras.getedicionList());
        itemViewHolder.rvitemcategoria.setLayoutManager(layoutManager);
        itemViewHolder.rvitemcategoria.setAdapter(subAdaptadorArticulo);
        itemViewHolder.rvitemcategoria.setRecycledViewPool(viewPool);
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
