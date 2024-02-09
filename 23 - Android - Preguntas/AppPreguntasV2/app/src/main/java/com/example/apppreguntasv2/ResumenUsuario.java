package com.example.apppreguntasv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apppreguntasv2.utils.Config;
import com.example.apppreguntasv2.utils.Resumen;
import com.example.apppreguntasv2.utils.ResumenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumenUsuario extends AppCompatActivity {

    String id_usuario;
    String nombre_usuario;

    TextView etq_usuario;

    Config dataConfig;

    RecyclerView recyclerView;
    ResumenAdapter adapter;
    List<Resumen> listaResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_usuario);

        dataConfig = new Config(getApplicationContext());

        etq_usuario = findViewById(R.id.etq_usuario);

        SharedPreferences archivo = getSharedPreferences("app_preguntas", Context.MODE_PRIVATE);
        id_usuario = archivo.getString("id_usuario", null);
        nombre_usuario = archivo.getString("nombres", null);

        etq_usuario.setText(nombre_usuario);

        FloatingActionButton btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        recyclerView = findViewById(R.id.recyclerSumary);
        listaResumen = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        obtenerCuestionarios();
    }

    public void cerrarSesion(){
        SharedPreferences sharedPreferences = getSharedPreferences("app_preguntas", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intencion);
        finish();
    }

    public void obtenerCuestionarios(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/getCuestionarios.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response: "+response);
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);

                    cargarCuestionarios( jsonObject.getJSONArray("resumen") );
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con un error:");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en Datos Servidor: "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor POST responde con un error:");
                System.out.println(error.getMessage());
                Toast.makeText(getApplicationContext(), "Error en Servidor: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_usuario", id_usuario);
                return params;
            }
        };

        queue.add(solicitud);
    }

    public void cargarCuestionarios(JSONArray datos){
        listaResumen = new ArrayList<>();
        for (int i=0; i<datos.length(); i++){
            try {
                JSONObject cuestionario = datos.getJSONObject(i);
                listaResumen.add(new Resumen(cuestionario.getString("id_cuestionario"), cuestionario.getString("fecha_inicio"), cuestionario.getString("fecha_fin"), cuestionario.getString("cant_preguntas"), cuestionario.getString("cant_preguntas_ok"), cuestionario.getString("cant_preguntas_error")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        adapter = new ResumenAdapter(listaResumen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void nuevoCuestionario(View vista) {
        Intent intencion = new Intent(getApplicationContext(), NuevoCuestionario.class);
        startActivity(intencion);
    }

    public void onBackPressed() {
    }
}