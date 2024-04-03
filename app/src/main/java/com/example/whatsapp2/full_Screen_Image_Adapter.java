package com.example.whatsapp2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class full_Screen_Image_Adapter extends RecyclerView.Adapter<full_Screen_Image_Adapter.ViewHolder> {

    full_Screen_Image_Adapter(){


    }

    @NonNull
    @Override
    public full_Screen_Image_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_screen_image_layout, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull full_Screen_Image_Adapter.ViewHolder holder, int position) {
//        holder.image_in_layout.setTransitionName("imageTransition");
        DocumentFile filee= BaseActivity.imageList.get(position);
        String filePath= filee.getUri() + File.separator + filee.getName();
        holder.image_in_layout.setImageURI(Uri.parse(filePath));
    }

    @Override
    public int getItemCount() {
        return BaseActivity.imageList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_in_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_in_layout= itemView.findViewById(R.id.fullScreenImageInLayout);

        }
    }


}
