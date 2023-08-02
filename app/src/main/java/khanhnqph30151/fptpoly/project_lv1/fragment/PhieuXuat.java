package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import khanhnqph30151.fptpoly.project_lv1.Adapter.PhieuXuatAdapter;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuNkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.PhieuXkDAO;
import khanhnqph30151.fptpoly.project_lv1.data.SanPhamDAO;
import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;
import khanhnqph30151.fptpoly.project_lv1.model.SanPham;


public class PhieuXuat extends Fragment {

    private ArrayList<PhieuXuatKho> list;
    private ArrayList<SanPham> listSanPham;
    private PhieuXuatAdapter adapter;
    private ArrayAdapter<SanPham> adapterSanPham;
    PhieuXkDAO dao;
//    SanPhamDAO sanPhamDAO;
    RecyclerView rvPhieuXuat;


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

        rvPhieuXuat = view.findViewById(R.id.rvDanhSachPhieuXuatKho);
        FloatingActionButton floatAdd = view.findViewById(R.id.Float_add_Phieu_xuat_kho);

        EditText edSearch = view.findViewById(R.id.edTimKiemPhieuSuatKho);
        ImageButton btnSearch = view.findViewById(R.id.btn_phieuxk_timkiem);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSearch.length() > 0) {
                    String tentimkiem = edSearch.getText().toString();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
                    rvPhieuXuat.setLayoutManager(layoutManager);
                    PhieuXkDAO phieuXkDAO = new PhieuXkDAO(getContext());
                    list = new ArrayList<>();
                    list = phieuXkDAO.TimKiemPhXK(tentimkiem);
                    adapter.setData(list);
                    rvPhieuXuat.setAdapter(adapter);

                } else {
                    reloadData();
                }
            }
        });


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
                Spinner spinnerSanPham;
                AppCompatButton btnThem,btnHuy;

                spinnerSanPham = dialog.findViewById(R.id.SpTenSpPhieuXuatThem);
//                edTenSp = dialog.findViewById(R.id.edTenSpPhieuXuatThem);
                edSoLuong = dialog.findViewById(R.id.edSoLuongSPPhieuXuatThem);
                edNgayXuat = dialog.findViewById(R.id.edNgayXuatPhieuXuatThem);
                btnThem = dialog.findViewById(R.id.btnThemPhieuXuat);
                btnHuy = dialog.findViewById(R.id.btnHuyLayouThemPhieuXuat);

                SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
                listSanPham = sanPhamDAO.getAllData();
                adapterSanPham = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listSanPham);
                spinnerSanPham.setAdapter(adapterSanPham);
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                edNgayXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                month = month + 1;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);
                                String date = sdf.format(selectedCalendar.getTime());
                                edNgayXuat.setText(date);
                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });

                edNgayXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                month = month + 1;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);
                                String date = sdf.format(selectedCalendar.getTime());
                                edNgayXuat.setText(date);
                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SanPham id = (SanPham) spinnerSanPham.getSelectedItem();
                        PhieuNkDAO nkDAO = new PhieuNkDAO(getContext());
                        int slNhap = nkDAO.CheckSoLuong();
                        int soLuong = Integer.parseInt(edSoLuong.getText().toString());
                        if (soLuong >= slNhap){
                            Toast.makeText(getContext(), "Số lượng xuất không thế lớn hơn số lượng nhập !", Toast.LENGTH_SHORT).show();
                        } else if (id == null){
                            Toast.makeText(getContext(), "Không có sản phẩm không thể xuất", Toast.LENGTH_SHORT).show();
                        } else if (nkDAO.getAllData().isEmpty()) {
                            Toast.makeText(getContext(), "Không có phiếu nhập không thể xuất", Toast.LENGTH_SHORT).show();
                        } else if (kiemTra()){
                                int tenSp = id.getId_sp();
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
                                    SanPham sp=sanPhamDAO.getByID1(String.valueOf(phieuXuatKho.getId_sp()));
                                    SanPham sanPham=new SanPham();
                                    sanPham.setId_sp(sp.getId_sp());
//                                sanPhamDAO.updateSL(sanPham);
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                    }

                    private boolean kiemTra() {
                        if (
                                        edNgayXuat.getText().toString().equals("")
                                        ||edSoLuong.getText().toString().equals("")
                        ){
                            Toast.makeText(getContext(), "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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

        reloadData();
        super.onViewCreated(view, savedInstanceState);
    }
    private void reloadData(){
        dao = new PhieuXkDAO(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPhieuXuat.setLayoutManager(layoutManager);
        list = dao.getAllData();
        adapter.setData(list);
        rvPhieuXuat.setAdapter(adapter);
    }
}