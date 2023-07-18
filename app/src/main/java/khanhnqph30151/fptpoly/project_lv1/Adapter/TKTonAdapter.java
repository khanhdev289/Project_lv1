package khanhnqph30151.fptpoly.project_lv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;

public class TKTonAdapter extends RecyclerView.Adapter<TKTonAdapter.DbVH> {
    private ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;
    private Context context;
    public TKTonAdapter(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
       sanPhamDAO=new SanPhamDAO(context);
    }
    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_thongketon, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
        SanPham sanPham=list.get(position);
        holder.tv_name_sp_ton.setText(sanPham.getTen_sp());
        holder.tv_soluong_sp_ton.setText(sanPham.getSoLuong_sp()+"");
    }
    public void setData(ArrayList<SanPham> lst) {
        this.list = lst;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;

    }

    class DbVH extends RecyclerView.ViewHolder {
        TextView tv_soluong_sp_ton,tv_name_sp_ton;
        public DbVH(@NonNull View itemView) {
            super(itemView);
            tv_name_sp_ton=itemView.findViewById(R.id.tv_name_sp_ton);
            tv_soluong_sp_ton=itemView.findViewById(R.id.tv_soluong_sp_ton);
        }
    }
}
