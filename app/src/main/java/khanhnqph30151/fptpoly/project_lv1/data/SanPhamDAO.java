package khanhnqph30151.fptpoly.project_lv1.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.model.SanPham;

public class SanPhamDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SanPhamDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(SanPham ob) {
        ContentValues values = new ContentValues();
        values.put("Sp_tenSp", ob.getTen_sp());
        values.put("loaiSp_id",ob.getId_loaiSP());
        values.put("Sp_soLuong",ob.getSoLuong_sp());
        values.put("Sp_giaTien",ob.getGia_sp());
        values.put("Sp_ngayLuuKho",ob.getNgayLuuKho_sp());
        return sqLiteDatabase.insert("tbl_Sp", null, values);
    }

    public int update(SanPham ob) {
        ContentValues values = new ContentValues();
        values.put("Sp_tenSp", ob.getTen_sp());
        values.put("loaiSp_id",ob.getId_loaiSP());
        values.put("Sp_soLuong",ob.getSoLuong_sp());
        values.put("Sp_giaTien",ob.getGia_sp());
        values.put("Sp_ngayLuuKho",ob.getNgayLuuKho_sp());
        return sqLiteDatabase.update("tbl_Sp", values, "Sp_id=?", new String[]{String.valueOf(ob.getId_sp())});
    }


    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_Sp", "Sp_id=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<SanPham> getData(String sql, String... SelectAvgs) {
        ArrayList<SanPham> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            SanPham ob = new SanPham();
            ob.setId_sp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Sp_id"))));
            ob.setGia_sp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Sp_giaTien"))));
            ob.setId_loaiSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("loaiSp_id"))));
            ob.setTen_sp(cursor.getString(cursor.getColumnIndex("Sp_tenSp")));
            ob.setSoLuong_sp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Sp_soLuong"))));
           ob.setNgayLuuKho_sp(cursor.getString(cursor.getColumnIndex("Sp_ngayLuuKho")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<SanPham> getAllData() {
        String sql = "SELECT * FROM tbl_Sp";
        return getData(sql);
    }

    public SanPham getByID(String id) {
        String sql = "SELECT * FROM tbl_Sp  where Sp_id=?";
        return getData(sql, id).get(0);
    }
    public ArrayList<String> getName(String sql,String...SelectAvgs){
        ArrayList<String> lst=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,SelectAvgs);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex("Sp_tenSp"));
            lst.add(name);
        }
        return lst;

    }
    public ArrayList<String> name(){
        String name="SELECT Sp_tenSp FROM tbl_Sp";
        return getName(name);
    }
}
