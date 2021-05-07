package com.xyl.adapter.personnel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.domain.personnel.RenShiPictures;
import com.xyl.global.NetContacts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ApprovalAllShowImageAdapter extends RecyclerView.Adapter<ApprovalAllShowImageAdapter.ShowViewHolder> {


    private Context mContext;
    private int count;
    private Bitmap bitmap;
    List<RenShiPictures> renShiPictures;
    private int type;
    private ArrayList<File> fileList = new ArrayList<File>();
    public ApprovalAllShowImageAdapter(Context context, List<RenShiPictures> renShiPictures) {
        this.mContext = context;
        this.renShiPictures = renShiPictures;

    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_leave_show, null);
        ShowViewHolder showViewHolder = new ShowViewHolder(inflate);
        return showViewHolder;
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, final int position) {
        RenShiPictures renShiPictures = this.renShiPictures.get(position);
        String omage_url = NetContacts.getInstance()
                .BASE_IMAGE_URL + "/" + renShiPictures.getPictureUrl();
        Glide.with(mContext).load(omage_url).into(holder.ivPhotoImage);
    }

    @Override
    public int getItemCount() {
        //count = mUris == null ? 1 : mUris.size() + 1;
        return renShiPictures.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhotoImage;
        ImageView ivDeleteImage;

        public ShowViewHolder(View itemView) {
            super(itemView);
            ivPhotoImage = itemView.findViewById(R.id.iv_photo_image);
            ivDeleteImage = itemView.findViewById(R.id.iv_delete_image);


        }
    }
}
