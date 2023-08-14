package khanhnqph30151.fptpoly.project_lv1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.data.ThongKeDAO;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;
import khanhnqph30151.fptpoly.project_lv1.model.NhapKho;
import khanhnqph30151.fptpoly.project_lv1.model.XuatKho;

public class TKXuatAdapter extends RecyclerView.Adapter<TKXuatAdapter.ViewHolder> {
    private ArrayList<XuatKho> list_xuat;
    private ArrayList<NhapKho> list_ton;
    Context context;

    private ThongKeDAO dao;
    SanPhamDAO sanPhamDAO;

    public TKXuatAdapter(ArrayList<XuatKho> list_xuat, ArrayList<NhapKho> list_ton, Context context) {
        this.list_xuat = list_xuat;
        this.list_ton = list_ton;
        this.context = context;
        sanPhamDAO=new SanPhamDAO(context);
    }

    public void setData(ArrayList<XuatKho> list_xuat, ArrayList<NhapKho> list_ton) {
        this.list_ton = list_ton;
        this.list_xuat = list_xuat;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TKXuatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TKXuatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NhapKho nhapKho = list_ton.get(position);
        XuatKho xuatKho = list_xuat.get(position);
        SanPham sanPham=sanPhamDAO.getByID1(String.valueOf(xuatKho.getSp_Id()));
        holder.tvIdSPl.setText((position+1)+". "+sanPham.getTen_sp() + "");
        holder.tvTon.setText("Tồn : "+(nhapKho.getTonKho() - xuatKho.getXuatKho()) + " sp");
        holder.tvXuat.setText("Xuất : "+ xuatKho.getXuatKho() + " sp");
    }

    @Override
    public int getItemCount() {
        if(list_xuat==null)
            return 0;
        return list_xuat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdSPl, tvTon, tvXuat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdSPl = itemView.findViewById(R.id.tv_id_sp);
            tvXuat = itemView.findViewById(R.id.tv_xuat);
            tvTon = itemView.findViewById(R.id.tv_ton);

        }
    }

}
