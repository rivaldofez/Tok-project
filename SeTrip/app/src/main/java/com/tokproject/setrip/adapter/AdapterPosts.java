package com.tokproject.setrip.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tokproject.setrip.R;
import com.tokproject.setrip.model.ModelPost;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder> {

    Context context;
    List<ModelPost> postList;

    String myUid;

    private DatabaseReference likeRef; //for likes database node
    private DatabaseReference postRef1; //reference of posts

    boolean mProcessLike = false;

    public AdapterPosts(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        likeRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        postRef1 = FirebaseDatabase.getInstance().getReference().child("Posts");
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false);

        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        //get data
        final String uid = postList.get(position).getUid();
        String uEmail = postList.get(position).getuEmail();
        String uName = postList.get(position).getuName();
        String uDp = postList.get(position).getuDp();
        final String pId = postList.get(position).getpId();
        String pTitle = postList.get(position).getpTitle();
        String pDesc = postList.get(position).getpDescription();
        final String pImage = postList.get(position).getpImage();
        String pTimeStamp = postList.get(position).getpTime();
        String pLikes = postList.get(position).getpLikes();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MMM/yyyy hh:mm aa", calendar).toString();

        //set data
        holder.uNameTv.setText(uName);
        holder.pTimeTv.setText(pTime);
        holder.pTitleTv.setText(pTitle);
        holder.pDesc.setText(pDesc);
        holder.pLikesTv.setText(pLikes + " Menyukai"); //e.g. 100 likes

        setLikes(holder, pId);

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
            //hide imageView
           holder.pImageIv.setVisibility(View.VISIBLE); //make sure correct

            Glide.with(context)
                    .load(pImage)
                    .into(holder.pImageIv);
        }


        //handle button click
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showModeOptions(holder.moreBtn, uid, myUid, pId, pImage);
            }
        });

        //handle button click
        holder.pLikeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final int pLikes = Integer.parseInt(postList.get(position).getpLikes());
               mProcessLike = true;

               final String postIde = postList.get(position).getpId();
               likeRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(mProcessLike) {
                           if(snapshot.child(postIde).hasChild(myUid)) {
                               //already liked, so remove like
                               postRef1.child(postIde).child("pLikes").setValue(""+ (pLikes-1));
                               likeRef.child(postIde).child(myUid).removeValue();
                               mProcessLike = false;
                           } else {
                               postRef1.child(postIde).child("pLikes").setValue(""+(pLikes+1));
                               likeRef.child(postIde).child(myUid).setValue(R.string.menyukai);
                               mProcessLike = false;
                           }
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
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

    private void setLikes(final MyHolder holder, final String postKey) {
        likeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postKey).hasChild(myUid)){
                    //user has liked this post
                    holder.pLikeIv.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else {
                    //user hasn't like this post
                    holder.pLikeIv.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showModeOptions(ImageButton moreBtn, String uid, String myUid, final String pId, final String pImage) {

        PopupMenu popupMenu = new PopupMenu(context, moreBtn, Gravity.END);

        //show delete option in only post(s) currently sign-in
        if(uid.equals(myUid)) {
            popupMenu.getMenu().add(Menu.NONE,0,0, "Delete");
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == 0) {
                    //delete is clicked
                    beginDelete(pId, pImage);

                }
                return false;
            }
        });

        popupMenu.show();

    }

    private void beginDelete(String pId, String pImage) {
        if(pImage.equals("noImage")) {
            //post without image
            deleteWitoutImage(pId);
        } else {
            //with image
            deleteWithImage(pId, pImage);
        }
    }

    private void deleteWithImage(final String pId, String pImage) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting...");

        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(pImage);
        reference.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //success delete
                        Query query = FirebaseDatabase.getInstance().getReference("Posts").orderByChild("pId").equalTo(pId);

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds: snapshot.getChildren()) {
                                    ds.getRef().removeValue();
                                }

                                //deleted
                                Toast.makeText(context, R.string.berhasil_menghapus, Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failure
                        pd.dismiss();
                        Toast.makeText(context, R.string.maaf1, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteWitoutImage(String pId) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting...");

        Query query = FirebaseDatabase.getInstance().getReference("Posts").orderByChild("pId")
                .equalTo(pId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    ds.getRef().removeValue();
                }

                //deleted
                Toast.makeText(context, R.string.berhasil_menghapus, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
