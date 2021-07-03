package com.example.ql_cuahangdongho.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.Adapter.Adapter_DHtheoDM;
import com.example.ql_cuahangdongho.Adapter.Adapter_DongHo;
import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.GIOHANG;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class ChiTietActivity extends AppCompatActivity {

    Toolbar toolbarCTSP;
    ImageView imgCTSP;
    TextView tvTen, tvGia, tvXuatXu, tvGioiTinh, tvLoaiDH, tvThuongHieu;
    Spinner spinnerCTSP;
    Button btnThem;

    String id = "";
    String ten = "";
    int gia = 0;
    String xuatxu = "";
    String gioitinh = "";
    String idloai = "";
    String idthuonghieu = "";
    String hinh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        anhXa();
        actionBar();
        getInfomation();
        catchEventSpinner();
        eventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGH:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void anhXa() {
        toolbarCTSP = findViewById(R.id.toolBarCTSP);
        imgCTSP = findViewById(R.id.imgCTSP);
        tvTen = findViewById(R.id.tvTenDHtheoCTSP);
        tvGia = findViewById(R.id.tvGiaDHtheoCTSP);
        tvXuatXu = findViewById(R.id.tvXuatXu);
        tvGioiTinh = findViewById(R.id.tvGioiTinh);
        tvLoaiDH = findViewById(R.id.tvLoaiDH);
        tvThuongHieu = findViewById(R.id.tvThuongHieu);
        spinnerCTSP = findViewById(R.id.spinnerCTSP);
        btnThem = findViewById(R.id.btnThem);
    }

    private void actionBar() {
        setSupportActionBar(toolbarCTSP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCTSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getInfomation() {

        DONGHO dongho = (DONGHO) getIntent().getSerializableExtra("chitiet");
        id = dongho.getId();
        ten = dongho.getTen();
        gia = dongho.getGia();
        xuatxu = dongho.getXuatxu();
        gioitinh = dongho.getGioitinh();
        idloai = dongho.getIdloai();
        idthuonghieu = dongho.getIdthuonghieu();
        hinh = dongho.getHinh();
        tvTen.setText(ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGia.setText("Giá: " + decimalFormat.format(gia) + " VNĐ");
        tvXuatXu.setText(xuatxu);
        tvGioiTinh.setText(gioitinh);
        tvLoaiDH.setText(idloai);
        tvThuongHieu.setText(idthuonghieu);
        Picasso.with(getApplicationContext()).load(Server.loadHinh + hinh)
                .placeholder(R.drawable.no_image)
                .into(imgCTSP);
    }

    private void catchEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinnerCTSP.setAdapter(arrayAdapter);
    }

    private void eventButton() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dataGH.size() > 0) {
                    int sl = Integer.parseInt(spinnerCTSP.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.dataGH.size(); i++) {
                        if (MainActivity.dataGH.get(i).getId() == id) {
                            MainActivity.dataGH.get(i).setSoluong(MainActivity.dataGH.get(i).getSoluong() + sl);
                            if (MainActivity.dataGH.get(i).getSoluong() >= 10) {
                                MainActivity.dataGH.get(i).setSoluong(10);
                            }
                            MainActivity.dataGH.get(i).setGia(gia * MainActivity.dataGH.get(i).getSoluong());
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        int soluong = Integer.parseInt(spinnerCTSP.getSelectedItem().toString());
                        int giaNew = soluong * gia;
                        MainActivity.dataGH.add(new GIOHANG(id, ten, hinh, giaNew, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt(spinnerCTSP.getSelectedItem().toString());
                    int giaNew = soluong * gia;
                    MainActivity.dataGH.add(new GIOHANG(id, ten, hinh, giaNew, soluong));

                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }
}