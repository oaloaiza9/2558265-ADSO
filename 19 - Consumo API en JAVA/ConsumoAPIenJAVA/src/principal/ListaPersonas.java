package principal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class ListaPersonas extends javax.swing.JFrame {
    
    DefaultTableModel tableModel;
    Persona listaPersonas [];
    
    public ListaPersonas() {
        
        // Consumo de la API en PHP
        ConsumoAPI apiPHP = new ConsumoAPI();
        String respuestaApi = apiPHP.consumoGET("http://localhost/APIenPHP/Obtener.php");
        
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
        
        // Con el arreglo de Personas lleno se puede llenar el JTable como lo hacian anteriormente
        // En el Altern component
        initComponents();
        initAlternComponents();
    }
    
    public void initAlternComponents(){
        tableModel =  (DefaultTableModel) this.tablaPersonas.getModel();
        tableModel.setNumRows(0);
        for (int i=0; i<listaPersonas.length; i++) {
            String cedula = listaPersonas[i].getCedula();
            String nombres = listaPersonas[i].getNombres();
            String apellidos = listaPersonas[i].getApellidos();
            String telefono = listaPersonas[i].getTelefono();
            String direccion = listaPersonas[i].getDireccion();
            String email = listaPersonas[i].getEmail();
            
            Object[] temporal = new Object[]{ cedula, nombres+" "+apellidos, telefono, email};
            tableModel.addRow(temporal);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 51, 153));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("LISTADO DESDE LA API");

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CEDULA", "NOMBRES", "TELEFONO", "EMAIL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPersonas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
