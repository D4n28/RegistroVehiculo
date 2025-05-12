package com.example.registrovehiculos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    ArrayList<Vehiculo> lista;

    public VehiculoAdapter(ArrayList<Vehiculo> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehiculo v = lista.get(position);
        holder.tvPlaca.setText("Placa: " + v.getPlaca());
        holder.tvMarca.setText("Marca: " + v.getMarca());
        holder.tvColor.setText("Color: " + v.getColor());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlaca, tvMarca, tvColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvColor = itemView.findViewById(R.id.tvColor);
        }
    }
}