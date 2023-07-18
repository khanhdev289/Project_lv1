package khanhnqph30151.fptpoly.project_lv1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import khanhnqph30151.fptpoly.project_lv1.fragment.TKTonFragment;
import khanhnqph30151.fptpoly.project_lv1.fragment.TKXuatFragment;

public class ThongKeViewPagerAdapter extends FragmentStateAdapter {
    public ThongKeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TKXuatFragment();
            default: return new TKTonFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
