package simrskhanza;

/**
 *
 * @author MUSTAFA DAENG MUMA
 */
public class DlgNotifikasi extends javax.swing.JDialog {
private boolean okPressed = false;
    /**
     * Creates new form DlgNotifikasi
     */
    public DlgNotifikasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        BtnOK.addActionListener(e -> {
            okPressed = true;
            dispose(); // Menutup dialog
        });
    }

    public boolean isOkPressed() {
        return okPressed;
    }

    public void setNotificationText(String text) {
        lblNotificationText.setText("<html>" + text + "</html>");
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame = new widget.InternalFrame();
        panelBiasa5 = new widget.PanelBiasa();
        lblNotificationText = new widget.Label();
        BtnOK = new widget.Button();
        jLabelIcon = new widget.Label();
        BtnTutup = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notifikasi Pendaftaran Online");
        setPreferredSize(new java.awt.Dimension(400, 200));
        setResizable(false);

        internalFrame.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame.setPreferredSize(new java.awt.Dimension(400, 200));
        internalFrame.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa5.setPreferredSize(new java.awt.Dimension(400, 200));
        panelBiasa5.setLayout(null);

        lblNotificationText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNotificationText.setText("Nama : Mustafa");
        lblNotificationText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNotificationText.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        panelBiasa5.add(lblNotificationText);
        lblNotificationText.setBounds(110, 16, 270, 100);

        BtnOK.setBackground(new java.awt.Color(242, 242, 242));
        BtnOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Select.png"))); // NOI18N
        BtnOK.setMnemonic('T');
        BtnOK.setText("OK");
        BtnOK.setToolTipText("Alt+T");
        BtnOK.setPreferredSize(new java.awt.Dimension(70, 30));
        BtnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOKActionPerformed(evt);
            }
        });
        BtnOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnOKKeyPressed(evt);
            }
        });
        panelBiasa5.add(BtnOK);
        BtnOK.setBounds(110, 120, 70, 30);

        jLabelIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/active_64.png"))); // NOI18N
        panelBiasa5.add(jLabelIcon);
        jLabelIcon.setBounds(20, 20, 70, 70);

        BtnTutup.setBackground(new java.awt.Color(242, 242, 242));
        BtnTutup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/101.png"))); // NOI18N
        BtnTutup.setMnemonic('T');
        BtnTutup.setText("Tutup");
        BtnTutup.setToolTipText("Alt+T");
        BtnTutup.setPreferredSize(new java.awt.Dimension(70, 30));
        BtnTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTutupActionPerformed(evt);
            }
        });
        BtnTutup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTutupKeyPressed(evt);
            }
        });
        panelBiasa5.add(BtnTutup);
        BtnTutup.setBounds(190, 120, 80, 30);

        internalFrame.add(panelBiasa5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(internalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(internalFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnOKActionPerformed

    private void BtnOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnOKKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnOKKeyPressed

    private void BtnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTutupActionPerformed
     dispose();
    }//GEN-LAST:event_BtnTutupActionPerformed

    private void BtnTutupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTutupKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnTutupKeyPressed

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
            java.util.logging.Logger.getLogger(DlgNotifikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgNotifikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgNotifikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgNotifikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgNotifikasi dialog = new DlgNotifikasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnOK;
    private widget.Button BtnTutup;
    private widget.InternalFrame internalFrame;
    private widget.Label jLabelIcon;
    private widget.Label lblNotificationText;
    private widget.PanelBiasa panelBiasa5;
    // End of variables declaration//GEN-END:variables

}
