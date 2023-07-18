package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.Adapter.PhieuXuatAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;


public class PhieuXuat extends Fragment {

    private ArrayList<PhieuXuatKho> list;
    private PhieuXuatAdapter adapter;



    public PhieuXuat() {
        // Required empty public constructor
    }


    public static PhieuXuat newInstance() {
        PhieuXuat fragment = new PhieuXuat();

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
        return inflater.inflate(R.layout.fragment_phieu_xuat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvPhieuXuat = view.findViewById(R.id.rvDanhSachPhieuXuatKho);
        FloatingActionButton floatAdd = view.findViewById(R.id.Float_add_Phieu_xuat_kho);

        PhieuXkDAO phieuXkDAO = new PhieuXkDAO(getContext());
        list = phieuXkDAO.getAllData();
        adapter = new PhieuXuatAdapter(getContext(),list);


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_add_phieu_xuat);
                dialog.setCancelable(false);

                Window window = dialog.getWindow();
                window.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                );
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText edTenSp,edSoLuong,edNgayXuat;
                AppCompatButton btnThem,btnHuy;

                edTenSp = dialog.findViewById(R.id.edTenSpPhieuXuatThem);
                edSoLuong = dialog.findViewById(R.id.edSoLuongSPPhieuXuatThem);
                edNgayXuat = dialog.findViewById(R.id.edNgayXuatPhieuXuatThem);
                btnThem = dialog.findViewById(R.id.btnThemPhieuXuat);
                btnHuy = dialog.findViewById(R.id.btnHuyLayouThemPhieuXuat);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (kiemTra()){
                            int tenSp = Integer.parseInt(edTenSp.getText().toString());
                            int soLuong = Integer.parseInt(edSoLuong.getText().toString());
                            String ngayXuat = edNgayXuat.getText().toString();

                            PhieuXuatKho phieuXuatKho = new PhieuXuatKho();
                            phieuXuatKho.setId_sp(tenSp);
                            phieuXuatKho.setNgayXuat(ngayXuat);
                            phieuXuatKho.setSoluong(soLuong);

                            long kq = phieuXkDAO.insert(phieuXuatKho);
                            if (kq > 0){
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                list = phieuXkDAO.getAllData();
                                adapter.setData(list);
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    private boolean kiemTra() {
                        if (
                                edTenSp.getText().toString().equals("")
                                        || edNgayXuat.getText().toString().equals("")
                                        ||edSoLuong.getText().toString().equals("")
                        ){
                            Toast.makeText(getContext(), "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        try {
                            Integer.parseInt(edTenSp.getText().toString());

                        }catch (NumberFormatException ex){
                            Toast.makeText(getContext(), "Mã sản phẩm phải là số", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        try {
                            Integer.parseInt(edSoLuong.getText().toString());
                        }catch (NumberFormatException ex){
                            Toast.makeText(getContext(), "Số lượng sản phẩm phải là số", Toast.LENGTH_SHORT).show();
                            return false;
                        }


                        if (Integer.parseInt(edSoLuong.getText().toString())<=0){
                            Toast.makeText(getContext(), "Số lượng sản phẩm phải lớn hơn 0 !", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        return true;
                    }
                });
                dialog.show();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvPhieuXuat.setLayoutManager(manager);
        rvPhieuXuat.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}