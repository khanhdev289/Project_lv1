package khanhnqph30151.fptpoly.project_lv1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.project_lv1.model.LoaiSP;

public class LoaiSPDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public LoaiSPDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(LoaiSP ob) {
        ContentValues values = new ContentValues();
        values.put("loaiSp_tenLoai", ob.getName_loaiSP());

        return sqLiteDatabase.insert("tbl_loaiSp", null, values);
    }

    public int update(LoaiSP ob) {
        ContentValues values = new ContentValues();
        values.put("loaiSp_tenLoai", ob.getName_loaiSP());
        return sqLiteDatabase.update("tbl_loaiSp", values, "loaiSp_id=?", new String[]{String.valueOf(ob.getName_loaiSP())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_loaiSp", "loaiSp_id=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<LoaiSP> getData(String sql, String... SelectAvgs) {
        ArrayList<LoaiSP> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            LoaiSP ob = new LoaiSP();
            ob.setId_loaiSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("loaiSp_id"))));
            ob.setName_loaiSP(cursor.getString(cursor.getColumnIndex("loaiSp_tenLoai")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<LoaiSP> getAllData() {
        String sql = "SELECT * FROM tbl_loaiSp";
        return getData(sql);
    }

    public LoaiSP getByID(String id) {
        String sql = "SELECT * FROM tbl_loaiSp  where loaiSp_id=?";
        return getData(sql, id).get(0);
    }
    public ArrayList<String> getName(String sql,String...SelectAvgs){
        ArrayList<String> lst=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,SelectAvgs);
        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("loaiSp_tenLoai"));
            lst.add(name);
        }
        return lst;

    }
    public ArrayList<String> name(){
        String name="SELECT loaiSp_tenLoai FROM tbl_loaiSp";
        return getName(name);
    }
}
