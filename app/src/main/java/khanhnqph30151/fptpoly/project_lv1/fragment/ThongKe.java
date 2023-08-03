package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import khanhnqph30151.fptpoly.project_lv1.Adapter.ThongKeViewPagerAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;


public class ThongKe extends Fragment {

    private TabLayout tab;
    private ViewPager2 vp;
    private ThongKeViewPagerAdapter adapter;

    public ThongKe() {
        // Required empty public constructor
    }


    public static ThongKe newInstance() {
        ThongKe fragment = new ThongKe();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab=view.findViewById(R.id.tab);
        vp=view.findViewById(R.id.vp_statis);
        adapter=new ThongKeViewPagerAdapter(this);
        vp.setAdapter(adapter);
        new TabLayoutMediator(tab, vp, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Nhập");
                    break;
                case 1:
                    tab.setText("Xuất");
                    break;
            }
        }).attach();

    }
}