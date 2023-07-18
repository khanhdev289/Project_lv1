package khanhnqph30151.fptpoly.project_lv1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.LoaiSPDAO;
import khanhnqph30151.fptpoly.project_lv1.data.ThanhVienDAO;
import khanhnqph30151.fptpoly.project_lv1.model.LoaiSP;
import khanhnqph30151.fptpoly.project_lv1.model.ThanhVien;

public class DanhSachNhanVien_adapter extends RecyclerView.Adapter<DanhSachNhanVien_adapter.ViewHolder>{
    private ArrayList<ThanhVien> list;
    Context context;

    private ThanhVienDAO dao;
    public DanhSachNhanVien_adapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    public void setData(ArrayList<ThanhVien> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_quan_ly_danh_sach_thanh_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getHoTen_tv());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata(context, list.get(position));
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_pouup_sua_xoa, builder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, v);
                builder.setCallback(new MenuBuilder.Callback() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_edit) {
//                            showUpdate(list.get(position), position);
                            return true;
                        } else if (item.getItemId() == R.id.option_delete) {
                            showDele(position);
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionmenu.show();
                return true;
            }
        });
    }
    public void showDele(int id){
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("Bạn có muốn xóa không?");
        dialogDL.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDL.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThanhVienDAO dao = new ThanhVienDAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
//    public void showUpdate(ThanhVien thanhVien, int id){
//        Dialog dialog = new Dialog(context);
//        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
//        dialog.setContentView(R.layout.dialog_loaisach_edit);
//        EditText ed1;
//        Button btnDialogAddCancel, btnDialogAddSubmit;
//        ed1 = dialog.findViewById(R.id.edt_dialog_loaisach_edit_name);
//
//        ed1.setText(list.get(id).getTenLoaiSach());
//
//        btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_loaisach_edit_cancel);
//        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_loaisach_edit_add);
//
//        btnDialogAddCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoaiSachDAO loaiDao = new LoaiSachDAO(context);
//                String ten = ed1.getText().toString();
//                if (ten.trim().equals("")) {
//                    Toast.makeText(context, "ko dc de trong", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    thanhVien.setTenLoaiSach(ed1.getText().toString());
//                    if (loaiDao.update(thanhVien) > 0) {
//                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
//                        list = loaiDao.getAllData();
//                        setData(list);
//                        dialog.dismiss();
//                    } else {
//                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//            }
//        });
//        dialog.show();
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tvTenDangNhapCuaThanhVien);

        }
    }
    public void showdata(Context context, ThanhVien thanhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông tin Tai khoản");
        builder.setMessage("Tên Tài khoản:" + thanhVien.getHoTen_tv()
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alertDialog.dismiss();
            }
        });
    }
}
