package com.example.ql_cuahangdongho.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.Adapter.Adapter_DanhMuc;
import com.example.ql_cuahangdongho.Adapter.Adapter_Spinner;
import com.example.ql_cuahangdongho.Model.DANHMUC;
import com.example.ql_cuahangdongho.Model.SPINNER;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.CheckConnection;
import com.example.ql_cuahangdongho.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhMucActivity extends AppCompatActivity {

    Toolbar toolbarDM;

    Spinner spinner;
    ArrayList<SPINNER> dataSpinner = new ArrayList<>();
    Adapter_Spinner adapter_spinner;

    GridView gridViewDM;
    ArrayList<DANHMUC> dataDM = new ArrayList<>();
    Adapter_DanhMuc adapter_danhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        anhXa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            actionBar();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void anhXa() {
        toolbarDM = findViewById(R.id.toolBarDanhMuc);
        gridViewDM = findViewById(R.id.gridViewDM);
        spinner = findViewById(R.id.spinnerDM);
        dataSpinner = getSpinnerItem();
        adapter_spinner = new Adapter_Spinner(getApplicationContext(), dataSpinner);
        spinner.setAdapter(adapter_spinner);
        catchOnSpinner();
        adapter_danhMuc = new Adapter_DanhMuc(getApplicationContext(), dataDM);
        gridViewDM.setAdapter(adapter_danhMuc);
        catchOnItemGridView();
    }

    private void actionBar() {
        setSupportActionBar(toolbarDM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDM.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public ArrayList<SPINNER> getSpinnerItem() {
        dataSpinner.add(new SPINNER(Server.urlLoaiDH, "Loại đồng hồ"));
        dataSpinner.add(new SPINNER(Server.urlThuongHieu, "Thương hiệu"));
        return dataSpinner;
    }

    private void catchOnSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDanhMuc(dataSpinner.get(position).getUrl());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getDanhMuc(String url) {
        dataDM.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    String id = "";
                    String ten = "";
                    String hinh = "";
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("ID");
                            ten = jsonObject.getString("TEN");
                            hinh = jsonObject.getString("HINH");
                            dataDM.add(new DANHMUC(id, ten, hinh));
                            adapter_danhMuc.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void catchOnItemGridView() {
        gridViewDM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                    Intent intent = new Intent(getApplicationContext(), DongHoActivity.class);
                    intent.putExtra("DHtheoDM", dataDM.get(position).getIddm());
                    startActivity(intent);
                } else {
                    CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                }

            }
        });
    }
}