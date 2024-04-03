//package com.example.whatsapp2;
//
//
////import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
//
//import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.documentfile.provider.DocumentFile;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.widget.ViewPager2;
//
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.ContentObserver;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.FileObserver;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//import android.util.Log;
//
//
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import android.os.Handler;
//public class MainActivity extends AppCompatActivity {
//
//    FragmentActivity fa= this;
//    TabLayout tabLayout;
//    ViewPager2 viewPager2;
//    String[] tabName={" Images ", " Videos " ,"Downloads" };
//
//    public static   List<DocumentFile> videoList = new ArrayList<>();
//    public  static  List<DocumentFile> imageList = new ArrayList<>();
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        // code of shared preference to know whether user earlier gave permission or not , here set permissionGiven
//
////        variousFunction vf= new variousFunction();
//        if (!isPermissionGiven()) {
//            showSomedemo();
//
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                AskPermission();
//
//
//            }
//        } else {
//            SharedPreferences pref3 = getSharedPreferences("DATA_PATH",MODE_PRIVATE);
//
//            String uriPath =pref3.getString("PATH","");
//
//            ContentResolver resolver = getContentResolver();
//            Uri uri = Uri.parse(uriPath);
//
//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
////
//            DocumentFile folder = DocumentFile.fromTreeUri(getApplicationContext(), uri);
//
//            // here do sort folder , according to date
//            imageList.clear();
//            videoList.clear();
//            for (DocumentFile file : folder.listFiles()) {
//
//                String filePath = file.getUri() + File.separator + file.getName();
////                    Log.d("filesss",filePath);
//                if (file.getName() == ".nomedia") {
//
//                } else if (file.getName().endsWith(".mp4")) {
//                    videoList.add(file);
//
//                } else if (file.getName().endsWith(".jpg")) {
//
//                    imageList.add(file);
//                }
//
//
//            }
//
//            settingAdapter();
//
//        }
//
//    }
//    public Boolean isPermissionGiven() {
//        // check in shared pref whether permissionGiven is true or false
//        SharedPreferences pref = getSharedPreferences("permission_",MODE_PRIVATE);
//
//        Boolean permissionGiven =pref.getBoolean("permission_variable",false);
//
//        return permissionGiven;
//
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public  void AskPermission() {
//        tellHowUseThisFolderLooks();
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        Uri wa_status_uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia/document/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, wa_status_uri);
////android.content.extra.SHOW_ADVANCED
//        startActivityForResult(intent, 10001);
//
//    }
//
//    public  void tellHowUseThisFolderLooks() {
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("filesss", "workinhg");
//        try {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (requestCode == 10001  && resultCode  == RESULT_OK) {
//
//                // here, set permissionGiven in shared prefs
//                SharedPreferences pref= getSharedPreferences("permission_",MODE_PRIVATE);
//                SharedPreferences.Editor editor=pref.edit();
//
//                editor.putBoolean("permission_variable",true);
//                editor.apply();
//
//                Uri uri = data.getData();
//
//                SharedPreferences pref2= getSharedPreferences("DATA_PATH",MODE_PRIVATE);
//                SharedPreferences.Editor editor2=pref2.edit();
//
//                editor2.putString("PATH",uri.toString());
//
//                Log.d("herehere",uri.toString());
//                editor2.apply();
//
//
//
//                ContentResolver resolver = getContentResolver();
////                Uri uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia/document/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//
//                resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
//                DocumentFile folder = DocumentFile.fromTreeUri(getApplicationContext(), uri);
//
//                // here do sort folder , according to date
//
//                for (DocumentFile file : folder.listFiles()) {
//
//                    String filePath = file.getUri() + File.separator + file.getName();
////                    Log.d("filesss",filePath);
//                    if(file.getName()==".nomedia"){
//
//                    }
//                    else if(file.getName().endsWith(".mp4")){
//                        videoList.add(file);
//
//                    }
//                    else if(file.getName().endsWith(".jpg")){
//
//                        imageList.add(file);
//                    }
//
//                }
//
//                Log.d("whatwhat","workwork");
//
//            }
//            if(!isPermissionGiven()){
//                // show a fragement with button ,which can clicked execute AskPermission
//                showGivePermissionButton();
//            }
//            else {
//                settingAdapter();
//            }
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//    private void showGivePermissionButton() {
//        //                    Log.d("filesss",filePath);
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager2 = findViewById(R.id.viewPager);
//        Objects.requireNonNull(getSupportActionBar()).hide();
//
//        ViewPagerForButtonAdapter ad = new ViewPagerForButtonAdapter(this);
//        viewPager2.setAdapter(ad);
//
//        tabLayout.setTabTextColors(Color.parseColor("#F1F1F1"),Color.parseColor("#FFFFFF"));
//        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(tabName[position]))).attach();
//
//    }
//
//    public void settingAdapter() {
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager2 = findViewById(R.id.viewPager);
//        Objects.requireNonNull(getSupportActionBar()).hide();
//
//        ViewPagerAdapter ad = new ViewPagerAdapter(this);
//        viewPager2.setAdapter(ad);
//
//        tabLayout.setTabTextColors(Color.parseColor("#E4E4E4"),Color.parseColor("#FFFFFF"));
//        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(tabName[position]))).attach();
//
//    }
//
//    public void showSomedemo() {
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        SharedPreferences pref3 = getSharedPreferences("DATA_PATH",MODE_PRIVATE);
//
//        String uriPath =pref3.getString("PATH","");
//
//        ContentResolver resolver = getContentResolver();
//        Uri uri = Uri.parse(uriPath);
//
//        resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
////
//        DocumentFile folder = DocumentFile.fromTreeUri(getApplicationContext(), uri);
//
//        // here do sort folder , according to date
//        imageList.clear();;
//        videoList.clear();
//        for (DocumentFile file : folder.listFiles()) {
//
//            String filePath = file.getUri() + File.separator + file.getName();
////                    Log.d("filesss",filePath);
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
//
//        }
//
//        settingAdapter();
//
//
//    }
//}