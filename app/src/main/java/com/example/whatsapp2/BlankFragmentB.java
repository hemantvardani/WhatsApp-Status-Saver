package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;
import static com.example.whatsapp2.custom_created_adapter_video.totalselected_videos;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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


public class BlankFragmentB extends Fragment {
    AppBarLayout appBarLayout;
    Toolbar toolbar1;
    TextView textview1;
    ImageView cancel_cross_icon;
    ViewPager2 viewPager2;
    ImageView select_all_icon_btn_video;




    static RecyclerView recyclerView1_video;
    custom_created_adapter_video ad;
    Context context;
    Window window;
    TabLayout tabLayout;
    ImageView     download_icon_btn_multiple_images;

    public BlankFragmentB(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1, ImageView select_all_icon_btn_video,ImageView download_icon_btn_multiple_images, ImageView cancel_cross_icon, ViewPager2 viewPager2, TabLayout tabLayout, Window window) {
        this.toolbar1= toolbar1;
        this.appBarLayout=appBarLayout;
        // Required empty public constructor

        this.textview1= textview1;
        this.context=context;
        this.download_icon_btn_multiple_images=download_icon_btn_multiple_images;
        this.select_all_icon_btn_video=select_all_icon_btn_video;
        this.cancel_cross_icon= cancel_cross_icon;
        this.viewPager2= viewPager2;
        this.window=window;
        this.tabLayout=tabLayout;







    }


//    @Override
//    public void onResume() {
//        super.onResume();
//
//        Log.d("pure","vide");
//        for (int i=0;i<BaseActivity.videoList.size();i++)
//        {
//            custom_created_adapter_video.itemSelected_video[i]=false;
//        }
//        custom_created_adapter_video.totalselected_videos =0;
//        if(ad!=null) ad.notifyAllChanges_videos();
//        appBarLayout.setVisibility(View.GONE);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank_b, container, false);
        recyclerView1_video =  view.findViewById(R.id.videosRecycler);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        recyclerView1_video.setHasFixedSize(true);

        recyclerView1_video.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
//        recyclerView1_video.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        ad= new custom_created_adapter_video(view.getContext(),toolbar1,appBarLayout,tabLayout,window);
        recyclerView1_video.setAdapter(ad);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the RecyclerView goes here
                // ...

                swipeRefreshLayout.setRefreshing(true);

                // Execute the updateCode() method on a background thre
                // Execute the updateCode() method on a background thread
//                new UpdateCodeTask(swipeRefreshLayout).execute();



                class_for_loading_fragmentB task = new class_for_loading_fragmentB(context, swipeRefreshLayout, appBarLayout, ad,tabLayout,window);
                task.execute();


            }
        });






//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the RecyclerView goes here
//                // ...
//
//                swipeRefreshLayout.setRefreshing(true);
//
//
//                    SharedPreferences pref3 = context.getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
//
//                    String uriPath = pref3.getString("PATH", "");
//
//                    ContentResolver resolver = context.getContentResolver();
//                    Uri uri = Uri.parse(uriPath);
//                    Log.d("taggg",uri.toString());
//
////            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
//                    DocumentFile folder = DocumentFile.fromTreeUri(context, uri);
//
//
//                    videoList.clear();
//                    Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//                    for (DocumentFile file : folder.listFiles()) {
//                        String filePath = file.getUri() + File.separator + file.getName();
//                        Log.d("filesss",filePath);
//                        if (file.getName() == ".nomedia") {
//
//                        } else if (file.getName().endsWith(".mp4")) {
//                            videoList.add(file);
//
//                        }
//
//                    }
//
//
//
//
//                    videoList.sort(new Comparator<DocumentFile>() {
//                        @Override
//                        public int compare(DocumentFile file1, DocumentFile file2) {
//                            long timestamp1 = file1.lastModified();
//                            long timestamp2 = file2.lastModified();
//                            return Long.compare(timestamp2, timestamp1); // sort in descending order
//                        }
//                    });
//
//
//
//                    itemSelected_video = new boolean[BaseActivity.videoList.size()];
//                    Arrays.fill(itemSelected_video, false);
//
//
//                    for (int i = 0; i < videoList.size(); i++) {
//                        itemSelected_video[i] = false;
//                    }
//                    custom_created_adapter_video.totalselected_videos =0;
//                    notifyFunForB();
//
//                    appBarLayout.setVisibility(View.GONE);
//
//                    Log.d("rrrr","ttt");
//
//
//
//
//
//
//                // When you are done updating the RecyclerView list, call setRefreshing(false)
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//
//
//            }
//        });
//










                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                download_icon_btn_multiple_images.setVisibility(View.VISIBLE);

                if(totalselected_videos>0)
                {
                    for (int i=0;i<BaseActivity.videoList.size();i++)
                    {
                        custom_created_adapter_video.itemSelected_video[i]=false;
                    }
                    custom_created_adapter_video.totalselected_videos =0;
                    ad.notifyAllChanges_videos();
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

























        return view;
    }

    public void notifyFunForB() {

        ad.notifyAllChanges_videos();
    }
}




//        select_all_icon_btn_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i < videoList.size(); i++) {
//                    itemSelected_video[i] = true;
//                }
//                totalselected_videos =videoList.size();
//
//                ad.notifyAllChanges_videos();
//                textview1.setText(String.valueOf(totalselected_videos));
//
//            }});



//        cancel_cross_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i < videoList.size(); i++) {
//                    itemSelected_video[i] = false;
//                }
//                totalselected_videos =0;
//                ad.notifyAllChanges_videos();
//                appBarLayout.setVisibility(View.GONE);
//            }
//        });



//                switch (BlankFragment_main.previousPage){
//
//                    case 0:
//
//                        break;
//                    case 1:
//                        for (int i=0;i<BaseActivity.videoList.size();i++)
//                        {
//                            custom_created_adapter_video.itemSelected_video[i]=false;
//                        }
//                        custom_created_adapter_video.totalselected_videos =0;
//                        ad.notifyAllChanges_videos();
//                        appBarLayout.setVisibility(View.GONE);
//                        Log.d("hemhem","hem B");
//
//
//                        break;
//                    case 2:
//                        break;
//                    default:
//
//                }


//                BlankFragment_main.previousPage=position;


