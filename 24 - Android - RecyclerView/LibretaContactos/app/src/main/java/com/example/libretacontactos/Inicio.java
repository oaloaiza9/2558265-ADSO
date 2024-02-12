package com.example.libretacontactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.libretacontactos.utils.AdapterContacto;
import com.example.libretacontactos.utils.Config;
import com.example.libretacontactos.utils.Contacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Inicio extends AppCompatActivity {

    Config dataConfig;
    String nombreUsuario, idUsuario;
    RecyclerView recyclerView;
    AdapterContacto adapter;
    List<Contacto> listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        dataConfig = new Config(getApplicationContext());

        SharedPreferences archivo = getSharedPreferences("app_preguntas", Context.MODE_PRIVATE);
        idUsuario = archivo.getString("id_usuario", null);
        nombreUsuario = archivo.getString("nombres", null);

        recyclerView = findViewById(R.id.recyclerContacts);
        listaContactos = new ArrayList<>();
        listaContactos.add( new Contacto("Oscar Andres","Loaiza Pabon","MASCULINO", "32", "3000000001", "Carrera 8 # 31 - 10", "andres@mail.com", "default.png") );
        listaContactos.add( new Contacto("Ana","Perez Atehortua","FEMENINO", "28", "3000000002", "Carrera 8 # 31 - 10", "ana@mail.com", "default.png") );
        adapter = new AdapterContacto(listaContactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    public void cerrarSesion(){
        SharedPreferences sharedPreferences = getSharedPreferences("app_libreta", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intencion);
        finish();
    }

    public void onBackPressed() {
    }

}