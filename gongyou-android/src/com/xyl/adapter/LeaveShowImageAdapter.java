package com.xyl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.xyl.R;
import com.xyl.domain.personnel.RenShiPictures;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class LeaveShowImageAdapter extends RecyclerView.Adapter<LeaveShowImageAdapter.ShowViewHolder> {


    private List<Uri> mUris;
    private List<String> mPaths;
    private Context mContext;
    private int count;
    private Bitmap bitmap;
    List<RenShiPictures> renShiPictures;
    private int type;  
    private ArrayList<File> fileList = new ArrayList<File>();
    public LeaveShowImageAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_leave_show, null);
        ShowViewHolder showViewHolder = new ShowViewHolder(inflate);
        return showViewHolder;
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, final int position) {
        if (mUris == null){
            holder.ivPhotoImage.setImageResource(R.drawable.tupian);
            holder.ivDeleteImage.setVisibility(View.GONE);
        }else if (position == mUris.size()) {
            holder.ivPhotoImage.setImageResource(R.drawable.tupian);
            holder.ivDeleteImage.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < mUris.size(); i++) {
                File   file = new File(mPaths.get(i));
                Log.e("file",file.getPath());
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mPaths.get(position), options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            bitmap = BitmapFactory.decodeFile(mPaths.get(position), options);
            holder.ivPhotoImage.setImageBitmap(bitmap);
            holder.ivDeleteImage.setVisibility(View.VISIBLE);
        }

//        File file = CommonUtil.saveBitmap2file(bitmap,
//                String.valueOf(System.currentTimeMillis()).concat(".png"));
//        fileList.add(file);

        if (onGetFileListener != null){
            onGetFileListener.onGetFile(fileList);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,count-1);
                }
            }

        });
        holder.ivDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteItemClickListener != null) {
                    onDeleteItemClickListener.onDeleteClick(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        count = mUris == null ? 1 : mUris.size() + 1;
        return count;
    }

    public void setData(List<Uri> uris, List<String> strings) {
        this.mUris = uris;
        this.mPaths= strings;
        notifyDataSetChanged();
    }

    public void setIntentData(List<RenShiPictures> renShiPictures, int type) {
        this.renShiPictures = renShiPictures;
        this.type = type;
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

    private onItemClickListener onItemClickListener;
    private onDeleteItemClickListener onDeleteItemClickListener;

    private  onGetFileListener onGetFileListener;


    public interface onItemClickListener {
        void onItemClick(int position,int size);
    }

    public void setOnItemClickListener(LeaveShowImageAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onDeleteItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteItemClickListener(LeaveShowImageAdapter.onDeleteItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }



    public interface onGetFileListener{
        void onGetFile(ArrayList<File> fileArrayList);
    }

    public void setOnGetFileListener(LeaveShowImageAdapter.onGetFileListener onGetFileListener) {
        this.onGetFileListener = onGetFileListener;
    }
}
