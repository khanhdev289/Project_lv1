package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.Adapter.TKXuatAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.ThongKeDAO;
import khanhnqph30151.fptpoly.project_lv1.model.NhapKho;
import khanhnqph30151.fptpoly.project_lv1.model.XuatKho;

public class TKXuatFragment extends Fragment {
    private ArrayList<XuatKho> list_xuat;
    private ArrayList<NhapKho> list_ton;
    private ThongKeDAO dao;
    TKXuatAdapter adapter;
    RecyclerView recy;

    public TKXuatFragment() {
        // Required empty public constructor
    }


    public static TKXuatFragment newInstance() {
        TKXuatFragment fragment = new TKXuatFragment();
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
        return inflater.inflate(R.layout.fragment_t_k_ton, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner sp_month = view.findViewById(R.id.sp_month);
        recy = view.findViewById(R.id.recy_statis);
        dao = new ThongKeDAO(getContext());
        RecyclerView rc = view.findViewById(R.id.recy_statis);
        adapter = new TKXuatAdapter(list_xuat, list_ton, getContext());

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
                list_xuat = dao.XuatKho( m);
                list_ton = dao.TonKho( m);
                adapter.setData(list_xuat,list_ton);
                recy.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}