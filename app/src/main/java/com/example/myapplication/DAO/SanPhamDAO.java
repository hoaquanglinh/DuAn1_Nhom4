package com.example.myapplication.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DBHelper;
import com.example.myapplication.model.SanPham;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private Context context;
    public SanPhamDAO(Context context) {
        this.context = context;
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("mamau", obj.getMamau());
        values.put("mahang", obj.getMahang());
        values.put("tensp", obj.getTensp());
        values.put("gia", obj.getGiasp());
        values.put("khohang", obj.getKhoHang());
        values.put("mota", obj.getMota());
        return db.insert("sanpham", null, values);
    }

    public long update(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("mamau", obj.getMamau());
        values.put("mahang", obj.getMahang());
        values.put("tensp", obj.getTensp());
        values.put("gia", obj.getGiasp());
        values.put("khohang", obj.getKhoHang());
        values.put("mota", obj.getMota());
        return db.update("sanpham", values, "masp = ?", new String[]{String.valueOf(obj.getMasp())});
    }

    public long delete(String id) {
        return db.delete("sanpham", "masp = ?", new String[]{String.valueOf(id)});
    }

    public List<SanPham> getAll() {
        String sql = "SELECT * FROM sanpham";
        return getData(sql);
    }

    public SanPham getID(String id) {
        String sql = "SELECT * FROM sanpham WHERE masp=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<SanPham> getData(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            SanPham obj = new SanPham();
            obj.setMamau(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mamau"))));
            obj.setMahang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mahang"))));
            obj.setTensp(cursor.getString(cursor.getColumnIndex("tensp")));
            obj.setGiasp(Double.parseDouble(cursor.getString(cursor.getColumnIndex("gia"))));
            obj.setKhoHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("khohang"))));
            obj.setMota(cursor.getString(cursor.getColumnIndex("mota")));
            list.add(obj);
        }
        return list;
    }
}
