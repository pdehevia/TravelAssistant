package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.Critery;
import proyect.travelassistant.sqlite.Recom;


/**
 * Created by pgarcia on 15/11/16.
 */

public class RecyclerAdapterRecom extends RecyclerView.Adapter<RecyclerAdapterRecom.ViewHolder> {
    private LayoutInflater inflador;
    private List<Recom> listaRecomendaciones;
    private List<Critery> listaCriteries;
    private View v;

    public void setListaRecomendaciones(List<Recom> listaRecomendaciones) {
        this.listaRecomendaciones = listaRecomendaciones;
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerAdapterRecom(Context context, List<Recom> listaRecomendaciones, List<Critery> listaCriteries, AdapterView.OnItemClickListener onItemClickListener) {
        this.listaRecomendaciones = listaRecomendaciones;
        this.listaCriteries = listaCriteries;
        this.onItemClickListener = onItemClickListener;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public RecyclerAdapterRecom.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = inflador.inflate(R.layout.edit_recom_custom_element_item, parent, false);
        return new RecyclerAdapterRecom.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterRecom.ViewHolder holder, int i) {
        holder.texto.setText(listaRecomendaciones.get(i).getDescripcion());
        holder.categoria.setText("");

        if(listaRecomendaciones.get(i).isVisible()){
            holder.visible.setImageResource(R.drawable.visible);
        }else{
            holder.visible.setImageResource(R.drawable.invisible);
        }

        for(int j = 0; j< listaCriteries.size(); j++){
            if(listaCriteries.get(j).getId() == listaRecomendaciones.get(i).getCritero()){
                holder.categoria.setText(listaCriteries.get(j).getDescripcion());

                switch (j){
                    case 0:
                        holder.imagen.setImageResource(R.drawable.cat_00);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_00));
                        break;
                    case 1:
                        holder.imagen.setImageResource(R.drawable.cat_01);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_01));
                        break;
                    case 2:
                        holder.imagen.setImageResource(R.drawable.cat_02);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_02));
                        break;
                    case 3:
                        holder.imagen.setImageResource(R.drawable.cat_03);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_03));
                        break;
                    case 4:
                        holder.imagen.setImageResource(R.drawable.cat_04);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_04));
                        break;
                    case 5:
                        holder.imagen.setImageResource(R.drawable.cat_05);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_05));
                        break;
                    case 6:
                        holder.imagen.setImageResource(R.drawable.cat_06);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_06));
                        break;
                    case 7:
                        holder.imagen.setImageResource(R.drawable.cat_07);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_07));
                        break;
                    case 8:
                        holder.imagen.setImageResource(R.drawable.cat_08);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_08));
                        break;
                    case 9:
                        holder.imagen.setImageResource(R.drawable.cat_09);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_09));
                        break;
                    case 10:
                        holder.imagen.setImageResource(R.drawable.cat_10);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_10));
                        break;
                    case 11:
                        holder.imagen.setImageResource(R.drawable.cat_11);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_11));
                        break;
                    case 12:
                        holder.imagen.setImageResource(R.drawable.cat_12);
                        holder.categoria.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorCat_12));
                        break;
                }
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return listaRecomendaciones.size();
    }

    public Recom getItem(int position) {
        return listaRecomendaciones.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout llRecomendacion;
        private TextView texto;
        private TextView categoria;
        private ImageView imagen;
        private ImageView visible;



        ViewHolder(View itemView) {
            super(itemView);
            llRecomendacion = (LinearLayout) itemView.findViewById(R.id.llRecomen);
            texto = (TextView)itemView.findViewById(R.id.textRecom);
            imagen = (ImageView) itemView.findViewById(R.id.imageRecom);
            categoria = (TextView) itemView.findViewById(R.id.textRecomCategory);
            visible = (ImageView) itemView.findViewById(R.id.imageViewRecomVisible);

            llRecomendacion.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}
