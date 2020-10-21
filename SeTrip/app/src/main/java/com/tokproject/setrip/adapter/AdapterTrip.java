package com.tokproject.setrip.adapter;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelTrip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AdapterTrip extends RecyclerView.Adapter implements Filterable {
    List<ModelTrip> modelTripList;
    List<ModelTrip> modelTripListAll;

    public AdapterTrip(List<ModelTrip> modelTripList) {
        this.modelTripList = modelTripList;
        modelTripListAll = new ArrayList<>(modelTripList);
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
            viewHolderClass.outTime.setText(convertTime(modelTrip.getOutTime()));
        }

        viewHolderClass.inTime.setText(convertTime(modelTrip.getInTime()));
        viewHolderClass.lokasi.setText(modelTrip.getLokasi());
    }

    @Override
    public int getItemCount() {
        return modelTripList.size();
    }

    @Override
    public Filter getFilter() {
        return FilteredTrip;
    }

    private Filter FilteredTrip = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchText = charSequence.toString().toLowerCase();
            List<ModelTrip>tempList = new ArrayList<>();
            if(searchText.length() == 0 || searchText.isEmpty() || searchText.equals("")){

                tempList.addAll(modelTripListAll);
                Integer size = tempList.size();
                Log.d("Temp", size.toString());
                size = modelTripListAll.size();
                Log.d("All", size.toString());

            }else{
                for(ModelTrip item:modelTripList){
                    if(item.getLokasi().toLowerCase().contains(searchText)||
                            convertTime(item.getInTime()).toLowerCase().contains(searchText)||
                            convertTime(item.getOutTime()).toLowerCase().contains(searchText)){
                        tempList.add(item);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tempList;

                return filterResults;
            }
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            modelTripList.clear();
            modelTripList.addAll((Collection<? extends ModelTrip>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView inTime, outTime, lokasi ;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            lokasi = itemView.findViewById(R.id.txtName);
            inTime = itemView.findViewById(R.id.txtCheckin);
            outTime = itemView.findViewById(R.id.txtCheckout);
        }
    }

    public String convertTime(String input){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(input));
        return (DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString());
    }

}
