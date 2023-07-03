package khanhnqph30151.fptpoly.project_lv1.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ThongKeDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;


    public ThongKeDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();

    }
    public int getXuatKho(String tuThang, String denThang) {
        String sql = "SELECT SUM(soluong) as xuatKho FROM tbl_phieuXk WHERE month BETWEEN ? AND ? ";
        ArrayList<Integer> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[]{tuThang,denThang});
        while (c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("xuatKho"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
