package khanhnqph30151.fptpoly.project_lv1.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.model.TonKho;
import khanhnqph30151.fptpoly.project_lv1.model.XuatKho;

public class ThongKeDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;


    public ThongKeDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();

    }

    public ArrayList<XuatKho> XuatKho(String nam, String thang) {
        String query = "SELECT Sp_id, SUM(phieuXk_soLuong) AS totalExport FROM tbl_phieuXk " +
                "WHERE substr(phieuXk_ngayXuat,1,4) = ? AND substr(phieuXk_ngayXuat,6,2) = ? " +
                "GROUP BY Sp_id";
        ArrayList<XuatKho> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(query, new String[]{nam, thang});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    XuatKho xuatKho=new XuatKho();
                    xuatKho.setSp_Id(Integer.parseInt(c.getString(c.getColumnIndex("Sp_id"))));
                    xuatKho.setXuatKho(Integer.parseInt(c.getString(c.getColumnIndex("totalExport"))));
                    list.add(xuatKho);
                } while (c.moveToNext());
            }
            c.close();
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<TonKho> TonKho(String nam, String thang) {
        String query = "SELECT Sp_id, SUM(phieuNk_soLuong) AS total FROM tbl_phieuNk " +
                "WHERE substr(phieuNk_ngayNhap,1,4) = ? AND substr(phieuNk_ngayNhap,6,2) = ? " +
                "GROUP BY Sp_id";
        ArrayList<TonKho> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(query, new String[]{nam, thang});

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    TonKho tonKho=new TonKho();
                    tonKho.setSp_Id(Integer.parseInt(c.getString(c.getColumnIndex("Sp_id"))));
                    tonKho.setTonKho(Integer.parseInt(c.getString(c.getColumnIndex("total"))));
                    list.add(tonKho);
                } while (c.moveToNext());
            }
            c.close();
        }
        return list;
    }
}
