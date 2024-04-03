package com.example.whatsapp2;

import static com.example.whatsapp2.BaseActivity.add_to_saved_in_queue;
import static com.example.whatsapp2.BaseActivity.videoList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FullScreenImageActivity extends AppCompatActivity {

    ViewPager2 viewPager2_;
    static  String fullScreenSendingText="Hi, i am using this awesome app to save status, link:- ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Window window;
        window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the desired color for the notification bar during multi-selection
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int status_color= ContextCompat.getColor(FullScreenImageActivity.this , R.color.full_screen_green_color);
            window.setStatusBarColor(status_color);
        }

        int position= getIntent().getIntExtra("position_in_image_list",0);





        viewPager2_= findViewById(R.id.viewPager_in_fullscreen_image);
        viewPager2_.setAdapter(new full_Screen_Image_Adapter());
        viewPager2_.setCurrentItem(position,false);

      ImageView back_icon_image= findViewById(R.id.back_image);
      back_icon_image.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              onBackPressed();

          }
      });


      ImageView  download_white_icon1= findViewById(R.id.download_white_icon1_image);
        download_white_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        Toast.makeText(getApplicationContext(), "Saved !! Successful ", Toast.LENGTH_SHORT).show();





                        Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
                        DocumentFile sourceFolder = DocumentFile.fromTreeUri(getApplicationContext(), sourceUri);

                        if(sourceFolder.findFile("Downloads")== null)
                        {
                            Log.d("herework","aa");

//            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
//
//                            newFolderDoc = sourceFolder.findFile("Downloads");

                            if(newFolder==null){
                                Log.d("herework","aab");
                            }
                            else{
                                Log.d("herework","aac");
                            }
                        }


//                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);

//                        holder.selection_green_screen.setAlpha(0.0f);





                                DocumentFile each_file= BaseActivity.imageList.get(position);
                                 add_to_saved_in_queue.add(each_file);

                                String mimeType = getApplicationContext().getContentResolver().getType(each_file.getUri());
                                String fileName = each_file.getName();
//                    Log.d("filenamee",fileName);
                                assert sourceFolder.findFile("Downloads") != null;
                                DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);


                                InputStream in = null;
                                try {
                                    in = getApplicationContext().getContentResolver().openInputStream(each_file.getUri());
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }


                                OutputStream out = null;
                                try {
                                    out = getApplicationContext().getContentResolver().openOutputStream(newFile.getUri());
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









                });





        ImageView whatsapp_share_icon_image= findViewById(R.id.whatsapp_share_icon_image);
        whatsapp_share_icon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentFile each_file= BaseActivity.imageList.get(position);
            Intent intent= new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_TEXT,fullScreenSendingText);


            intent.putExtra(Intent.EXTRA_STREAM,each_file.getUri());
            intent.setPackage("com.whatsapp");
            startActivity(intent);



            }
        });









    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//
//    }
}