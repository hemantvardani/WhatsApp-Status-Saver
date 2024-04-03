package com.example.whatsapp2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.Arrays;

public class custom_created_adapter_video extends RecyclerView.Adapter<custom_created_adapter_video.ViewHolder> {

    Context context;

    static  boolean[] itemSelected_video;
    static int totalselected_videos=0;
    AppBarLayout appBarLayout;

    Toolbar toolbar1;
    TextView textView1;
    Window window;
    TabLayout tabLayout;




    custom_created_adapter_video(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TabLayout tabLayout, Window window){
        this.context=context;
        this.toolbar1= toolbar1;
        this.appBarLayout= appBarLayout;
        this.window=window;
        this.tabLayout=tabLayout;



        itemSelected_video = new boolean[BaseActivity.videoList.size()];
        Arrays.fill(itemSelected_video, false);


    }

    public  void notifyAllChanges_videos(){
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public custom_created_adapter_video.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_video_layout,parent,false);
        custom_created_adapter_video.ViewHolder viewHolder__ = new custom_created_adapter_video.ViewHolder(v);
        Log.d("fileqqq","hellzo");
        return viewHolder__;

    }

    @Override
    public void onBindViewHolder(@NonNull custom_created_adapter_video.ViewHolder holder, int position) {
        DocumentFile filee= BaseActivity.videoList.get(position);
        String filePath= filee.getUri() + File.separator + filee.getName();

//        holder.vid.setVideoURI(Uri.parse(filePath));
        // or
        Glide.with(context)
                .load(Uri.parse(filePath))
                .into(holder.vid);


        if(itemSelected_video[position])
        {
            holder.selection_green_screen_video.setAlpha(1.0f);
        }
        else{
            holder.selection_green_screen_video.setAlpha(0.0f);
        }



        holder.selection_green_screen_video.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(totalselected_videos ==0){


//                    selected_imageList.clear();

                    // make new toolbar appear and edits

                    //produce vibration

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Set the desired color for the notification bar during multi-selection
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        int status_color= ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                        window.setStatusBarColor(status_color);
                    }




                    holder.selection_green_screen_video.setAlpha(1f);

                    int color = ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                    tabLayout.setBackgroundColor(color);







                    totalselected_videos++;
                    itemSelected_video[position]=!itemSelected_video[position];
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbar1.setTitle("");
                    textView1= appBarLayout.findViewById(R.id.how_many_selection);


                    String s=  Integer.toString(totalselected_videos);
                    textView1.setText(s);

//                    selected_imageList.add(imageList.get(position));





                    // toogle

                }

                return true;
            }
        });


        holder.selection_green_screen_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("manman", "yes");

//                holder.itemView.setSelected(true);
//                notifyDataSetChanged();

                if(totalselected_videos >0){



                    if(itemSelected_video[position]){
                        totalselected_videos--;
                        holder.selection_green_screen_video.setAlpha(0.0f);
//                        selected_imageList.remove(imageList.get(position));


                    }else{
                        totalselected_videos++;
                        holder.selection_green_screen_video.setAlpha(1f);
//                        selected_imageList.add(imageList.get(position));
                    }
                    itemSelected_video[position]=!itemSelected_video[position];

                    if(totalselected_videos ==0){
                        appBarLayout.setVisibility(View.GONE);
//                        toolbar1.setVisibility(View.GONE);
//                        selected_imageList.clear();

                        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                        tabLayout.setBackgroundColor(color);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // Set the desired color for the notification bar during multi-selection
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            int status_color=ContextCompat.getColor(context, R.color.whatsapp_green);
                            window.setStatusBarColor(status_color);
                        }

                    }

                    String s=  Integer.toString(totalselected_videos);
                    textView1.setText(s);





                }
                else
                {
                    Intent intent= new Intent(context, FullScreenVideoActivity.class );
                    intent.putExtra("position_in_video_list",position);
//                    holder.img.setTransitionName("imageTransition");
//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.img , "imageTransition");
//                    context.startActivity(intent, options.toBundle());
                    context.startActivity(intent);


                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return BaseActivity.videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView vid;
        RelativeLayout selection_green_screen_video;

        public ViewHolder(@NonNull View i){
            super(i);

            vid=i.findViewById(R.id.video_in_layout);
//            vid=i.findViewById(R.id.cardViewVideo);

            selection_green_screen_video = i.findViewById(R.id.selection_green_screen);




        }
    }
}
