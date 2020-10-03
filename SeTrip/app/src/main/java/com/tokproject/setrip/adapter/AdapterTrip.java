package com.tokproject.setrip.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelTrip;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterTrip extends RecyclerView.Adapter {
    List<ModelTrip> modelTripList;

    public AdapterTrip(List<ModelTrip> modelTripList) {
        this.modelTripList = modelTripList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        ModelTrip modelTrip = modelTripList.get(position);


        if(modelTrip.getOutTime().equals("Not Yet")){
            viewHolderClass.outTime.setText("Belum Checkout");
        }else{
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(Long.parseLong(modelTrip.getOutTime()));
            String poutTime = DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString();
            viewHolderClass.outTime.setText(poutTime);
        }

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(modelTrip.getInTime()));
        String pinTime = DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString();
        viewHolderClass.inTime.setText(pinTime);

        viewHolderClass.lokasi.setText(modelTrip.getLokasi());
    }

    @Override
    public int getItemCount() {
        return modelTripList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView inTime, outTime, lokasi ;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            lokasi = itemView.findViewById(R.id.txtName);
            inTime = itemView.findViewById(R.id.txtCheckin);
            outTime = itemView.findViewById(R.id.txtCheckout);
        }
    }

}
