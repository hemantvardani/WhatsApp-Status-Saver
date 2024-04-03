package com.example.whatsapp2;
// whatsapp 2

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.savedList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.FullScreenImageActivity.fullScreenSendingText;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;
import static com.example.whatsapp2.custom_created_adapter_video.totalselected_videos;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements LifecycleObserver {

    DrawerLayout drawerLayout;
    NavigationView navigationView;


//    boolean isInfoDialogON=false;
//    boolean isPrivacyPolicyDialogON=false;
//    Dialog dialogInfo;
//    Dialog dialogPrivacyPolicy;
    Toolbar toolbar;
    static boolean  isPermissionGiven_variable=false;
    public static List<DocumentFile> videoList = new ArrayList<>();
    public static List<DocumentFile> savedList = new ArrayList<>();
    public static List<DocumentFile> add_to_saved_in_queue = new ArrayList<>();

    ImageView select_all_icon_btn_images;
    Activity activity;
    Toolbar toolbar1;
    AppBarLayout appBarLayout;
    TextView textview1;
    static DocumentFile newFolderDoc;
    FragmentActivity fg;
    ImageView download_icon_btn_multiple_images;

    ImageView share_whatsapp_icon_btn_images;

    ImageView cancel_icon;
    BlankFragment_main blankFragment_main;
    boolean goneToBackground=false;
    private loadingDialog loadingDialog_object;
    static String SendingTextMultiple="";



    boolean are_all_selected=false;
    Window window;

    public  static  List<DocumentFile> imageList = new ArrayList<>();
    Boolean is_first_time_permission_ask_done=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


         fg=this;
        activity= this;

        window = getWindow();

        drawerLayout= findViewById(R.id.drawer);
        navigationView= findViewById(R.id.navigation_bar);
        toolbar= findViewById(R.id.toolbar);
        toolbar1= findViewById(R.id.toolbar_at_selection);
        appBarLayout=findViewById(R.id.appBar_for_toolbar_image);
        textview1= findViewById(R.id.how_many_selection);
        download_icon_btn_multiple_images= findViewById(R.id.download_white_icon);
        share_whatsapp_icon_btn_images= findViewById(R.id.whatsapp_share_icon_image);
        select_all_icon_btn_images= findViewById(R.id.select_all_icon);
        cancel_icon= findViewById(R.id.cancel_iconn);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        loadingDialog_object= new loadingDialog(this);


        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar1);



        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();









        if (!isPermissionGiven()) {
//            showSomedemo();
                Log.d("pet","petpet");

            Dialog dialog= new Dialog(this);
            dialog.setContentView(R.layout.custom_layout_for_dialog_box);

            //by default if user click at outside of dialog , dialog dismiss
            //to avoid it , add this line
            dialog.setCancelable(false);

            Button btnOkay= dialog.findViewById(R.id.btnOkay);
//            btnOkay.setBackgroundColor(Color.parseColor("#000000"));

            btnOkay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AskPermission();

                    }

                    dialog.dismiss();






                }
            });

            dialog.show();;



            showDialogBoxForPermission();

            Log.d("taggg","6");

        }
        else {

            SharedPreferences pref3 = getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);

            String uriPath = pref3.getString("PATH", "");

            ContentResolver resolver = getContentResolver();
            Uri uri = Uri.parse(uriPath);
        Log.d("taggg",uri.toString());

//            resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
//
            DocumentFile folder = DocumentFile.fromTreeUri(getApplicationContext(), uri);

        Log.d("problemidentify","2");


            loadContent(folder);





        }






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                int id=item.getItemId();

                if(id==R.id.option_1){



                    Intent i= new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT,"Hi, I am using this " + R.string.app_name + "app for saving whatsapp status. Really simple to use. You should try too. Link - "  + R.string.app_link  +" ");
                    startActivity(Intent.createChooser(i,"Share via ? "));





                    Toast.makeText(BaseActivity.this, "Share App", Toast.LENGTH_SHORT).show();
                    Log.d("fileqqqu","hellzo");
                }
                else if (id==R.id.option_2){
//                    loadFragment(new BlankFragmentC());
//                    loadFragment( new BlankFragment_main(this));


                    Toast.makeText(BaseActivity.this, "Privacy policy", Toast.LENGTH_SHORT).show();



                    Dialog dialog= new Dialog(BaseActivity.this);
                    dialog.setContentView(R.layout.privacy_policy_custom_layout_for_dialog_box);

                    //by default if user click at outside of dialog , dialog dismiss
                    //to avoid it , add this line
//                    dialog.setCancelable(false);

                    Button btnOkay= dialog.findViewById(R.id.btnOkay);
//            btnOkay.setBackgroundColor(Color.parseColor("#000000"));


                    dialog.show();;

                    btnOkay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            dialog.dismiss();
                        }
                    });











                }
                else{
                    Toast.makeText(BaseActivity.this, "Report Bug", Toast.LENGTH_SHORT).show();

                    Intent i= new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL,new String[]{String.valueOf(R.string.email_for_reporting_bug)});
                    i.putExtra(Intent.EXTRA_SUBJECT,"I noticed a bug");
                    i.putExtra(Intent.EXTRA_TEXT,"Here write bug, that you observed");
                    try{
                        i.setPackage("com.google.android.gm");
                        startActivity(i);

                    }catch (Exception e)
                    {
                        Toast.makeText(activity, "Gmail not installed", Toast.LENGTH_SHORT).show();
                    }





                }
                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });






        share_whatsapp_icon_btn_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);


                if(ViewPagerAdapter.Global_position==0)
                {
                    sendIntent.setType("image/*");
                    ArrayList<Uri> imageUris = new ArrayList<>();





                    for (int i = 0; i < imageList.size(); i++) {
                        if (itemSelected_images[i])
                            imageUris.add((imageList.get(i)).getUri());

                    }


                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);









                }
                else{
                    sendIntent.setType("video/*");
                    ArrayList<Uri> videoUris = new ArrayList<>();

                    for(int i=0;i<videoList.size();i++){
                        if(itemSelected_video[i])
                        {
                            videoUris.add((videoList.get(i)).getUri());

                        }
                    }
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, videoUris);


                }

                sendIntent.putExtra(Intent.EXTRA_TEXT, SendingTextMultiple);


                sendIntent.setPackage("com.whatsapp");

                startActivity(sendIntent);
            }
        });






        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case  R.id.info_icon:


                         Dialog dialogInfo= new Dialog(BaseActivity.this);
                        dialogInfo.setContentView(R.layout.info_custom_layout_dialog_box);

                        //by default if user click at outside of dialog , dialog dismiss
                        //to avoid it , add this line
//                        dialogInfo.setCancelable(false);

                        Button btnOkay= dialogInfo.findViewById(R.id.btnOkay);
//            btnOkay.setBackgroundColor(Color.parseColor("#000000"));

                        btnOkay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                dialogInfo.dismiss();
                            }
                        }



                        );

                        dialogInfo.show();;
                        return true;

                    case R.id.whatsapp_icon:

//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_MAIN);
//                        intent.setPackage("com.whatsapp");
//                        startActivity(intent);

//                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
//                        if (launchIntent != null) {
//                            startActivity(launchIntent);
//                        } else {
//                            Toast.makeText(activity, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
//                        }
//                        Toast.makeText(activity, "hello", Toast.LENGTH_SHORT).show();
                        PackageManager pm = getPackageManager();
                        try {
                            Intent intent = pm.getLaunchIntentForPackage("com.whatsapp");
                            if (intent != null) {
                                startActivity(intent);
                            } else {
                                Toast.makeText(activity, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(activity, "An error occurred while opening WhatsApp", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    default:
                        return false;
                }
            }
        });






    }












//    private boolean isAppInForeground = false,
    private  boolean justFirstTime=true;
















    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {

        Log.d("rrrr2","f12");

        if(isPermissionGiven())
        {
            if(!justFirstTime)
            {
                Log.d("rrrr","f12");


                try{

                    class_for_loading_while_user_returns task = new class_for_loading_while_user_returns(BaseActivity.this, blankFragment_main,loadingDialog_object);
                    task.execute();
                    loadingDialog_object.show();

                }catch (Exception e)
                {

                    Log.d("rrww","f1");

                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }




            }
            justFirstTime=false;
        }




    }


//

//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    public void onAppBackgrounded() {
//
//        Log.d("rrrr","f2");
//    }









    private void loadContent(DocumentFile folder) {




        Log.d("chachch","1");


                FragmentManager fragmentManager = getSupportFragmentManager();

                loadContentAsyncTask loadContentTask = new loadContentAsyncTask(folder, getApplicationContext(),fragmentManager);
                loadContentTask.execute();

                Log.d("chachch","2");





//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        loadContentAsyncTask loadContentTask = new loadContentAsyncTask(folder, getApplicationContext(),fragmentManager);
//        loadContentTask.execute();





//        imageList.clear();
//        videoList.clear();
//            Log.d("problemidetify","Stifolder.listFiles().length()");
//
//
//                for (DocumentFile file : folder.listFiles()) {
//                    String filePath = file.getUri() + File.separator + file.getName();
//                    Log.d("filesss",filePath);
//                    if (file.getName() == ".nomedia") {
//
//                    } else if (file.getName().endsWith(".mp4")) {
//                        videoList.add(file);
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
//        Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//        DocumentFile sourceFolder = DocumentFile.fromTreeUri(getApplicationContext(), sourceUri);
//
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
//                if(f.getName().endsWith(".mp4")){
//                    Log.d("wetwet","w");
//
//                };
//            }
//
//
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//        imageList.sort(new Comparator<DocumentFile>() {
//            @Override
//            public int compare(DocumentFile file1, DocumentFile file2) {
//                long timestamp1 = file1.lastModified();
//                long timestamp2 = file2.lastModified();
//                return Long.compare(timestamp2, timestamp1); // sort in descending order
//            }
//        });
//
//
//        videoList.sort(new Comparator<DocumentFile>() {
//            @Override
//            public int compare(DocumentFile file1, DocumentFile file2) {
//                long timestamp1 = file1.lastModified();
//                long timestamp2 = file2.lastModified();
//                return Long.compare(timestamp2, timestamp1); // sort in descending order
//            }
//        });
//
//// //  // // sort the saved array
//
//
//
//
//
//        Log.d("filesss","helllzo");
//
//        // // //  // //  put default fragment here
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            Log.d("problemidentify","32");
//
//            blankFragment_main=new BlankFragment_main( BaseActivity.this,fg,toolbar1,appBarLayout,textview1,download_icon_btn_multiple_images,select_all_icon_btn_images,cancel_icon, toolbar,window);
//
//            loadFragment(blankFragment_main  );
//        }
//
//
//        Log.d("problemidentify","3");




    }




    public class loadContentAsyncTask extends AsyncTask<Void, Void, Void> {

        DocumentFile folder;
        BlankFragment_main blankFragment_main;

        private  final WeakReference<Context> contextRef;
        FragmentManager fragmentManager;

        loadContentAsyncTask(DocumentFile folder, Context context,FragmentManager fragmentManager ){
            this.folder=folder;
            this.contextRef= new WeakReference<>(context);
            this.fragmentManager=fragmentManager;


        }

        @Override
        protected Void doInBackground(Void... voids) {

            Context context= contextRef.get();
            imageList.clear();
            videoList.clear();
            Log.d("problemidetify","Stifolder.listFiles().length()");





//            for (DocumentFile file : folder.listFiles()) {
//                String filePath = file.getUri() + File.separator + file.getName();
//                Log.d("filesss",filePath);
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




            Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
            DocumentFile sourceFolder = DocumentFile.fromTreeUri(context, sourceUri);

            if(sourceFolder.findFile("Downloads")!=null)
            {
                DocumentFile file_of_saved= sourceFolder.findFile("Downloads");

                for (DocumentFile file : file_of_saved.listFiles()) {
                    String filePath = file.getUri() + File.separator + file.getName();
                    Log.d("filesss",filePath);

                    savedList.add(file);


                }



            }












//            imageList.sort(new Comparator<DocumentFile>() {
//                @Override
//                public int compare(DocumentFile file1, DocumentFile file2) {
//                    long timestamp1 = file1.lastModified();
//                    long timestamp2 = file2.lastModified();
//                    return Long.compare(timestamp2, timestamp1); // sort in descending order
//                }
//            });
//
//
//            videoList.sort(new Comparator<DocumentFile>() {
//                @Override
//                public int compare(DocumentFile file1, DocumentFile file2) {
//                    long timestamp1 = file1.lastModified();
//                    long timestamp2 = file2.lastModified();
//                    return Long.compare(timestamp2, timestamp1); // sort in descending order
//                }
//            });

// //  // // sort the saved array





            Log.d("filesss","helllzo");


            return null;
        }


        @Override
        protected void onPostExecute(Void unused) {

            Context context= contextRef.get();

            Log.d("filesss","helllzo");

            // // //  // //  put default fragment here
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.d("problemidentify","32");

                blankFragment_main=new BlankFragment_main( context,fg,toolbar1,appBarLayout,textview1,download_icon_btn_multiple_images,select_all_icon_btn_images,cancel_icon, toolbar,window);

                loadFragment(blankFragment_main  );
            }


            Log.d("problemidentify","3");

        }


        private void loadFragment(Fragment fragment){
//        FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fragmentManager.beginTransaction();
            ft.add(R.id.container, fragment);
            ft.commit();
        }

    }









    private void showDialogBoxForPermission() {
                Boolean shouldPermissionBeGranted=true;

                // // //  // //   code for custom dialog

                if(shouldPermissionBeGranted){

                }
                else{

                }

    }


    public Boolean isPermissionGiven() {
        // // //  // //  // //  // //  check in shared pref whether permissionGiven is true or false




        File permissionFile = new File(getFilesDir(), "permission.txt");

        if (permissionFile.exists()) {
            // The file exists, so the permission has already been granted
            return true;
        } else {
            // The file doesn't exist, so this is a fresh install or a reinstall
            // Prompt the user to grant the permission

            // Create the file to store the permission status


            return false;
        }



    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AskPermission() {
//        tellHowUseThisFolderLooks();
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        Uri wa_status_uri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia/document/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, wa_status_uri);
//android.content.extra.SHOW_ADVANCED
        Log.d("taggg","1");
        startActivityForResult(intent, 10001);
        Log.d("taggg","1.5");

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("taggg","7");
            if (requestCode == 10001 && resultCode == Activity.RESULT_OK) {

                Log.d("taggg","2");
                // here, set permissionGiven in shared prefs
                SharedPreferences pref = getSharedPreferences("permission_", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putBoolean("permission_variable", true);
                editor.apply();

//                isPermissionGiven_variable=true;








                File permissionFile = new File(getFilesDir(), "permission.txt");
                try {
                    permissionFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Store the permission status in the file



                try {
                    FileWriter fileWriter = new FileWriter(permissionFile);
                    fileWriter.write("granted");
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }









                Uri uri = data.getData();
                Log.d("taggg","3");
                SharedPreferences pref2 = getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = pref2.edit();

                editor2.putString("PATH", uri.toString());

                Log.d("herehere", uri.toString());
                editor2.apply();


                ContentResolver resolver = getContentResolver();

                resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
                DocumentFile folder = DocumentFile.fromTreeUri(this, uri);

//                DocumentFile documentFile = DocumentFile.fromTreeUri(this, uri);
//                DocumentFile fol = folder.createDirectory("newFolder");
//               Log.d("tagtag", (fol.getUri()).toString());



                Log.d("taggg","4");

                loadContent(folder);


                // here do sort folder , according to date




//                Intent intent = BaseActivity.this.getPackageManager().getLaunchIntentForPackage(BaseActivity.this.getPackageName());
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                startActivity(intent);



            }
            else{
                Toast.makeText(activity, "You have to give permission to view statuses.", Toast.LENGTH_SHORT).show();
            }
//            if (!isPermissionGiven()) {
//                // show a fragment with button ,which can clicked execute AskPermission
//                showGivePermissionButton();
//            } else {
//                settingAdapter();
//            }
        }
        catch (Exception ex) {

        }

    }







//    private void loadFragment(Fragment fragment){
//        FragmentManager fm= getSupportFragmentManager();
//        FragmentTransaction ft= fm.beginTransaction();
//        ft.add(R.id.container, fragment);
//        ft.commit();
//    }






    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            {
                super.onBackPressed();
            }

    }

}






//        download_icon_btn_multiple_images.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view1){
//
//
//                appBarLayout.setVisibility(View.GONE);
//                Toast.makeText(activity, "Saved !! Successful ", Toast.LENGTH_SHORT).show();
//
//                int color = ContextCompat.getColor(BaseActivity.this, R.color.whatsapp_green);
//                tabLayout.setBackgroundColor(color);
//
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    // Set the desired color for the notification bar during multi-selection
//                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                    int status_color=ContextCompat.getColor(BaseActivity.this, R.color.whatsapp_green);
//                    window.setStatusBarColor(status_color);
//                }
//
//
//
//                if(ViewPagerAdapter.Global_position==0)
//                {
//                    for(int i=0;i<imageList.size();i++){
//
//                        if(itemSelected_images[i] ) {
//                            custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);
//
//                            holder.selection_green_screen_image.setAlpha(0.0f);
//
//
//                        }
//
//                    }
//
//
//
//
//
//                    new Handler().postDelayed(new Runnable() {
//                                                  @Override
//                                                  public void run() {
//
//                                                      Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//                                                      DocumentFile sourceFolder = DocumentFile.fromTreeUri(getApplicationContext(), sourceUri);
//
//                                                      if(sourceFolder.findFile("Downloads")== null)
//                                                      {
//                                                          Log.d("herework","aa");
//
////            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                                                          DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
////
//                                                          newFolderDoc = sourceFolder.findFile("Downloads");
//
//                                                          if(newFolder==null){
//                                                              Log.d("herework","aab");
//                                                          }
//                                                          else{
//                                                              Log.d("herework","aac");
//                                                          }
//                                                      }
//
//                                                      for(int i=0;i<imageList.size();i++){
//                                                          if(itemSelected_images[i] ) {
////                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);
//
////                        holder.selection_green_screen.setAlpha(0.0f);
//                                                              totalselected_images--;
//                                                              itemSelected_images[i]=!itemSelected_images[i];
//
//
//                                                              DocumentFile each_file= BaseActivity.imageList.get(i);
//
//                                                              String mimeType = getApplicationContext().getContentResolver().getType(each_file.getUri());
//                                                              String fileName = each_file.getName();
////                    Log.d("filenamee",fileName);
//                                                              assert sourceFolder.findFile("Downloads") != null;
//                                                              DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);
//
//
//                                                              InputStream in = null;
//                                                              try {
//                                                                  in = getApplicationContext().getContentResolver().openInputStream(each_file.getUri());
//                                                              } catch (FileNotFoundException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//
//                                                              OutputStream out = null;
//                                                              try {
//                                                                  out = getApplicationContext().getContentResolver().openOutputStream(newFile.getUri());
//                                                              } catch (FileNotFoundException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//                                                              byte[] buffer = new byte[1024];
//                                                              int read;
//                                                              while (true) {
//                                                                  try {
//                                                                      if (!((read = in.read(buffer)) != -1)) break;
//                                                                  } catch (IOException e) {
//                                                                      throw new RuntimeException(e);
//                                                                  }
//                                                                  try {
//                                                                      out.write(buffer, 0, read);
//                                                                  } catch (IOException e) {
//                                                                      throw new RuntimeException(e);
//                                                                  }
//                                                              }
//
//                                                              try {
//                                                                  in.close();
//                                                              } catch (IOException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//                                                              try {
//                                                                  out.close();
//                                                              } catch (IOException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//
//
//                                                          }
//                                                      }
//
//                                                  }
//                                              }, 1000
//                    );
//                }
//                else{
//
//                    for(int i=0;i<videoList.size();i++){
//
//                        if(itemSelected_video[i] ) {
//                            custom_created_adapter_video.ViewHolder holder= (custom_created_adapter_video.ViewHolder) BlankFragmentB.recyclerView1_video.findViewHolderForAdapterPosition(i);
//
//                            holder.selection_green_screen_video.setAlpha(0.0f);
//
//
//                        }
//
//                    }
//
//
//
//
//
//                    new Handler().postDelayed(new Runnable() {
//                                                  @Override
//                                                  public void run() {
//
//                                                      Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//                                                      DocumentFile sourceFolder = DocumentFile.fromTreeUri(getApplicationContext(), sourceUri);
//
//                                                      if(sourceFolder.findFile("Downloads")== null)
//                                                      {
//                                                          Log.d("herework","aa");
//
////            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                                                          DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
////
//                                                          newFolderDoc = sourceFolder.findFile("Downloads");
//
//                                                          if(newFolder==null){
//                                                              Log.d("herework","aab");
//                                                          }
//                                                          else{
//                                                              Log.d("herework","aac");
//                                                          }
//                                                      }
//
//                                                      for(int i=0;i<videoList.size();i++){
//                                                          if(itemSelected_video[i] ) {
////                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);
//
////                        holder.selection_green_screen.setAlpha(0.0f);
//                                                              totalselected_videos--;
//                                                              itemSelected_video[i]=!itemSelected_video[i];
//
//
//                                                              DocumentFile each_file= BaseActivity.videoList.get(i);
//
//                                                              String mimeType = getApplicationContext().getContentResolver().getType(each_file.getUri());
//                                                              String fileName = each_file.getName();
////                    Log.d("filenamee",fileName);
//                                                              assert sourceFolder.findFile("Downloads") != null;
//                                                              DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);
//
//
//                                                              InputStream in = null;
//                                                              try {
//                                                                  in = getApplicationContext().getContentResolver().openInputStream(each_file.getUri());
//                                                              } catch (FileNotFoundException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//
//                                                              OutputStream out = null;
//                                                              try {
//                                                                  out = getApplicationContext().getContentResolver().openOutputStream(newFile.getUri());
//                                                              } catch (FileNotFoundException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//                                                              byte[] buffer = new byte[1024];
//                                                              int read;
//                                                              while (true) {
//                                                                  try {
//                                                                      if (!((read = in.read(buffer)) != -1)) break;
//                                                                  } catch (IOException e) {
//                                                                      throw new RuntimeException(e);
//                                                                  }
//                                                                  try {
//                                                                      out.write(buffer, 0, read);
//                                                                  } catch (IOException e) {
//                                                                      throw new RuntimeException(e);
//                                                                  }
//                                                              }
//
//                                                              try {
//                                                                  in.close();
//                                                              } catch (IOException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//                                                              try {
//                                                                  out.close();
//                                                              } catch (IOException e) {
//                                                                  throw new RuntimeException(e);
//                                                              }
//
//
//
//                                                          }
//                                                      }
//
//                                                  }
//                                              }, 1000
//                    );
//                }
//
//
//
//
//
//            }
//
//
//        });


