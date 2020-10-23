package com.tokproject.setrip.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelTokoApd;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class AdapterTokoApd extends RecyclerView.Adapter<AdapterTokoApd.MyHolder> {

    Context context;
    List<ModelTokoApd> tokoApdList;
    String myUid;

    public AdapterTokoApd(Context context, List<ModelTokoApd> tokoApdList) {
        this.context = context;
        this.tokoApdList = tokoApdList;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_toko_apd, parent, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String nama_toko = tokoApdList.get(position).getpTitle();
        String lokasi = tokoApdList.get(position).getpLocation();
        String deskripsi = tokoApdList.get(position).getpDescription();
        String foto_produk = tokoApdList.get(position).getpImage();
        String nomor_hp = tokoApdList.get(position).getpNumber();
        String harga = tokoApdList.get(position).getpPrice();
        String uid = tokoApdList.get(position).getUid();
        String email = tokoApdList.get(position).getuEmail();



        holder.nameTv.setText(nama_toko);
        holder.locationTv.setText(lokasi);
        holder.descriptionTv.setText(deskripsi);
        holder.numberTv.setText("Kontak: "+nomor_hp);
        holder.priceTv.setText("Rp."+harga);

        Glide.with(context).load(foto_produk)
                .error(R.drawable.ic_baseline_face_24)
                .placeholder(R.drawable.ic_baseline_face_24)
                .into(holder.pImageIv);

        holder.callTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //set post image
        //if there image or not
        if(foto_produk.equals("noImage")) {
            //hide imageView
            holder.pImageIv.setVisibility(View.GONE);
        } else {
            //hide imageView
            holder.pImageIv.setVisibility(View.VISIBLE); //make sure correct

            Glide.with(context)
                    .load(foto_produk)
                    .into(holder.pImageIv);
        }
    }

    @Override
    public int getItemCount() {
        return tokoApdList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView pImageIv;
        TextView nameTv, locationTv, descriptionTv, numberTv, priceTv;
        Button callTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            pImageIv = itemView.findViewById(R.id.pImageIv);
            nameTv = itemView.findViewById(R.id.namaTokoTv);
            descriptionTv = itemView.findViewById(R.id.deskripsiTokoTv);
            locationTv = itemView.findViewById(R.id.lokasiTokoTv);
            numberTv = itemView.findViewById(R.id.nomor_telepon);
            priceTv = itemView.findViewById(R.id.hargaTv);
            callTv = itemView.findViewById(R.id.hubungi_toko);
        }
    }
}
