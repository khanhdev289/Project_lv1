package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import khanhnqph30151.fptpoly.project_lv1.Adapter.TKXuatEAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.model.GroupObject;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TKXuatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TKXuatFragment extends Fragment {

    private ExpandableListView elvThu;
    private List<GroupObject> listGroup;
    private LinkedHashMap<GroupObject, List<PhieuXuatKho>> listItem;
    private TKXuatEAdapter adapter;
    private TextView tvTotal;
    private PhieuXkDAO dao;
    private final List<PhieuXuatKho> listKhoanThuT1 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT2 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT3 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT4 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT5 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT6 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT7 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT8 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT9 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT10 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT11 = new ArrayList<>();
    private final List<PhieuXuatKho> listKhoanThuT12 = new ArrayList<>();
    Calendar cal = Calendar.getInstance();


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
        return inflater.inflate(R.layout.fragment_t_k_xuat, container, false);
    }



    private LinkedHashMap<GroupObject, List<PhieuXuatKho>> getListItem() {
        int total = 0;
        LinkedHashMap<GroupObject, List<PhieuXuatKho>> listMap = new LinkedHashMap<>();
        List<PhieuXuatKho> list = dao.getAllData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (PhieuXuatKho pxk : list) {
            total += pxk.getSoluong();
            try {
                cal.setTime(dateFormat.parse(pxk.getNgayXuat()));
                switch (cal.get(Calendar.MONTH) + 1) {
                    case 1:
                        listKhoanThuT1.add(pxk);
                        break;
                    case 2:
                        listKhoanThuT2.add(pxk);
                        break;
                    case 3:
                        listKhoanThuT3.add(pxk);
                        break;
                    case 4:
                        listKhoanThuT4.add(pxk);
                        break;
                    case 5:
                        listKhoanThuT5.add(pxk);
                        break;
                    case 6:
                        listKhoanThuT6.add(pxk);
                        break;
                    case 7:
                        listKhoanThuT7.add(pxk);
                        break;
                    case 8:
                        listKhoanThuT8.add(pxk);
                        break;
                    case 9:
                        listKhoanThuT9.add(pxk);
                        break;
                    case 10:
                        listKhoanThuT10.add(pxk);
                        break;
                    case 11:
                        listKhoanThuT11.add(pxk);
                        break;
                    case 12:
                        listKhoanThuT12.add(pxk);
                        break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        tvTotal.setText(total+"");
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        elvThu = view.findViewById(R.id.elv_xuatKho);
        tvTotal = view.findViewById(R.id.tv_total_value);
        dao = new PhieuXkDAO(getContext());
        listItem = getListItem();
        listGroup = new ArrayList<>(listItem.keySet());
        adapter = new TKXuatEAdapter(listGroup, listItem,getContext());
        elvThu.setAdapter(adapter);
        for (int i = 0; i < 10; i++) elvThu.expandGroup(i);
    }
}