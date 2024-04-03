package com.example.whatsapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.Objects;















//
//public class variousFunction extends  MainActivity {
//
//
////    Context context;
////    variousFunction(Context context){
////        this.context=context;
////    }
//
//
//    public Boolean isPermissionGiven() {
//        // check in shared pref whether permissionGiven is true or false
//        SharedPreferences pref = super.getSharedPreferences("permission_",MODE_PRIVATE);
////
////        Boolean permissionGiven =pref.getBoolean("permission_variable",false);
//        return true;
////        return permissionGiven;
//
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public  void AskPermission() {
//        tellHowUseThisFolderLooks();
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//
//
//        Uri wa_status_uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia/document/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, wa_status_uri);
////        context.startActivityForResult(intent, 10001);
//        super.startActivityForResult(intent, 10001);
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
//
//
//                Uri uri = data.getData();
//                DocumentFile folder = DocumentFile.fromTreeUri(super.getApplicationContext(), uri);
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
////                showGivePermissionButton();
//            }
//            else {
////                settingAdapter();
//            }
//        } catch (Exception ex) {
//
//        }
//
//    }
//
//    private void showGivePermissionButton() {
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager2 = findViewById(R.id.viewPager);
//        Objects.requireNonNull(getSupportActionBar()).hide();
//
//        ViewPagerForButtonAdapter ad = new ViewPagerForButtonAdapter(super.fa);
//        viewPager2.setAdapter(ad);
//
//
//        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(tabName[position]))).attach();
//
//    }
//
//    public void settingAdapter() {
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager2 = findViewById(R.id.viewPager);
//        Objects.requireNonNull(getSupportActionBar()).hide();
//
//        ViewPagerAdapter ad = new ViewPagerAdapter(super.fa);
//        viewPager2.setAdapter(ad);
//
//
//        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(tabName[position]))).attach();
//
//    }
//
//    public void showSomedemo() {
//    }
//
//}
