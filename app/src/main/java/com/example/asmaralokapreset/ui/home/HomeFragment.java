package com.example.asmaralokapreset.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.asmaralokapreset.MainActivity;
import com.example.asmaralokapreset.MoodyFall;
import com.example.asmaralokapreset.Pack;
import com.example.asmaralokapreset.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private CardView SeeAll1, SeeAll2;
    private CardView Faded, Genesis, OrangeTeal, BlackWhite, Travel;

    // Data Preset
    private TextView tvFaded, tvGenesis, tvOrangeTeal, tvBlackWhite, tvTravel;
    private TextView tvHargaFaded, tvHargaGenesis, tvHargaOrange, tvHargaBlack, tvHargaTravel;

    // Firebase
    private FirebaseAuth auth;

    // Membuat Kode Permintaan
    private int RC_SIGN_IN = 1;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        // Mulai
        SeeAll1 = root.findViewById(R.id.seeAll1);
        SeeAll1.setOnClickListener(this);
        SeeAll2 = root.findViewById(R.id.seeAll2);
        SeeAll2.setOnClickListener(this);

        // Click Profile Preset
        Faded = root.findViewById(R.id.faded);
        Genesis = root.findViewById(R.id.genesis);
        OrangeTeal = root.findViewById(R.id.orangeTeal);
        BlackWhite = root.findViewById(R.id.blackWhite);
        Travel = root.findViewById(R.id.travel);

        Faded.setOnClickListener(this);
        Genesis.setOnClickListener(this);
        OrangeTeal.setOnClickListener(this);
        BlackWhite.setOnClickListener(this);
        Travel.setOnClickListener(this);

        // Panggil Data Preset
        tvFaded = root.findViewById(R.id.txtFaded1);
        tvHargaFaded = root.findViewById(R.id.txtFaded3);
        tvGenesis = root.findViewById(R.id.txtGenesis1);
        tvHargaGenesis = root.findViewById(R.id.txtGenesis3);
        tvOrangeTeal = root.findViewById(R.id.txtOrangeTeal1);
        tvHargaOrange = root.findViewById(R.id.txtOrangeTeal3);
        tvBlackWhite = root.findViewById(R.id.txtBlackWhite1);
        tvHargaBlack = root.findViewById(R.id.txtBlackWhite3);
        tvTravel = root.findViewById(R.id.txtTravel1);
        tvHargaTravel = root.findViewById(R.id.txtTravel3);

        // Firebase
        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == SeeAll1) {
            Intent i = new Intent(v.getContext(), MoodyFall.class);
            startActivity(i);
        } else if (v == SeeAll2) {
            Intent i = new Intent(v.getContext(), Pack.class);
            startActivity(i);
        } else if (v == Faded) {
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.putExtra("tvNamaPreset",tvFaded.getText().toString());
            i.putExtra("tvHargaPreset",tvHargaFaded.getText().toString());
            i.putExtra("imgPreset", R.drawable.faded);
            startActivity(i);
        } else if (v == Genesis){
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.putExtra("tvNamaPreset",tvGenesis.getText().toString());
            i.putExtra("tvHargaPreset",tvHargaGenesis.getText().toString());
            i.putExtra("imgPreset", R.drawable.genesis);
            startActivity(i);
        } else if (v == OrangeTeal){
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.putExtra("tvNamaPreset",tvOrangeTeal.getText().toString());
            i.putExtra("tvHargaPreset",tvHargaOrange.getText().toString());
            i.putExtra("imgPreset", R.drawable.orangeandteal);
            startActivity(i);
        } else if (v == BlackWhite){
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.putExtra("tvNamaPreset",tvBlackWhite.getText().toString());
            i.putExtra("tvHargaPreset",tvHargaBlack.getText().toString());
            i.putExtra("imgPreset", R.drawable.blackwhite);
            startActivity(i);
        } else if (v == Travel){
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.putExtra("tvNamaPreset",tvTravel.getText().toString());
            i.putExtra("tvHargaPreset",tvHargaTravel.getText().toString());
            i.putExtra("imgPreset", R.drawable.travel);
            startActivity(i);
        }
    }
}