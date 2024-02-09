package com.example.apppreguntasv2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apppreguntasv2.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pregunta extends AppCompatActivity {

    TextView etq_preg_usuario;
    TextView etq_preg_fecha_inicio;
    TextView etq_preg_numero;
    TextView etq_pregunta;
    ImageView img_pregunta;
    RadioGroup grupo_opciones;

    String id_cuestionario;
    int numero_pregunta;
    Config dataConfig;
    JSONObject preguntaActual;
    JSONArray opcionesActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        dataConfig = new Config(getApplicationContext());

        etq_preg_usuario = findViewById(R.id.etq_preg_usuario);
        etq_preg_fecha_inicio = findViewById(R.id.etq_preg_fecha_inicio);
        etq_preg_numero = findViewById(R.id.etq_preg_numero);
        etq_pregunta = findViewById(R.id.etq_pregunta);
        grupo_opciones = findViewById(R.id.grupo_opciones);
        img_pregunta = findViewById(R.id.img_pregunta);

        Bundle datos = getIntent().getExtras();
        id_cuestionario = datos.getString("id_cuestionario");
        numero_pregunta = 0;



        etq_preg_usuario.setText(datos.getString("usuario"));
        etq_preg_fecha_inicio.setText(datos.getString("fecha_inicio"));
        etq_preg_numero.setText(""+numero_pregunta);

        cargarNuevaPregunta();
    }

    public void cargarNuevaPregunta(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/getOtherPregunta.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response: "+response);
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);

                    preguntaActual = jsonObject.getJSONObject("pregunta");
                    JSONArray opciones = jsonObject.getJSONArray("opciones");
                    opcionesActual = opciones;

                    etq_pregunta.setText(preguntaActual.getString("descripcion"));
                    cargarImagen(preguntaActual.getString("url_imagen"));
                    // Borra los RadioButton existentes
                    int count = grupo_opciones.getChildCount();
                    for (int i = 0; i < count; i++) {
                        grupo_opciones.removeViewAt(0);
                    }

                    // Crea los nuevos RadioButton
                    for (int i = 0; i < opciones.length(); i++) {
                        JSONObject temp = opciones.getJSONObject(i);

                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioButton.setText(temp.getString("descripcion"));
                        radioButton.setTextSize(22);
                        grupo_opciones.addView(radioButton);
                    }

                    numero_pregunta++;
                    etq_preg_numero.setText(""+numero_pregunta);
                    grupo_opciones.clearCheck();
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

    public void siguientePregunta(View vista){
        int idRadioButton = grupo_opciones.getCheckedRadioButtonId();
        if (idRadioButton!=-1){
            RadioButton radioButtonSeleccionado = findViewById(idRadioButton);
            String textoSeleccionado = radioButtonSeleccionado.getText().toString();

            try {
                // Buscar el Id de la Opcion
                String id_selected = "";
                for (int i=0; i<opcionesActual.length(); i++){
                    JSONObject temporal = opcionesActual.getJSONObject(i);
                    if (textoSeleccionado.equalsIgnoreCase(temporal.getString("descripcion"))){
                        id_selected = temporal.getString("id");
                        break;
                    }
                }

                String post_id_cuestionario = id_cuestionario;
                String post_id_pregunta = preguntaActual.getString("id");
                String post_respuesta = id_selected;
                String post_valido = (preguntaActual.getString("id_correcta").equalsIgnoreCase(id_selected) )? "OK":"ERROR";

                registrarRespuesta(post_id_cuestionario, post_id_pregunta, post_respuesta, post_valido);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debe seleccionar una opción.", Toast.LENGTH_LONG).show();
        }
    }

    public void registrarRespuesta(String post_id_cuestionario, String post_id_pregunta, String post_respuesta, String post_valido){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/saveRespuesta.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response: "+response);
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);

                    cargarNuevaPregunta();
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
                params.put("id_cuestionario", post_id_cuestionario);
                params.put("id_pregunta", post_id_pregunta);
                params.put("respuesta", post_respuesta);
                params.put("estado", post_valido);
                return params;
            }
        };

        queue.add(solicitud);
    }

    public void cargarImagen(String url){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        ImageRequest solicitud = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        // Set the image in the ImageView.
                        img_pregunta.setImageBitmap(bitmap);
                    }
                },
                0, 0, null, // maxWidth, maxHeight, decodeConfig;
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error.
                    }
                });

        queue.add(solicitud);
    }

    @Override
    public void onBackPressed() {
    }
}
