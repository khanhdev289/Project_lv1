package khanhnqph30151.fptpoly.project_lv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import khanhnqph30151.fptpoly.project_lv1.data.ThanhVienDAO;
import khanhnqph30151.fptpoly.project_lv1.model.ThanhVien;

public class LoginActivity extends AppCompatActivity {

    public static String tenDangNhap = "";

    TextInputEditText edTenDN;
    TextInputEditText edMatKhau;
    AppCompatButton btnDangNhap;
    List<ThanhVien> thanhVienList;
    ThanhVienDAO thanhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edTenDN = findViewById(R.id.edTenDN);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        thanhVienDAO = new ThanhVienDAO(this);
        thanhVienList = thanhVienDAO.getAllData();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < thanhVienList.size(); i++){
                    if(edTenDN.getText().toString().equals(thanhVienList.get(i).getHoTen_tv()) && edMatKhau.getText().toString().equals(thanhVienList.get(i).getMatKhau_tv())){
                        tenDangNhap = edTenDN.getText().toString();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}