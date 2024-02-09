package com.example.apppreguntasv2.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppreguntasv2.Cuestionario;
import com.example.apppreguntasv2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ResumenAdapter extends RecyclerView.Adapter<ResumenAdapter.ViewHolder> {

    private List<Resumen> listaResumen;

    public ResumenAdapter(List<Resumen> listaResumen){
        this.listaResumen = listaResumen;
    }

    @NonNull
    @Override
    public ResumenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resumen, parent, false);
            return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumenAdapter.ViewHolder holder, int position) {
        Resumen resumen = listaResumen.get(position);
        holder.cargarResumen(resumen);
    }

    @Override
    public int getItemCount() {
        return listaResumen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sumary_fecha_inicio;
        TextView sumary_fecha_fin;
        TextView sumary_preguntas;
        TextView sumary_ok;
        TextView sumary_error;
        Button sumary_btn;
        Context contexto;

        public ViewHolder(View itemView){
            super(itemView);

            contexto = itemView.getContext();
            sumary_fecha_inicio = itemView.findViewById(R.id.sumary_fecha_inicio);
            sumary_fecha_fin = itemView.findViewById(R.id.sumary_fecha_fin);
            sumary_preguntas = itemView.findViewById(R.id.sumary_preguntas);
            sumary_ok = itemView.findViewById(R.id.sumary_ok);
            sumary_error = itemView.findViewById(R.id.sumary_error);
            sumary_btn = itemView.findViewById(R.id.sumary_btn);
        }

        public void cargarResumen(Resumen resumen){
            sumary_fecha_inicio.setText(resumen.fecha_inicio);
            sumary_fecha_fin.setText((!resumen.fecha_fin.equals("null"))? resumen.fecha_fin : "" );
            sumary_preguntas.setText(resumen.cant_preguntas);
            sumary_ok.setText(resumen.cant_ok);
            sumary_error.setText(resumen.cant_error);

            sumary_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intencion = new Intent(contexto.getApplicationContext(), Cuestionario.class);
                    intencion.putExtra("nombre_usuario", "nombre_usuario");
                    intencion.putExtra("id_cuestionario", resumen.id );
                    intencion.putExtra("cant_preguntas", resumen.cant_preguntas );
                    intencion.putExtra("cant_preguntas_ok", resumen.cant_ok );
                    intencion.putExtra("cant_preguntas_error", resumen.cant_error);
                    intencion.putExtra("fecha_inicio", resumen.fecha_inicio);
                    intencion.putExtra("fecha_fin", resumen.fecha_fin);
                    contexto.startActivity(intencion);
                }
            });

        }

        public void eliminarCuestionario(){

        }
    }
}
