<?php
    if(strpos($_SERVER['REQUEST_URI'],"pages")){
        exit(header("Location:../index.php"));
    }
    
    $action   = isset($_GET['action'])?$_GET['action']:NULL;
    $keyword  = str_replace("_"," ",isset($_GET['keyword']))?str_replace("_"," ",$_GET['keyword']):NULL;
    $keyword  = validTeks($keyword);
    echo "<input type=hidden name=keyword value=$keyword><input type=hidden name=action value=$action>";
?>
<div style="width: 100%; height: 99%; overflow: auto;">
    <?php
        $_sql   = "SELECT pegawai.id,pegawai.nik,pegawai.nama FROM pegawai where pegawai.stts_aktif='AKTIF' and (pegawai.nik like '%".$keyword."%' or pegawai.nama like '%".$keyword."%') order by pegawai.id ASC ";
        $hasil  = bukaquery($_sql);
        $jumlah = mysqli_num_rows($hasil);
        if(mysqli_num_rows($hasil)!=0) {
            echo "<table width='99.8%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='5%'><div align='center'>Proses</div></td>
                        <td width='11%'><div align='center'>NIP</div></td>
                        <td width='25%'><div align='center'>Nama</div></td>
                        <td width='59%'><div align='center'>Riwayat Penghargaan Pegawai</div></td>
                    </tr>";
                    while($baris = mysqli_fetch_array($hasil)) {
                        echo "<tr class='isi' title='$baris[1] $baris[2]'>
                                <td valign='top'>
                                    <center>
                                        <a href=?act=InputRiwayatPenghargaan&action=TAMBAH&id=$baris[0]>[Detail]</a>
                                    </center>
                               </td>
                                <td valign='top'><a href=?act=InputRiwayatPenghargaan&action=TAMBAH&id=$baris[0]>$baris[1]</a></td>
                                <td valign='top'><a href=?act=InputRiwayatPenghargaan&action=TAMBAH&id=$baris[0]>$baris[2]</a></td>
                                <td valign='top'>
                                   <table width='99.8%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>";
                        $_sql2 = "SELECT nama_penghargaan, tanggal, instansi,pejabat_pemberi from riwayat_penghargaan where id='$baris[0]' ORDER BY tanggal ASC ";
                        $hasil2=bukaquery($_sql2);
                        if(mysqli_num_rows($hasil2)!=0) {
                            echo "    <tr class='isi7'>
                                        <td width='5%'><div align='center'>NO.</div></td>
                                        <td width='35%'><div align='center'>Nama Penghargaan</div></td>
                                        <td width='10%'><div align='center'>Tanggal</div></td>
                                        <td width='25%'><div align='center'>Instansi Pemberi</div></td>
                                        <td width='25%'><div align='center'>Pejabat Pemberi</div></td>
                                      </tr>";
                        }
                        $no=1;
                        while($baris2 = mysqli_fetch_array($hasil2)) { 
                            echo "    <tr> 
                                        <td>$no</td>
                                        <td>$baris2[0]</td>
                                        <td>$baris2[1]</td>
                                        <td>$baris2[2]</td>
                                        <td>$baris2[3]</td>
                                      </tr>";
                            $no++;
                        }
                        echo "      </table>
                                </td>
                             </tr>";
                    }
            echo "</table>";           
        } else {
            echo "<table width='99.8%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='5%'><div align='center'>Proses</div></td>
                        <td width='11%'><div align='center'>NIP</div></td>
                        <td width='25%'><div align='center'>Nama</div></td>
                        <td width='59%'><div align='center'>Riwayat Penghargaan Pegawai</div></td>
                    </tr>
                  </table>";
        }
    ?>
</div>
