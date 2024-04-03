package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.add_to_saved_in_queue;
import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.savedList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_saved.itemSelected_saved;
import static com.example.whatsapp2.custom_created_adapter_saved.totalselected_saved;

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

public class class_for_loading_fragmentD extends AsyncTask<Void, Void, Void>  {


    private WeakReference<SwipeRefreshLayout> swipeRefreshLayout;


    private final WeakReference<Context> contextRef;
    //    AppBarLayout appBarLayout;
    private final WeakReference<AppBarLayout> appBarLayoutWeakReference;

    custom_created_adapter_saved ad;

    private final WeakReference<TabLayout> tabLayoutWeakReference;
    Window window;





    class_for_loading_fragmentD(Context context, SwipeRefreshLayout swipeRefreshLayout, AppBarLayout appBarLayout, custom_created_adapter_saved ad, TabLayout tabLayout, Window window){

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










            for (DocumentFile file : add_to_saved_in_queue) {

                savedList.add(file);
//                ad.notifyItemChanged(savedList.size()-1);
                ad.notifyItemInserted(savedList.size() - 1);


//                Log.d("wetwetyy",file.getName());


            }


                add_to_saved_in_queue.clear();
//            for(DocumentFile f:savedList){
//                Log.d("wetwet",f.getName());
//            }






        itemSelected_saved = new boolean[BaseActivity.savedList.size()];
        Arrays.fill(itemSelected_saved, false);


        for (int i = 0; i < savedList.size(); i++) {
            itemSelected_saved[i] = false;
        }

        totalselected_saved=0;
////                notifyFunForA();


        Log.d("rrrr","ttt");



        return null;
    }



//    @Override
//    protected void onProgressUpdate(Void... values) {
//        // This method runs on the main UI thread and is used to update the UI.
//        // Notify the adapter of the data change here.
//        ad.notifyItemInserted(savedList.size() - 1);
//    }

    @Override
    protected void onPostExecute(Void result) {

//        ad.notifyItemChanged(savedList.size()-1);
        Log.d("wetwetyy","file.getName()");


//        // Hide the refreshing circle when updateCode() has finished executing
////        BlankFragmentA.notifyFunForA();
//        AppBarLayout appBarLayout=appBarLayoutWeakReference.get();
//        Context context = contextRef.get();
//
//        appBarLayout.setVisibility(View.GONE);
//
//        ad.notifyAllChanges_saved();
//
//        TabLayout tabLayout=tabLayoutWeakReference.get();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            // Set the desired color for the notification bar during multi-selection
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            int status_color= ContextCompat.getColor(context, R.color.whatsapp_green);
//            window.setStatusBarColor(status_color);
//        }
//        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
//        tabLayout.setBackgroundColor(color);
//
//
//
//
//
//        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout.get();
//        if (swipeRefreshLayout != null) {
//            swipeRefreshLayout.setRefreshing(false);
//        }

    }


}








//
//package com.example.whatsapp2;
//
//import static com.example.whatsapp2.BaseActivity.imageList;
//import static com.example.whatsapp2.BaseActivity.savedList;
//import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
//import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
//import static com.example.whatsapp2.custom_created_adapter_saved.itemSelected_saved;
//import static com.example.whatsapp2.custom_created_adapter_saved.totalselected_saved;
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import androidx.core.content.ContextCompat;
//import androidx.documentfile.provider.DocumentFile;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.google.android.material.appbar.AppBarLayout;
//import com.google.android.material.tabs.TabLayout;
//
//import java.io.File;
//import java.lang.ref.WeakReference;
//import java.util.Arrays;
//import java.util.Comparator;
//
//public class class_for_loading_fragmentD extends AsyncTask<Void, Void, Void>  {
//
//
//    private WeakReference<SwipeRefreshLayout> swipeRefreshLayout;
//
//
//    private final WeakReference<Context> contextRef;
//    //    AppBarLayout appBarLayout;
//    private final WeakReference<AppBarLayout> appBarLayoutWeakReference;
//
//    custom_created_adapter_saved ad;
//
//    private final WeakReference<TabLayout> tabLayoutWeakReference;
//    Window window;
//
//
//
//
//
//    class_for_loading_fragmentD(Context context, SwipeRefreshLayout swipeRefreshLayout, AppBarLayout appBarLayout, custom_created_adapter_saved ad, TabLayout tabLayout, Window window){
//
//        this.contextRef = new WeakReference<>(context);
//        this.swipeRefreshLayout = new WeakReference<>(swipeRefreshLayout);
//        this.appBarLayoutWeakReference=new WeakReference<>(appBarLayout);
//        this.tabLayoutWeakReference=new WeakReference<>(tabLayout);
//        this.window=window;
//
//
//        this.ad=ad;
//
//
//
//
//    }
//
//
//    @Override
//    protected Void doInBackground(Void... params) {
//
//        Context context = contextRef.get();
//        if (context == null) {
//            // Context has been garbage collected, handle error
//            return null;
//        }
//
//
//
//        Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//        DocumentFile sourceFolder = DocumentFile.fromTreeUri(context, sourceUri);
//
//        savedList.clear();
//        if(sourceFolder.findFile("Downloads")!=null)
//        {
//            DocumentFile file_of_saved= sourceFolder.findFile("Downloads");
//
//            for (DocumentFile file : file_of_saved.listFiles()) {
//                String filePath = file.getUri() + File.separator + file.getName();
//                Log.d("filesss",filePath);
//
//                savedList.add(file);
//
//
//            }
//
//            for(DocumentFile f:savedList){
//                Log.d("wetwet",f.getName());
//            }
//
//        }
//
//
//        savedList.sort(new Comparator<DocumentFile>() {
//            @Override
//            public int compare(DocumentFile file1, DocumentFile file2) {
//                long timestamp1 = file1.lastModified();
//                long timestamp2 = file2.lastModified();
//                return Long.compare(timestamp2, timestamp1); // sort in descending order
//            }
//        });
//
//
//        Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//
//
//
//        itemSelected_saved = new boolean[BaseActivity.savedList.size()];
//        Arrays.fill(itemSelected_saved, false);
//
//
//        for (int i = 0; i < savedList.size(); i++) {
//            itemSelected_saved[i] = false;
//        }
//
//        totalselected_saved=0;
//////                notifyFunForA();
//
//
//        Log.d("rrrr","ttt");
//
//
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void result) {
//        // Hide the refreshing circle when updateCode() has finished executing
////        BlankFragmentA.notifyFunForA();
//        AppBarLayout appBarLayout=appBarLayoutWeakReference.get();
//        Context context = contextRef.get();
//
//        appBarLayout.setVisibility(View.GONE);
//
//        ad.notifyAllChanges_saved();
//
//        TabLayout tabLayout=tabLayoutWeakReference.get();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            // Set the desired color for the notification bar during multi-selection
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            int status_color= ContextCompat.getColor(context, R.color.whatsapp_green);
//            window.setStatusBarColor(status_color);
//        }
//        int color = ContextCompat.getColor(context, R.color.whatsapp_green);
//        tabLayout.setBackgroundColor(color);
//
//
//
//
//
//        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout.get();
//        if (swipeRefreshLayout != null) {
//            swipeRefreshLayout.setRefreshing(false);
//        }
//
//    }
//}
//
//
//
