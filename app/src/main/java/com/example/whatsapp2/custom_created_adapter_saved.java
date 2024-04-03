package com.example.whatsapp2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.Arrays;

public class custom_created_adapter_saved extends RecyclerView.Adapter<custom_created_adapter_saved.ViewHolder>{

    Context context;
    static boolean[] itemSelected_saved;
    ActionBar actionBar;
    Toolbar toolbar1;
    static int totalselected_saved =0;

    AppBarLayout appBarLayout;
    TextView textView1;
    ImageView download_icon_btn_multiple_saved;


    Toolbar toolbar;
    TabLayout tabLayout;
    Window window;


    custom_created_adapter_saved(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1,ImageView download_icon_btn_multiple_saved,Toolbar toolbar,    TabLayout tabLayout,Window window){
        this.context=context;
        this.toolbar1= toolbar1;
        this.appBarLayout= appBarLayout;
        this.download_icon_btn_multiple_saved= download_icon_btn_multiple_saved;
        this.toolbar=toolbar;
        this.tabLayout=tabLayout;
        this.window=window;




        itemSelected_saved = new boolean[BaseActivity.savedList.size()];
        Arrays.fill(itemSelected_saved, false);


    }





    public  void notifyAllChanges_saved(){
        Log.d("pure","imag11");
        notifyDataSetChanged();
    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_video_layout,parent,false);
        ViewHolder viewHolder__ = new ViewHolder(v);

        Log.d("fileqqq","hellzo");
        return viewHolder__;
    }







    @Override
    public void onBindViewHolder(@NonNull custom_created_adapter_saved.ViewHolder holder, int position) {
        DocumentFile filee= BaseActivity.savedList.get(position);
        String filePath= filee.getUri() + File.separator + filee.getName();
        actionBar= ((AppCompatActivity) context).getSupportActionBar();





        Glide.with(context)
                .load(Uri.parse(filePath))
                .into(holder.img);

        holder.playButton.setVisibility(View.VISIBLE);


        Log.d("nana",filee.getName());
               if(  filee.getName().endsWith(".jpg")){
                   holder.playButton.setVisibility(View.GONE);
                   Log.d("nana",filee.getName());
               }

        Log.d("nana","......");







        if(itemSelected_saved[position])
        {
            holder.selection_green_screen_saved.setAlpha(1.0f);
        }
        else{
            holder.selection_green_screen_saved.setAlpha(0.0f);
        }



        holder.selection_green_screen_saved.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(totalselected_saved ==0){




                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Set the desired color for the notification bar during multi-selection
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        int status_color=ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                        window.setStatusBarColor(status_color);
                    }


                    holder.selection_green_screen_saved.setAlpha(1f);

                    int color = ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                    tabLayout.setBackgroundColor(color);



                    totalselected_saved++;
                    itemSelected_saved[position]=!itemSelected_saved[position];
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbar1.setTitle("");
                    textView1= appBarLayout.findViewById(R.id.how_many_selection);


                    String s=  Integer.toString(totalselected_saved);
                    textView1.setText(s);



                }

                return true;
            }
        });






        holder.selection_green_screen_saved.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("manman", "yes");



                if(totalselected_saved >0){



                    if(itemSelected_saved[position]){
                        totalselected_saved--;
                        holder.selection_green_screen_saved.setAlpha(0.0f);



                    }else{
                        totalselected_saved++;
                        holder.selection_green_screen_saved.setAlpha(1f);

                    }
                    itemSelected_saved[position]=!itemSelected_saved[position];

                    if(totalselected_saved ==0){
                        appBarLayout.setVisibility(View.GONE);


                        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                        tabLayout.setBackgroundColor(color);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            int status_color=ContextCompat.getColor(context, R.color.whatsapp_green);
                            window.setStatusBarColor(status_color);
                        }


                    }

                    String s=  Integer.toString(totalselected_saved);
                    textView1.setText(s);





                }
                else
                {
                    Intent intent= new Intent(context, FullScreenSavedActivity.class );
                    intent.putExtra("position_in_saved_list",position);

                    context.startActivity(intent);


                }


            }
        });



    }



    @Override
    public int getItemCount() {
        return BaseActivity.savedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        RelativeLayout selection_green_screen_saved;
        Toolbar toolbar;
        ImageView playButton;



        public ViewHolder(@NonNull View i){
            super(i);
            img= i.findViewById(R.id.video_in_layout);
            playButton=i.findViewById(R.id.playbuttonid);
            toolbar=i.findViewById(R.id.toolbar);
            selection_green_screen_saved = i.findViewById(R.id.selection_green_screen);
        }
    }

}

