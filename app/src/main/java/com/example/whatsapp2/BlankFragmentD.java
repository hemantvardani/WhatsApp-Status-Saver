package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.savedList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_saved.totalselected_saved;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;


public class BlankFragmentD extends Fragment {


    Toolbar toolbar1;
    AppBarLayout appBarLayout;
    TextView textview1;
    ImageView download_icon_btn_multiple_images;
    static RecyclerView recyclerView1_saved;
    ImageView select_all_icon_btn_saved;
    ImageView cancel_cross_icon;
    custom_created_adapter_saved ad;
    ViewPager2 viewPager2;
    Context context;
    Toolbar toolbar;
    TabLayout tabLayout;
    Window window;

    public BlankFragmentD(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1, ImageView select_all_icon_btn_video,ImageView download_icon_btn_multiple_images, ImageView cancel_cross_icon, ViewPager2 viewPager2, TabLayout tabLayout, Window window) {

        this.toolbar1= toolbar1;
        this.appBarLayout=appBarLayout;
        this.textview1= textview1;
        this.download_icon_btn_multiple_images=download_icon_btn_multiple_images;
        this.select_all_icon_btn_saved=select_all_icon_btn_saved;
        this.cancel_cross_icon= cancel_cross_icon;
        this.viewPager2= viewPager2;
        this.context= context;
        this.toolbar =toolbar;
        this.tabLayout=tabLayout;
        this.window=window;






    }

    public  void notifyFunForD() {

        ad.notifyAllChanges_saved();
    }



















    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view2= inflater.inflate(R.layout.fragment_blank_d, container, false);
        recyclerView1_saved =  view2.findViewById(R.id.SavedRecycler);

        SwipeRefreshLayout swipeRefreshLayout = view2.findViewById(R.id.swipe_refresh_layout);

        recyclerView1_saved.setHasFixedSize(true);

//        recyclerView1_saved.setLayoutManager(new GridLayoutManager(view2.getContext(), 3));


        // Assuming you have a RecyclerView instance called "recyclerView1_saved"



//        GridLayoutManager layoutManager = new GridLayoutManager(view2.getContext(), 3);
//        layoutManager.setReverseLayout(true);
//        recyclerView1_saved.setLayoutManager(layoutManager);
//
//        ad = new custom_created_adapter_saved(view2.getContext(), toolbar1, appBarLayout, textview1, download_icon_btn_multiple_images, toolbar, tabLayout, window);
//
//        recyclerView1_saved.setAdapter(ad);


//        GridLayoutManager layoutManager = new GridLayoutManager(view2.getContext(), 3);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//            }
//        });
//
//        recyclerView1_saved.setLayoutManager(layoutManager);
//
//        ad = new custom_created_adapter_saved(view2.getContext(), toolbar1, appBarLayout, textview1, download_icon_btn_multiple_images, toolbar, tabLayout, window);
//
//        recyclerView1_saved.setAdapter(ad);

        GridLayoutManager layoutManager = new GridLayoutManager(view2.getContext(), 3);
        layoutManager.setReverseLayout(true);

// Calculate the total number of items in the adapter
        int totalItems = ad.getItemCount();

// Calculate the number of items in the last row
        int lastRowItems = totalItems % layoutManager.getSpanCount();

// Set the span size lookup to distribute items evenly in reverse order
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < lastRowItems) {
                    // Distribute remaining items in the last row
                    return layoutManager.getSpanCount();
                } else {
                    // Distribute items evenly in other rows
                    return 1;
                }
            }
        });

        recyclerView1_saved.setLayoutManager(layoutManager);
        recyclerView1_saved.setAdapter(ad);
//





//
//        ad= new custom_created_adapter_saved(view2.getContext(),toolbar1,appBarLayout,textview1,download_icon_btn_multiple_images,toolbar,tabLayout,window);
//
//        recyclerView1_saved.setAdapter(ad);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the RecyclerView goes here
                // ...

                swipeRefreshLayout.setRefreshing(true);

                // Execute the updateCode() method on a background thre
                // Execute the updateCode() method on a background thread
//                new UpdateCodeTask(swipeRefreshLayout).execute();



//                class_for_loading_fragmentD task = new class_for_loading_fragmentD(context, swipeRefreshLayout, appBarLayout, ad,tabLayout,window);
//                task.execute();


            }
        });






        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);


                if(position==2){


                download_icon_btn_multiple_images.setVisibility(View.GONE);








//                    class_for_loading_fragmentD task = new class_for_loading_fragmentD(context, swipeRefreshLayout, appBarLayout, ad,tabLayout,window);
//                    task.execute();

                }


                if(totalselected_saved>0){

                    for (int i=0;i<BaseActivity.savedList.size();i++)
                    {
                        custom_created_adapter_saved.itemSelected_saved[i]=false;
                    }
                    totalselected_saved =0;
                    ad.notifyAllChanges_saved();
                    appBarLayout.setVisibility(View.GONE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Set the desired color for the notification bar during multi-selection
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        int status_color= ContextCompat.getColor(context, R.color.whatsapp_green);
                        window.setStatusBarColor(status_color);
                    }
                    int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                    tabLayout.setBackgroundColor(color);



                }



            }

        });








        return view2;


    }





}




