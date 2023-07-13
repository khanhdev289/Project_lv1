package khanhnqph30151.fptpoly.project_lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import khanhnqph30151.fptpoly.project_lv1.data.ThanhVienDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edTenDN, edMatKhau;
    Button btnDangNhap;
    ThanhVienDAO dao;
    String strUser, strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        edTenDN=findViewById(R.id.edTenDN);
        edMatKhau=findViewById(R.id.edMatKhau);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        dao=new ThanhVienDAO(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

    }
    public void checkLogin() {
        strUser = edTenDN.getText().toString();
        strPass = edMatKhau.getText().toString();
        if (strUser.length() == 0) {
            edTenDN.requestFocus();
            edTenDN.setError("Vui lòng nhập tên đăng nhập");
        } else if (strPass.length() == 0) {
            edMatKhau.requestFocus();
            edMatKhau.setError("Vui lòng nhập mật khẩu");
        }else {
            if (dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(this, "Login thanh cong", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                rememberUser(strUser, strPass);
                finish();
            } else {
                Toast.makeText(this, "Login k thanh cong", Toast.LENGTH_SHORT).show();
            }

        }


    }
    public void rememberUser(String u, String p) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME", u);
        edit.putString("PASSWORD", p);
        edit.commit();
    }
}