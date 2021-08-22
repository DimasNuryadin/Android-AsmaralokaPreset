package com.example.asmaralokapreset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PembayaranActivity extends AppCompatActivity implements View.OnClickListener {
    TextView Bayar;
    ImageView ImgPreset;
    TextView NamaPreset, HargaPreset, Harga;

    RadioGroup MPembayaran;
    RadioButton MetodePembayaran;

    // Deklarasi Java mail
    public TextView mSubject, mMessage;
    public EditText mEmail;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        Bayar = findViewById(R.id.bayar);
        Bayar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        ImgPreset = findViewById(R.id.imgPreset);
        NamaPreset = findViewById(R.id.namaPreset);
        HargaPreset = findViewById(R.id.hargaPreset);
        Harga = findViewById(R.id.harga);

        // Panggil Data Preset
        String namaPreset = getIntent().getExtras().getString("namaPreset");
        String hargaPreset = getIntent().getExtras().getString("hargaPreset");
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            int setImage = bundle.getInt("imgPreset");
//            ImgPreset.setImageResource(setImage);
//        }

        NamaPreset.setText(namaPreset);
        HargaPreset.setText(hargaPreset);
        Harga.setText(hargaPreset);

        // Panggil ID Java Mail
        mEmail = findViewById(R.id.mailID);
        mMessage = findViewById(R.id.messageID);
        mSubject = findViewById(R.id.subjectID);

        // Radio Group Metode Pembayaran
        MPembayaran = findViewById(R.id.mPembayaran);

    }

    // Mengecek apakah ada data yang kosong, akan digunakan pada Tutorial Selanjutnya
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bayar:
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                String getUserID = auth.getCurrentUser().getUid();

                // Mendapatkan instance dari Firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                // Menyimpan Data yang diinputkan User Kedalam Variabel
                String getNamaPreset = NamaPreset.getText().toString();
                String getHargaPreset = Harga.getText().toString();
                String getEmail = mEmail.getText().toString();
                String getMetodePembayaran = MetodePembayaran.getText().toString();


                // Mendapatkan refernsi dari database
                getReference = database.getReference();

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getEmail)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(PembayaranActivity.this, "Email Harus diisi !!", Toast.LENGTH_SHORT).show();
                }else {
                    getReference.child("dbUsers").child(getUserID).child("pembayaran").push()
                            .setValue(new DataRiwayatPembayaran(getNamaPreset, getHargaPreset, getEmail, getMetodePembayaran))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    sendMail();
                                    Intent i = new Intent(PembayaranActivity.this, PembayaranBerhasil.class);
                                    startActivity(i);
                                }
                            });
                }
                break;
        }
    }

    // Method Java Mail
    private void sendMail() {
        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString().trim();
        String subject = mSubject.getText().toString().trim();

        // Kirim Email
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, message, subject);
        javaMailAPI.execute();
    }

    // Radio Button Metode Pembayaran
    public void checkButton(View v) {
        int radioId = MPembayaran.getCheckedRadioButtonId();
        MetodePembayaran = findViewById(radioId);
        Toast.makeText(this, "" + MetodePembayaran.getText(), Toast.LENGTH_SHORT).show();
    }
}