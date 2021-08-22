package com.example.asmaralokapreset.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.asmaralokapreset.ActivityLogin;
import com.example.asmaralokapreset.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private SlideshowViewModel slideshowViewModel;

    // Deklarasi Variable
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    private Button Logout;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        // Logout
        Logout = root.findViewById(R.id.logout);
        Logout.setOnClickListener(this);

        // Instance firebase Auth
        auth = FirebaseAuth.getInstance();

        // Firebase
        // Menambahkan Listener Untuk mengecek apakah userteleh logout / Keluar
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Jika iya atau null maka akan pindah pada halaman login
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Toast.makeText(SlideshowFragment.this.getActivity(), "Logout Succes", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SlideshowFragment.this.getActivity(), ActivityLogin.class));
                    getActivity().finish();
                }
            }
        };

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;



    }
    // Menerapkan Listener
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    // Melepaskan listener
    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    // Logout Onclick
    @Override
    public void onClick(View v) {
        auth.signOut();
        switch (v.getId()){
            case R.id.logout:
                AuthUI.getInstance()
                        .signOut(SlideshowFragment.this.getActivity())
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                Toast.makeText(SlideshowFragment.this.getActivity(), "Berhasil Logout", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
        }
    }

}