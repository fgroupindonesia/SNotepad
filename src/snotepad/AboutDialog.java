package snotepad;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * Project : SNotepad
 * File : AboutDialog.java
 * @author FGroupIndonesia
 */
public class AboutDialog extends javax.swing.JDialog {

    /**
     * Creates new form AboutDialog
     */
    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        readProperties();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label_website = new javax.swing.JLabel();
        label_releasedDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        label_version = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setPreferredSize(new java.awt.Dimension(300, 330));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo64.png"))); // NOI18N
        jLabel1.setText("SNPAD");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 195, 77);

        label_website.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label_website.setForeground(new java.awt.Color(0, 102, 255));
        label_website.setText("<html><u>FGroupIndonesia.</u></html>");
        label_website.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_websiteMouseClicked(evt);
            }
        });
        getContentPane().add(label_website);
        label_website.setBounds(140, 140, 130, 30);

        label_releasedDate.setText("Released Date : xx");
        getContentPane().add(label_releasedDate);
        label_releasedDate.setBounds(20, 190, 240, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel4.setText("aka : SNotepad");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(100, 80, 130, 30);

        label_version.setText("Version : xx");
        getContentPane().add(label_version);
        label_version.setBounds(20, 160, 150, 30);

        jLabel5.setText("brought to you by ❤");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(130, 110, 130, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void label_websiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_websiteMouseClicked

        try {
            Desktop.getDesktop().browse(new URI("http://www.fgroupindonesia.com"));
        } catch (Exception e1) {
            System.err.println("Error at opening browser...");
        }
    }//GEN-LAST:event_label_websiteMouseClicked

    private void readProperties() {
        Properties props = new Properties();

        try {

            URL url = ClassLoader.getSystemClassLoader().getResource("snotepad/helper");

            File packageDir = new File(url.getFile());

            File propertiesFile = new File(packageDir, "data.prop");

            FileInputStream fis = new FileInputStream(propertiesFile);
            props.load(fis);

            String ver = props.getProperty("version");
            String rdate = props.getProperty("released-date");

            String outputFormat = "EEEE, dd MMMM yyyy";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
            SimpleDateFormat sdf2 = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

            Date date = sdf.parse(rdate);
            String formattedDate = sdf2.format(date);
            
            label_version.setText("Version : " + ver);
            label_releasedDate.setText("Released Date : " + formattedDate);

        } catch (Exception e) {
            System.err.println("Error while reading a file");
        }
    }

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
            java.util.logging.Logger.getLogger(AboutDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutDialog dialog = new AboutDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel label_releasedDate;
    private javax.swing.JLabel label_version;
    private javax.swing.JLabel label_website;
    // End of variables declaration//GEN-END:variables
}
