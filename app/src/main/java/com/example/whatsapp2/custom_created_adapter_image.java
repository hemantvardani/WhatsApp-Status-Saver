package com.example.whatsapp2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class custom_created_adapter_image extends RecyclerView.Adapter<custom_created_adapter_image.ViewHolder>{
// implements ActionMode.Callback
    Context context;
    static boolean[] itemSelected_images;
    ActionBar actionBar;
    Toolbar toolbar1;
    static int totalselected_images =0;

    AppBarLayout appBarLayout;
    TextView textView1;
    ImageView download_icon_btn_multiple_images;

//    public  static List<DocumentFile> selected_imageList= new ArrayList<>();
    Toolbar toolbar;
    TabLayout tabLayout;
    Window window;


    custom_created_adapter_image(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1,ImageView download_icon_btn_multiple_images,Toolbar toolbar,    TabLayout tabLayout,Window window){
        this.context=context;
        this.toolbar1= toolbar1;
        this.appBarLayout= appBarLayout;
        this.download_icon_btn_multiple_images= download_icon_btn_multiple_images;
        this.toolbar=toolbar;
        this.tabLayout=tabLayout;
        this.window=window;

//        this.textView1= textview1;


        itemSelected_images = new boolean[BaseActivity.imageList.size()];
        Arrays.fill(itemSelected_images, false);


    }





    public  void notifyAllChanges_images(){
        Log.d("pure","imag11");
        notifyDataSetChanged();
    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_image_layout,parent,false);
        ViewHolder viewHolder__ = new ViewHolder(v);

        Log.d("fileqqq","hellzo");
        return viewHolder__;
    }







    @Override
    public void onBindViewHolder(@NonNull custom_created_adapter_image.ViewHolder holder, int position) {
        DocumentFile filee= BaseActivity.imageList.get(position);
        String filePath= filee.getUri() + File.separator + filee.getName();
        actionBar= ((AppCompatActivity) context).getSupportActionBar();



//        holder.img.setImageURI(Uri.parse(filePath));
        //or

        // guide with also one option to load image ( check it)
        Glide.with(context)
                .load(Uri.parse(filePath))
                .into(holder.img);


//        holder.btn

        if(itemSelected_images[position])
        {
            holder.selection_green_screen_image.setAlpha(1.0f);
        }
        else{
            holder.selection_green_screen_image.setAlpha(0.0f);
        }



        holder.selection_green_screen_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(totalselected_images ==0){


//                    selected_imageList.clear();

                    // make new toolbar appear and edits

                    //produce vibration


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Set the desired color for the notification bar during multi-selection
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        int status_color=ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                        window.setStatusBarColor(status_color);
                    }


                    holder.selection_green_screen_image.setAlpha(1f);

                    int color = ContextCompat.getColor(context, R.color.toolbar_at_selection_color1);
                    tabLayout.setBackgroundColor(color);



                    totalselected_images++;
                    itemSelected_images[position]=!itemSelected_images[position];
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbar1.setTitle("");
                    textView1= appBarLayout.findViewById(R.id.how_many_selection);


                    String s=  Integer.toString(totalselected_images);
                    textView1.setText(s);

//                    selected_imageList.add(imageList.get(position));





                    // toogle

                }

                return true;
            }
        });






        holder.selection_green_screen_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("manman", "yes");

//                holder.itemView.setSelected(true);
//                notifyDataSetChanged();

                if(totalselected_images >0){



                    if(itemSelected_images[position]){
                        totalselected_images--;
                        holder.selection_green_screen_image.setAlpha(0.0f);
//                        selected_imageList.remove(imageList.get(position));


                    }else{
                        totalselected_images++;
                        holder.selection_green_screen_image.setAlpha(1f);
//                        selected_imageList.add(imageList.get(position));
                    }
                    itemSelected_images[position]=!itemSelected_images[position];

                    if(totalselected_images ==0){
                        appBarLayout.setVisibility(View.GONE);
//                        toolbar1.setVisibility(View.GONE);
//                        selected_imageList.clear();

                        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                        tabLayout.setBackgroundColor(color);





                        // Get the window object


// Check if the device is running Android Lollipop or higher
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // Set the desired color for the notification bar during multi-selection
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            int status_color=ContextCompat.getColor(context, R.color.whatsapp_green);
                            window.setStatusBarColor(status_color);
                        }







                    }

                    String s=  Integer.toString(totalselected_images);
                    textView1.setText(s);





                }
                else
                {
                    Intent intent= new Intent(context, FullScreenImageActivity.class );
                    intent.putExtra("position_in_image_list",position);
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
        return BaseActivity.imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        RelativeLayout selection_green_screen_image;
        Toolbar toolbar;



        public ViewHolder(@NonNull View i){
            super(i);
            img= i.findViewById(R.id.image_in_layout);
//            btn =i.findViewById(R.id.download_button);
            toolbar=i.findViewById(R.id.toolbar);
            selection_green_screen_image = i.findViewById(R.id.selection_green_screen);
        }
    }

}

