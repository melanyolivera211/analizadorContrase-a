package interfaz;
import analyzer.PasswordAnalyzer;
import analyzer.PasswordRecommendation;
import analyzer.PasswordReport;
import analyzer.PasswordStrength;
import static analyzer.PasswordStrength.MODERATE;
import static analyzer.PasswordStrength.STRONG;
import static analyzer.PasswordStrength.VERY_STRONG;
import static analyzer.PasswordStrength.VERY_WEAK;
import static analyzer.PasswordStrength.WEAK;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author User
 */
public class MainUI extends javax.swing.JFrame {
    private JTextField passwordField;
    private JButton analyzeButton;
    private JLabel resultLabel;
    private JLabel timeLabel;
    private JLabel recommendation;
    private JTextField recommendedField;
    private JButton copyButton;
    private JProgressBar strengthBar;
    private PasswordAnalyzer analyzer;
    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
        // Establecer layout primero
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        analyzer = new PasswordAnalyzer();

// Título
        JLabel titleLabel = new JLabel("Analizador de Contraseñas");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 0, 128));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
// Menor espacio arriba (top = 0)
        gbc.insets = new Insets(0, 10, 10, 10);
        add(titleLabel, gbc);

// Resto de tu configuración de ventana
        setTitle("Analizador de Contraseñas");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
       
       
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de contraseña
        passwordField = new JTextField(20);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        add(passwordField, gbc);

        // Botón Analizar
        analyzeButton = new JButton("Analizar");
        analyzeButton.setBackground(new Color(70, 130, 180));
        analyzeButton.setForeground(Color.WHITE);
        analyzeButton.setFocusPainted(false);
        gbc.gridy = 2; gbc.gridwidth = 2;
        add(analyzeButton, gbc);

        // Barra de progreso
        strengthBar = new JProgressBar(0, 100);
        strengthBar.setStringPainted(true);
        gbc.gridy = 3;
        add(strengthBar, gbc);

        // Resultado de fuerza
        resultLabel = new JLabel("Ingrese una contraseña para analizar");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 4;
        add(resultLabel, gbc);

        // Tiempo estimado
        timeLabel = new JLabel("");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 5;
        add(timeLabel, gbc);

        // Recomendación
        recommendation = new JLabel("Contraseña recomendada:");
        recommendation.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 6;
        add(recommendation, gbc);
        // Eventos
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzePassword();
            }
        });

        // Campo con contraseña recomendada
        recommendedField = new JTextField();
        recommendedField.setEditable(false);
        gbc.gridy = 7;
        add(recommendedField, gbc);

       // Botón copiar
        copyButton = new JButton("Copiar");
        copyButton.setBackground(new Color(0, 153, 76));
        copyButton.setForeground(Color.WHITE);
        gbc.gridy = 8;
        add(copyButton, gbc);

        // Eventos
        analyzeButton.addActionListener(e -> analyzePassword());
        copyButton.addActionListener(e -> copyToClipboard());

        setVisible(true);
    }
private void analyzePassword() {
    String password = passwordField.getText();
    if (password.isEmpty()) {
        resultLabel.setText("Por favor, ingrese una contraseña.");
        return;
    }

    PasswordReport report = analyzer.analyze(password);
    String suggested = PasswordRecommendation.enhancePassword(password);
    int strength = (int) (report.getOverallScore() * 100);

    strengthBar.setValue(strength);
    strengthBar.setForeground(getColorByPercentage(strength));
    strengthBar.setBackground(Color.LIGHT_GRAY); // Fondo neutro
    strengthBar.setForeground(getColorByPercentage(strength));
    strengthBar.setString(strength + " %"); // Texto estático
    strengthBar.setFont(new Font("Arial", Font.BOLD, 12));
    strengthBar.setForeground(getColorByPercentage(strength)); // Color del avance
    strengthBar.setOpaque(true);
    strengthBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
        @Override
        protected Color getSelectionForeground() {
            return Color.BLACK; // Texto negro sobre color de barra
        }

        @Override
        protected Color getSelectionBackground() {
            return Color.BLACK; // Fondo del texto negro también
        }
    });

    resultLabel.setText("Fuerza: " + report.getStrength().getDescription());
    timeLabel.setText("Tiempo estimado de descifrado: " + report.getBruteForceTime());
    recommendedField.setText(suggested);
}

    private void copyToClipboard() {
        String text = recommendedField.getText();
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Contraseña copiada al portapapeles");
    }
private Color getColorByPercentage(int value) {
    if (value <= 25) return Color.RED;
    else if (value <= 50) return Color.ORANGE;
    else if (value <= 79) return Color.YELLOW;
    else return new Color(50, 205, 50); // Verde claro
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

