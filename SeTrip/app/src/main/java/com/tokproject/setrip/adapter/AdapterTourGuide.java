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
import com.tokproject.setrip.model.ModelTourGuide;

import java.util.List;

public class AdapterTourGuide extends RecyclerView.Adapter<AdapterTourGuide.MyHolder> {


    Context context;
    List<ModelTourGuide> tourGuideList;
    String myUid;

    public AdapterTourGuide(Context context, List<ModelTourGuide> tourGuideList) {
        this.context = context;
        this.tourGuideList = tourGuideList;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    @NonNull
    @Override
    public AdapterTourGuide.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tourguide, parent, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterTourGuide.MyHolder holder, int position) {

        String nama_tour_guide = tourGuideList.get(position).getpTitle();
        String lokasi = tourGuideList.get(position).getpLocation();
        String deskripsi = tourGuideList.get(position).getpDescription();
        String foto_tour_guide = tourGuideList.get(position).getpImage();
        String nomor_hp = tourGuideList.get(position).getpNumber();
        String usia = tourGuideList.get(position).getpAge();
        String uid = tourGuideList.get(position).getUid();
        String email = tourGuideList.get(position).getuEmail();



        holder.nameTv.setText(nama_tour_guide);
        holder.locationTv.setText(lokasi);
        holder.descriptionTv.setText(deskripsi);
        holder.numberTv.setText("Kontak: "+nomor_hp);
        holder.ageTv.setText("Usia "+usia+ " Tahun");

        Glide.with(context).load(foto_tour_guide)
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
        if(foto_tour_guide.equals("noImage")) {
            //hide imageView
            holder.pImageIv.setVisibility(View.GONE);
        } else {
            //hide imageView
            holder.pImageIv.setVisibility(View.VISIBLE); //make sure correct

            Glide.with(context)
                    .load(foto_tour_guide)
                    .into(holder.pImageIv);
        }

    }

    @Override
    public int getItemCount() {
        return tourGuideList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView pImageIv;
        TextView nameTv, locationTv, descriptionTv, numberTv, ageTv;
        Button callTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            pImageIv = itemView.findViewById(R.id.profiletg);
            nameTv = itemView.findViewById(R.id.namatgTv);
            descriptionTv = itemView.findViewById(R.id.deskripsiTv);
            locationTv = itemView.findViewById(R.id.alamatTv);
            numberTv = itemView.findViewById(R.id.nomor_telepon);
            ageTv = itemView.findViewById(R.id.usiaTv);
            callTv = itemView.findViewById(R.id.hubungi_toko);

        }
    }
}
