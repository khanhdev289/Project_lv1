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

import khanhnqph30151.fptpoly.project_lv1.adapter.QuanLyThanhVienView_Adapter;
import khanhnqph30151.fptpoly.project_lv1.R;

public class QuanLyThanhVien extends Fragment {

    private TabLayout tabLayoutthanhvien;
    private ViewPager2 vpthanhVien;
    private QuanLyThanhVienView_Adapter quanLyThanhVienView_adapter;



    public QuanLyThanhVien() {
        // Required empty public constructor
    }


    public static QuanLyThanhVien newInstance() {
        QuanLyThanhVien fragment = new QuanLyThanhVien();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutthanhvien = view.findViewById(R.id.tab_layout_thanhvien);
        vpthanhVien = view.findViewById(R.id.vp_thanhvien);
        quanLyThanhVienView_adapter = new QuanLyThanhVienView_Adapter(getActivity());
        vpthanhVien.setAdapter(quanLyThanhVienView_adapter);
        new TabLayoutMediator(tabLayoutthanhvien, vpthanhVien, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Danh sách");
                    break;
                case 1:
                    tab.setText("Đăng Ký");
                    break;
            }
        })).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
    }
}