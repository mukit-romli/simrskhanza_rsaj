/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package laporan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public final class DlgRl312Pembedahan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,pscari;
    private ResultSet rs,rscari;
    private int i=0,khusus=0,besar=0,sedang=0,kecil;   
    
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgRl312Pembedahan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={"No.","Spesialisasi","Khusus","Besar","Sedang","Kecil","Total"};
        tabMode=new DefaultTableModel(null,rowRwJlDr){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(40);
            }else if(i==1){
                column.setPreferredWidth(250);
            }else{
                column.setPreferredWidth(110);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        try {
//            ps=koneksi.prepareStatement(
//                    "select kode_paket,nm_perawatan from paket_operasi where kategori='Operasi' order by nm_perawatan");         
//            pscari=koneksi.prepareStatement("select count(operasi.kode_paket) from operasi where "+
//                    "operasi.kode_paket=? and operasi.tgl_operasi between ? and ? and operasi.kategori=?");

        // modif mustafa daeng muma
        ps = koneksi.prepareStatement(
            "SELECT s.nm_sps, " +
            "SUM(CASE WHEN o.kategori = 'Khusus' THEN 1 ELSE 0 END) AS khusus, " +
            "SUM(CASE WHEN o.kategori = 'Besar' THEN 1 ELSE 0 END) AS besar, " +
            "SUM(CASE WHEN o.kategori = 'Sedang' THEN 1 ELSE 0 END) AS sedang, " +
            "SUM(CASE WHEN o.kategori = 'Kecil' THEN 1 ELSE 0 END) AS kecil " +
            "FROM paket_operasi po " +
            "JOIN operasi o ON po.kode_paket = o.kode_paket " +
            "JOIN dokter d ON o.operator1 = d.kd_dokter " +
            "JOIN spesialis s ON d.kd_sps = s.kd_sps " +
            "WHERE po.kategori = 'Operasi' " +
            "AND o.tgl_operasi BETWEEN ? AND ? " +
            "GROUP BY s.nm_sps " +
            "ORDER BY s.nm_sps"
        );

        pscari = koneksi.prepareStatement(
            "SELECT COUNT(o.kode_paket) FROM operasi o " +
            "JOIN dokter d ON o.operator1 = d.kd_dokter " +
            "JOIN spesialis s ON d.kd_sps = s.kd_sps " +
            "WHERE o.kode_paket = ? " +
            "AND o.tgl_operasi BETWEEN ? AND ? " +
            "AND o.kategori = ?"
        );

        } catch (Exception e) {
            System.out.println(e);
        }
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Formulir RL 3.12 Rekapitulasi Kegiatan Pelayanan Pembedahan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setName("tbBangsal"); // NOI18N
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/report24.png"))); // NOI18N
        jLabel1.setText("RL 3.12 - Pembedahan");
        jLabel1.setIconTextGap(10);
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            
            Map<String, Object> param = new HashMap<>();         
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("periode",Tgl1.getSelectedItem()+" s.d. "+Tgl2.getSelectedItem());   
            param.put("tanggal",Tgl2.getDate());  
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));  
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            for(int r=0;r<tabMode.getRowCount();r++){ 
                if(!tbBangsal.getValueAt(r,0).toString().contains(">>")){
                    Sequel.menyimpan("temporary","'"+r+"','"+
                                    tabMode.getValueAt(r,0).toString()+"','"+
                                    tabMode.getValueAt(r,1).toString()+"','"+
                                    tabMode.getValueAt(r,2).toString()+"','"+
                                    tabMode.getValueAt(r,3).toString()+"','"+
                                    tabMode.getValueAt(r,4).toString()+"','"+
                                    tabMode.getValueAt(r,5).toString()+"','"+
                                    tabMode.getValueAt(r,6).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Rekap Nota Pembayaran");
                }                    
            }
               
            Valid.MyReportqry("rptRl36.jasper","report","::[ Formulir RL 3.6 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
       tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TCari, BtnPrint);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
           TCari.setText("");
           tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tampil();

    }//GEN-LAST:event_formWindowActivated

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRl312Pembedahan dialog = new DlgRl312Pembedahan(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel1;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil(){  
//        try {
//            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
//            Valid.tabelKosong(tabMode);   
//            rs=ps.executeQuery();
//            i=1;
//            while(rs.next()){
//                pscari.setString(1,rs.getString("kode_paket"));
//                pscari.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:00.0");
//                pscari.setString(3,Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59.0");
//                pscari.setString(4,"Khusus");
//                rscari=pscari.executeQuery();
//                khusus=0;
//                if(rscari.next()){
//                    khusus=rscari.getInt(1);
//                }
//                
//                pscari.setString(1,rs.getString("kode_paket"));
//                pscari.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:00.0");
//                pscari.setString(3,Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59.0");
//                pscari.setString(4,"Besar");
//                rscari=pscari.executeQuery();
//                besar=0;
//                if(rscari.next()){
//                    besar=rscari.getInt(1);
//                }
//                
//                pscari.setString(1,rs.getString("kode_paket"));
//                pscari.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:00.0");
//                pscari.setString(3,Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59.0");
//                pscari.setString(4,"Sedang");
//                rscari=pscari.executeQuery();
//                sedang=0;
//                if(rscari.next()){
//                    sedang=rscari.getInt(1);
//                }
//                
//                pscari.setString(1,rs.getString("kode_paket"));
//                pscari.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" 00:00:00.0");
//                pscari.setString(3,Valid.SetTgl(Tgl2.getSelectedItem()+"")+" 23:59:59.0");
//                pscari.setString(4,"Kecil");
//                rscari=pscari.executeQuery();
//                kecil=0;
//                if(rscari.next()){
//                    kecil=rscari.getInt(1);
//                }
//                
//                tabMode.addRow(new Object[]{
//                    i,rs.getString("nm_perawatan"),khusus,besar,sedang,kecil,(khusus+besar+sedang+kecil)
//                });
//                i++;
//            }
//            this.setCursor(Cursor.getDefaultCursor());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    
    // modif mustafa daeng muma
    try {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Valid.tabelKosong(tabMode);   
        
        ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00.0");
        ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59.0");
        rs = ps.executeQuery();
        
        i = 1;
        while(rs.next()){
            tabMode.addRow(new Object[]{
                i,
                rs.getString("nm_sps"),
                rs.getInt("khusus"),
                rs.getInt("besar"),
                rs.getInt("sedang"),
                rs.getInt("kecil"),
                rs.getInt("khusus") + rs.getInt("besar") + rs.getInt("sedang") + rs.getInt("kecil")
            });
            i++;
        }
        this.setCursor(Cursor.getDefaultCursor());
    } catch (Exception e) {
        System.out.println(e);
    }
    
    }

}
