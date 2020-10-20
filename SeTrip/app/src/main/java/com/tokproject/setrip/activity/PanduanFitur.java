package com.tokproject.setrip.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.tokproject.setrip.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PanduanFitur extends AppCompatActivity {
    ActionBar actionBar;

    ListView listView;
    SimpleAdapter adapter;
    HashMap<String, String> map;
    String[] ask;
    String[] answ;
    ArrayList<HashMap<String, String>> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_fitur);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Bantuan Panduan Fitur");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);

        ask = new String[] {
                "Bagaimana caranya untuk mencari lokasi wisata yang tersedia?",
                "Bagaimana jika saya ingin didampingi pemandu wisata?",
                "Bagaimana jika saya ingin menjadi pemandu wisata?",
                "Bagaimana cara saya untuk mengetahui informasi terkait Covid-19 ini?",
                "Bagaimana jika saya ingin bepergian namun belum memiliki alat pelindung diri (APD)?",
                "Bagaimana jika saya ingin membuka/menjual APD?",
                "Bagaimana jika saya ingin membuat/mencari postingan?",
                "Bagaimana caranya untuk Check-in ke sebuah lokasi wisata?",
                "Bagaimana caranya untuk Check-out dari lokasi wisata?",
                "Bagaimana caranya untuk mengganti foto profil?",
                "Bagaimana caranya untuk mengganti nama pengguna?",
                "Bagaimana caranya untuk mengganti nomor telepon?",
                "Bagaimana caranya untuk membuat/membuka catatan/pengingat?",
                "Bagaimana caranya untuk menyetel alarm pengingat?",
                "Bagaimana caranya untuk logout dari aplikasi SETRIP?"
        };

        answ = new String[] {
                "-\tAnda dapat mencari lokasi wisata pada navigasi “Temukan wisata” lalu pilih Cari dan temukan Wisata Favoritmu.\n" +
                        "-\tAnda dapat mencari lokasi wisata melalui kolom Cari wisata yang disediakan.\n" +
                        "-\tAnda dapat melihat detail informasi lokasi pariwisata dengan menekan salah satu item pariwisata.\n" +
                        "-\tPada halaman detail pariwisata Anda dapat menandai, menyukai lokasi pariwisata maupun membagikannya kepada orang lain.\n",
                "-\tAnda dapat menekan kolom “Butuh pemandu wisata” yang terletak pada setiap halaman detail informasi pariwisata.\n" +
                        "-\tSetelah itu, Anda akan diarahkan ke halaman “Pemandu Wisata”, pada halaman ini disediakan informasi dari para pemandu wisata mulai dari nama, umur, alamat, kontak, dan juga deskripsi pemandu tersebut.\n" +
                        "-\tUntuk menghubunginya Anda dapat menekan tombol hubungi yang terletak di kanan bawah informasi pemandu.\n" +
                        "-\tAnda juga dapat mencari pemandu wisata melalui ikon pencarian yang terletak di bagian kanan atas halaman ini.\n",
                "-\tPada halaman Pemandu Wisata tekan kolom “Ingin berkarir menjadi pemandu wisata?” dan akan dialihkan ke halaman pengisian biodata diri Anda.\n" +
                        "-\tAnda dapat mendaftarkan diri Anda dengan mengisi data-data yang diperlukan serta mengunggah foto diri Anda.\n",
                "-\tAnda dapat masuk ke halaman “Info Covid-19” yang ada pada navigasi “Temukan Wisata”.\n" +
                        "-\tPada halaman “Info Covid-19” ini, Anda dapat melihat informasi mulai dari total kasus pasien terinfeksi, pasien sembuh, dan pasien meninggal dunia.\n" +
                        "-\tAnda dapat melaporkan informasi terkait Covid-19 melalui halaman ini dengan menekan “Lapor Covid-19” dan akan diarahkan untuk menghubungi nomor yang disediakan.\n" +
                        "-\tAnda juga dapat membaca tips wisata aman dengan menekan “Tips wisata aman” yang akan diarahkan ke halaman “Tips wisata aman”.\n" +
                        "-\tAnda juga dapat membaca tips untuk mencegah tertular maupun penularan virus Covid-19 ini dengan menekan kolom “Tips Cegah Covid-19” dan akan diarahkan ke halaman “Tips Cegah Covid-19”.\n",
                "-\tKami memiliki fitur penjualan APD pada navigasi “Temukan wisata” dengan menekan kolom “Beli Alat Pelindung Diri” yang akan mengarahkan Anda ke halaman toko yang menjual berbagai macam APD.\n" +
                        "-\tPada halaman ini Anda dapat mencari perlengkapan APD dengan menekan ikon pencarian yang terletak di bagian atas halaman. Hasil pencarian perlengkapan APD terkait akan langsung ditampilkan.\n" +
                        "-\tJika Anda ingin membeli perlengkapan APD yang ada pada halaman ini, Anda dapat menghubunginya melalui kontak yang tersedia atau dapat dengan menekan tombol “Hubungi” yang ada pada informasi item APD.\n",
                "-\tAnda dapat mendaftarkan perlengkapan yang ingin Anda jual melalui halaman Tambah APD baru yang dapat diakses dengan cara menekan “Buka Toko” pada halaman penjualan APD.\n" +
                        "-\tPada halaman Tambah APD baru Anda harus mengisi data informasi terkait perlengkapan APD yang akan Anda jual serta mengunggah foto APD tersebut.\n",
                "-\tAnda dapat melihat postingan-postingan pada halaman “Timeline” pada navigasi “Beranda”.\n" +
                        "-\tAnda dapat mencari postingan dengan cara menekan ikon “pencarian” yang terletak di bagian atas. Hasil pencarian postingan terkait akan langsung ditampilkan.\n" +
                        "-\tUntuk membuat postingan, Anda dapat menekan ikon + yang terletak di bagian atas halaman di samping ikon pencarian. Setelah itu, Anda akan diarahkan ke halaman untuk membuat postingan dan Anda harus mengisi mulai dari judul postingan, foto postingan, dan juga deskripsi postingan yang ingin Anda unggah.\n",
                "-\tUntuk Check-in, Anda harus menekan ikon “Check-in” yang terletak pada bagian navigasi “Cek”. Setelah itu akan ditampilkan QR Code Anda.\n" +
                        "-\tSerahkan QR Code Anda kepada petugas pariwisata untuk dilakukan pemindaian.\n" +
                        "-\tSetelah pemindaian berhasil Anda dapat masuk ke lokasi wisata, dan aplikasi SETRIP akan memberikan notifikasi kepada Anda untuk mengenakan masker dan menggunakan hand sanitizer.\n",
                "-\tUntuk Check-out, Anda harus menekan ikon “Check-out” yang terletak pada bagian navigasi “Cek”. Setelah itu akan ditampilkan QR Code Anda.\n" +
                        "-\tSerahkan QR Code Anda kepada petugas pariwisata untuk dilakukan pemindaian.\n" +
                        "-\tSetelah pemindaian berhasil Anda dapat keluar dari lokasi wisata, dan aplikasi SETRIP akan memberikan notofikasi kepada Anda untuk membersihkan diri Anda setelah keluar dari lokasi wisata tersebut.\n",
                "-\tUntuk mengganti foto profil, Anda dapat melakukannya pada halaman “Profil”.\n" +
                        "-\tPilih/tekan ikon “kamera” untuk mengganti foto profil Anda.\n" +
                        "-\tAnda akan diberikan dua pilihan pengambilan gambar yaitu melalui kamera langsung maupun mengambil gambar dari galeri Anda.\n",
                "-\tUntuk mengganti nama pengguna, Anda dapat menekan ikon “pena” di samping kolom nama pengguna pada halaman “Profil”.\n" +
                        "-\tSetelah itu akan tampil popup dialog untuk penggantian nama pengguna, isi kolom nama tersebut dengan nama pengguna baru yang ingin Anda gunakan lalu tekan tombol “konfirmasi”.\n",
                "-\tUntuk mengganti nomor telepon, Anda dapat menekan ikon “pena” di samping kolom nomor telepon pada halaman “Profil”.\n" +
                        "-\tSetelah itu akan tampil popup dialog untuk penggantian nomor telepon, isi kolom nomor telepon tersebut dengan nomor telepon baru yang ingin Anda gunakan lalu tekan tombol “konfirmasi”.\n",
                "-\tUntuk membuat catatan Anda dapat menekan “Buat catatan/pengingat” pada halaman “Profil”.\n" +
                        "-\tAnda akan diarahkan ke halaman Pengingatmu, Anda dapat mengisi judul dan deskripsi catatan pada halaman ini.\n" +
                        "-\tUntuk menyimpan catatan, Anda dapat menekan ikon “save” pada bagian bawah halaman.\n" +
                        "-\tUntuk membuka catatan, Anda dapat menekan ikon “folder” yang terletak di atas ikon save. Anda dapat memilih catatan yang ingin Anda lihat dengan menekan list catatan tersebut.\n",
                "-\tTekan ikon “pengaturan” yang terletak di bagian atas pada halaman “Profil”. Setelah itu tekan ikon “jam/alarm” untuk masuk ke halaman setel pengingat.\n" +
                        "-\tPada halaman setel pengingat memiliki dua fitur yaitu alarm sekali dan alarm berulang.\n" +
                        "-\tUntuk menyetel alarm pengingat, Anda hanya perlu memasukkan tanggal dan waktu serta pesan pengingat yang diinginkan lalu menekan tombol set alarm.\n",
                "-\tMasuk ke halaman “Pengaturan profil” dengan cara menekan ikon “pengaturan” pada halaman “Profil”.\n" +
                        "-\tSetelah itu Anda dapat logout dengan menekan ikon “Logout”.\n"
        };

        mylist = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<ask.length; i++) {
            map = new HashMap<String, String>();
            map.put("question", ask[i]);
            map.put("answer", answ[i]);
            mylist.add(map);
        }

        adapter = new SimpleAdapter(this,
                mylist,
                R.layout.list_item,
                new String[]{"question", "answer"},
                new int[]{R.id.txt_pertanyaan, (R.id.txt_jawaban)});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt_ans = (TextView)adapterView.getAdapter().getView(i, view, adapterView).findViewById(R.id.txt_jawaban);
                View garis = (View)adapterView.getAdapter().getView(i, view, adapterView).findViewById(R.id.garis);
                ImageView arrow = (ImageView)adapterView.getAdapter().getView(i, view, adapterView).findViewById(R.id.arrow);
                if (txt_ans.getVisibility() == View.VISIBLE) {
                    txt_ans.setVisibility(View.GONE);
                    garis.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    txt_ans.setVisibility(View.VISIBLE);
                    garis.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
