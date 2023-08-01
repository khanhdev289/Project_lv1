package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import khanhnqph30151.fptpoly.project_lv1.Adapter.ThongKeAdapter;
import khanhnqph30151.fptpoly.project_lv1.Adapter.ThongKeViewPagerAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.ThongKeDAO;
import khanhnqph30151.fptpoly.project_lv1.model.TonKho;
import khanhnqph30151.fptpoly.project_lv1.model.XuatKho;


public class ThongKe extends Fragment {

    private TabLayout tabLayoutThongke;
    private ViewPager2 vpThongke;
    private ThongKeViewPagerAdapter viewPagerAdapter;

    private ArrayList<XuatKho> list_xuat;
    private ArrayList<TonKho> list_ton;
    private ThongKeDAO dao;
    ThongKeAdapter adapter;
    RecyclerView recy;

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
        Spinner sp_month = view.findViewById(R.id.sp_month);
        recy = view.findViewById(R.id.recy_statis);
        dao = new ThongKeDAO(getContext());
        String y = "2023";
        RecyclerView rc = view.findViewById(R.id.recy_statis);
        adapter = new ThongKeAdapter(list_xuat, list_ton, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);
        sp_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String m;
                if(i<9){
                  m="0"+""+(i+1);
                }else{
                m=""+(i+1);
                }

                list_xuat = dao.XuatKho(y, m);
                list_ton = dao.TonKho(y, m);
                adapter.setData(list_xuat,list_ton);
                recy.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}