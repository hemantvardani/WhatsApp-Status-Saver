package com.example.whatsapp2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerForButtonAdapter extends FragmentStateAdapter {
    public ViewPagerForButtonAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerForButtonAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerForButtonAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return new BlankFragmentC();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
