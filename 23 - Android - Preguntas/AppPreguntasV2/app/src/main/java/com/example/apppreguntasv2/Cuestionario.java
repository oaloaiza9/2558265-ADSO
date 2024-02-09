package com.example.apppreguntasv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apppreguntasv2.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Cuestionario extends AppCompatActivity {

    TextView etq_cuest_usuario;
    TextView etq_cuest_fecha_inicio;
    TextView etq_cuest_fecha_fin;
    TextView etq_cuest_cant;
    TextView etq_cuest_ok;
    TextView etq_cuest_error;
    LinearLayout layoutPreguntas;
    String id_cuestionario;
    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        dataConfig = new Config(getApplicationContext());
        SharedPreferences archivo = getSharedPreferences("app_preguntas", Context.MODE_PRIVATE);

        Bundle datos = getIntent().getExtras();
        id_cuestionario = datos.getString("id_cuestionario", "");
        String nombre_usuario = archivo.getString("nombres", null);
        String cant_preguntas = datos.getString("cant_preguntas", "");
        String cant_preguntas_ok = datos.getString("cant_preguntas_ok", "");
        String cant_preguntas_error = datos.getString("cant_preguntas_error", "");
        String fecha_inicio = datos.getString("fecha_inicio", "");
        String fecha_fin = datos.getString("fecha_fin", "");
        fecha_fin = (fecha_fin.equals("null"))? "" : fecha_fin;

        etq_cuest_usuario = findViewById(R.id.etq_cuest_usuario);
        etq_cuest_fecha_inicio = findViewById(R.id.etq_cuest_fecha_inicio);
        etq_cuest_fecha_fin = findViewById(R.id.etq_cuest_fecha_fin);
        etq_cuest_cant = findViewById(R.id.etq_cuest_cant);
        etq_cuest_ok = findViewById(R.id.etq_cuest_ok);
        etq_cuest_error = findViewById(R.id.etq_cuest_error);
        layoutPreguntas = findViewById(R.id.layoutPreguntas);

        etq_cuest_usuario.setText(nombre_usuario);
        etq_cuest_fecha_inicio.setText(fecha_inicio);
        etq_cuest_fecha_fin.setText(fecha_fin);
        etq_cuest_cant.setText(cant_preguntas);
        etq_cuest_ok.setText(cant_preguntas_ok);
        etq_cuest_error.setText(cant_preguntas_error);

        cargarRespuestas();
    }

    public void backToResumen(View vista){
        Intent intencion = new Intent(getApplicationContext(), ResumenUsuario.class);
        startActivity(intencion);
        finish();
    }

    public void cargarRespuestas(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/getRespuestas.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response: "+response);
                try {
                    System.out.println("El servidor POST responde OK: "+response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataAll = jsonObject.getJSONArray("respuestas");

                    Drawable drawable = getResources().getDrawable(R.drawable.textview_respuesta);
                    for (int i=0; i<dataAll.length(); i++){
                        JSONObject respuesta = dataAll.getJSONObject(i);
                        JSONObject pregunta = respuesta.getJSONObject("pregunta");
                        JSONArray opciones = respuesta.getJSONArray("opciones");

                        TextView text_numero = new TextView(getApplicationContext());
                        text_numero.setText("Pregunta: "+(i+1));
                        text_numero.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        text_numero.setTextColor(Color.BLACK);
                        text_numero.setTextSize(18);
                        layoutPreguntas.addView(text_numero);

                        TextView text_pregunta = new TextView(getApplicationContext());
                        text_pregunta.setText(pregunta.getString("descripcion"));
                        text_pregunta.setTextColor(Color.BLACK);
                        text_pregunta.setTextSize(16);
                        layoutPreguntas.addView(text_pregunta);

                        for(int j=0; j<opciones.length(); j++){
                            JSONObject tempOpcion = opciones.getJSONObject(j);
                            String temp_opciones = "• "+tempOpcion.getString("descripcion")+((j==opciones.length()-1)? "\n":"");

                            TextView text_opciones = new TextView(getApplicationContext());
                            text_opciones.setText(temp_opciones);
                            text_opciones.setTextColor(Color.BLACK);
                            text_opciones.setBackground(drawable);
                            text_opciones.setTextSize(15);

                            if (tempOpcion.getString("id").equalsIgnoreCase( pregunta.getString("respuesta"))){
                                text_opciones.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                                text_opciones.setTextColor( (pregunta.getString("id_correcta").equalsIgnoreCase(tempOpcion.getString("id")))? Color.rgb(0,161,0) : Color.rgb(161,0,0));
                            }

                            layoutPreguntas.addView(text_opciones);
                        }
                    }

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
                params.put("id_cuestionario", id_cuestionario);
                return params;
            }
        };

        queue.add(solicitud);
    }

}