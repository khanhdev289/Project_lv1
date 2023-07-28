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
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import khanhnqph30151.fptpoly.project_lv1.Adapter.TKTonAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.model.GroupObject;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TKTonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TKTonFragment extends Fragment {
    private TextView tv_total_value_ton;
    private PhieuXkDAO phieuXkDAO;
    private SanPhamDAO sanPhamDAO;
    private ArrayList<SanPham> lstSP;
    private ArrayList<PhieuXuatKho> lstPXK;
    RecyclerView rc_ton;
    TKTonAdapter adapter;


    public TKTonFragment() {
        // Required empty public constructor
    }


    public static TKTonFragment newInstance() {
        TKTonFragment fragment = new TKTonFragment();
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
        tv_total_value_ton = view.findViewById(R.id.tv_total_value_ton);
        phieuXkDAO = new PhieuXkDAO(getContext());
        lstPXK = phieuXkDAO.getAllData();
        sanPhamDAO = new SanPhamDAO(getContext());
        lstSP = sanPhamDAO.getAllData();
        int total = 0;

//        for (SanPham sp : lstSP) {
//            total += sp.getSoLuong_sp();
//        }
        tv_total_value_ton.setText(total+ "");
        rc_ton = view.findViewById(R.id.rc_ton);
        adapter = new TKTonAdapter(lstSP, getContext());
        RecyclerView rc = view.findViewById(R.id.rc_ton);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);
        rc_ton.setAdapter(adapter);


    }
}