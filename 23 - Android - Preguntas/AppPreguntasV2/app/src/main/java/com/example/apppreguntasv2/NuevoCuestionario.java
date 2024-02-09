package com.example.apppreguntasv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apppreguntasv2.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NuevoCuestionario extends AppCompatActivity {

    TextView etq_new_usuario;
    TextView etq_new_fecha_inicio;
    String id_usuario;
    String fecha_inicio;
    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cuestionario);

        dataConfig = new Config(getApplicationContext());

        SharedPreferences sharedPreferences = getSharedPreferences("app_preguntas", Context.MODE_PRIVATE);
        etq_new_usuario = findViewById(R.id.etq_new_usuario);
        etq_new_fecha_inicio = findViewById(R.id.etq_new_fecha_inicio);

        etq_new_usuario.setText( sharedPreferences.getString("nombres", "") );
        setFechaACtual();

        id_usuario = sharedPreferences.getString("id_usuario", "");
    }

    public void setFechaACtual(){
        // Obtiene la instancia de Calendar
        Calendar calendar = Calendar.getInstance();

        // Obtiene la fecha
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Obtiene la hora
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        // Construye la fecha y hora
        String dateTime = String.format("%04d-%02d-%02d \n %02d:%02d:%02d", year, month, day, hour, minute, second);
        fecha_inicio = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
        etq_new_fecha_inicio.setText(dateTime);
    }

    public void crearCuestionario(View vista){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/createCuestionario.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response: "+response);
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);

                    String id_cuestionario = jsonObject.getString("id_cuestionario");

                    Intent intencion = new Intent(getApplicationContext(), Pregunta.class);
                    intencion.putExtra("id_cuestionario", id_cuestionario);
                    intencion.putExtra("usuario", etq_new_usuario.getText().toString());
                    intencion.putExtra("fecha_inicio", fecha_inicio);
                    startActivity(intencion);
                    finish();
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
                params.put("fecha_inicio", fecha_inicio);
                return params;
            }
        };

        queue.add(solicitud);
    }
}