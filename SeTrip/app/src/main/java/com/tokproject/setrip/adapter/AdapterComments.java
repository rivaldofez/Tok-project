package com.tokproject.setrip.adapter;

import android.content.Context;
import android.media.Image;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelComment;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.MyHolder>  {

    Context context;
    List<ModelComment> commentList;

    public AdapterComments(Context context, List<ModelComment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String uid = commentList.get(position).getUid();
        String name = commentList.get(position).getuName();
        String email = commentList.get(position).getuEmail();
        String image = commentList.get(position).getuDp();
        String cid = commentList.get(position).getcId();
        String comment = commentList.get(position).getComment();
        String time = commentList.get(position).getTimestamp();


        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(time));
        String pTime = DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString();

        holder.nameTv.setText(name);
        holder.commentTv.setText(comment);
        holder.timeTv.setText(pTime);

        Glide.with(context).load(image)
                .error(R.drawable.ic_baseline_face_24)
                .placeholder(R.drawable.ic_baseline_face_24)
                .into(holder.avaterIv);







    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView avaterIv;
        TextView nameTv, commentTv, timeTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            avaterIv = itemView.findViewById(R.id.avatarIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            commentTv = itemView.findViewById(R.id.commentTv);
            timeTv = itemView.findViewById(R.id.timeTv);


        }
    }
}
