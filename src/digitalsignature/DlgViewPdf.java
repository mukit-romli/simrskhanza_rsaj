/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package digitalsignature;

//import custom.*;
//import fungsi.akses;
//import simrskhanza.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import inventory.DlgCariKonversi;
import inventory.DlgCariObat;
import inventory.DlgCariObat2;
import inventory.DlgCariObat3;
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
//import org.icepdf.ri.common.ComponentKeyBinding;
//import org.icepdf.ri.common.SwingController;
//import org.icepdf.ri.common.SwingViewBuilder;
import org.mozilla.javascript.tools.idswitch.FileBody;
import org.springframework.http.HttpEntity;

/**
 *
 * @author perpustakaan
 */
public class DlgViewPdf extends javax.swing.JDialog {
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private String username="",alasanHapus,fileOpen="",LocationFile="";
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    DlgPassPhrase passphrase=new DlgPassPhrase(null,true);

    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgViewPdf(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);
        txtLokasiFile.setVisible(false);
        txtNoRawat.setVisible(false);
        
        //TCatatan.setText(TCatatan); 
//        TCatatan.setLineWrap(true);
//        TCatatan.setWrapStyleWord(true);
//        status.setVisible(false);
        
passphrase.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
//            viewpdf(); 
            BtnViewFileActionPerformed(null);
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        panelGlass8 = new widget.panelisi();
        BtnViewFile = new widget.Button();
        BtnSignTTE = new widget.Button();
        BtnKeluar = new widget.Button();
        txtNameFile = new widget.TextBox();
        txtLokasiFile = new widget.TextBox();
        txtNoRawat = new widget.TextBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Signature Resume Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N
        internalFrame1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnViewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search.png"))); // NOI18N
        BtnViewFile.setMnemonic('S');
        BtnViewFile.setText("View");
        BtnViewFile.setToolTipText("Alt+S");
        BtnViewFile.setName("BtnViewFile"); // NOI18N
        BtnViewFile.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnViewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnViewFileActionPerformed(evt);
            }
        });
        BtnViewFile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnViewFileKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnViewFile);

        BtnSignTTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        BtnSignTTE.setMnemonic('S');
        BtnSignTTE.setText("Sign TTE");
        BtnSignTTE.setToolTipText("Alt+S");
        BtnSignTTE.setName("BtnSignTTE"); // NOI18N
        BtnSignTTE.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSignTTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSignTTEActionPerformed(evt);
            }
        });
        BtnSignTTE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSignTTEKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSignTTE);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+T");
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
        panelGlass8.add(BtnKeluar);

        txtNameFile.setEditable(false);
        txtNameFile.setText("2022_01_09_000001.pdf");
        txtNameFile.setName("txtNameFile"); // NOI18N
        txtNameFile.setPreferredSize(new java.awt.Dimension(200, 24));
        txtNameFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameFileActionPerformed(evt);
            }
        });
        panelGlass8.add(txtNameFile);

        txtLokasiFile.setEditable(false);
        txtLokasiFile.setName("txtLokasiFile"); // NOI18N
        txtLokasiFile.setPreferredSize(new java.awt.Dimension(200, 24));
        txtLokasiFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLokasiFileActionPerformed(evt);
            }
        });
        panelGlass8.add(txtLokasiFile);

        txtNoRawat.setEditable(false);
        txtNoRawat.setName("txtNoRawat"); // NOI18N
        txtNoRawat.setPreferredSize(new java.awt.Dimension(200, 24));
        txtNoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoRawatActionPerformed(evt);
            }
        });
        panelGlass8.add(txtNoRawat);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnViewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnViewFileActionPerformed
    if(Sequel.cariInteger("select count(nama_file) from berkas_tte where nama_file='"+txtNameFile.getText()+"'")>0){
       LocationFile="server"; 
       setButton(false);
       deleteFile();
    }else{
       setButton(true);
       LocationFile="local"; 
    }
    viewpdf(txtNameFile.getText(),LocationFile); 
}//GEN-LAST:event_BtnViewFileActionPerformed
void viewpdf(String fileName,String fileLocation){
//          try {
//                //HttpPost postRequest = new HttpPost("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/berkastte/upload.php?doc="+docpath);
////                SwingController ctrl = new SwingController();
////                SwingViewBuilder vb = new SwingViewBuilder(ctrl);
//                JPanel s = vb.buildViewerPanel();
////                ComponentKeyBinding.install(ctrl, s);
//                ctrl.setToolBarVisible(false);
//                ctrl.getDocumentViewController().setAnnotationCallback(
//                new org.icepdf.ri.common.MyAnnotationCallback(ctrl.getDocumentViewController())
//                );
//                if(fileLocation.equals("local")){  
//                  ctrl.openDocument("tempfile/"+txtNameFile.getText());
//                }else{
//                 URL url =new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+"/"+koneksiDB.HYBRIDWEB()+"/"+txtLokasiFile.getText()+"/"+txtNameFile.getText());
//                   ctrl.openDocument(url);
//                }
//              jScrollPane1.setViewportView(s); 
//        }
//        catch (Exception e){
//            
//        }
}
    void openpdf(String file){
//          try {
//            URL url =new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+"/"+koneksiDB.HYBRIDWEB()+"/"+txtLokasiFile.getText()+"/"+file);
//            SwingController ctrl = new SwingController();
//            SwingViewBuilder vb = new SwingViewBuilder(ctrl);
//            JPanel s = vb.buildViewerPanel();
//            ComponentKeyBinding.install(ctrl, s);
//            ctrl.setToolBarVisible(false);
//            ctrl.getDocumentViewController().setAnnotationCallback(
//            new org.icepdf.ri.common.MyAnnotationCallback(ctrl.getDocumentViewController())
//            );
//            ctrl.openDocument(url);
//            
////            dashboardview.add(s);
//              jScrollPane1.setViewportView(s); 
//        }
//        catch (Exception e){
//            
//        }
//  
//    try {
//          Defs.setProperty("java.awt.headless", "true");
// 	    Defs.setProperty("org.icepdf.core.scaleImages", "false");
// 	    Defs.setProperty("org.icepdf.core.print.disableAlpha", "true");
//        File file = new File("tempfile/rptLaporanResumeRanapPDF.pdf");
//        String filePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\PDFReader\\src\\pdfreader\\contoh.pdf";

// build a controller
//URL url =new URL("http://localhost/khanza-lite/berkasrawat/pages/upload/231012.pdf");
//SwingController controller = new SwingController();
//SwingViewBuilder factory = new SwingViewBuilder(controller);
//controller.openDocument(url);   
//           SwingController control=new SwingController();
//            SwingViewBuilder factry=new SwingViewBuilder(control);
//            JPanel veiwerCompntpnl=factry.buildViewerPanel();
//            ComponentKeyBinding.install(control, veiwerCompntpnl);
//            control.getDocumentViewController().setAnnotationCallback(
//                    new org.icepdf.ri.common.MyAnnotationCallback(
//                    control.getDocumentViewController()));
//                   control.openDocument(url);
//        jScrollPane1.setViewportView(veiwerCompntpnl); 
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this,"Cannot Load Pdf");
//        }
}
    private void BtnViewFileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnViewFileKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnViewFileActionPerformed(null);
        }else{
//            Valid.pindah(evt,TCatatan,BtnKeluar);
        }
}//GEN-LAST:event_BtnViewFileKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
      dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
//        TCatatan.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void txtNameFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameFileActionPerformed

    private void BtnSignTTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSignTTEActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       
        passphrase.setNamaFile(txtNameFile.getText(),txtLokasiFile.getText(),txtNoRawat.getText());
        passphrase.setSize(676,168);
        passphrase.setLocationRelativeTo(internalFrame1);
        passphrase.setVisible(true);
        
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnSignTTEActionPerformed

    private void BtnSignTTEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSignTTEKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSignTTEKeyPressed

    private void txtLokasiFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLokasiFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLokasiFileActionPerformed

    private void txtNoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoRawatActionPerformed
    void signtte(){
        
        
        
        
//         System.out.println("sign");
//        try{
//        File file =new File("tempfile/cobaResume.pdf");
//        byte[] data = new byte[(int) file.length()];
//        data = FileUtils.readFileToByteArray(file);
////        byte[] data = file.toByteArray();//convert ur file into byte[]
//        HttpClient httpClient = new DefaultHttpClient();//Client
//        HttpPost postRequest = new HttpPost("http://localhost/webapps/berkastte/upload.php");//Post Request to specified URL
//        ByteArrayBody bab = new ByteArrayBody(data, "b.pdf");
//        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);// Multipart data
//        reqEntity.addPart("file", bab); //adding data to request entity
//        postRequest.setEntity(reqEntity);//adding request entity to post request
//        HttpResponse response = (HttpResponse) httpClient.execute(postRequest); 
//         System.out.println(response);
//        }catch (Exception e){
//            
//        }
    
}
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgViewPdf dialog = new DlgViewPdf(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnSignTTE;
    private widget.Button BtnViewFile;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private widget.panelisi panelGlass8;
    private widget.TextBox txtLokasiFile;
    private widget.TextBox txtNameFile;
    private widget.TextBox txtNoRawat;
    // End of variables declaration//GEN-END:variables
    

    private void isPsien() {
//        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ",TPasien,TNoRM.getText());
    }

    
    
    
    public void isCek(){
        BtnViewFile.setEnabled(true);

       
        
    }
public void tampilPdf(String namFile,String pathFile)
{
     txtNameFile.setText(namFile);
     txtLokasiFile.setText(pathFile);
        openpdf(namFile);
 
    

}
public void tampilPdf2(String namFile)
{
     txtNameFile.setText(namFile);
        openpdf(namFile);
 
    

}
public void tampilPdfLocal(String namFile,String Location,String pathFile,String NoRawat)
{
     txtNameFile.setText(namFile);
     txtLokasiFile.setText(pathFile);
     txtNoRawat.setText(NoRawat);
  viewpdf(namFile,Location);
 

}
public void setButton(Boolean BtnTTE)
{
    BtnSignTTE.setVisible(BtnTTE);
}

void deleteFile(){
       File file = new File("tempfile");      
        String[] myFiles;    
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]); 
                myFile.delete();
            }
        }
   }


}
