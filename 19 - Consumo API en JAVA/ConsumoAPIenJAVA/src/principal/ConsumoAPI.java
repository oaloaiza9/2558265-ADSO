package principal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ConsumoAPI {
    
    public ConsumoAPI(){
    }
    
    public String consumoGET(String endpoint){
        try {
            // URL de la API
            URL url = new URL(endpoint);
            
            // Abrir una conexión HTTP y configurar para Metodo GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Obtener el código de respuesta enviado por el Servidor
            int responseCode = connection.getResponseCode();
            
            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println(" -- Catch -- ");
            System.out.println( e.getMessage() );
            return null;
        }
    }
    
    public String consumoGET(String endpoint, Map<String, String> getData){
        try {
            
            // Construir la URL con los datos GET
            StringBuilder urlBuilder = new StringBuilder(endpoint);
            if (!getData.isEmpty()) {
                urlBuilder.append('?');
                for (Map.Entry<String, String> entry : getData.entrySet()) {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    urlBuilder.append('=');
                    urlBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    urlBuilder.append('&');
                }
                urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Eliminar el último '&'
            }
            
            // URL de la API
            URL url = new URL(urlBuilder.toString());
            
            // Abrir una conexión HTTP y configurar para Metodo GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Obtener el código de respuesta enviado por el Servidor
            int responseCode = connection.getResponseCode();
            
            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println(" -- Catch -- ");
            e.printStackTrace();
            return null;
        }
    }
    
    public String consumoPOST(String endpoint){
        try {
            // URL de la API
            URL url = new URL(endpoint);

            // Abrir una conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la conexión para el método POST sin Datos
            connection.setRequestMethod("POST");
            connection.setDoOutput(false);
            
            // Obtener el código de respuesta enviado por el Servidor
            int responseCode = connection.getResponseCode();
            
            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta de la API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public String consumoPOST(String endpoint, Map<String, String> postData){
        try {
            // URL de la API
            URL url = new URL(endpoint);

            // Abrir una conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la conexión para el método POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            
            // Construir la cadena de datos a enviar
            StringBuilder data = new StringBuilder();
            for (Map.Entry<String, String> entry : postData.entrySet()) {
                if (data.length() != 0) {
                    data.append('&');
                }
                data.append(entry.getKey());
                data.append('=');
                data.append(entry.getValue());
            }

            // Enviar los datos en el cuerpo de la solicitud
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(data.toString());
                wr.flush();
            }

            // Obtener el código de respuesta
            int responseCode = connection.getResponseCode();
            
            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta de la API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public Persona[] getListaPersonas(String endpoint){
        // Realizamos el consumo
        String respuestaApi = consumoGET(endpoint);
        Persona listaPersonas [] = null;
        
        if (respuestaApi!=null) {
            // Convertimos toda la respuesta de la API en Objeto Json
            // De ese objeto se extrae el value del key "registros" y ese valor se convierte en Array Jsoon
            JsonObject jsonRegistros = JsonParser.parseString(respuestaApi).getAsJsonObject();
            JsonArray jsonArray = jsonRegistros.get("registros").getAsJsonArray();

            // Se crea un arreglo de Tipo Persona del tamaño del arreglo construido anteriormente
            listaPersonas = new Persona [jsonArray.size()];

            // [opcional] Se crea una instancia Gson para converir un objeto Json en una clase Persona de forma automatica
            Gson gson01 = new Gson();

            // Se recorre el arreglo que llego de la API y se alimenta el arreglo de Personas
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                
                Persona temporal = gson01.fromJson(jsonObject, Persona.class);
                listaPersonas[i] = temporal;
            }       
        }
        
        return listaPersonas;
    }
}
