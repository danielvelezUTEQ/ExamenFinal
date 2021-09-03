package com.example.examenfinal.RecyclerViewAdaptador;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examenfinal.R;
import com.example.examenfinal.Secciones.Articulo;
import com.example.examenfinal.Secciones.Edicion;
import java.util.List;
public class RecyclerViewAdaptadorSub  extends RecyclerView.Adapter<RecyclerViewAdaptadorSub.SubViewHolder> implements View.OnClickListener{

    private List<Edicion> subList;
    private Articulo e;
    private View.OnClickListener onClickListener;

    RecyclerViewAdaptadorSub(List<Edicion> subItemList) {
        this.subList = subItemList;
    }
    public void setOnClickListener(View.OnClickListener onClickListenerp){
        this.onClickListener=onClickListenerp;
    }
    @Override
    public void onClick(View v) {
        if (onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    class SubViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle,date,doi,autores;
        ImageView imagensub;
        SubViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.txtitle);
            date=itemView.findViewById(R.id.txtautores);
            //imagensub=itemView.findViewById(R.id.img);
            //autores=itemView.findViewById(R.id.a);
        }
    }
    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub, viewGroup, false);
        view.setOnClickListener(this);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {
        Edicion subItem = subList.get(i);
        subViewHolder.tvSubItemTitle.setText("tittle: "+subItem.gettittle());
        subViewHolder.date.setText("date: "+subItem.getdate());
        subViewHolder.autores.setText("autores: "+subItem.getautores());
    }

    @Override
    public int getItemCount() {
        return subList.size();
    }


}
