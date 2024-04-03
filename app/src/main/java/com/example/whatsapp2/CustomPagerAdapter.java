//package com.example.whatsapp2;
//
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.PagerAdapter;
//
//public class CustomPagerAdapter extends PagerAdapter {
//
//
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        View view = (View) object;
//        RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) view.getTag();
//
//        // Stop and suspend any videos that may be playing in the view
//        if (holder != null && holder.video_in_layout != null) {
//            holder.video_in_layout.stopPlayback();
//            holder.video_in_layout.suspend();
//        }
//
//        container.removeView(view);
//    }
//
//
//}
