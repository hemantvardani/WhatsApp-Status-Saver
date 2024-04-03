


package com.example.whatsapp2;

        import static com.example.whatsapp2.FullScreenImageActivity.fullScreenSendingText;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.ContextCompat;
        import androidx.documentfile.provider.DocumentFile;
        import androidx.viewpager2.widget.ViewPager2;

        import android.content.Intent;
        import android.graphics.Color;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.ImageView;
        import android.widget.Toast;
        import android.widget.VideoView;

        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.lang.reflect.Field;

public class FullScreenSavedActivity extends AppCompatActivity {

    ViewPager2 viewPager2_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_saved);

        Window window;
        window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the desired color for the notification bar during multi-selection
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int status_color= ContextCompat.getColor(FullScreenSavedActivity.this , R.color.full_screen_green_color);
            window.setStatusBarColor(status_color);
        }



        int position= getIntent().getIntExtra("position_in_saved_list",0);

        viewPager2_= findViewById(R.id.viewPager_in_fullscreen_saved);
        viewPager2_.setAdapter(new full_Screen_Saved_Adapter());
        viewPager2_.setCurrentItem(position,false);
//        viewPager2_.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);


        ImageView back_icon_video= findViewById(R.id.back_video);
        back_icon_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        DocumentFile filee= BaseActivity.savedList.get(position);

        if(  filee.getName().endsWith(".mp4")) {


            viewPager2_.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);


                    Log.d("tantan", String.valueOf(position));
                    View view = viewPager2_.getChildAt(0);
                    VideoView videoView = view.findViewById(R.id.fullScreenVideoInLayout);

//


                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // Start playing the video
//                        videoView.start();
                            if (!videoView.isPlaying()) {

                                videoView.start();
                            }


                        }
                    });
                    // Set a listener for when the view is destroyed
                    videoView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View view) {
                            // VideoView is attached to the view hierarchy, do nothing
                            if (!videoView.isPlaying()) {

                                videoView.start();
                            }

                        }

                        @Override
                        public void onViewDetachedFromWindow(View view) {
                            // VideoView is detached from the view hierarchy, release the video resources
                            videoView.stopPlayback();
                            videoView.suspend();
                        }


                    });


                }
            });
        }








        ImageView whatsapp_share_icon_video= findViewById(R.id.whatsapp_share_icon_video);
        whatsapp_share_icon_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentFile each_file= BaseActivity.videoList.get(position);
                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setType("video/*");
                intent.putExtra(Intent.EXTRA_STREAM,each_file.getUri());
                intent.putExtra(Intent.EXTRA_TEXT,fullScreenSendingText);

                intent.setPackage("com.whatsapp");
                startActivity(intent);



            }
        });





    }


}



//        ImageView  download_white_icon1_video= findViewById(R.id.download_white_icon1_video);
//        download_white_icon1_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Toast.makeText(getApplicationContext(), "Saved !! Successful ", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//                Uri sourceUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses");
//                DocumentFile sourceFolder = DocumentFile.fromTreeUri(getApplicationContext(), sourceUri);
//
//                if(sourceFolder.findFile("Downloads")== null)
//                {
//                    Log.d("herework","aa");
//
////            this.getContentResolver().takePersistableUriPermission(sourceUri, Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                    DocumentFile newFolder = sourceFolder.createDirectory("Downloads");
////
////                            newFolderDoc = sourceFolder.findFile("Downloads");
//
//                    if(newFolder==null){
//                        Log.d("herework","aab");
//                    }
//                    else{
//                        Log.d("herework","aac");
//                    }
//                }
//
//
////                        custom_created_adapter_image.ViewHolder holder= (custom_created_adapter_image.ViewHolder) BlankFragmentA.recyclerView1_image.findViewHolderForAdapterPosition(i);
//
////                        holder.selection_green_screen.setAlpha(0.0f);
//
//
//
//
//
//                DocumentFile each_file= BaseActivity.videoList.get(position);
//
//                String mimeType = getApplicationContext().getContentResolver().getType(each_file.getUri());
//                String fileName = each_file.getName();
////                    Log.d("filenamee",fileName);
//                assert sourceFolder.findFile("Downloads") != null;
//                DocumentFile newFile = sourceFolder.findFile("Downloads").createFile(mimeType, fileName);
//
//
//                InputStream in = null;
//                try {
//                    in = getApplicationContext().getContentResolver().openInputStream(each_file.getUri());
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                OutputStream out = null;
//                try {
//                    out = getApplicationContext().getContentResolver().openOutputStream(newFile.getUri());
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//
//                byte[] buffer = new byte[1024];
//                int read;
//                while (true) {
//                    try {
//                        if (!((read = in.read(buffer)) != -1)) break;
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    try {
//                        out.write(buffer, 0, read);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//
//            }
//
//
//
//
//
//
//
//
//
//        });