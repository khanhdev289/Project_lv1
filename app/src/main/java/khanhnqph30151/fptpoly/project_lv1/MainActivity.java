package khanhnqph30151.fptpoly.project_lv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import khanhnqph30151.fptpoly.project_lv1.fragment.DanhSach_Sp;
import khanhnqph30151.fptpoly.project_lv1.fragment.DoiMK;
import khanhnqph30151.fptpoly.project_lv1.fragment.Loai_Sp;
import khanhnqph30151.fptpoly.project_lv1.fragment.PhieuXuat;
import khanhnqph30151.fptpoly.project_lv1.fragment.QuanLyThanhVien;
import khanhnqph30151.fptpoly.project_lv1.fragment.ThongKe;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.id_drawer);

//        View headerLayout = navigationView.getHeaderView(0);
//
//        TextView tvUser = headerLayout.findViewById(R.id.tv_header_username);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.id_nav);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new DanhSach_Sp());


//        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
//        String role = sharedPreferences.getString("thuThu_role", "");
//        if (!role.equals("admin")){
//            Menu menu = navigationView.getMenu();
//            menu.findItem(R.id.top10).setVisible(false);
//            menu.findItem(R.id.doanhthu).setVisible(false);
//        }
//        String user = sharedPreferences.getString("thuThu_hoTen", "");
//        tvUser.setText(user);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.close();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setTitle(item.getTitle());
        if (item.getItemId() == R.id.SanPham) {
            drawerLayout.close();
            replaceFragment(DanhSach_Sp.newInstance());
            return true;
        }
        else if (item.getItemId() == R.id.loaiSanPham) {
            drawerLayout.close();
            replaceFragment(Loai_Sp.newInstance());
            return true;
        }
        else if (item.getItemId() == R.id.phieuXuatKho) {
            drawerLayout.close();
            replaceFragment(PhieuXuat.newInstance());
            return true;
        } else if (item.getItemId() == R.id.QuanlyTv) {
            drawerLayout.close();
            replaceFragment(QuanLyThanhVien.newInstance());
            return true;
        }  else if (item.getItemId() == R.id.ThongKe) {
            drawerLayout.close();
            replaceFragment(ThongKe.newInstance());
            return true;
        } else if (item.getItemId() == R.id.doiMatKhau) {
            drawerLayout.close();
            replaceFragment(DoiMK.newInstance());
            return true;
        } else if (item.getItemId() == R.id.Thoat) {
            finish();
            return true;
        } else {
            return false;
        }




    }


    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}