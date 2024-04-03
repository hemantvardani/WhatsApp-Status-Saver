package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.savedList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_saved.itemSelected_saved;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;
import static com.example.whatsapp2.custom_created_adapter_video.totalselected_videos;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class ViewPagerAdapter  extends FragmentStateAdapter {
    Activity activity;
    Toolbar toolbar1;
    AppBarLayout appBarLayout;
    TextView textView1;
    ImageView download_icon_btn_multiple_images;
    ImageView select_all_icon_btn_images;
    ImageView cancel_cross_icon;
    ViewPager2 viewPager2;
    static int Global_position;
    BlankFragmentA blankFragmentA_object;
    BlankFragmentB blankFragmentB_object;
    BlankFragmentD blankFragmentD_object;
    Context context;
    Toolbar toolbar;
    TabLayout tabLayout;
    Window window;


    public ViewPagerAdapter(Context context, @NonNull FragmentActivity fa, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1, ImageView download_icon_btn_multiple_images , ImageView select_all_icon_btn_images, ImageView cancel_cross_icon, ViewPager2 viewPager2,Toolbar toolbar,    TabLayout tabLayout, Window window) {
        super(fa);
        this.toolbar1= toolbar1;
        this.textView1=textview1;
        this.appBarLayout=appBarLayout;
        this.download_icon_btn_multiple_images= download_icon_btn_multiple_images;
        this.select_all_icon_btn_images=select_all_icon_btn_images;
        this.cancel_cross_icon= cancel_cross_icon;
        this.viewPager2= viewPager2;
        this.context= context;
        this.toolbar=toolbar;
        this.tabLayout=tabLayout;
        this.window=window;





        select_all_icon_btn_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(Global_position==0){
                    Log.d("manman","wo1");

                    for (int i = 0; i < imageList.size(); i++) {
                        itemSelected_images[i] = true;
                    }
                    totalselected_images =imageList.size();

                    blankFragmentA_object.notifyFunForA();
                    textview1.setText(String.valueOf(totalselected_images));


                }
                else{
                    Log.d("manman","wo2");

                    for (int i = 0; i < videoList.size(); i++) {
                        itemSelected_video[i] = true;
                    }
                    totalselected_videos =videoList.size();

                    blankFragmentB_object.notifyFunForB();
                    textview1.setText(String.valueOf(totalselected_videos));

                }


            }
        });










        cancel_cross_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                tabLayout.setBackgroundColor(color);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Set the desired color for the notification bar during multi-selection
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    int status_color=ContextCompat.getColor(context, R.color.whatsapp_green);
                    window.setStatusBarColor(status_color);
                }



                if(Global_position==0){
                    Log.d("pure","putImg");
                    for (int i = 0; i < imageList.size(); i++) {
                        itemSelected_images[i] = false;
                    }
                    totalselected_images =0;
                    blankFragmentA_object.notifyFunForA();

                }
                else if(Global_position==1){
                    Log.d("pure","putvide");
                    for (int i = 0; i < videoList.size(); i++) {
                        itemSelected_video[i] = false;
                    }
                    custom_created_adapter_video.totalselected_videos =0;
                    blankFragmentB_object.notifyFunForB();


                }
                else{
                    for (int i = 0; i < savedList.size(); i++) {
                        itemSelected_saved[i] = false;
                    }
                    custom_created_adapter_saved.totalselected_saved =0;
//                    blankFragmentD_object.notifyFunForD();

                }

                appBarLayout.setVisibility(View.GONE);
            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Global_position=position;
                Log.d("hemhem","hem1");
//                BlankFragment_main.previousPage=position;

            }

        });



    }





//    @NonNull
    @Override
    public Fragment createFragment(int position) {



        if (position == 0) {

//            return  null;
           blankFragmentA_object=  new BlankFragmentA(context,toolbar1 , appBarLayout, textView1,download_icon_btn_multiple_images,select_all_icon_btn_images,cancel_cross_icon, viewPager2,toolbar,tabLayout,window);


            return blankFragmentA_object;
        } else if (position == 1)
         {
             blankFragmentB_object= new BlankFragmentB(context,toolbar1,  appBarLayout,  textView1, select_all_icon_btn_images,download_icon_btn_multiple_images, cancel_cross_icon,  viewPager2,tabLayout,window);
            return blankFragmentB_object;
//             return null;
    }else

        {

//            blankFragmentD_object = new BlankFragmentD(context, toolbar1, appBarLayout, textView1, select_all_icon_btn_images, download_icon_btn_multiple_images, cancel_cross_icon, viewPager2, tabLayout, window);
//            return blankFragmentD_object;

                                return new BlankFragment_for_testing();
//                                return null;

        }




    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void updateOnResume() {

        SharedPreferences pref3 = context.getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);

        String uriPath = pref3.getString("PATH", "");

        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse(uriPath);
        Log.d("taggg",uri.toString());

//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
//
        DocumentFile folder = DocumentFile.fromTreeUri(context, uri);

        imageList.clear();
        videoList.clear();
        Log.d("problemidetify","Stifolder.listFiles().length()");


        for (DocumentFile file : folder.listFiles()) {
            String filePath = file.getUri() + File.separator + file.getName();
            Log.d("filesss",filePath);
            if (file.getName() == ".nomedia") {

            } else if (file.getName().endsWith(".mp4")) {
                videoList.add(file);

            } else if (file.getName().endsWith(".jpg")) {

                imageList.add(file);
            }

        }

//
//
//
//
//
//
        imageList.sort(new Comparator<DocumentFile>() {
            @Override
            public int compare(DocumentFile file1, DocumentFile file2) {
                long timestamp1 = file1.lastModified();
                long timestamp2 = file2.lastModified();
                return Long.compare(timestamp2, timestamp1); // sort in descending order
            }
        });


        videoList.sort(new Comparator<DocumentFile>() {
            @Override
            public int compare(DocumentFile file1, DocumentFile file2) {
                long timestamp1 = file1.lastModified();
                long timestamp2 = file2.lastModified();
                return Long.compare(timestamp2, timestamp1); // sort in descending order
            }
        });



        itemSelected_images = new boolean[BaseActivity.imageList.size()];
        Arrays.fill(itemSelected_images, false);


        for (int i = 0; i < imageList.size(); i++) {
            itemSelected_images[i] = false;
        }
        totalselected_images =0;
//        blankFragmentA_object.notifyFunForA();


        itemSelected_video = new boolean[BaseActivity.videoList.size()];
        Arrays.fill(itemSelected_video, false);


        for (int i = 0; i < videoList.size(); i++) {
            itemSelected_video[i] = false;
        }
        custom_created_adapter_video.totalselected_videos =0;
//        blankFragmentB_object.notifyFunForB();

        appBarLayout.setVisibility(View.GONE);

    Log.d("rrrr","ttt");



    }


    public void otherNotifyFun(){
        blankFragmentA_object.notifyFunForA();
        blankFragmentB_object.notifyFunForB();


    }




}
