//package com.example.whatsapp2;
//
//import android.content.Context;
//import android.net.Uri;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.MediaController;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.documentfile.provider.DocumentFile;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.io.File;
//
//public class full_Screen_Video_Adapter  extends RecyclerView.Adapter<full_Screen_Video_Adapter.ViewHolder>  {
//
//    Context context;
//
//
//
//    full_Screen_Video_Adapter(Context context){
//        this.context= context;
//    }
//
//    @NonNull
//    @Override
//    public full_Screen_Video_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_screen_video_layout, parent, false);
//        return new ViewHolder(view);
//    }
//
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull full_Screen_Video_Adapter.ViewHolder holder, int position) {
//
//        DocumentFile filee= BaseActivity.videoList.get(position);
//        String filePath= filee.getUri() + File.separator + filee.getName();
//        holder.video_in_layout.setVideoURI(Uri.parse(filePath));
//
//        holder.video_in_layout.start();
//
////        Log.d("tantan1",String.valueOf(position));
//
//
//
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return BaseActivity.videoList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        VideoView video_in_layout;
//
//        public ViewHolder(@NonNull View itemView){
//            super(itemView);
//            video_in_layout= itemView.findViewById(R.id.fullScreenVideoInLayout);
//
//        }
//    }
//}


























package com.example.whatsapp2;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class full_Screen_Video_Adapter extends RecyclerView.Adapter<full_Screen_Video_Adapter.ViewHolder> {

    Context context;

    full_Screen_Video_Adapter(Context context){
        this.context= context;
    }

    @NonNull
    @Override
    public full_Screen_Video_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_screen_video_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull full_Screen_Video_Adapter.ViewHolder holder, int position) {
        DocumentFile filee = BaseActivity.videoList.get(position);
        String filePath = filee.getUri() + File.separator + filee.getName();

        holder.bind(filePath);
    }

    @Override
    public int getItemCount() {
        return BaseActivity.videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            videoView = itemView.findViewById(R.id.fullScreenVideoInLayout);
        }

        public void bind(String videoUrl) {
            // Load the video from the URL
            videoView.setVideoURI(Uri.parse(videoUrl));

            // Set up the video view to start playing when it's ready
            videoView.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                if(!videoView.isPlaying()){

                    videoView.start();
                }
            });
        }


    }
}



