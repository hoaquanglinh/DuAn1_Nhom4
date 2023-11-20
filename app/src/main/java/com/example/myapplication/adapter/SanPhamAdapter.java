package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.DAO.HangDAO;
import com.example.myapplication.DAO.MauSacDAO;
import com.example.myapplication.R;
import com.example.myapplication.fragment.ProductFragment;
import com.example.myapplication.model.MauSac;
import com.example.myapplication.model.SanPham;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    ProductFragment fragment;
    private ArrayList<SanPham> list;
    TextView tvtensp, tvgiasp, tvmau, tvkhohang;
    Button btnsua, btnxoa;
    HangDAO hangDAO;
    MauSacDAO mauSacDAO;

    public SanPhamAdapter(@NonNull Context context, ProductFragment fragment, ArrayList<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_product, null);
        }
        final SanPham item = list.get(position);
        if (item != null) {
            tvtensp = v.findViewById(R.id.tvTensp);
            tvtensp.setText(item.getTensp());

            mauSacDAO = new MauSacDAO(context);
            MauSac mauSac = mauSacDAO.getID(String.valueOf(item.getMamau()));
            tvmau = v.findViewById(R.id.tvMau);
            tvmau.setText("Màu: " + mauSac.getTenMau());

            tvgiasp = v.findViewById(R.id.tvGia);
            tvgiasp.setText("Giá: " + item.getGiasp());

            tvkhohang = v.findViewById(R.id.tvKho);
            tvkhohang.setText("Kho: " + item.getKhoHang());

            btnxoa = v.findViewById(R.id.btnXoa);
        }

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMasp()));
            }
        });

        return v;

    }
}
