package com.example.asmaralokapreset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Pack extends AppCompatActivity implements View.OnClickListener {
    CardView Kyoto, MoodyVol3, TravelFilm, RusticWeeding, Sahara;
    TextView tvKyoto, tvMoodyVol1, tvTravel, tvRusticWeeding, tvSahara ;
    TextView hargaKyoto, hargaMoodyVol1, hargaTravel, hargaRusticWeeding, hargaSahara ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack);

        Kyoto = findViewById(R.id.kyoto);
        MoodyVol3 = findViewById(R.id.moodyVol3);
        TravelFilm = findViewById(R.id.travelFilmMaker);
        RusticWeeding = findViewById(R.id.rusticWeeding);
        Sahara = findViewById(R.id.sahara);

        Kyoto.setOnClickListener(this);
        MoodyVol3.setOnClickListener(this);
        TravelFilm.setOnClickListener(this);
        RusticWeeding.setOnClickListener(this);
        Sahara.setOnClickListener(this);

        // Ambil Data
        tvKyoto = findViewById(R.id.txtKyoto1);
        hargaKyoto = findViewById(R.id.txtKyoto2);
        tvMoodyVol1 = findViewById(R.id.txtMoodyVol1);
        hargaMoodyVol1 = findViewById(R.id.txtMoodyVol2);
        tvTravel = findViewById(R.id.txtTravelF1);
        hargaTravel = findViewById(R.id.txtTravelF2);
        tvRusticWeeding = findViewById(R.id.txtRusticWeeding1);
        hargaRusticWeeding = findViewById(R.id.txtRusticWeeding2);
        tvSahara = findViewById(R.id.txtSahara1);
        hargaSahara = findViewById(R.id.txtSahara2);
    }

    @Override
    public void onClick(View v) {
        if (v == Kyoto) {
            Intent i = new Intent(Pack.this, MainActivity.class);
            i.putExtra("tvNamaPreset", tvKyoto.getText().toString());
            i.putExtra("tvHargaPreset", hargaKyoto.getText().toString());
            i.putExtra("imgPreset", R.drawable.kyoto);
            startActivity(i);
        } else if (v == MoodyVol3) {
            Intent i = new Intent(Pack.this, MainActivity.class);
            i.putExtra("tvNamaPreset", tvMoodyVol1.getText().toString());
            i.putExtra("tvHargaPreset", hargaMoodyVol1.getText().toString());
            startActivity(i);
        } else if (v == TravelFilm) {
            Intent i = new Intent(Pack.this, MainActivity.class);
            i.putExtra("tvNamaPreset", tvTravel.getText().toString());
            i.putExtra("tvHargaPreset", hargaTravel.getText().toString());
            startActivity(i);
        } else if (v == RusticWeeding) {
            Intent i = new Intent(Pack.this, MainActivity.class);
            i.putExtra("tvNamaPreset", tvRusticWeeding.getText().toString());
            i.putExtra("tvHargaPreset", hargaRusticWeeding.getText().toString());
            startActivity(i);
        } else if (v == Sahara) {
            Intent i = new Intent(Pack.this, MainActivity.class);
            i.putExtra("tvNamaPreset", tvSahara.getText().toString());
            i.putExtra("tvHargaPreset", hargaSahara.getText().toString());
            startActivity(i);
        }
    }
}