package bridging;


import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import wa.WhatsappKirimFonnte;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import laporan.DlgBerkasRawat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.iio.ImageStorage.ImageType;
import fungsi.koneksiDBSalim;
import fungsi.koneksiDBWA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.sf.jasperreports.engine.*;
import java.sql.Connection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author dosen
 */
public class BPJSSuratKontrol extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private BPJSCekReferensiDokterKontrol dokter=new BPJSCekReferensiDokterKontrol(null,false);
    private BPJSCekReferensiSpesialistikKontrol poli=new BPJSCekReferensiSpesialistikKontrol(null,false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private String link="",requestJson="",URL="",user="",URUTNOREG="",utc="",JADIKANBOOKINGSURATKONTROLAPIBPJS="no",kodedokter="",kodepoli="",noreg="";
    private ApiBPJS api=new ApiBPJS();
    private boolean status=false;
    

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public BPJSSuratKontrol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.SEP","No.Kartu","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Diagnosa","Tgl.Entri",
                "No.Surat","Tgl.Kontrol","Kode Dokter","Nama Dokter/Sepesialis","Kode Poli","Nama Poli/Unit","Alasan Kontrol"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(125);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(65);
            }else if(i==6){
                column.setPreferredWidth(25);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(65);
            }else if(i==9){
                column.setPreferredWidth(125);
            }else if(i==10){
                column.setPreferredWidth(65);
            }else if(i==11){
                column.setPreferredWidth(70);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(70);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(200);    
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        NoRawat.setDocument(new batasInput((byte)15).getKata(NoRawat));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));

        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        } 
        
        ChkInput.setSelected(true);
        isForm();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),2).toString());
                }
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
        
        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dokter.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){                    
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                }   
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        try {
            link=koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        try {
            URUTNOREG=koneksiDB.URUTNOREG();
        } catch (Exception ex) {
            URUTNOREG="";
        }
        
        try {
            JADIKANBOOKINGSURATKONTROLAPIBPJS=koneksiDB.JADIKANBOOKINGSURATKONTROLAPIBPJS();
        } catch (Exception ex) {
            JADIKANBOOKINGSURATKONTROLAPIBPJS="no";
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat = new javax.swing.JMenuItem();
        MnUploadSKDP = new javax.swing.JMenuItem();
        ppBerkasDigital = new javax.swing.JMenuItem();
        MnSuratKirimWA = new javax.swing.JMenuItem();
        MnSuratLepasRawat = new javax.swing.JMenuItem();
        NoKartu = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKirimPesan = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        DTPTanggalSurat1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPTanggalSurat2 = new widget.Tanggal();
        LCount1 = new widget.Label();
        R2 = new widget.RadioButton();
        DTPTanggalKontrol1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPTanggalKontrol2 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoSEP = new widget.TextBox();
        TanggalSurat = new widget.Tanggal();
        jLabel10 = new widget.Label();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalKontrol = new widget.Tanggal();
        jLabel15 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        Diagnosa = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel18 = new widget.Label();
        KeteranganKontrol = new widget.TextBox();
        BtnSimpanAlasan = new widget.Button();
        Scroll3 = new widget.ScrollPane();
        TPesan = new widget.TextArea();
        jLabel20 = new widget.Label();
        NoHp = new widget.TextBox();
        jLabel19 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Surat Kontrol");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        MnUploadSKDP.setBackground(new java.awt.Color(255, 255, 254));
        MnUploadSKDP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUploadSKDP.setForeground(new java.awt.Color(50, 50, 50));
        MnUploadSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUploadSKDP.setText("Upload SKDP");
        MnUploadSKDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUploadSKDP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUploadSKDP.setName("MnUploadSKDP"); // NOI18N
        MnUploadSKDP.setPreferredSize(new java.awt.Dimension(160, 26));
        MnUploadSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUploadSKDPActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnUploadSKDP);

        ppBerkasDigital.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkasDigital.setForeground(new java.awt.Color(50, 50, 50));
        ppBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkasDigital.setText("Berkas Digital Perawatan");
        ppBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkasDigital.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkasDigital.setName("ppBerkasDigital"); // NOI18N
        ppBerkasDigital.setPreferredSize(new java.awt.Dimension(220, 26));
        ppBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasDigitalBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBerkasDigital);

        MnSuratKirimWA.setBackground(new java.awt.Color(255, 255, 254));
        MnSuratKirimWA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKirimWA.setForeground(new java.awt.Color(50, 50, 50));
        MnSuratKirimWA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKirimWA.setText("Kirim ke WA Pasien");
        MnSuratKirimWA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKirimWA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKirimWA.setName("MnSuratKirimWA"); // NOI18N
        MnSuratKirimWA.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSuratKirimWA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKirimWAActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratKirimWA);

        MnSuratLepasRawat.setBackground(new java.awt.Color(255, 255, 254));
        MnSuratLepasRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratLepasRawat.setForeground(new java.awt.Color(50, 50, 50));
        MnSuratLepasRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratLepasRawat.setText("Surat Lepas Rawat");
        MnSuratLepasRawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratLepasRawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratLepasRawat.setName("MnSuratLepasRawat"); // NOI18N
        MnSuratLepasRawat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSuratLepasRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratLepasRawatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratLepasRawat);

        NoKartu.setEditable(false);
        NoKartu.setHighlighter(null);
        NoKartu.setName("NoKartu"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surat Kontrol VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(90, 30));
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
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(90, 30));
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
        panelGlass8.add(BtnAll);

        BtnKirimPesan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/email.png"))); // NOI18N
        BtnKirimPesan.setMnemonic('M');
        BtnKirimPesan.setText("Kirim Pesan");
        BtnKirimPesan.setToolTipText("Alt+M");
        BtnKirimPesan.setName("BtnKirimPesan"); // NOI18N
        BtnKirimPesan.setPreferredSize(new java.awt.Dimension(105, 30));
        BtnKirimPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimPesanActionPerformed(evt);
            }
        });
        BtnKirimPesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKirimPesanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKirimPesan);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 30));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Tanggal Surat :");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(115, 23));
        panelCari.add(R1);

        DTPTanggalSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-12-2024" }));
        DTPTanggalSurat1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat1.setName("DTPTanggalSurat1"); // NOI18N
        DTPTanggalSurat1.setOpaque(false);
        DTPTanggalSurat1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalSurat1ItemStateChanged(evt);
            }
        });
        DTPTanggalSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPTanggalSurat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-12-2024" }));
        DTPTanggalSurat2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat2.setName("DTPTanggalSurat2"); // NOI18N
        DTPTanggalSurat2.setOpaque(false);
        DTPTanggalSurat2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat2);

        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(45, 23));
        panelCari.add(LCount1);

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tanggal Kontrol :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(120, 23));
        panelCari.add(R2);

        DTPTanggalKontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-12-2024" }));
        DTPTanggalKontrol1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol1.setName("DTPTanggalKontrol1"); // NOI18N
        DTPTanggalKontrol1.setOpaque(false);
        DTPTanggalKontrol1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol1ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol1);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPTanggalKontrol2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-12-2024" }));
        DTPTanggalKontrol2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol2.setName("DTPTanggalKontrol2"); // NOI18N
        DTPTanggalKontrol2.setOpaque(false);
        DTPTanggalKontrol2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol2ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol2);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 320));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 180));
        FormInput.setLayout(null);

        jLabel4.setText("No.SEP :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(285, 100, 50, 23);

        NoRawat.setEditable(false);
        NoRawat.setHighlighter(null);
        NoRawat.setName("NoRawat"); // NOI18N
        FormInput.add(NoRawat);
        NoRawat.setBounds(110, 10, 130, 23);

        jLabel9.setText("Spesialis/Sub :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(10, 220, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(210, 220, 280, 23);

        NoSEP.setEditable(false);
        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N
        FormInput.add(NoSEP);
        NoSEP.setBounds(340, 100, 150, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-12-2024" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(110, 160, 95, 23);

        jLabel10.setText("Tanggal Entri :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(10, 160, 90, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(110, 220, 95, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(500, 220, 28, 23);

        jLabel11.setText("Unit/Poli :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(40, 190, 60, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(110, 190, 95, 23);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(210, 190, 280, 23);

        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(500, 190, 28, 23);

        jLabel14.setText("Tanggal Kontrol :  ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(290, 160, 100, 23);

        TanggalKontrol.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-01-2025 12:05:20" }));
        TanggalKontrol.setDisplayFormat("dd-MM-yyyy");
        TanggalKontrol.setName("TanggalKontrol"); // NOI18N
        TanggalKontrol.setOpaque(false);
        TanggalKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKontrolKeyPressed(evt);
            }
        });
        FormInput.add(TanggalKontrol);
        TanggalKontrol.setBounds(392, 160, 100, 23);

        jLabel15.setText("No. Surat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(10, 100, 90, 23);

        NoSurat.setEditable(false);
        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(110, 100, 170, 23);

        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(177, 40, 310, 23);

        NoRM.setEditable(false);
        NoRM.setHighlighter(null);
        NoRM.setName("NoRM"); // NOI18N
        FormInput.add(NoRM);
        NoRM.setBounds(110, 40, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(110, 70, 95, 23);

        jLabel13.setText("Tgl.Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(40, 70, 60, 23);

        jLabel16.setText("Kelamin : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(330, 70, 60, 23);

        JK.setEditable(false);
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(390, 70, 95, 23);

        jLabel17.setText("Diagnosa :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(40, 130, 60, 23);

        Diagnosa.setEditable(false);
        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(110, 130, 380, 23);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp (2).png"))); // NOI18N
        BtnCari1.setMnemonic('7');
        BtnCari1.setText("Whatsapp");
        BtnCari1.setToolTipText("Alt+7");
        BtnCari1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        FormInput.add(BtnCari1);
        BtnCari1.setBounds(20, 460, 100, 40);

        jLabel18.setText("Alasan Kontrol :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(10, 250, 91, 23);

        KeteranganKontrol.setName("KeteranganKontrol"); // NOI18N
        FormInput.add(KeteranganKontrol);
        KeteranganKontrol.setBounds(110, 250, 380, 26);

        BtnSimpanAlasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanAlasan.setMnemonic('S');
        BtnSimpanAlasan.setText("Simpan Alasan");
        BtnSimpanAlasan.setToolTipText("Alt+S");
        BtnSimpanAlasan.setIconTextGap(8);
        BtnSimpanAlasan.setName("BtnSimpanAlasan"); // NOI18N
        BtnSimpanAlasan.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnSimpanAlasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanAlasanActionPerformed(evt);
            }
        });
        BtnSimpanAlasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanAlasanKeyPressed(evt);
            }
        });
        FormInput.add(BtnSimpanAlasan);
        BtnSimpanAlasan.setBounds(500, 250, 120, 26);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        TPesan.setColumns(20);
        TPesan.setRows(5);
        TPesan.setText("Salam,\n\n*{nama}*, No. Rekam Medis  *{rm}*, diinformasikan bahwa jadwal kontrol Bapak / Ibu ke \n\n     Poliklinik *{poli}*,\n     Dokter Spesialis *{dokter}*\n     Tgl. Kontrol *{tanggal}*\n\nUntuk datang kontrol di RS. Akademis Jaury Jusuf Putera Makassar sesuai dengan jadwal di atas. \nDimohon untuk datang lebih awal _( 1 jam Sebelum Praktek Dokter)_. \nJika Anda pasien _*BPJS Kesehatan*_ , Pendaftaran Wajib menggunakan Aplikasi _*JKN Mobile*_. \n\nTerima kasih, selamat beraktifitas dan selalu jaga kesehatan.\nInfo lebih lanjut, hubungi Customer service 085242554146");
        TPesan.setName("TPesan"); // NOI18N
        Scroll3.setViewportView(TPesan);

        FormInput.add(Scroll3);
        Scroll3.setBounds(140, 430, 430, 320);

        jLabel20.setText("No. Telpon :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(260, 10, 120, 23);

        NoHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NoHp.setHighlighter(null);
        NoHp.setName("NoHp"); // NOI18N
        FormInput.add(NoHp);
        NoHp.setBounds(388, 10, 100, 23);

        jLabel19.setText("No. Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 10, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt,TCari,TanggalKontrol);
}//GEN-LAST:event_TanggalSuratKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if(NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")){
        Valid.textKosong(NoRawat, "pasien");
    } else if(NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")){
        Valid.textKosong(KdDokter, "Dokter");
    } else if(NmPoli.getText().trim().equals("") || KdPoli.getText().trim().equals("")){
        Valid.textKosong(KdPoli, "Poli");
    } else if (KeteranganKontrol.getText().trim().equals("")) {
        Valid.textKosong(KeteranganKontrol, "Alasan Kontrol");  
    } else if(NoHp.getText().trim().equals("")||NoHp.getText().trim().equals("-")){
            Valid.textKosong(NoHp,"No. HP");    
    } else {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            URL = link + "/RencanaKontrol/insert";            
            requestJson = "{" +
                            "\"request\": {" +
                                "\"noSEP\":\"" + NoSEP.getText() + "\"," +
                                "\"kodeDokter\":\"" + KdDokter.getText() + "\"," +
                                "\"poliKontrol\":\"" + KdPoli.getText() + "\"," +
                                "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\"," +
                                "\"user\":\"" + user + "\"" +
                            "}" +
                          "}";
            System.out.println("JSON : " + requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if(nameNode.path("code").asText().equals("200")){
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("noSuratKontrol");
                if(Sequel.menyimpantf("bridging_surat_kontrol_bpjs", "?,?,?,?,?,?,?,?", "No.Surat", 8, new String[]{
                        NoSEP.getText(), Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), response.asText(), 
                        Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), 
                        KdPoli.getText(), NmPoli.getText()
                    }) == true) {
                    
                    // Simpan keterangan kontrol ke tabel "keterangan_kontrol_bpjs"
                    Sequel.menyimpantf("keterangan_kontrol_bpjs", "?,?,?,NOW()", "Keterangan Kontrol", 3, new String[]{
                        NoRawat.getText(), KeteranganKontrol.getText(), akses.getkode()
                    });
                    BtnCari1ActionPerformed(null);
                    emptTeks();
                    tampil();
                    if(JADIKANBOOKINGSURATKONTROLAPIBPJS.equals("yes")){
                        if(isBooking() == false){
                            JOptionPane.showMessageDialog(null, "Gagal menyimpan booking, silahkan hubungi administrator...!!!!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging : " + ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,NoSurat,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            try {
                bodyWithDeleteRequest();
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau dihapus..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            if(R1.isSelected()==true){
                Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between '"+Valid.SetTgl(DTPTanggalSurat1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalSurat2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like '%"+TCari.getText().trim()+"%' or bridging_sep.no_sep like '%"+TCari.getText().trim()+"%' or bridging_sep.no_kartu like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_sep.nomr like '%"+TCari.getText().trim()+"%' or bridging_sep.nama_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
                    "order by bridging_surat_kontrol_bpjs.tgl_surat",param);
            }else if(R2.isSelected()==true){
                Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between '"+Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like '%"+TCari.getText().trim()+"%' or bridging_sep.no_sep like '%"+TCari.getText().trim()+"%' or bridging_sep.no_kartu like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_sep.nomr like '%"+TCari.getText().trim()+"%' or bridging_sep.nama_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
                    "order by bridging_surat_kontrol_bpjs.tgl_rencana",param);
            }
                
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, NoSEP);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if(KdPoli.getText().equals("")||NmPoli.getText().equals("")){
        Valid.textKosong(BtnPoli,"Unit/Poli");
    }else{
        dokter.SetKontrol(KdPoli.getText(),"2: Rencana Kontrol",Valid.SetTgl(TanggalKontrol.getSelectedItem()+""));
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }
        
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
        Valid.pindah(evt,TanggalKontrol,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPTanggalKontrol1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol1KeyPressed

    private void DTPTanggalKontrol2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol2KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
        Valid.textKosong(NoRawat, "pasien");
    } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
        Valid.textKosong(KdDokter, "Dokter");
    } else if (NmPoli.getText().trim().equals("") || KdPoli.getText().trim().equals("")) {
        Valid.textKosong(KdPoli, "Poli");
    } else {
        if (tbObat.getSelectedRow() != -1) {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = link + "/RencanaKontrol/Update";            
                requestJson = "{" +
                                "\"request\": {" +
                                    "\"noSuratKontrol\":\"" + NoSurat.getText() + "\"," +
                                    "\"noSEP\":\"" + NoSEP.getText() + "\"," +
                                    "\"kodeDokter\":\"" + KdDokter.getText() + "\"," +
                                    "\"poliKontrol\":\"" + KdPoli.getText() + "\"," +
                                    "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\"," +
                                    "\"user\":\"" + user + "\"" +
                                "}" +
                              "}";
                System.out.println("JSON : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                if (nameNode.path("code").asText().equals("200")) {
                    if (Sequel.mengedittf("bridging_surat_kontrol_bpjs", "no_surat=?", 
                            "tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?", 
                            7, new String[] {
                                Valid.SetTgl(TanggalSurat.getSelectedItem() + ""), 
                                Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), 
                                KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), 
                                NmPoli.getText(), NoSurat.getText()
                    }) == true) {
                        
                        Sequel.mengedittf("keterangan_kontrol_bpjs", "no_rawat=?,keterangan_kontrol=?,petugas=?,waktu=NOW()", 
                        "Keterangan Kontrol", 4, new String[]{
                            NoRawat.getText(), KeteranganKontrol.getText(), akses.getkode(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + "")
                    });

                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }   
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti...\n Klik data pada table untuk memilih data...!!!!");
        }                
    }
//        if(NoRawat.getText().trim().equals("")||NoSEP.getText().trim().equals("")){
//            Valid.textKosong(NoRawat,"pasien");
//        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
//            Valid.textKosong(KdDokter,"Dokter");
//        }else if(NmPoli.getText().trim().equals("")||NmPoli.getText().trim().equals("")){
//            Valid.textKosong(KdPoli,"Poli");
//        }else{
//            if(tbObat.getSelectedRow()!= -1){
//                try {
//                    headers = new HttpHeaders();
//                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
//                    utc=String.valueOf(api.GetUTCdatetimeAsString());
//                    headers.add("X-Timestamp",utc);
//                    headers.add("X-Signature",api.getHmac(utc));
//                    headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
//                    URL = link+"/RencanaKontrol/Update";            
//                    requestJson ="{" +
//                                    "\"request\": {" +
//                                        "\"noSuratKontrol\":\""+NoSurat.getText()+"\"," +
//                                        "\"noSEP\":\""+NoSEP.getText()+"\"," +
//                                        "\"kodeDokter\":\""+KdDokter.getText()+"\"," +
//                                        "\"poliKontrol\":\""+KdPoli.getText()+"\"," +
//                                        "\"tglRencanaKontrol\":\""+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"\"," +
//                                        "\"user\":\""+user+"\"" +
//                                    "}" +
//                                 "}";
//                    System.out.println("JSON : "+requestJson);
//                    requestEntity = new HttpEntity(requestJson,headers);
//                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
//                    nameNode = root.path("metaData");
//                    System.out.println("code : "+nameNode.path("code").asText());
//                    System.out.println("message : "+nameNode.path("message").asText());
//                    if(nameNode.path("code").asText().equals("200")){
//                        if(Sequel.mengedittf("bridging_surat_kontrol_bpjs","no_surat=?","tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?",7,new String[]{
//                                Valid.SetTgl(TanggalSurat.getSelectedItem()+""),Valid.SetTgl(TanggalKontrol.getSelectedItem()+""),KdDokter.getText(),NmDokter.getText(),KdPoli.getText(),NmPoli.getText(),NoSurat.getText()
//                            })==true){
//                            emptTeks();
//                            tampil();
//                        }
//                    }else{
//                        JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
//                    }   
//                }catch (Exception ex) {
//                    System.out.println("Notifikasi Bridging : "+ex);
//                    if(ex.toString().contains("UnknownHostException")){
//                        JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
//                    }
//                }
//            }else{
//                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti...\n Klik data pada table untuk memilih data...!!!!");
//            }                
//        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void DTPTanggalKontrol1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPTanggalKontrol1ItemStateChanged

    private void DTPTanggalKontrol2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2ItemStateChanged
        R2.setSelected(true);        
    }//GEN-LAST:event_DTPTanggalKontrol2ItemStateChanged

    private void TanggalKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKontrolKeyPressed
        Valid.pindah(evt,TanggalSurat,BtnDokter);
    }//GEN-LAST:event_TanggalKontrolKeyPressed

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt,TanggalSurat,TanggalKontrol);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnDokter,TanggalKontrol);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.SetKontrol(NoSEP.getText(),"2: Rencana Kontrol",Valid.SetTgl(TanggalKontrol.getSelectedItem()+""));
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select gambar.bpjs from gambar")); 
            param.put("parameter",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("alasan", Sequel.cariIsi("select keterangan_kontrol_bpjs.keterangan_kontrol from keterangan_kontrol_bpjs where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()+"\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()));
            Valid.MyReportqry("rptBridgingSuratKontrol2.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.no_surat='"+NoSurat.getText()+"'",param);              
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        } 

////KODINGAN BERHASIL
//      if (tbObat.getSelectedRow() != -1) {
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        
//        // Parameter untuk report
//        Map<String, Object> param = new HashMap<>();
//        param.put("namars", akses.getnamars());
//        param.put("alamatrs", akses.getalamatrs());
//        param.put("kotars", akses.getkabupatenrs());
//        param.put("propinsirs", akses.getpropinsirs());
//        param.put("kontakrs", akses.getkontakrs());
//        param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
//        param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
//        param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() 
//                + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString()
//                + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString()
//                + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));
//
//        try {
//            // Meminta pengguna untuk memilih folder khanza_auto_update
//            JFileChooser folderChooser = new JFileChooser();
//            folderChooser.setDialogTitle("Pilih Folder khanza_auto_update");
//            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            folderChooser.setAcceptAllFileFilterUsed(false);
//
//            // Menampilkan dialog pemilihan folder
//            int returnValue = folderChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                File khanzaDir = folderChooser.getSelectedFile();
//                
//                String jasperPath = khanzaDir.getAbsolutePath() + "\\report\\rptBridgingSuratKontrol2.jasper";
//                
//                // Load file Jasper dan isi data report
//                JasperPrint jasperPrint = JasperFillManager.fillReport(
//                        jasperPath, 
//                        param, 
//                        koneksiDB.condb()
//                );
//
//                // Tentukan lokasi file yang akan disimpan di folder Downloads pengguna
//                String outputPath = System.getProperty("user.home") + "\\Downloads\\report_image.png";
//
//                // Ekspor tiap halaman report ke gambar (PNG)
//                List<JRPrintPage> pages = jasperPrint.getPages();
//                if (pages != null && !pages.isEmpty()) {
//                    BufferedImage image = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, 2.0f);
//                    File outputFile = new File(outputPath);
//                    ImageIO.write(image, "png", outputFile);  // Simpan gambar sebagai PNG
//                }
//
//                JOptionPane.showMessageDialog(null, "Report berhasil disimpan sebagai gambar di: " + outputPath);
//            } else {
//                JOptionPane.showMessageDialog(null, "Pemilihan folder dibatalkan.");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            this.setCursor(Cursor.getDefaultCursor());
//        }
//    } else {
//        JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
//        BtnBatal.requestFocus();
//    }        
//if(tbObat.getSelectedRow() != -1) {
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
//        Map<String, Object> param = new HashMap<>();
//        param.put("namars", akses.getnamars());
//        param.put("alamatrs", akses.getalamatrs());
//        param.put("kotars", akses.getkabupatenrs());
//        param.put("propinsirs", akses.getpropinsirs());
//        param.put("kontakrs", akses.getkontakrs());
//        param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar")); 
//        param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
//        param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString() + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString() + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));
//
//        try {
//            // Load report
//            String projectDir = System.getProperty("user.dir"); // Mendapatkan path direktori proyek
////            String jasperReport = projectDir + "/report/report.png"; // Jalur relatif dari folder proyek
//            String sourceFileName = projectDir +"/report/rptBridgingSuratKontrol2.jrxml";
//            String jasperFileName = projectDir +"/report/rptBridgingSuratKontrol2.jasper";
//
//            // Kompilasi file .jrxml ke .jasper
//            JasperCompileManager.compileReportToFile(sourceFileName, jasperFileName);
//
//            // Load report
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperFileName);
//
//            // Fill report
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, koneksi);
//
//            // Export to PDF
////            String projectDir = System.getProperty("user.dir"); // Mendapatkan path direktori proyek
//            String outputPdfPath = projectDir + "/report/report.png"; // Jalur relatif dari folder proyek
////            String outputPdfPath = "path/to/khanza_auto_update/report.pdf"; // Ganti dengan path yang sesuai
//            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);
//
//            // Convert PDF to Image
//            PDDocument document = PDDocument.load(new File(outputPdfPath));
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//
//            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300); // Mengatur DPI ke 300
//            // Tentukan lokasi file yang akan disimpan di folder Downloads pengguna
//            String outputImagePath = System.getProperty("user.home") + "\\Downloads\\report_image.png";
////            String outputImagePath = "path/to/output/image.png"; // Ganti dengan path yang sesuai
//            ImageIO.write(bim, "PNG", new File(outputImagePath)); // Simpan gambar sebagai PNG
//
//            document.close(); // Tutup dokumen PDF
//            JOptionPane.showMessageDialog(null, "Report berhasil disimpan sebagai gambar di: " + outputImagePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Gagal membuat laporan: " + e.getMessage());
//        }
//
//        this.setCursor(Cursor.getDefaultCursor());
//    } else {
//        JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
//        BtnBatal.requestFocus();
//    }
}

// Fungsi untuk mencari folder secara rekursif
private File findFolderRecursively(File dir, String folderName) {
        if (dir == null || !dir.isDirectory()) return null;

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (file.getName().equalsIgnoreCase(folderName)) {
                        return file; // Folder ditemukan
                    } else {
                        File result = findFolderRecursively(file, folderName);
                        if (result != null) {
                            return result; // Folder ditemukan dalam subdirektori
                        }
                    }
                }
            }
        }
        return null; // Folder tidak ditemukan
    }//GEN-LAST:event_MnSuratActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void DTPTanggalSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1KeyPressed

    }//GEN-LAST:event_DTPTanggalSurat1KeyPressed

    private void DTPTanggalSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat1ItemStateChanged

    private void DTPTanggalSurat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat2KeyPressed
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat2KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
//        if(NmPasien.getText().trim().equals("")&&NoRM.getText().trim().equals("")){
//            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien...!!!");
//            TCari.requestFocus();
//        }else{
//            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            WhatsappKirimFonnte kirim=new WhatsappKirimFonnte(null,false);
//            kirim.setNoRm(NoRM.getText(),NoRawat.getText(),NmPoli.getText(),NmDokter.getText(),TanggalKontrol.getDate());
//            kirim.setSize(720,330);
//            kirim.setLocationRelativeTo(internalFrame1);
//            kirim.setVisible(true);
//            this.setCursor(Cursor.getDefaultCursor());
//        }

        if(NoHp.getText().trim().equals("")){
            Valid.textKosong(NoHp,"No.HP");
        }else if(TPesan.getText().trim().equals("")){
            Valid.textKosong(TPesan,"Isi Pesan Terlebih dahulu.. ");
        }else{  
        try {                                         
            String url = "https://api.fonnte.com/send";
            String TokenWA = koneksiDBWA.TOKENWA();            
            
            // Parameter yang akan dikirimkan
            String target = NoHp.getText()+"|"+NmPasien.getText()+"|"+NoRM.getText()+"|"+Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText())+"|"+NmPoli.getText()+"|"+NmDokter.getText()+"|"+TanggalKontrol.getSelectedItem();
            String message = TPesan.getText();
            String pesan = TPesan.getText();
            String countryCode = "62"; // optional            
            String tanggal = (String) TanggalKontrol.getSelectedItem();
            message = message.replace("{nama}", NmPasien.getText())
                 .replace("{rm}", NoRM.getText())
                 .replace("{antrian}", Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText()))
                 .replace("{poli}", NmPoli.getText())
                 .replace("{dokter}", NmDokter.getText())
                 .replace("{tanggal}", tanggal); 
            
            pesan = pesan.replace("{nama}", NmPasien.getText())
                 .replace("{rm}", NoRM.getText())
                 .replace("{antrian}", Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText()))
                 .replace("{poli}", NmPoli.getText())
                 .replace("{dokter}", NmDokter.getText())
                 .replace("{tanggal}", tanggal)
                 .replace("*", "")
                 .replace("_", "")
                 .replace("{", "")
                 .replace("}", "");              
           
            // Menggabungkan parameter ke dalam format yang sesuai untuk dikirimkan
            String postData = "target=" + target + "&message=" + message + "&countryCode=" + countryCode;
            
            // Membuat objek URL
            URL obj = new URL(url);
            
            // Membuka koneksi HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // Mengatur properti untuk koneksi
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", TokenWA);
            con.setDoOutput(true);
            
            // Mengirim data
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Menerima respon dari server
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                // Menampilkan respon
                System.out.println(response.toString());
        
        ObjectMapper objectMapper = new ObjectMapper();
        // Mengonversi JSON String menjadi JsonNode
        JsonNode jsonNode = objectMapper.readTree(response.toString());
        
        // Menampilkan nilai JSON
//        System.out.println("detail: " + jsonNode.get("detail").asText());
//        System.out.println("id: " + jsonNode.get("id").get(0).asText());
//        System.out.println("proses: " + jsonNode.get("process").asText());
//        System.out.println("status: " + jsonNode.get("status").asText());
//        System.out.println("target: " + jsonNode.get("target").get(0).asText());        

        if ("false".equals(jsonNode.get("status").asText())) {
            //pesan error     
            JOptionPane.showMessageDialog(null, "Gagal mengirim, mohon cek koneksi Whatsapp Gateway !!", "Error", JOptionPane.ERROR_MESSAGE);

            } else if ("true".equals(jsonNode.get("status").asText())) {
             LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Mengonversi LocalDateTime ke String dengan format yang diinginkan
            String DateNow = now.format(formatter);
            Sequel.queryu("insert into wa_report values('"+jsonNode.get("id").get(0).asText()+"','"+NoRM.getText()+"','"+NoHp.getText()+"','"+akses.getkode()+"','SIMRS Khanza','"+jsonNode.get("target").get(0).asText()+"','"+pesan+"','','"+jsonNode.get("status").asText()+"','','"+DateNow+"','"+DateNow+"')");
            //pesan sukses     
            JOptionPane.showMessageDialog(null, "Berhasil Mengirim Pesan !!", "Success", JOptionPane.INFORMATION_MESSAGE);
            BtnKeluarActionPerformed(evt);
            }
                } catch (IOException ex) {
                    Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ProtocolException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            }              
        }     
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void MnUploadSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUploadSKDPActionPerformed
      if(tbObat.getSelectedRow() != -1) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        // Menentukan jalur utama aplikasi
        String basePath = System.getProperty("user.dir");
        // Pastikan jalur tidak ada penggandaan
        String reportPath = basePath + "/report/rptBridgingSuratKontrol2.jasper";
        reportPath = reportPath.replace("\\", "/"); // Pastikan format jalur sesuai

            try {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
                param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                param.put("alasan", Sequel.cariIsi("select keterangan_kontrol_bpjs.keterangan_kontrol from keterangan_kontrol_bpjs where no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));
                param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString() + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString() + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));

                // Memuat laporan Jasper
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(reportPath));
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, koneksiDB.condb());

                // Menampilkan laporan
                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memuat laporan: " + ex.getMessage());
            } finally {
                this.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnUploadSKDPActionPerformed

    private void ppBerkasDigitalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasDigitalBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(!tbObat.getValueAt(tbObat.getSelectedRow(),0).toString().equals("")){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBerkasRawat berkas=new DlgBerkasRawat(null,true);
                    berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
                    try {
                        if(akses.gethapus_berkas_digital_perawatan()==true){
                            berkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/"+"berkasrawat/login2.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&no_rawat="+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                        }else{
                            berkas.loadURL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/"+"berkasrawat/login2nonhapus.php?act=login&usere="+koneksiDB.USERHYBRIDWEB()+"&passwordte="+koneksiDB.PASHYBRIDWEB()+"&no_rawat="+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                        }
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : "+ex);
                    }

                    berkas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    berkas.setLocationRelativeTo(internalFrame1);
                    berkas.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppBerkasDigitalBtnPrintActionPerformed

    private void BtnSimpanAlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanAlasanActionPerformed
    if(NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
        Valid.textKosong(NoRawat, "pasien");
    } else if(NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
        Valid.textKosong(KdDokter, "Dokter");
    } else if(NmPoli.getText().trim().equals("") || KdPoli.getText().trim().equals("")) {
        Valid.textKosong(KdPoli, "Poli");
    } else {
        try {
            // Cek apakah sudah ada data dengan NoRawat yang sama
            String cekData = "SELECT COUNT(*) FROM keterangan_kontrol_bpjs WHERE no_rawat = ?";
            PreparedStatement psCek = koneksi.prepareStatement(cekData);
            psCek.setString(1, NoRawat.getText().trim());
            ResultSet rsCek = psCek.executeQuery();
            rsCek.next();
            
            if (rsCek.getInt(1) > 0) {
                // Jika data sudah ada, lakukan UPDATE
                String updateData = "UPDATE keterangan_kontrol_bpjs SET keterangan_kontrol = ?, petugas = ?, waktu = NOW() WHERE no_rawat = ?";
                PreparedStatement psUpdate = koneksi.prepareStatement(updateData);
                psUpdate.setString(1, KeteranganKontrol.getText());
                psUpdate.setString(2, akses.getkode()); // Petugas diambil dari kode akses
                psUpdate.setString(3, NoRawat.getText().trim());
                int rowsUpdated = psUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Keterangan kontrol berhasil diperbarui.");
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui keterangan kontrol.");
                }
            } else {
                // Jika data tidak ada, lakukan INSERT
                String insertData = "INSERT INTO keterangan_kontrol_bpjs (no_rawat, keterangan_kontrol, petugas, waktu) VALUES (?, ?, ?, NOW())";
                PreparedStatement psInsert = koneksi.prepareStatement(insertData);
                psInsert.setString(1, NoRawat.getText());
                psInsert.setString(2, KeteranganKontrol.getText());
                psInsert.setString(3, akses.getkode()); // Petugas diambil dari kode akses
                if (psInsert.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Keterangan kontrol berhasil disimpan.");
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan keterangan kontrol.");
                }
            }
            
            emptTeks();
            tampil();
        } catch (Exception ex) {
            System.out.println("Notifikasi Simpan Keterangan Kontrol : " + ex);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan keterangan kontrol.");
        }
    }
    }//GEN-LAST:event_BtnSimpanAlasanActionPerformed

    private void BtnSimpanAlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanAlasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanAlasanKeyPressed

    private void MnSuratKirimWAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKirimWAActionPerformed
    if (tbObat.getSelectedRow() != -1) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
        param.put("parameter", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
        param.put("alasan", Sequel.cariIsi("select keterangan_kontrol_bpjs.keterangan_kontrol from keterangan_kontrol_bpjs where no_rawat=?", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));
//        param.put("finger", "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString() + "\nID " + tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString() + "\n" + Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()));
        
        // Generate PDF report
        Valid.MyReportPDF("rptBridgingSuratKontrolWA.jasper", "report", "::[ Data Surat Kontrol VClaim ]::", param);

        try {
            String url = "https://api.fonnte.com/send";
            String tokenWA = koneksiDBSalim.TOKENWA();
            String noWa = "+6282321694247"; // Nomor tujuan WhatsApp
            String countryCode = "62"; // Kode negara Indonesia

            // Path to the local file to be sent
            String filePath = "report/rptBridgingSuratKontrolWA.pdf";
            String fileName = tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString()+"_"+tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString() + ".pdf"; // Optional, nama file PDF

            // Prepare the HTTP connection
            String boundary = "----WebKitFormBoundary" + Long.toHexString(System.currentTimeMillis());
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", tokenWA);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setDoOutput(true);

            // Send the file without message text
            try (OutputStream outputStream = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)) {

                // Form field for target number
                writer.append("--").append(boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"target\"").append("\r\n\r\n").append(noWa).append("\r\n");

                // Form field for country code
                writer.append("--").append(boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"countryCode\"").append("\r\n\r\n").append(countryCode).append("\r\n");

                // Add file part
                writer.append("--").append(boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(fileName).append("\"\r\n");
                writer.append("Content-Type: application/pdf").append("\r\n\r\n").flush();

                // Write the PDF file to the output stream
                try (FileInputStream inputStream = new FileInputStream(filePath)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                }

                writer.append("\r\n").flush(); // End of file part

                // End of multipart request
                writer.append("--").append(boundary).append("--").append("\r\n").flush();
            }

            // Check the server's response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    // Process the JSON response
                    String jsonResponse = response.toString();
                    System.out.println(jsonResponse);

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                    if ("false".equals(jsonNode.get("status").asText())) {
                        JOptionPane.showMessageDialog(null, "Gagal mengirim, mohon cek koneksi Whatsapp Gateway !!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if ("true".equals(jsonNode.get("status").asText())) {
                        JOptionPane.showMessageDialog(null, "Berhasil Mengirim Lampiran PDF !!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                throw new IOException("Server returned non-OK status: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setCursor(Cursor.getDefaultCursor());
    } else {
        JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
        BtnBatal.requestFocus();
    } 
    }//GEN-LAST:event_MnSuratKirimWAActionPerformed

    private void MnSuratLepasRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratLepasRawatActionPerformed
            if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("umur",Sequel.cariIsi("SELECT CONCAT(TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()), ' Tahun') FROM bridging_sep WHERE bridging_sep.no_rawat =?",NoRawat.getText()));
            param.put("alamat",Sequel.cariIsi("SELECT pasien.alamat FROM pasien WHERE pasien.no_rkm_medis =?",NoRM.getText()));
            param.put("kamar",Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar_inap ki JOIN kamar k ON ki.kd_kamar = k.kd_kamar JOIN bangsal b ON k.kd_bangsal = b.kd_bangsal WHERE ki.stts_pulang <> 'Pindah Kamar' AND ki.no_rawat =?",NoRawat.getText()));
            param.put("tglmasuk",Sequel.cariIsi("SELECT MIN( tgl_masuk ) AS tgl_terawal FROM kamar_inap WHERE no_rawat =? GROUP BY no_rawat",NoRawat.getText()));
            param.put("tglkeluar",Sequel.cariIsi("SELECT tgl_keluar FROM kamar_inap WHERE stts_pulang <> 'Pindah Kamar' AND no_rawat =?",NoRawat.getText()));
            param.put("diagnosa",Sequel.cariIsi("SELECT kd_diagnosa_utama FROM resume_pasien_ranap WHERE no_rawat =?",NoRawat.getText()));
            param.put("obatpulang",Sequel.cariIsi("SELECT obat_pulang FROM resume_pasien_ranap WHERE no_rawat =?",NoRawat.getText()));
            param.put("hasillab",Sequel.cariIsi("SELECT hasil_laborat FROM resume_pasien_ranap WHERE no_rawat =?",NoRawat.getText()));
            param.put("hasilrab",Sequel.cariIsi("SELECT pemeriksaan_penunjang FROM resume_pasien_ranap WHERE no_rawat =?",NoRawat.getText()));
            param.put("namars",akses.getnamars());
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            param.put("parameter",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("alasan", Sequel.cariIsi("select keterangan_kontrol_bpjs.keterangan_kontrol from keterangan_kontrol_bpjs where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()));
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()+"\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()));
            Valid.MyReportqry("rptBridgingSuratLepasRawat.jasper","report","::[ Data Surat Lepas Rawat ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.no_surat='"+NoSurat.getText()+"'",param);              
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        } 
    }//GEN-LAST:event_MnSuratLepasRawatActionPerformed

    private void BtnKirimPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimPesanActionPerformed
        if(NoHp.getText().trim().equals("")||NoHp.getText().trim().equals("")){
            Valid.textKosong(NoHp,"No.Rekam Medis");
        }else if(TPesan.getText().trim().equals("")){
            Valid.textKosong(TPesan,"Isi Pesan Terlebih dahulu.. ");
        }else{  
        try {                                         
            String url = "https://api.fonnte.com/send";
            String TokenWA = koneksiDBWA.TOKENWA();            
            
            // Parameter yang akan dikirimkan
            String target = NoHp.getText()+"|"+NmPasien.getText()+"|"+NoRM.getText()+"|"+Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText())+"|"+NmPoli.getText()+"|"+NmDokter.getText()+"|"+TanggalKontrol.getSelectedItem();
            String message = TPesan.getText();
            String pesan = TPesan.getText();
            String countryCode = "62"; // optional            
            String tanggal = (String) TanggalKontrol.getSelectedItem();
            message = message.replace("{nama}", NmPasien.getText())
                 .replace("{rm}", NoRM.getText())
                 .replace("{antrian}", Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText()))
                 .replace("{poli}", NmPoli.getText())
                 .replace("{dokter}", NmDokter.getText())
                 .replace("{tanggal}", tanggal); 
            
            pesan = pesan.replace("{nama}", NmPasien.getText())
                 .replace("{rm}", NoRM.getText())
                 .replace("{antrian}", Sequel.cariIsi("Select no_reg from reg_periksa where no_rawat=?",NoRawat.getText()))
                 .replace("{poli}", NmPoli.getText())
                 .replace("{dokter}", NmDokter.getText())
                 .replace("{tanggal}", tanggal)
                 .replace("*", "")
                 .replace("_", "")
                 .replace("{", "")
                 .replace("}", "");          
                                       
            // Menggabungkan parameter ke dalam format yang sesuai untuk dikirimkan
            String postData = "target=" + target + "&message=" + message + "&countryCode=" + countryCode;
            
            // Membuat objek URL
            URL obj = new URL(url);
            
            // Membuka koneksi HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // Mengatur properti untuk koneksi
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", TokenWA);
            con.setDoOutput(true);
            
            // Mengirim data
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Menerima respon dari server
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                // Menampilkan respon
                System.out.println(response.toString());
        
        ObjectMapper objectMapper = new ObjectMapper();
        // Mengonversi JSON String menjadi JsonNode
        JsonNode jsonNode = objectMapper.readTree(response.toString());
        
        // Menampilkan nilai JSON
//        System.out.println("detail: " + jsonNode.get("detail").asText());
//        System.out.println("id: " + jsonNode.get("id").get(0).asText());
//        System.out.println("proses: " + jsonNode.get("process").asText());
//        System.out.println("status: " + jsonNode.get("status").asText());
//        System.out.println("target: " + jsonNode.get("target").get(0).asText());        

        if ("false".equals(jsonNode.get("status").asText())) {
            //pesan error     
            JOptionPane.showMessageDialog(null, "Gagal mengirim, mohon cek koneksi Whatsapp Gateway !!", "Error", JOptionPane.ERROR_MESSAGE);

            } else if ("true".equals(jsonNode.get("status").asText())) {
             LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Mengonversi LocalDateTime ke String dengan format yang diinginkan
            String DateNow = now.format(formatter);
            Sequel.queryu("insert into wa_report values('"+jsonNode.get("id").get(0).asText()+"','"+NoRM.getText()+"','"+NoHp.getText()+"','"+akses.getkode()+"','SIMRS Khanza','"+jsonNode.get("target").get(0).asText()+"','"+pesan+"','','"+jsonNode.get("status").asText()+"','','"+DateNow+"','"+DateNow+"')");
            //pesan sukses     
            JOptionPane.showMessageDialog(null, "Berhasil Mengirim Pesan !!", "Success", JOptionPane.INFORMATION_MESSAGE);
            BtnKeluarActionPerformed(evt);
            }
                } catch (IOException ex) {
                    Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (ProtocolException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WhatsappKirimFonnte.class.getName()).log(Level.SEVERE, null, ex);
            }              
        }              
    }//GEN-LAST:event_BtnKirimPesanActionPerformed

    private void BtnKirimPesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKirimPesanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKirimPesanKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSSuratKontrol dialog = new BPJSSuratKontrol(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirimPesan;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanAlasan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPTanggalKontrol1;
    private widget.Tanggal DTPTanggalKontrol2;
    private widget.Tanggal DTPTanggalSurat1;
    private widget.Tanggal DTPTanggalSurat2;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.TextBox KeteranganKontrol;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnSurat;
    private javax.swing.JMenuItem MnSuratKirimWA;
    private javax.swing.JMenuItem MnSuratLepasRawat;
    private javax.swing.JMenuItem MnUploadSKDP;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private widget.TextBox NoHp;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextArea TPesan;
    private widget.Tanggal TanggalKontrol;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBerkasDigital;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

private void tampil() {        
    Valid.tabelKosong(tabMode);
    try {
        if(R1.isSelected()==true){
            ps = koneksi.prepareStatement(
                "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir," +
                "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat," +
                "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs," +
                "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs, keterangan_kontrol_bpjs.keterangan_kontrol " +
                "from bridging_sep " +
                "inner join bridging_surat_kontrol_bpjs on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep " +
                "left join keterangan_kontrol_bpjs on keterangan_kontrol_bpjs.no_rawat = bridging_sep.no_rawat " +
                "where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? " +
                (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or " +
                "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or " +
                "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)") +
                " order by bridging_surat_kontrol_bpjs.tgl_surat");
            try {
                ps.setString(1, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                if(!TCari.getText().trim().equals("")) {
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");                    
                }

                rs = ps.executeQuery();
                while(rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_sep"), rs.getString("no_kartu"), rs.getString("nomr"), rs.getString("nama_pasien"),
                        rs.getString("tanggal_lahir"), rs.getString("jkel"), rs.getString("nmdiagnosaawal"), rs.getString("tgl_surat"), rs.getString("no_surat"),
                        rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"), rs.getString("kd_poli_bpjs"), rs.getString("nm_poli_bpjs"),
                        rs.getString("keterangan_kontrol")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
            }
        } else if(R2.isSelected()==true) {
            ps = koneksi.prepareStatement(
                "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir," +
                "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat," +
                "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs," +
                "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs, keterangan_kontrol_bpjs.keterangan_kontrol " +
                "from bridging_sep " +
                "inner join bridging_surat_kontrol_bpjs on bridging_surat_kontrol_bpjs.no_sep = bridging_sep.no_sep " +
                "left join keterangan_kontrol_bpjs on keterangan_kontrol_bpjs.no_rawat = bridging_sep.no_rawat " +
                "where bridging_surat_kontrol_bpjs.tgl_rencana between ? and ? " +
                (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or " +
                "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or " +
                "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)") +
                " order by bridging_surat_kontrol_bpjs.tgl_rencana");
            try {
                ps.setString(1, Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem() + ""));
                if(!TCari.getText().trim().equals("")) {
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                }

                rs = ps.executeQuery();
                while(rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_sep"), rs.getString("no_kartu"), rs.getString("nomr"), rs.getString("nama_pasien"),
                        rs.getString("tanggal_lahir"), rs.getString("jkel"), rs.getString("nmdiagnosaawal"), rs.getString("tgl_surat"), rs.getString("no_surat"),
                        rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"), rs.getString("kd_poli_bpjs"), rs.getString("nm_poli_bpjs"),
                        rs.getString("keterangan_kontrol")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if(rs != null) {
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Notif : " + e);
    }

    LCount.setText("" + tabMode.getRowCount());    

//        Valid.tabelKosong(tabMode);
//        try {
//           if(R1.isSelected()==true){
//                ps=koneksi.prepareStatement(
//                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
//                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
//                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
//                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
//                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? "+
//                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "+
//                    "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "+
//                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")+
//                    "order by bridging_surat_kontrol_bpjs.tgl_surat");
//                try {
//                    ps.setString(1,Valid.SetTgl(DTPTanggalSurat1.getSelectedItem()+""));
//                    ps.setString(2,Valid.SetTgl(DTPTanggalSurat2.getSelectedItem()+""));
//                    if(!TCari.getText().trim().equals("")){
//                        ps.setString(3,"%"+TCari.getText().trim()+"%");
//                        ps.setString(4,"%"+TCari.getText().trim()+"%");
//                        ps.setString(5,"%"+TCari.getText().trim()+"%");
//                        ps.setString(6,"%"+TCari.getText().trim()+"%");
//                        ps.setString(7,"%"+TCari.getText().trim()+"%");
//                        ps.setString(8,"%"+TCari.getText().trim()+"%");
//                        ps.setString(9,"%"+TCari.getText().trim()+"%");
//                        ps.setString(10,"%"+TCari.getText().trim()+"%");
//                    }
//                        
//                    rs=ps.executeQuery();
//                    while(rs.next()){
//                        tabMode.addRow(new Object[]{
//                            rs.getString("no_rawat"),rs.getString("no_sep"),rs.getString("no_kartu"),rs.getString("nomr"),rs.getString("nama_pasien"),
//                            rs.getString("tanggal_lahir"),rs.getString("jkel"),rs.getString("nmdiagnosaawal"),rs.getString("tgl_surat"),rs.getString("no_surat"),
//                            rs.getString("tgl_rencana"),rs.getString("kd_dokter_bpjs"),rs.getString("nm_dokter_bpjs"),rs.getString("kd_poli_bpjs"),rs.getString("nm_poli_bpjs"),rs.getString("keterangan_kontrol")
//                        });                    
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notif : "+e);
//                } finally{
//                    if(rs!=null){
//                        rs.close();
//                    }
//                    if(ps!=null){
//                        ps.close();
//                    }
//                }
//            }else if(R2.isSelected()==true){
//                ps=koneksi.prepareStatement(
//                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
//                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
//                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
//                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
//                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between ? and ? "+
//                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "+
//                    "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "+
//                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")+
//                    "order by bridging_surat_kontrol_bpjs.tgl_rencana");
//                try {
//                    ps.setString(1,Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem()+""));
//                    ps.setString(2,Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem()+""));
//                    if(!TCari.getText().trim().equals("")){
//                        ps.setString(3,"%"+TCari.getText().trim()+"%");
//                        ps.setString(4,"%"+TCari.getText().trim()+"%");
//                        ps.setString(5,"%"+TCari.getText().trim()+"%");
//                        ps.setString(6,"%"+TCari.getText().trim()+"%");
//                        ps.setString(7,"%"+TCari.getText().trim()+"%");
//                        ps.setString(8,"%"+TCari.getText().trim()+"%");
//                        ps.setString(9,"%"+TCari.getText().trim()+"%");
//                        ps.setString(10,"%"+TCari.getText().trim()+"%");
//                    }
//                        
//                    rs=ps.executeQuery();
//                    while(rs.next()){
//                        tabMode.addRow(new Object[]{
//                            rs.getString("no_rawat"),rs.getString("no_sep"),rs.getString("no_kartu"),rs.getString("nomr"),rs.getString("nama_pasien"),
//                            rs.getString("tanggal_lahir"),rs.getString("jkel"),rs.getString("nmdiagnosaawal"),rs.getString("tgl_surat"),rs.getString("no_surat"),
//                            rs.getString("tgl_rencana"),rs.getString("kd_dokter_bpjs"),rs.getString("nm_dokter_bpjs"),rs.getString("kd_poli_bpjs"),rs.getString("nm_poli_bpjs")
//                        });                    
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notif : "+e);
//                } finally{
//                    if(rs!=null){
//                        rs.close();
//                    }
//                    if(ps!=null){
//                        ps.close();
//                    }
//                }       
//            }
//        } catch (Exception e) {
//            System.out.println("Notif : "+e);
//        } 
//        LCount.setText(""+tabMode.getRowCount());
    }


    public void emptTeks() {
        NoRawat.setText("");
        NoSEP.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        Diagnosa.setText("");
        TanggalSurat.setDate(new Date());
        NoSurat.setText("");
        TanggalKontrol.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        KeteranganKontrol.setText("");
        TanggalSurat.requestFocus();
    }
   

    private void getData() {
    KeteranganKontrol.setText("");    
        if(tbObat.getSelectedRow()!= -1){
            NoRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            NoSEP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            NmPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().replaceAll("P","PEREMPUAN").replaceAll("L","LAKI-LAKI"));
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            KeteranganKontrol.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            Valid.SetTgl(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Valid.SetTgl(TanggalKontrol,tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NoHp.setText(Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis=?",NoRM.getText()));
        }
    }
    
    public void setNoRm(String norawat,String nosep,String nokartu,String norm,String namapasien,String tanggallahir,String jk,String diagnosa) {
        NoRawat.setText(norawat);
        NoSEP.setText(nosep);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        JK.setText(jk.replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
        Diagnosa.setText(diagnosa);
        TCari.setText(nosep);
        ChkInput.setSelected(true);
        NoHp.setText(Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis=?",NoRM.getText()));
        isForm();
        tampil();
    }
    
    public void setNoRm(String norm) {
        TCari.setText(norm);
        ChkInput.setSelected(false);
        isForm();
        tampil();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,320));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
    // Tombol ini diaktifkan jika pengguna memiliki akses "bpjs_surat_kontrol"
    boolean aksesSuratKontrol = akses.getbpjs_surat_kontrol();
    
    BtnSimpan.setEnabled(aksesSuratKontrol);
    BtnPrint.setEnabled(aksesSuratKontrol);
    BtnEdit.setEnabled(aksesSuratKontrol);

    // Tombol ini diaktifkan jika pengguna memiliki level akses Admin Utama
    BtnHapus.setEnabled(akses.getadmin());
    }

    public JTable getTable(){
        return tbObat;
    }
    
    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    @Test
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
            utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = link+"/RencanaKontrol/Delete";
            requestJson ="{\"request\":{\"t_suratkontrol\":{\"noSuratKontrol\":\""+NoSurat.getText()+"\",\"user\":\""+user+"\"}}}";            
            requestEntity = new HttpEntity(requestJson,headers);
            root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE,requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText());
            if(nameNode.path("code").asText().equals("200")){
                Sequel.meghapus("bridging_surat_kontrol_bpjs","no_surat",NoSurat.getText());
                tabMode.removeRow(tbObat.getSelectedRow());
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
            }
        } catch (Exception e) {   
            System.out.println("Notif : "+e);
            if(e.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    private boolean isBooking(){
        status=true;
        kodedokter=Sequel.cariIsi("select maping_dokter_dpjpvclaim.kd_dokter from maping_dokter_dpjpvclaim where maping_dokter_dpjpvclaim.kd_dokter_bpjs=?",KdDokter.getText());
        if(kodedokter.equals("")){
            status=false;
            System.out.println("Notif : Mapping kode dokter tidak ditemukan");
        } 
        kodepoli=Sequel.cariIsi("select maping_poli_bpjs.kd_poli_rs from maping_poli_bpjs where maping_poli_bpjs.kd_poli_bpjs=?",KdPoli.getText());
        if(kodepoli.equals("")){
            status=false;
            System.out.println("Notif : Mapping kode poli tidak ditemukan");
        } 
        if(status==true){
            noreg="";
            switch (URUTNOREG) {
                case "poli":
                    noreg=Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_poli='"+kodepoli+"' and booking_registrasi.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3);
                    break;
                case "dokter":
                    noreg=Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='"+kodedokter+"' and booking_registrasi.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3);
                    break;
                case "dokter + poli":             
                    noreg=Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='"+kodedokter+"' and booking_registrasi.kd_poli='"+kodepoli+"' and booking_registrasi.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3);
                    break;
                default:
                    noreg=Valid.autoNomer3("select ifnull(MAX(CONVERT(booking_registrasi.no_reg,signed)),0) from booking_registrasi where booking_registrasi.kd_dokter='"+kodedokter+"' and booking_registrasi.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3);
                    break;
            }
            status=Sequel.menyimpantf2("booking_registrasi","?,?,?,?,?,?,?,?,?,?,?","Pasien dan Tanggal",11,new String[]{
                Valid.SetTgl(TanggalSurat.getSelectedItem()+""),"08:00:00",NoRM.getText(),Valid.SetTgl(TanggalKontrol.getSelectedItem()+""),
                kodedokter,kodepoli,noreg,Sequel.cariIsi("select pasien.kd_pj from pasien where pasien.no_rkm_medis=?",NoRM.getText()),"0",
                Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+" "+TanggalKontrol.getSelectedItem().toString().substring(11,19),"belum"
            });
        }
            
        return status;
    }
    
private static void disableSSLVerification() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }    

public void resetTanggalHariIni() {
    // Set tanggal DTPCari2 ke hari ini
    java.util.Date hariIni = new java.util.Date();
    DTPTanggalSurat2.setDate(hariIni);    
    
    // Hitung tanggal 30 hari sebelum hari ini
    Calendar cal = Calendar.getInstance();
    cal.setTime(hariIni);
    cal.add(Calendar.DAY_OF_MONTH, -30); // Kurangi 30 hari
    DTPTanggalSurat1.setDate(cal.getTime());        
}

}

