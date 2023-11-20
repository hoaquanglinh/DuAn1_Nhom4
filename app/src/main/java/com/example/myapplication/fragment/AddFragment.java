package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DAO.HangDAO;
import com.example.myapplication.DAO.MauSacDAO;
import com.example.myapplication.DAO.SanPhamDAO;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HangSpinerAdapter;
import com.example.myapplication.adapter.MauSacSpinerAdapter;
import com.example.myapplication.model.Hang;
import com.example.myapplication.model.MauSac;
import com.example.myapplication.model.SanPham;

import java.util.ArrayList;

public class AddFragment extends Fragment {
    EditText edTenSp, edGiaSp, edKhohang, edMota;
    Spinner spmamau, spmahang;

    MauSacSpinerAdapter mauSacSpinerAdapter;
    ArrayList<MauSac> listMauSac;
    MauSacDAO mauSacDAO;
    MauSac mauSac;
    int maMauSac;

    SanPhamDAO dao;
    SanPham item;

    HangSpinerAdapter hangSpinerAdapter;
    ArrayList<Hang> listHang;
    HangDAO hangDAO;
    Hang hang;
    int maHang;
    int positionMau, positionHang;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        dao = new SanPhamDAO(getActivity());

        context = getContext();
        edTenSp = view.findViewById(R.id.edTenSp);
        edGiaSp = view.findViewById(R.id.edGiaSp);
        edKhohang = view.findViewById(R.id.edKhohang);
        edMota = view.findViewById(R.id.edMoTa);
        spmahang = view.findViewById(R.id.spMaHang);
        spmamau = view.findViewById(R.id.spMaMau);

        mauSacDAO = new MauSacDAO(getContext());
        listMauSac = new ArrayList<>();
        listMauSac = (ArrayList<MauSac>) mauSacDAO.getAll();
        mauSacSpinerAdapter = new MauSacSpinerAdapter(context, listMauSac);
        spmamau.setAdapter(mauSacSpinerAdapter);
        spmamau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maMauSac = listMauSac.get(position).getMamau();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hangDAO = new HangDAO(getContext());
        listHang = new ArrayList<>();
        listHang = (ArrayList<Hang>) hangDAO.getAll();
        hangSpinerAdapter = new HangSpinerAdapter(context, listHang);
        spmahang.setAdapter(hangSpinerAdapter);
        spmahang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maHang = listHang.get(position).getMahang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.btnSaveSP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edTenSp.getText().toString();
                String giaStr = edGiaSp.getText().toString();
                String khohangStr = edKhohang.getText().toString();
                String motasp = edMota.getText().toString();

                if (tensp.isEmpty() || giaStr.isEmpty() || khohangStr.isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                Double giasp = Double.parseDouble(giaStr);
                Integer khohang = Integer.parseInt(khohangStr);

                item = new SanPham();
                item.setMamau(maMauSac);
                item.setMahang(maHang);
                item.setTensp(tensp);
                item.setGiasp(giasp);
                item.setKhoHang(khohang);
                item.setMota(motasp);

                long insert = dao.insert(item);
                if (insert > 0) {
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}