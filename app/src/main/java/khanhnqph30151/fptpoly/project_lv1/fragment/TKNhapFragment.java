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

import khanhnqph30151.fptpoly.project_lv1.Adapter.TKNhapAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.ThongKeDAO;
import khanhnqph30151.fptpoly.project_lv1.model.NhapKho;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TKNhapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TKNhapFragment extends Fragment {
    private ArrayList<NhapKho> list_nhap;

    private ThongKeDAO dao;
    TKNhapAdapter adapter;
    RecyclerView recy;
    public TKNhapFragment() {
        // Required empty public constructor
    }

    public static TKNhapFragment newInstance() {
        TKNhapFragment fragment = new TKNhapFragment();


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
        return inflater.inflate(R.layout.fragment_t_k_xuat, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner sp_month = view.findViewById(R.id.sp_month);
        recy = view.findViewById(R.id.recy_statis);
        dao = new ThongKeDAO(getContext());
        RecyclerView rc = view.findViewById(R.id.recy_statis);
        adapter = new TKNhapAdapter(list_nhap,getContext());

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
                list_nhap = dao.TonKho( m);
                adapter.setData(list_nhap);
                recy.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}