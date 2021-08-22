package com.example.asmaralokapreset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    // Deklarasi Variabel
    private Button Login;
    private EditText myEmail, myPassword;

    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;
    private String getEmail, getPassword;

    // Google
    private ImageButton LoginG;
    // Membuat Kode Permintaan
    private int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisiasi Widget
        myEmail = findViewById(R.id.getEmail);
        myPassword = findViewById(R.id.getPassword);
        Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        // Login google
        LoginG = findViewById(R.id.loginG);
        LoginG.setOnClickListener(this);

        // Menyembunyikan / Hide Password
        myPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // Instance / Membuat Objek Firebase Authentication
        auth = FirebaseAuth.getInstance();

        //
        // Mengecek Keberadaan User
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Mengecek apakah ada user yang sudah login / belum logout
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Jika ada maka langsung pindah ke halama main activity
                    startActivity(new Intent(ActivityLogin.this, ActivityUtama.class));
                    finish();
                }
            }
        };
    }

    // Menerapkan listener
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    // Melepaskan listener
    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            auth.removeAuthStateListener(listener);
        }
    }

    // Merhod Ini digunakan untuk proses autentikasi user menggunakan email dan kata sandi
    private void loginUserAccount() {
        auth.signInWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // Mengecek Status keberhasilan saat login
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityLogin.this, "Login Succes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityLogin.this, "Tidak dapat masuk, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void btnTReg(View view) {
        Intent i = new Intent(ActivityLogin.this, ActivityRegister.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin :
                // Mendapatkan data yang diinput user
                getEmail = myEmail.getText().toString();
                getPassword = myPassword.getText().toString();

                // Mengecek apakah email dan sandi kosong atau tidak
                if (TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)) {
                    Toast.makeText(this, "Email dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    loginUserAccount();
                    progressBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.loginG :
                // Statement program login/masuk
                startActivityForResult(AuthUI.getInstance().getInstance()
                        .createSignInIntentBuilder()

                        // Memilih provide atau method masuk yang kita gunakan
                        .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .setIsSmartLockEnabled(false)
                        .build(), RC_SIGN_IN);
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }
    // Mengecek apakah data yang kosong, akan digunakan lllllllll
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        // RC_SIGN_IN adalah kode permintaan yang anda berikan ke start ActivityForResul, saat memluai masuknya arus.
        if (requestCode == RC_SIGN_IN) {
            // Berhasil masuk
            if (resultCode == RESULT_OK) {
                Toast.makeText(ActivityLogin.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityLogin.this, "Login Dibatalkan", Toast.LENGTH_SHORT).show();
            }
        }
    }
}