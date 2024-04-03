package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Comparator;

public class class_for_loading_fragmentB  extends AsyncTask<Void, Void, Void> {



    private WeakReference<SwipeRefreshLayout> swipeRefreshLayout;

    //   static Context contextm;
    private final WeakReference<Context> contextRef;
    //    AppBarLayout appBarLayout;
    private final WeakReference<AppBarLayout> appBarLayoutWeakReference;

    private final WeakReference<TabLayout> tabLayoutWeakReference;
    Window window;
    custom_created_adapter_video ad;


//    private final WeakReference<SwipeRefreshLayout> swipeRefreshLayoutWeakReference;



    class_for_loading_fragmentB(Context context, SwipeRefreshLayout swipeRefreshLayout, AppBarLayout appBarLayout,custom_created_adapter_video ad, TabLayout tabLayout, Window window){
//        swipeRefreshLayoutWeakReference=swipeRefreshLayout;
//        contextm= context;
        this.contextRef = new WeakReference<>(context);
        this.swipeRefreshLayout = new WeakReference<>(swipeRefreshLayout);
        this.appBarLayoutWeakReference=new WeakReference<>(appBarLayout);
        this.tabLayoutWeakReference=new WeakReference<>(tabLayout);
        this.window=window;
        this.ad=ad;




    }


    @Override
    protected Void doInBackground(Void... params) {

        Context context = contextRef.get();


        if (context == null) {
            // Context has been garbage collected, handle error
            return null;
        }

        // Your updateCode() method here
        SharedPreferences pref3 = context.getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);

                    String uriPath = pref3.getString("PATH", "");

                    ContentResolver resolver = context.getContentResolver();
                    Uri uri = Uri.parse(uriPath);
                    Log.d("taggg",uri.toString());

//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
//
                    DocumentFile folder = DocumentFile.fromTreeUri(context, uri);


                    videoList.clear();
                    Log.d("problemidetify","Stifolder.listFiles().length()");


                    for (DocumentFile file : folder.listFiles()) {
                        String filePath = file.getUri() + File.separator + file.getName();
                        Log.d("filesss",filePath);
                        if (file.getName() == ".nomedia") {

                        } else if (file.getName().endsWith(".mp4")) {
                            videoList.add(file);

                        }

                    }




                    videoList.sort(new Comparator<DocumentFile>() {
                        @Override
                        public int compare(DocumentFile file1, DocumentFile file2) {
                            long timestamp1 = file1.lastModified();
                            long timestamp2 = file2.lastModified();
                            return Long.compare(timestamp2, timestamp1); // sort in descending order
                        }
                    });



                    itemSelected_video = new boolean[BaseActivity.videoList.size()];
                    Arrays.fill(itemSelected_video, false);


                    for (int i = 0; i < videoList.size(); i++) {
                        itemSelected_video[i] = false;
                    }
                    custom_created_adapter_video.totalselected_videos =0;
//                    notifyFunForB();


                    Log.d("rrrr","ttt");




        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // Hide the refreshing circle when updateCode() has finished executing
//        BlankFragmentA.notifyFunForA();

        AppBarLayout appBarLayout=appBarLayoutWeakReference.get();
        Context context = contextRef.get();
        appBarLayout.setVisibility(View.GONE);



        ad.notifyAllChanges_videos();

        TabLayout tabLayout=tabLayoutWeakReference.get();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the desired color for the notification bar during multi-selection
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int status_color= ContextCompat.getColor(context, R.color.whatsapp_green);
            window.setStatusBarColor(status_color);
        }
        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
        tabLayout.setBackgroundColor(color);







        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout.get();
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }
}
