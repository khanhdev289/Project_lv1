package khanhnqph30151.fptpoly.project_lv1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import khanhnqph30151.fptpoly.project_lv1.LoginActivity;
import khanhnqph30151.fptpoly.project_lv1.R;
import khanhnqph30151.fptpoly.project_lv1.data.ThanhVienDAO;
import khanhnqph30151.fptpoly.project_lv1.model.ThanhVien;


public class DangKy extends Fragment {

    EditText edUsername_regis,edUserpass_regis,edUserRePass;
    Button btn_register;
    ThanhVienDAO dao;

    public DangKy() {
        // Required empty public constructor
    }


    public static DangKy newInstance() {
        DangKy fragment = new DangKy();

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
        return inflater.inflate(R.layout.fragment_dang_ky2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        edUsername_regis=view.findViewById(R.id.edUsername_regis);
        edUserpass_regis=view.findViewById(R.id.edUserpass_regis);
        edUserRePass=view.findViewById(R.id.edUserRePass);
        btn_register=view.findViewById(R.id.btn_Register);
        dao=new ThanhVienDAO(getActivity());
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien user=new ThanhVien();
                user.setHoTen_tv(edUsername_regis.getText().toString());
                user.setMatKhau_tv(edUserpass_regis.getText().toString());
                user.setRole_tv("user");
                if(validate()>0){
                    if(dao.insert(user)>0){
                        Toast.makeText(getActivity(), "thêm tai khoan thanh cong", Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
//                        finish();
//                        startActivity(i);
                    }else {
                        Toast.makeText(getActivity(), "them that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
    public int validate(){
        int check=1;
        if(edUsername_regis.getText().length()==0||edUserpass_regis.getText().length()==0||edUserRePass.getText().length()==0
        ){
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
            String pass=edUserpass_regis.getText().toString();
            String repass=edUserRePass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }else{
                check=1;
            }

        }
        return check;
    }
}