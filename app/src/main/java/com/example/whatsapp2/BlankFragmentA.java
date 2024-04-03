package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.add_to_saved_in_queue;
import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.savedList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
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


public class BlankFragmentA extends Fragment {


    Toolbar toolbar1;
    AppBarLayout appBarLayout;
    TextView textview1;
    ImageView download_icon_btn_multiple_images;
    static RecyclerView recyclerView1_image;
    ImageView select_all_icon_btn_images;
    ImageView cancel_cross_icon;
     custom_created_adapter_image ad;
    ViewPager2 viewPager2;
     Context context;
    Toolbar toolbar;
    TabLayout tabLayout;
    Window window;

    public BlankFragmentA(Context context, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1, ImageView download_icon_btn_multiple_images, ImageView select_all_icon_btn_images, ImageView cancel_cross_icon, ViewPager2 viewPager2,Toolbar toolbar,    TabLayout tabLayout,Window window) {

        this.toolbar1= toolbar1;
        this.appBarLayout=appBarLayout;
        this.textview1= textview1;
        this.download_icon_btn_multiple_images=download_icon_btn_multiple_images;
        this.select_all_icon_btn_images=select_all_icon_btn_images;
        this.cancel_cross_icon= cancel_cross_icon;
        this.viewPager2= viewPager2;
        this.context= context;
        this.toolbar =toolbar;
        this.tabLayout=tabLayout;
        this.window=window;






//        if(this.cancel_cross_icon==null)Log.d("manwo","he");

        // Required empty public constructor
//    RecyclerView recyclerView1 =  R.layout.fragment_blank_a.findViewById(R.id.imagesRecycler);


    }

    public  void notifyFunForA() {
        ad.notifyAllChanges_images();
    }










//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d("punep","van");
//        SharedPreferences pref3 = getContext().getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
//
//        String uriPath = pref3.getString("PATH", "");
//
//        ContentResolver resolver = getContext().getContentResolver();
//        Uri uri = Uri.parse(uriPath);
//        Log.d("taggg",uri.toString());
//
////            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
//        DocumentFile folder = DocumentFile.fromTreeUri(getContext(), uri);
//
//        Log.d("problemidentify","2");
//
//
//        imageList.clear();
//        videoList.clear();
//        Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//        for (DocumentFile file : folder.listFiles()) {
//            String filePath = file.getUri() + File.separator + file.getName();
//            Log.d("filesss",filePath);
//            if (file.getName() == ".nomedia") {
//
//            } else if (file.getName().endsWith(".mp4")) {
//                videoList.add(file);
//
//            } else if (file.getName().endsWith(".jpg")) {
//
//                imageList.add(file);
//            }
//
//        }
//
//        Log.d("pure","imag");
//        for (int i=0;i<BaseActivity.imageList.size();i++)
//        {
//            custom_created_adapter_image.itemSelected_images[i]=false;
//        }
//        totalselected_images =0;
//        if(ad!=null) ad.notifyAllChanges_images();
//        appBarLayout.setVisibility(View.GONE);
//
//    }












    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view2= inflater.inflate(R.layout.fragment_blank_a, container, false);
        recyclerView1_image =  view2.findViewById(R.id.imagesRecycler);

        SwipeRefreshLayout swipeRefreshLayout = view2.findViewById(R.id.swipe_refresh_layout);

        recyclerView1_image.setHasFixedSize(true);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView1_image.setLayoutManager(new GridLayoutManager(view2.getContext(), 3));
//        recyclerView1_image.addItemDecoration(new SquareItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_spacing)));


//        recyclerView1_image.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
//        recyclerView1_image.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));






        ad= new custom_created_adapter_image(view2.getContext(),toolbar1,appBarLayout,textview1,download_icon_btn_multiple_images,toolbar,tabLayout,window);

//        functionA(functionACallback, ad);



        recyclerView1_image.setAdapter(ad);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref3 =  getActivity().getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);

                String uriPath = pref3.getString("PATH", "");

                ContentResolver resolver = getActivity().getContentResolver();
                Uri uri = Uri.parse(uriPath);
                Log.d("taggg",uri.toString());

//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
//
                DocumentFile folder = DocumentFile.fromTreeUri(context, uri);

                Log.d("sizesize0", Integer.toString(imageList.size()));


                for (DocumentFile file : folder.listFiles()) {



                    String filePath = file.getUri() + File.separator + file.getName();
                    Log.d("filesss",filePath);
                    if (file.getName().endsWith(".jpg")) {

                        imageList.add(file);
//                    ad.notifyItemInserted(imageList.size() - 1);
                        Log.d("sizesize1", Integer.toString(imageList.size()));
//                    publishProgress(); // Trigger onProgressUpdate to update UI after adding each image
                        break;

                    }


                }

                int lastIndex = imageList.size() - 1;


                ad.notifyItemChanged(lastIndex);


            }
        },4000);






//        class_for_loading_A_data_TRIAL loadContentTask = new class_for_loading_A_data_TRIAL( ad);
//        loadContentTask.execute();




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the RecyclerView goes here
                // ...

                swipeRefreshLayout.setRefreshing(true);

                // Execute the updateCode() method on a background thre
                // Execute the updateCode() method on a background thread
//                new UpdateCodeTask(swipeRefreshLayout).execute();



                class_for_loading_fragmentA task = new class_for_loading_fragmentA(context, swipeRefreshLayout, appBarLayout, ad,tabLayout,window);
                task.execute();


            }
        });






        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                download_icon_btn_multiple_images.setVisibility(View.VISIBLE);

                if(totalselected_images>0){

                    for (int i=0;i<BaseActivity.imageList.size();i++)
                    {
                        custom_created_adapter_image.itemSelected_images[i]=false;
                    }
                    totalselected_images =0;
                    ad.notifyAllChanges_images();
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



    public interface FunctionACallback {
        void onFunctionACompleted();
    }


    public void functionA(FunctionACallback callback, custom_created_adapter_image ad) {
        // Perform function A tasks
        recyclerView1_image.setAdapter(ad);
        // Notify the callback that function A is completed
        callback.onFunctionACompleted();
    }

    FunctionACallback functionACallback = new FunctionACallback() {
        @Override
        public void onFunctionACompleted( ) {
            functionB();
        }

        private void functionB( ){

            class_for_loading_A_data_TRIAL loadContentTask = new class_for_loading_A_data_TRIAL();
            loadContentTask.execute();
        }
    };




    public class class_for_loading_A_data_TRIAL extends AsyncTask<Void, Void, Void> {

//        custom_created_adapter_image ad;
//        class_for_loading_A_data_TRIAL(custom_created_adapter_image ad)
//        {
//            this.ad=ad;
//        }
//        @Override
//        protected void onPreExecute() {
//                    recyclerView1_image.setAdapter(ad);
//
//        }

        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences pref3 =  getActivity().getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);

            String uriPath = pref3.getString("PATH", "");

            ContentResolver resolver = getActivity().getContentResolver();
            Uri uri = Uri.parse(uriPath);
            Log.d("taggg",uri.toString());

//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
//
            DocumentFile folder = DocumentFile.fromTreeUri(context, uri);

            Log.d("sizesize0", Integer.toString(imageList.size()));


            for (DocumentFile file : folder.listFiles()) {



                String filePath = file.getUri() + File.separator + file.getName();
                Log.d("filesss",filePath);
                if (file.getName().endsWith(".jpg")) {

                    imageList.add(file);
//                    ad.notifyItemInserted(imageList.size() - 1);
                    Log.d("sizesize1", Integer.toString(imageList.size()));
//                    publishProgress(); // Trigger onProgressUpdate to update UI after adding each image


                }


            }

            return null;
        }



        @Override
        protected void onProgressUpdate(Void... values) {
            // Update the adapter to notify the newly added image
            Log.d("sizesize2", Integer.toString(imageList.size()));

            int lastIndex = imageList.size() - 1;
            ad.notifyItemChanged(lastIndex);
//            ad.notifyItemRangeChanged(lastIndex, 1); // Update other items if needed


        }

        @Override
        protected void onPostExecute(Void unused) {

//            ad.noti
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int lastIndex = imageList.size() - 1;
                    ad.notifyItemChanged(lastIndex);
//                    ad.notifyItemRangeInserted(1, 3); // Update other items if needed

                }
            },3000);

        }
    }





}


//    private class UpdateCodeTask extends AsyncTask<Void, Void, Void> {
//        SwipeRefreshLayout swipeRefreshLayout;
//        UpdateCodeTask(SwipeRefreshLayout swipeRefreshLayout){
//            this.swipeRefreshLayout=swipeRefreshLayout;
//
//
//        }
//        @Override
//        protected Void doInBackground(Void... params) {
//            // Your updateCode() method here
//            SharedPreferences pref3 = context.getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
//
//                String uriPath = pref3.getString("PATH", "");
//
//                ContentResolver resolver = context.getContentResolver();
//                Uri uri = Uri.parse(uriPath);
//                Log.d("taggg",uri.toString());
//
////            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
//                DocumentFile folder = DocumentFile.fromTreeUri(context, uri);
//
//
//                imageList.clear();
//                Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//                for (DocumentFile file : folder.listFiles()) {
//                    String filePath = file.getUri() + File.separator + file.getName();
//                    Log.d("filesss",filePath);
//                    if (file.getName() == ".nomedia") {
//
//                    } else if (file.getName().endsWith(".jpg")) {
//
//                        imageList.add(file);
//                    }
//
//                }
//
//
//
//
//                imageList.sort(new Comparator<DocumentFile>() {
//                    @Override
//                    public int compare(DocumentFile file1, DocumentFile file2) {
//                        long timestamp1 = file1.lastModified();
//                        long timestamp2 = file2.lastModified();
//                        return Long.compare(timestamp2, timestamp1); // sort in descending order
//                    }
//                });
//
//
//
//                itemSelected_images = new boolean[BaseActivity.imageList.size()];
//                Arrays.fill(itemSelected_images, false);
//
//
//                for (int i = 0; i < imageList.size(); i++) {
//                    itemSelected_images[i] = false;
//                }
//
//                totalselected_images=0;
////                notifyFunForA();
//
//                appBarLayout.setVisibility(View.GONE);
//
//                Log.d("rrrr","ttt");
//
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            // Hide the refreshing circle when updateCode() has finished executing
//            notifyFunForA();
//        swipeRefreshLayout.setRefreshing(false);
//        }
//    }
//



//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the RecyclerView goes here
//                // ...
//
//                swipeRefreshLayout.setRefreshing(true);
//
//
//
//                SharedPreferences pref3 = context.getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
//
//                String uriPath = pref3.getString("PATH", "");
//
//                ContentResolver resolver = context.getContentResolver();
//                Uri uri = Uri.parse(uriPath);
//                Log.d("taggg",uri.toString());
//
////            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
//                DocumentFile folder = DocumentFile.fromTreeUri(context, uri);
//
//
//                imageList.clear();
//                Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//                for (DocumentFile file : folder.listFiles()) {
//                    String filePath = file.getUri() + File.separator + file.getName();
//                    Log.d("filesss",filePath);
//                    if (file.getName() == ".nomedia") {
//
//                    } else if (file.getName().endsWith(".jpg")) {
//
//                        imageList.add(file);
//                    }
//
//                }
//
//
//
//
//                imageList.sort(new Comparator<DocumentFile>() {
//                    @Override
//                    public int compare(DocumentFile file1, DocumentFile file2) {
//                        long timestamp1 = file1.lastModified();
//                        long timestamp2 = file2.lastModified();
//                        return Long.compare(timestamp2, timestamp1); // sort in descending order
//                    }
//                });
//
//
//
//                itemSelected_images = new boolean[BaseActivity.imageList.size()];
//                Arrays.fill(itemSelected_images, false);
//
//
//                for (int i = 0; i < imageList.size(); i++) {
//                    itemSelected_images[i] = false;
//                }
//
//                totalselected_images=0;
//                notifyFunForA();
//
//                appBarLayout.setVisibility(View.GONE);
//
//                Log.d("rrrr","ttt");
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//
//
////                swipeRefreshLayout.setRefreshing(false);
//
//
//
//
//
//
//
//                // When you are done updating the RecyclerView list, call setRefreshing(false)
//
//
//
//
//            }
//        });



//        select_all_icon_btn_images.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (int i = 0; i < imageList.size(); i++) {
//                    itemSelected_images[i] = true;
//                }
//                totalselected_images =imageList.size();
//
//                ad.notifyAllChanges_images();
//                textview1.setText(String.valueOf(totalselected_images));
//
//            }
//
//
//        });






//        cancel_cross_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("pure","putImg");
//                for (int i = 0; i < imageList.size(); i++) {
//                    itemSelected_images[i] = false;
//                }
//                totalselected_images =0;
//                ad.notifyAllChanges_images();
//                appBarLayout.setVisibility(View.GONE);
//            }
//        });




