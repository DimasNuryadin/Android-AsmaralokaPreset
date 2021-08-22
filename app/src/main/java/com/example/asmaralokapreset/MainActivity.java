package com.example.asmaralokapreset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ImgPreset;
    CardView Beli;

    TextView NamaPreset, HargaPreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImgPreset = findViewById(R.id.imgProfile);
        Beli = findViewById(R.id.beli);
        Beli.setOnClickListener(this);

        // Panggil Foto Preset
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int setimage = bundle.getInt("imgPreset");
            ImgPreset.setImageResource(setimage);
        }

        // Panggil Data Preset
        String namaPreset = getIntent().getExtras().getString("tvNamaPreset");
        String hargaPreset = getIntent().getExtras().getString("tvHargaPreset");

        // Panggil id
        NamaPreset = findViewById(R.id.txt1);
        HargaPreset = findViewById(R.id.harga);

        NamaPreset.setText(namaPreset);
        HargaPreset.setText(hargaPreset);

    }

    @Override
    public void onClick(View v) {
        if (v == Beli){
            Intent i = new Intent(MainActivity.this, PembayaranActivity.class);
            i.putExtra("namaPreset",NamaPreset.getText().toString());
            i.putExtra("hargaPreset", HargaPreset.getText().toString());
//            i.putExtra("imgPreset", ImgPreset.getBackground().toString());
            startActivity(i);
        }
    }
}