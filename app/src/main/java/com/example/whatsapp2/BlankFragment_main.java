package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.add_to_saved_in_queue;
import static com.example.whatsapp2.BaseActivity.imageList;
import static com.example.whatsapp2.BaseActivity.videoList;
import static com.example.whatsapp2.custom_created_adapter_image.itemSelected_images;
import static com.example.whatsapp2.custom_created_adapter_image.totalselected_images;
import static com.example.whatsapp2.custom_created_adapter_video.itemSelected_video;
import static com.example.whatsapp2.custom_created_adapter_video.totalselected_videos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class BlankFragment_main extends Fragment{

    TabLayout tabLayout;
    static boolean isAppJustStarted=true;
    ViewPager2 viewPager2;
    String[] tabName = {" Images ", " Videos ", "Downloads"};
    FragmentActivity fg;
    Activity activity;
    Toolbar toolbar1;
    AppBarLayout appBarLayout;
    TextView textview1;
    ImageView download_icon_btn_multiple_images;
    ImageView select_all_icon_btn_images;
        ImageView cancel_cross_icon;
        static  int previousPage=0;
        Context context;
    ViewPagerAdapter ad;
    Toolbar toolbar;
    Window window;



    @RequiresApi(api = Build.VERSION_CODES.Q)
    public BlankFragment_main(Context context, FragmentActivity fg, Toolbar toolbar1, AppBarLayout appBarLayout, TextView textview1, ImageView download_icon_btn_multiple_images, ImageView select_all_icon_btn_images, ImageView cancel_cross_icon, Toolbar toolbar , Window window ) {
       this.fg= fg;
       this.textview1= textview1;
       this.toolbar1= toolbar1;
       this.appBarLayout= appBarLayout;
       this.download_icon_btn_multiple_images=download_icon_btn_multiple_images;
       this.select_all_icon_btn_images= select_all_icon_btn_images;
       this.cancel_cross_icon= cancel_cross_icon;
       this.context=context;
       this.toolbar=toolbar;
       this.window =window;



//         code of shared preference to know whether user earlier gave permission or not , here set permissionGiven


//        settingAdapter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_blank_main, container, false);

        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager2 = v.findViewById(R.id.viewPager);
        viewPager2.setOffscreenPageLimit(1);





        download_icon_btn_multiple_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1){


                appBarLayout.setVisibility(View.GONE);
                Toast.makeText(fg, "Saved !! Successful ", Toast.LENGTH_SHORT).show();

                int color = ContextCompat.getColor(context, R.color.whatsapp_green);
                tabLayout.setBackgroundColor(color);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Set the desired color for the notification bar during multi-selection
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    int status_color=ContextCompat.getColor(context, R.color.whatsapp_green);
                    window.setStatusBarColor(status_color);
                }



                if(ViewPagerAdapter.Global_position==0)
                {
                    for(int i=0;i<imageList.size();i++){

                        if(itemSelected_images[i] ) {
                            custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);

                            holder.selection_green_screen_image.setAlpha(0.0f);


                        }

                    }





                    new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {

                                                      Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
                                                      DocumentFile sourceFolder = DocumentFile.fromTreeUri(context, sourceUri);

                                                      if(sourceFolder.findFile("Downloads")== null)
                                                      {
                                                          Log.d("herework","aa");

//            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                                          DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
//
                                                          BaseActivity.newFolderDoc = sourceFolder.findFile("Downloads");

                                                          if(newFolder==null){
                                                              Log.d("herework","aab");
                                                          }
                                                          else{
                                                              Log.d("herework","aac");
                                                          }
                                                      }

                                                      for(int i=0;i<imageList.size();i++){
                                                          if(itemSelected_images[i] ) {
//                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);

//                        holder.selection_green_screen.setAlpha(0.0f);


                                                              add_to_saved_in_queue.add(imageList.get(i));
                                                              totalselected_images--;
                                                              itemSelected_images[i]=!itemSelected_images[i];


                                                              DocumentFile each_file= imageList.get(i);

                                                              String mimeType = context.getContentResolver().getType(each_file.getUri());
                                                              String fileName = each_file.getName();
//                    Log.d("filenamee",fileName);
                                                              assert sourceFolder.findFile("Downloads") != null;
                                                              DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);


                                                              InputStream in = null;
                                                              try {
                                                                  in = context.getContentResolver().openInputStream(each_file.getUri());
                                                              } catch (FileNotFoundException e) {
                                                                  throw new RuntimeException(e);
                                                              }


                                                              OutputStream out = null;
                                                              try {
                                                                  out = context.getContentResolver().openOutputStream(newFile.getUri());
                                                              } catch (FileNotFoundException e) {
                                                                  throw new RuntimeException(e);
                                                              }

                                                              byte[] buffer = new byte[1024];
                                                              int read;
                                                              while (true) {
                                                                  try {
                                                                      if (!((read = in.read(buffer)) != -1)) break;
                                                                  } catch (IOException e) {
                                                                      throw new RuntimeException(e);
                                                                  }
                                                                  try {
                                                                      out.write(buffer, 0, read);
                                                                  } catch (IOException e) {
                                                                      throw new RuntimeException(e);
                                                                  }
                                                              }

                                                              try {
                                                                  in.close();
                                                              } catch (IOException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              try {
                                                                  out.close();
                                                              } catch (IOException e) {
                                                                  throw new RuntimeException(e);
                                                              }



                                                          }
                                                      }

                                                  }
                                              }, 1000
                    );
                }
                else{

                    for(int i=0;i<videoList.size();i++){

                        if(itemSelected_video[i] ) {
                            custom_created_adapter_video.ViewHolder holder= (custom_created_adapter_video.ViewHolder) BlankFragmentB.recyclerView1_video.findViewHolderForAdapterPosition(i);

                            holder.selection_green_screen_video.setAlpha(0.0f);


                        }

                    }





                    new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {

                                                      Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
                                                      DocumentFile sourceFolder = DocumentFile.fromTreeUri(context, sourceUri);

                                                      if(sourceFolder.findFile("Downloads")== null)
                                                      {
                                                          Log.d("herework","aa");

//            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                                          DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
//
                                                          BaseActivity.newFolderDoc = sourceFolder.findFile("Downloads");

                                                          if(newFolder==null){
                                                              Log.d("herework","aab");
                                                          }
                                                          else{
                                                              Log.d("herework","aac");
                                                          }
                                                      }

                                                      for(int i=0;i<videoList.size();i++){
                                                          if(itemSelected_video[i] ) {
//                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);

//                        holder.selection_green_screen.setAlpha(0.0f);
                                                              add_to_saved_in_queue.add(videoList.get(i));
                                                              totalselected_videos--;
                                                              itemSelected_video[i]=!itemSelected_video[i];


                                                              DocumentFile each_file= videoList.get(i);

                                                              String mimeType = context.getContentResolver().getType(each_file.getUri());
                                                              String fileName = each_file.getName();
//                    Log.d("filenamee",fileName);
                                                              assert sourceFolder.findFile("Downloads") != null;
                                                              DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);


                                                              InputStream in = null;
                                                              try {
                                                                  in = context.getContentResolver().openInputStream(each_file.getUri());
                                                              } catch (FileNotFoundException e) {
                                                                  throw new RuntimeException(e);
                                                              }


                                                              OutputStream out = null;
                                                              try {
                                                                  out = context.getContentResolver().openOutputStream(newFile.getUri());
                                                              } catch (FileNotFoundException e) {
                                                                  throw new RuntimeException(e);
                                                              }

                                                              byte[] buffer = new byte[1024];
                                                              int read;
                                                              while (true) {
                                                                  try {
                                                                      if (!((read = in.read(buffer)) != -1)) break;
                                                                  } catch (IOException e) {
                                                                      throw new RuntimeException(e);
                                                                  }
                                                                  try {
                                                                      out.write(buffer, 0, read);
                                                                  } catch (IOException e) {
                                                                      throw new RuntimeException(e);
                                                                  }
                                                              }

                                                              try {
                                                                  in.close();
                                                              } catch (IOException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              try {
                                                                  out.close();
                                                              } catch (IOException e) {
                                                                  throw new RuntimeException(e);
                                                              }



                                                          }
                                                      }

                                                  }
                                              }, 1000
                    );
                }





            }


        });










        settingAdapter();
        return  v;
    }






    public void tellHowUseThisFolderLooks() {
    }




    public void settingAdapter() {



        ad = new ViewPagerAdapter(context,fg,toolbar1,appBarLayout,textview1,download_icon_btn_multiple_images ,select_all_icon_btn_images,cancel_cross_icon,viewPager2, toolbar, tabLayout,window);
        viewPager2.setAdapter(ad);


        tabLayout.setTabTextColors(Color.parseColor("#E4E4E4"), Color.parseColor("#FFFFFF"));
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(tabName[position]))).attach();







    }



//    @Override
//    public void onResume() {
//        super.onResume();
//        if(!isAppJustStarted)
//        {
//        ad.updateOnResume();
//
//        }
//        isAppJustStarted=false;
//
//
//
//    }




    public void showSomedemo() {
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        SharedPreferences pref3 = this.requireActivity().getSharedPreferences("DATA_PATH", Context.MODE_PRIVATE);
//
//        String uriPath = pref3.getString("PATH", "");
//
//        ContentResolver resolver = requireActivity().getContentResolver();
//        Uri uri = Uri.parse(uriPath);
//
//        resolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION);
////
////
//        DocumentFile folder = DocumentFile.fromTreeUri(getActivity(), uri);
//
//        // here do sort folder , according to date
//        BaseActivity.imageList.clear();
//        ;
//        BaseActivity.videoList.clear();
//        for (DocumentFile file : folder.listFiles()) {
//
//            String filePath = file.getUri() + File.separator + file.getName();
////                    Log.d("filesss",filePath);
//            if (file.getName() == ".nomedia") {
//
//            } else if (file.getName().endsWith(".mp4")) {
//                BaseActivity.videoList.add(file);
//
//            } else if (file.getName().endsWith(".jpg")) {
//
//                BaseActivity.imageList.add(file);
//            }
//
//
//        }
//
//        settingAdapter();
//
//    }



}