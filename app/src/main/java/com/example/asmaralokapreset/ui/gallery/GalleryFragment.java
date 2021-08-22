package com.example.asmaralokapreset.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmaralokapreset.R;
import com.example.asmaralokapreset.UsersAdapter;
import com.example.asmaralokapreset.DataRiwayatPembayaran;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    //deklarasi variabel database reference dan ArrayList dengan parameter class model;

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ArrayList<DataRiwayatPembayaran> listRiwayat;
    RecyclerView list_riwayatPembayaran;
    private UsersAdapter recyclerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        //hubungkan objek dengan widget di xml/Inisialisasi objek
        list_riwayatPembayaran = root.findViewById(R.id.list_riwayatPembayaran);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getContext());
        list_riwayatPembayaran.setLayoutManager(mLayout);
        list_riwayatPembayaran.setItemAnimator(new DefaultItemAnimator());

        showData();

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }
    private void showData(){
        database.child("dbUsers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listRiwayat = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    DataRiwayatPembayaran mhs = item.getValue(DataRiwayatPembayaran.class);
                    mhs.setKey(item.getKey());
                    listRiwayat.add(mhs);
                }

                recyclerAdapter = new UsersAdapter(listRiwayat);
                list_riwayatPembayaran.setAdapter(recyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}