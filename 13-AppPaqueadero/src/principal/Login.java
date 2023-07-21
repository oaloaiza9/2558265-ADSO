package principal;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        initComponents2();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etq_titulo = new javax.swing.JLabel();
        etq_imagen = new javax.swing.JLabel();
        etq_email = new javax.swing.JLabel();
        campo_email = new javax.swing.JTextField();
        etq_password = new javax.swing.JLabel();
        campo_password = new javax.swing.JPasswordField();
        btn_ingresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar Sesion");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getToolkit().createImage( ClassLoader.getSystemResource("imagenes/icono_almacenes.png") ));

        etq_titulo.setBackground(new java.awt.Color(0, 0, 153));
        etq_titulo.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        etq_titulo.setForeground(new java.awt.Color(0, 0, 153));
        etq_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etq_titulo.setText("INICIAR SESION");

        etq_imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        etq_email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etq_email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etq_email.setText("CORREO ELECTRONICO");

        campo_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campo_email.setText("andres@mail.com.co");
        campo_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateEmail(evt);
            }
        });

        etq_password.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etq_password.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etq_password.setText("CONTRASEÑA");

        campo_password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campo_password.setText("12345");
        campo_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pruebaKey(evt);
            }
        });

        btn_ingresar.setBackground(new java.awt.Color(0, 0, 204));
        btn_ingresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ingresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_ingresar.setText("INGRESAR");
        btn_ingresar.setFocusable(false);
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etq_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(etq_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campo_password)
                                .addComponent(campo_email)
                                .addComponent(etq_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(etq_email, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(btn_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(etq_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etq_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etq_email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etq_password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campo_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
        String email = campo_email.getText();
        String password = campo_password.getText();
        
        if (email.equalsIgnoreCase("andres@mail.com.co") && password.equalsIgnoreCase("12345")) {   
            Menu ventana = new Menu();
            dispose();            
        }else{
            System.out.println("DATOS INVALIDOS");
        }
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void pruebaKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pruebaKey
        JTextField temporal = (JTextField) evt.getSource();
        
        if (evt.getKeyCode()<48 || evt.getKeyCode()>57) {
            String texto = temporal.getText();
            String tecla = String.valueOf(evt.getKeyChar());            
            temporal.setText( texto.replaceAll(tecla, "") );
        }
        
        System.out.println("Tecla presionada: "+evt.getKeyChar());
        System.out.println("Texto en input: "+temporal.getText());
        System.out.println("Codigo: "+evt.getKeyCode());
    }//GEN-LAST:event_pruebaKey

    private void validateEmail(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_validateEmail
        JTextField temporal = (JTextField) evt.getSource();
        String texto = temporal.getText();
        
        int cont = 0;
        int contPuntos = 0;
        for (int i=0; i<texto.length(); i++) {
            if (texto.charAt(i)=='@') {
                cont++;
            }
            if (cont==1 && texto.charAt(i)=='.') {
                contPuntos++;
            }
        }
        
        if (cont==1 && contPuntos==2) {
            System.out.println("Correo valido.");
            
            JTextField referencia = new JTextField();
            temporal.setBorder( referencia.getBorder() );
        }else{
            System.out.println("Correo in-valido.");
            
            Border borderColor = new LineBorder(Color.RED, 1, true);
            Border borderPadding = new EmptyBorder(2,5,2,5);
            Border borderRojo = new CompoundBorder(borderColor, borderPadding);
            temporal.setBorder(borderRojo);
        }
        
    }//GEN-LAST:event_validateEmail

    public void initComponents2(){
        setVisible(true);
        setLocationRelativeTo(null);
        
        Image img_candado = getToolkit().createImage(ClassLoader.getSystemResource("imagenes/icono_candado.png"));
        img_candado = img_candado.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        etq_imagen.setIcon(new ImageIcon(img_candado));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JTextField campo_email;
    private javax.swing.JTextField campo_password;
    private javax.swing.JLabel etq_email;
    private javax.swing.JLabel etq_imagen;
    private javax.swing.JLabel etq_password;
    private javax.swing.JLabel etq_titulo;
    // End of variables declaration//GEN-END:variables
}
