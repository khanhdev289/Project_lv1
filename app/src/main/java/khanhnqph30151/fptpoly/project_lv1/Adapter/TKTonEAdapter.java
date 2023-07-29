package khanhnqph30151.fptpoly.project_lv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuNkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.model.GroupObject;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuNhapKho;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;

public class TKTonEAdapter extends BaseExpandableListAdapter {
    SanPhamDAO sp_dao;
    Context context;
    PhieuNkDAO phieuNkDAO;
    PhieuXkDAO phieuXkDAO;

    private final List<GroupObject> groupObjectList;
    private final Map<GroupObject, List<PhieuNhapKho>> phieuNhapListItem;



    public TKTonEAdapter(List<GroupObject> groupObjectList, Map<GroupObject, List<PhieuNhapKho>> phieuNhapListItem, Context context) {
        this.groupObjectList = groupObjectList;
        this.phieuNhapListItem = phieuNhapListItem;
        this.context=context;
        sp_dao=new SanPhamDAO(context);
        phieuNkDAO=new PhieuNkDAO(context);
        phieuXkDAO=new PhieuXkDAO(context);
    }

    @Override
    public int getGroupCount() {
        return groupObjectList != null ? groupObjectList.size() : 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if (groupObjectList != null && phieuNhapListItem != null) {
            return phieuNhapListItem.get(groupObjectList.get(i)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return groupObjectList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return phieuNhapListItem.get(groupObjectList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return groupObjectList.get(i).getId();
    }

    @Override
    public long getChildId(int i, int i1) {
        return phieuNhapListItem.get(groupObjectList.get(i)).get(i1).getId_pnk();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_thongke_group, viewGroup, false);
        }
        TextView tvNameGroup = view.findViewById(R.id.tv_group_name);
        tvNameGroup.setText(groupObjectList.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_thongkexuat, viewGroup, false);
        }
        TextView tv_name_sp, tv_ngayXuat, tv_soluong_sp;
        tv_name_sp = view.findViewById(R.id.tv_name_sp);
        tv_ngayXuat = view.findViewById(R.id.tv_ngayXuat);
        tv_soluong_sp = view.findViewById(R.id.tv_soluong_sp);
        PhieuNhapKho pnk = phieuNhapListItem.get(groupObjectList.get(i)).get(i1);
        PhieuXuatKho pxk=phieuXkDAO.getByID_SP(String.valueOf(pnk.getId_sp()));
        SanPham sp=sp_dao.getByID1(String.valueOf(pnk.getId_sp()));
        tv_name_sp.setText(sp.getTen_sp());
        tv_ngayXuat.setText(pnk.getNgayNhap());
        tv_soluong_sp.setText((pnk.getSoluong()-pxk.getSoluong())+"");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
