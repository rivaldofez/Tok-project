package com.tokproject.setrip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelWisata;

import java.util.ArrayList;

public class AdapterWisata extends RecyclerView.Adapter<AdapterWisata.MyHolder> {

    private ArrayList<ModelWisata> wisataList;

    public AdapterWisata(ArrayList<ModelWisata> wisataList) {
        this.wisataList = wisataList;
    }

    @NonNull
    @Override
    public AdapterWisata.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_wisata, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterWisata.MyHolder holder, int position) {
        ModelWisata hc = wisataList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hc.getFoto())
                .error(R.drawable.ic_baseline_face_24)
                .placeholder(R.drawable.ic_baseline_face_24)
                .into(holder.gambar);
        holder.tvNama.setText(hc.getNama());
        holder.tvDetail.setText(hc.getDetail());
        holder.tvLokasi.setText(hc.getLokasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(wisataList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView gambar;
        TextView tvNama, tvLokasi, tvDetail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.imageIv);
            tvNama = itemView.findViewById(R.id.namaTv);
            tvLokasi = itemView.findViewById(R.id.lokasiTv);
            tvDetail = itemView.findViewById(R.id.detailTv);

        }
    }

    private OnItemClickCallback onItemClickCallback;

    public void SetOnItemClickCallback (OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback{
        void onItemClicked(ModelWisata hc);
    }
}