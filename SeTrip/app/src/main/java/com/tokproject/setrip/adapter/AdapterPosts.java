package com.tokproject.setrip.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelPost;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder> {

    Context context;
    List<ModelPost> postList;

    public AdapterPosts(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //get data
        String uid = postList.get(position).getUid();
        String uEmail = postList.get(position).getuEmail();
        String uName = postList.get(position).getuName();
        String uDp = postList.get(position).getuDp();
        String pTitle = postList.get(position).getpTitle();
        String pDesc = postList.get(position).getpDescription();
        String pImage = postList.get(position).getpImage();
        String pTimeStamp = postList.get(position).getpTime();

        //convert timestamp
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString();

        //set data
        holder.uNameTv.setText(uName);
        holder.pTimeTv.setText(pTime);
        holder.pTitleTv.setText(pTitle);
        holder.pDesc.setText(pDesc);

        //set user dp
        Glide.with(context).load(uDp)
                .error(R.drawable.ic_baseline_face_24)
                .placeholder(R.drawable.ic_baseline_face_24)
                .into(holder.uPictureIv);


        //set post image
        //if there image or not
        if(pImage.equals("noImage")) {
            //hide imageView
            holder.pImageIv.setVisibility(View.GONE);
        } else {
            Glide.with(context)
                    .load(pImage)
                    .into(holder.pImageIv);
        }


        //handle button click
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wiil imlement later
            }
        });

        //handle button click
        holder.pLikeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wiil imlement later
            }
        });

        //handle button click
        holder.pCommentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wiil imlement later
            }
        });




    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    //view holder class
    class MyHolder extends RecyclerView.ViewHolder{

        ImageView uPictureIv, pImageIv, pLikeIv, pCommentIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDesc, pLikesTv;
        ImageButton moreBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init view
            uPictureIv = itemView.findViewById(R.id.uPictureIv);
            pImageIv = itemView.findViewById(R.id.pImageIv);
            pLikeIv = itemView.findViewById(R.id.likeBtn);
            pCommentIv = itemView.findViewById(R.id.commentBtn);
            uNameTv = itemView.findViewById(R.id.uNameTv);
            pTimeTv = itemView.findViewById(R.id.pTimeTv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pDesc = itemView.findViewById(R.id.pDescTv);
            pLikesTv = itemView.findViewById(R.id.pLikesTv);
            moreBtn = itemView.findViewById(R.id.moreBtn);



        }
    }

}
