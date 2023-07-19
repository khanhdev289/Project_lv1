package khanhnqph30151.fptpoly.project_lv1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.model.PhieuXuatKho;

public class PhieuXkDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public PhieuXkDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(PhieuXuatKho ob) {
        ContentValues values = new ContentValues();
        values.put("Sp_id", ob.getId_sp());
        values.put("phieuXk_soLuong",ob.getSoluong());
        values.put("phieuXk_ngayXuat",ob.getNgayXuat());
        values.put("thanhVien_id",ob.getId_tv());
        return sqLiteDatabase.insert("tbl_phieuXk", null, values);
    }

    public int update(PhieuXuatKho ob) {
        ContentValues values = new ContentValues();
        values.put("Sp_id", ob.getId_sp());
        values.put("phieuXk_soLuong",ob.getSoluong());
        values.put("phieuXk_ngayXuat",ob.getNgayXuat());
        values.put("thanhVien_id",ob.getId_tv());
        return sqLiteDatabase.update("tbl_phieuXk", values, "phieuXk_id=?", new String[]{String.valueOf(ob.getId_pxk())});
    }


    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_phieuXk", "phieuXk_id=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<PhieuXuatKho> getData(String sql, String... SelectAvgs) {
        ArrayList<PhieuXuatKho> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            PhieuXuatKho ob=new PhieuXuatKho();
            ob.setId_pxk(Integer.parseInt(cursor.getString(cursor.getColumnIndex("phieuXk_id"))));
            ob.setId_sp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Sp_id"))));
            ob.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("phieuXk_soLuong"))));
            ob.setNgayXuat(cursor.getString(cursor.getColumnIndex("phieuXk_ngayXuat")));
            ob.setId_tv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("thanhVien_id"))));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<PhieuXuatKho> getAllData() {
        String sql = "SELECT * FROM tbl_phieuXk";
        return getData(sql);
    }

    public PhieuXuatKho getByID(String id) {
        String sql = "SELECT * FROM tbl_phieuXk  where phieuXk_id=?";
        return getData(sql, id).get(0);
    }
    public PhieuXuatKho getByIDSp(String id_sp) {
        String sql = "SELECT * FROM tbl_phieuXk  where Sp_id = ?";
        return getData(sql, id_sp).get(0);
    }
  
}
