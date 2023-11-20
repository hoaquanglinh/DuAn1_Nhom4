package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "FastFhone2";
    public static final int DB_VERSION = 3;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTaiKhoanNguoiDung = "create table taikhoanND (matknd integer primary key autoincrement, taikhoannd text not null, matkhaund text not null)";
        db.execSQL(createTableTaiKhoanNguoiDung);
        db.execSQL("insert into taikhoanND values (1, '123', '123')");

        String createTableQuanTriVien = "create table taikhoanQTV(taikhoanadmin text primary key, tenadmin text not null, matkhauadmin text not null)";
        db.execSQL(createTableQuanTriVien);

        String createTableMau = "create table mausac(mamau integer primary key autoincrement, tenmau text not null)";
        db.execSQL(createTableMau);
        db.execSQL("insert into mausac values (1, 'Xanh'), (2, 'Đỏ'),(3, 'Vàng'),(4, 'Đen'), (5, 'Trắng')");

        String createTableHang = "create table hang(mahang integer primary key autoincrement, tenhang text not null)";
        db.execSQL(createTableHang);
        db.execSQL("insert into hang values (1, 'SamSung'), (2, 'iphone'),(3, 'Xiaomi'),(4, 'Realme'), (5, 'Nokia')");

        String createTableSanPham = "create table sanpham (" +
                "masp integer primary key autoincrement, " +
                "mamau TEXT REFERENCES mausac(mamau)," +
                "mahang TEXT REFERENCES hang(mahang)," +
                "tensp text not null, " +
                "gia real not null," +
                "khohang integer not null," +
                "mota text not null)";
        db.execSQL(createTableSanPham);
        db.execSQL("insert into sanpham values (1, 2, 3,'Xiaomi redmi note 11', 2000000, 120, 'Pin 5000 mah Chip sanpdragon 8+ gen1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
