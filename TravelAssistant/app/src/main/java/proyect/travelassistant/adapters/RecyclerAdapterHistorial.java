package proyect.travelassistant.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.Consult;

/**
 * Created by Pablo on 04/12/2016.
 */

public class RecyclerAdapterHistorial extends RecyclerView.Adapter<RecyclerAdapterHistorial.ViewHolder> {
    private LayoutInflater inflador;
    private List<Consult> lista;

    private AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerAdapterHistorial(Context context, List<Consult> lista, AdapterView.OnItemClickListener onItemClickListener) {
        this.lista = lista;
        this.onItemClickListener = onItemClickListener;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.historial_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.textViewTitulo.setText(lista.get(i).getDestino());
        holder.textViewFecha.setText(lista.get(i).getFecha());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout llHistorico;
        private TextView textViewTitulo;
        private TextView textViewFecha;

        ViewHolder(View itemView) {
            super(itemView);
            llHistorico = (LinearLayout) itemView.findViewById(R.id.historicalItemList);
            textViewTitulo = (TextView)itemView.findViewById(R.id.textViewItemHist1);
            textViewFecha = (TextView)itemView.findViewById(R.id.textViewItemHist2);

            llHistorico.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}
