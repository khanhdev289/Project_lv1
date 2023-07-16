package khanhnqph30151.fptpoly.project_lv1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data";
    private static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static final String TABLE_THANH_VIEN_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_thanhVien (" +
            "thanhVien_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "thanhVien_hoTen TEXT NOT NULL," +
            "thanhVien_matKhau TEXT NOT NULL," +
            "thanhVien_role TEXT" +
            ")";
    public static final String TABLE_LOAI_SAN_PHAM_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_loaiSp (" +
            "loaiSp_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "loaiSp_tenLoai TEXT NOT NULL" +
            ")";
    public static final String TABLE_SAN_PHAM_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_Sp (" +
            "Sp_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Sp_tenSp TEXT NOT NULL," +
            "Sp_giaTien TEXT NOT NULL," +
            "Sp_ngayLuuKho TEXT NOT NULL," +
            "Sp_soLuong INTEGER NOT NULL," +
            "loaiSp_id  REFERENCES tbl_loaiSp(loaiSp_id)" +
            ")";
    public static final String TABLE_PHIEU_XUAT_KHO_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_phieuXk(" +
            "phieuXk_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thanhVien_id TEXT REFERENCES tbl_thanhVien(thanhVien_id)," +
            "Sp_id INTEGER REFERENCES tbl_Sp(Sp_id)," +
            "phieuXk_soLuong INTEGER NOT NULL ," +
            "phieuXk_ngayXuat TEXT NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DBHelper", "vao oncreate");

        //Thanh Vien
        db.execSQL(TABLE_THANH_VIEN_CREATE);
        //Loai San Pham
        db.execSQL(TABLE_LOAI_SAN_PHAM_CREATE);
        //San Pham
        db.execSQL(TABLE_SAN_PHAM_CREATE);
        //Phieu Xuat Kho
        db.execSQL(TABLE_PHIEU_XUAT_KHO_CREATE);


//        public static final String TABLE_THANH_VIEN_CREATE = "CREATE TABLE IF NOT EXISTS " +
//                "tbl_thanhVien (" +
//                "thanhVien_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
//                "thanhVien_hoTen TEXT NOT NULL," +
//                "thanhVien_matKhau TEXT NOT NULL," +
//                "thanhVien_role TEXT" +
//                ")";

        db.execSQL("INSERT INTO tbl_thanhVien VALUES (0, 'tv1', 'tv1', 'tv')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS tbl_thanhVien");
            db.execSQL("DROP TABLE IF EXISTS tbl_loaiSp");
            db.execSQL("DROP TABLE IF EXISTS tbl_Sp");
            db.execSQL("DROP TABLE IF EXISTS tbl_phieuXK");
            onCreate(db);
        }

    }
}
