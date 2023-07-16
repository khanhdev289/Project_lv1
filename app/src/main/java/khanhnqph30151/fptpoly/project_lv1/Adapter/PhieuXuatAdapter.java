package khanhnqph30151.fptpoly.project_lv1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;

public class PhieuXuatAdapter extends RecyclerView.Adapter<PhieuXuatAdapter.ViewHolder>{

    Context myContext;
    ArrayList<PhieuXuatKho> list;

    public PhieuXuatAdapter(Context myContext, ArrayList<PhieuXuatKho> list) {
        this.myContext = myContext;
        this.list = list;
    }
    public void setData(ArrayList<PhieuXuatKho> list){
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)myContext).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieu_xuat_kho,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuXuatKho idPhieu = list.get(position);
        PhieuXkDAO phieuXkDAO = new PhieuXkDAO(myContext);


        holder.tvTenSp.setText(list.get(position).getId_sp()+"");
        holder.tvSoLuong.setText(list.get(position).getSoluong()+"");
        holder.tvNgayXuat.setText(list.get(position).getNgayXuat());

        //Sựa kiện xóa
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle("Thông báo ! ")
                        .setMessage("Bạn chắc xóa thông tin phiếu này không ?")
                        .setCancelable(true)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int kq = phieuXkDAO.delete(idPhieu.getId_pxk());
                                if (kq > 0){
                                    Toast.makeText(myContext, "Xoa thành công", Toast.LENGTH_SHORT).show();
                                    list = phieuXkDAO.getAllData();
                                    setData(list);
                                }else {
                                    Toast.makeText(myContext, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        //Sựa kiên sửa
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(myContext);
                dialog.setContentView(R.layout.dialog_update_phieu_xuat);
                dialog.setCancelable(false);

                Window window = dialog.getWindow();
                window.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                );
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText edTen,edSoLuong,edNgayXuat;
                AppCompatButton btnSua,btnHuy;

                edTen = dialog.findViewById(R.id.edTenSpPhieuXuatSua);
                edSoLuong = dialog.findViewById(R.id.edSoLuongSPPhieuXuatSua);
                edNgayXuat = dialog.findViewById(R.id.edNgayXuatPhieuXuatSua);
                btnSua = dialog.findViewById(R.id.btnSuaPhieuXuat);
                btnHuy = dialog.findViewById(R.id.btnHuyLayouSuaPhieuXuat);

                edTen.setText(idPhieu.getId_sp()+"");
                edSoLuong.setText(idPhieu.getSoluong()+"");
                edNgayXuat.setText(idPhieu.getNgayXuat());

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (kiemTra()){
                            idPhieu.setId_sp(Integer.parseInt(edTen.getText().toString()));
                            idPhieu.setSoluong(Integer.parseInt(edSoLuong.getText().toString()));
                            idPhieu.setNgayXuat(edNgayXuat.getText().toString());

                            int kq = phieuXkDAO.update(idPhieu);
                            if (kq > 0){
                                Toast.makeText(myContext, "Sửa thành công ", Toast.LENGTH_SHORT).show();

                                list = phieuXkDAO.getAllData();
                                setData(list);
                                dialog.dismiss();

                            }else {
                                Toast.makeText(myContext, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    private boolean kiemTra() {

                        if (
                                edTen.getText().toString().equals("")
                                        || edNgayXuat.getText().toString().equals("")
                                        ||edSoLuong.getText().toString().equals("")
                        ){
                            Toast.makeText(myContext, "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        try {
                            Integer.parseInt(edTen.getText().toString());

                        }catch (NumberFormatException ex){
                            Toast.makeText(myContext, "Mã sản phẩm phải là số", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        try {
                            Integer.parseInt(edSoLuong.getText().toString());
                        }catch (NumberFormatException ex){
                            Toast.makeText(myContext, "Số lượng sản phẩm phải là số", Toast.LENGTH_SHORT).show();
                            return false;
                        }


                        if (Integer.parseInt(edSoLuong.getText().toString())<=0){
                            Toast.makeText(myContext, "Số lượng sản phẩm phải lớn hơn 0 !", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        return true;
                    }
                });

                dialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSp,tvSoLuong,tvNgayXuat;
        ImageView ivXoa,ivSua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSp = itemView.findViewById(R.id.tvTenSanPhamPhieuXuat);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuongSanPhamPhieuXuat);
            tvNgayXuat = itemView.findViewById(R.id.tvNgayXuatSanPhamPhieuXuat);
            ivSua = itemView.findViewById(R.id.ivSuaSPPhieuXuat);
            ivXoa = itemView.findViewById(R.id.ivXoaSPPhieuXuat);


        }
    }

}
