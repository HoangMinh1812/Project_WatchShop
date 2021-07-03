package com.example.ql_cuahangdongho.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Adapter.Adapter_GioHang;
import com.example.ql_cuahangdongho.Model.GIOHANG;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    Toolbar toolbarGH;
    ListView listViewGH;
    TextView tvEmpty;
    static TextView tvTongTien;
    Button btnThanhToan, btnTiepTuc;
    Adapter_GioHang adapter_gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        anhXa();
        actionBar();
        checkData();
        eventUtil();
        catchOnItemListView();
        eventButton();
    }

    private void anhXa() {
        toolbarGH = findViewById(R.id.toolBarGH);
        listViewGH = findViewById(R.id.listViewGH);
        tvEmpty = findViewById(R.id.tvEmpty);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        adapter_gioHang = new Adapter_GioHang(getApplicationContext(), MainActivity.dataGH);
        listViewGH.setAdapter(adapter_gioHang);
    }

    private void actionBar() {
        setSupportActionBar(toolbarGH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkData() {
        if (MainActivity.dataGH.size() <= 0) {
            adapter_gioHang.notifyDataSetChanged();
            tvEmpty.setVisibility(View.VISIBLE);
            listViewGH.setVisibility(View.INVISIBLE);
        } else {
            adapter_gioHang.notifyDataSetChanged();
            tvEmpty.setVisibility(View.INVISIBLE);
            listViewGH.setVisibility(View.VISIBLE);
        }
    }

    public static void eventUtil() {
        int tongtien = 0;
        for (int i = 0; i < MainActivity.dataGH.size(); i++)
            tongtien += MainActivity.dataGH.get(i).getGia();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTien.setText(decimalFormat.format(tongtien) + " VNĐ");
    }

    private void catchOnItemListView() {
        listViewGH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.dataGH.size() <= 0) {
                            tvEmpty.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.dataGH.remove(position);
                            adapter_gioHang.notifyDataSetChanged();
                            eventUtil();
                            if (MainActivity.dataGH.size() <= 0) {
                                tvEmpty.setVisibility(View.VISIBLE);
                            } else {
                                tvEmpty.setVisibility(View.INVISIBLE);
                                adapter_gioHang.notifyDataSetChanged();
                                eventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter_gioHang.notifyDataSetChanged();
                        eventUtil();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    private void eventButton()
    {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.dataGH.size()>0)
                {
                    Intent intent =new Intent(getApplicationContext(), KhachHangActivity.class);
                    startActivity(intent);
                }else
                {
                    CheckConnection.showToast_Short(getApplicationContext(), "Giỏ hàng của bạn chưa có sản phẩm để thanh toán");
                }
            }
        });
    }
}