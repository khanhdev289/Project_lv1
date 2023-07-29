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
import khanhnqph30151.fptpoly.project_lv1.Adapter.TKTonEAdapter;
import khanhnqph30151.fptpoly.project_lv1.Adapter.TKXuatEAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuNkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.model.GroupObject;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuNhapKho;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TKTonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TKTonFragment extends Fragment {
    private ExpandableListView elvTon;
    private List<GroupObject> listGroup;
    private LinkedHashMap<GroupObject, List<PhieuNhapKho>> listItem;
    private TKTonEAdapter adapter;
    private TextView tvTotal;
    private PhieuNkDAO dao;
    private PhieuXkDAO phieuXkDAO;
    private final List<PhieuNhapKho> listKhoanThuT1 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT2 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT3 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT4 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT5 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT6 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT7 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT8 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT9 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT10 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT11 = new ArrayList<>();
    private final List<PhieuNhapKho> listKhoanThuT12 = new ArrayList<>();
    Calendar cal = Calendar.getInstance();


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
        tvTotal = view.findViewById(R.id.tv_total_value_ton);
        elvTon = view.findViewById(R.id.elv_tonKho);
        dao = new PhieuNkDAO(getContext());
        phieuXkDAO=new PhieuXkDAO(getContext());
        listItem = getListItem();
        listGroup = new ArrayList<>(listItem.keySet());
        adapter = new TKTonEAdapter(listGroup, listItem,getContext());
        elvTon.setAdapter(adapter);
        for (int i = 0; i < 10; i++) elvTon.expandGroup(i);


    }
    private LinkedHashMap<GroupObject, List<PhieuNhapKho>> getListItem() {
        int total = 0;int total1=0;
        LinkedHashMap<GroupObject, List<PhieuNhapKho>> listMap = new LinkedHashMap<>();
        List<PhieuNhapKho> list = dao.getAllData();
        ArrayList<PhieuXuatKho> list1=phieuXkDAO.getAllData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (PhieuNhapKho pnk : list) {
            total += pnk.getSoluong();

            try {
                cal.setTime(dateFormat.parse(pnk.getNgayNhap()));
                switch (cal.get(Calendar.MONTH) + 1) {
                    case 1:
                        listKhoanThuT1.add(pnk);
                        break;
                    case 2:
                        listKhoanThuT2.add(pnk);
                        break;
                    case 3:
                        listKhoanThuT3.add(pnk);
                        break;
                    case 4:
                        listKhoanThuT4.add(pnk);
                        break;
                    case 5:
                        listKhoanThuT5.add(pnk);
                        break;
                    case 6:
                        listKhoanThuT6.add(pnk);
                        break;
                    case 7:
                        listKhoanThuT7.add(pnk);
                        break;
                    case 8:
                        listKhoanThuT8.add(pnk);
                        break;
                    case 9:
                        listKhoanThuT9.add(pnk);
                        break;
                    case 10:
                        listKhoanThuT10.add(pnk);
                        break;
                    case 11:
                        listKhoanThuT11.add(pnk);
                        break;
                    case 12:
                        listKhoanThuT12.add(pnk);
                        break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for(PhieuXuatKho pxk:list1){
            total1+=pxk.getSoluong();
        }
        tvTotal.setText((total-total1)+"");
        listMap.put(new GroupObject(1, "Tháng 1"), listKhoanThuT1);
        listMap.put(new GroupObject(2, "Tháng 2"), listKhoanThuT2);
        listMap.put(new GroupObject(3, "Tháng 3"), listKhoanThuT3);
        listMap.put(new GroupObject(4, "Tháng 4"), listKhoanThuT4);
        listMap.put(new GroupObject(5, "Tháng 5"), listKhoanThuT5);
        listMap.put(new GroupObject(6, "Tháng 6"), listKhoanThuT6);
        listMap.put(new GroupObject(7, "Tháng 7"), listKhoanThuT7);
        listMap.put(new GroupObject(8, "Tháng 8"), listKhoanThuT8);
        listMap.put(new GroupObject(9, "Tháng 9"), listKhoanThuT9);
        listMap.put(new GroupObject(10, "Tháng 10"), listKhoanThuT10);
        listMap.put(new GroupObject(11, "Tháng 11"), listKhoanThuT11);
        listMap.put(new GroupObject(12, "Tháng 12"), listKhoanThuT12);

        return listMap;
    }
}