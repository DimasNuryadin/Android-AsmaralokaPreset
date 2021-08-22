package com.example.asmaralokapreset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private List<DataRiwayatPembayaran> mlist;

    public UsersAdapter(List<DataRiwayatPembayaran> mlist) {this.mlist = mlist;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_riwayat_pembayaran, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataRiwayatPembayaran dataRiwayatPembayaran = mlist.get(position);
        holder.tv_namaPreset.setText(dataRiwayatPembayaran.getNamaPreset());
        holder.tv_hargaPreset.setText(dataRiwayatPembayaran.getHargaPreset());
        holder.tv_metodePembayaran.setText(dataRiwayatPembayaran.getMetodePembayaran());
        holder.tv_email.setText(dataRiwayatPembayaran.getEmailKirim());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_namaPreset;
        TextView tv_hargaPreset;
        TextView tv_metodePembayaran;
        TextView tv_email;

        RelativeLayout list_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_namaPreset = itemView.findViewById(R.id.tv_namaPreset);
            tv_hargaPreset = itemView.findViewById(R.id.tv_hargaPreset);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_metodePembayaran = itemView.findViewById(R.id.tv_metodePembayaran);
            list_item = itemView.findViewById(R.id.list_item);
        }
    }
}
