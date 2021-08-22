package com.example.asmaralokapreset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asmaralokapreset.ui.gallery.GalleryFragment;
import com.example.asmaralokapreset.ui.home.HomeFragment;

public class PembayaranBerhasil extends AppCompatActivity implements View.OnClickListener {
    TextView RiwayatTransaksi, Beranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_berhasil);

        RiwayatTransaksi = findViewById(R.id.btnRiwayat);
        RiwayatTransaksi.setOnClickListener(this);

        Beranda = findViewById(R.id.btnBeranda);
        Beranda.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == RiwayatTransaksi) {
            Intent i = new Intent(PembayaranBerhasil.this, GalleryFragment.class);
            startActivity(i);
        } else if (v == Beranda) {
            Intent i = new Intent(PembayaranBerhasil.this, ActivityUtama.class);
            startActivity(i);
        }
    }
}