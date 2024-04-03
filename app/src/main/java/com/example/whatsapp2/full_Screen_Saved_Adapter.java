

package com.example.whatsapp2;

        import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.VideoView;

        import androidx.annotation.NonNull;
        import androidx.documentfile.provider.DocumentFile;
        import androidx.recyclerview.widget.RecyclerView;

        import java.io.File;

public class full_Screen_Saved_Adapter extends RecyclerView.Adapter<full_Screen_Saved_Adapter.ViewHolder> {

    full_Screen_Saved_Adapter(){


    }

    @NonNull
    @Override
    public full_Screen_Saved_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_screen_saved_layout, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull full_Screen_Saved_Adapter.ViewHolder holder, int position) {
//        holder.image_in_layout.setTransitionName("imageTransition");
        DocumentFile filee= BaseActivity.savedList.get(position);
        String filePath= filee.getUri() + File.separator + filee.getName();


        if(filee.getName().endsWith(".jpg"))
        {
            holder.image_in_layout.setImageURI(Uri.parse(filePath));
            holder.videoView.setVisibility(View.GONE);

        }
        else{
            holder.bind(filePath);
            holder.image_in_layout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return BaseActivity.savedList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_in_layout;
        VideoView videoView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_in_layout= itemView.findViewById(R.id.fullScreenImageInLayout);
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
