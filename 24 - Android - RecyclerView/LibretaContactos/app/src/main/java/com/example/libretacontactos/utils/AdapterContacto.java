package com.example.libretacontactos.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libretacontactos.R;

import java.util.List;

public class AdapterContacto extends RecyclerView.Adapter<AdapterContacto.ViewHolder> {

    List<Contacto> listaContactos;

    public AdapterContacto(List<Contacto> listaContactos){
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public AdapterContacto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContacto.ViewHolder holder, int position) {
        Contacto contacto =listaContactos.get(position);
        holder.cargarContacto(contacto);
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context contexto;
        ImageView contact_img;
        TextView contact_nombres, contact_edad, contact_genero, contact_telefono, contact_direccion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contexto = itemView.getContext();
            contact_img = itemView.findViewById(R.id.contact_img);
            contact_nombres = itemView.findViewById(R.id.contact_nombres);
            contact_edad = itemView.findViewById(R.id.contact_edad);
            contact_genero = itemView.findViewById(R.id.contact_genero);
            contact_telefono = itemView.findViewById(R.id.contact_telefono);
            contact_direccion = itemView.findViewById(R.id.contact_direccion);

            // Configurar el clic del elemento
            itemView.setOnClickListener(this);
        }

        public void cargarContacto(Contacto contacto){
            String texto_nombres = contacto.getNombres()+" "+contacto.getApellidos();
            contact_nombres.setText(recortarTexto(texto_nombres, 20));
            contact_edad.setText(contacto.getEdad());
            contact_telefono.setText(contacto.getTelefono());
            contact_direccion.setText(recortarTexto(contacto.getDireccion(), 20));

            if (contacto.getGenero().equalsIgnoreCase("MASCULINO")){
                contact_genero.setText("M");
                contact_img.setImageResource(R.mipmap.ic_masculino);
            }else{
                contact_genero.setText("F");
                contact_img.setImageResource(R.mipmap.ic_femenino);
            }
        }

        private String recortarTexto(String texto, int limite){
            if (texto.length()>=limite){
                return texto.substring(0, limite)+"...";
            }else{
                return texto;
            }
        }

        @Override
        public void onClick(View v) {
            // Obtener la posición del elemento clicado
            int position = getAdapterPosition();

            // Verificar si la posición es válida
            if (position != RecyclerView.NO_POSITION) {
                // Obtener el contexto
                Context context = v.getContext();

                // Obtener el objeto Contacto en la posición clicada
                Contacto contactoClicado = listaContactos.get(position);

                System.out.println("Has dado click en:");
                System.out.println(contactoClicado.toString());
            }
        }

    }
}
