package com.example.ql_cuahangdongho.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.Adapter.Adapter_DHtheoDM;
import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.CheckConnection;
import com.example.ql_cuahangdongho.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DongHoActivity extends AppCompatActivity {

    Toolbar toolbarDHtheoDM;
    ListView listViewDHtheoDM;
    ArrayList<DONGHO> dataDHtheoDM;
    Adapter_DHtheoDM adapter_DHtheoDM;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongho);

        anhXa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            actionBar();
            getIdDHtheoDM();
            getDHtheoDM(id);
            catchOnItemListView();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
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
        toolbarDHtheoDM = findViewById(R.id.toolBarDHtheoDM);
        listViewDHtheoDM = findViewById(R.id.listViewDHtheoDM);
        dataDHtheoDM = new ArrayList<>();
        adapter_DHtheoDM = new Adapter_DHtheoDM(getApplicationContext(), dataDHtheoDM);
        listViewDHtheoDM.setAdapter(adapter_DHtheoDM);
    }

    private void actionBar() {
        setSupportActionBar(toolbarDHtheoDM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDHtheoDM.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIdDHtheoDM() {
        Intent intent = getIntent();
        id = (String) intent.getSerializableExtra("DHtheoDM");
    }

    private void getDHtheoDM(String idDM) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.urlDHtheoDM + idDM, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    String id = "";
                    String ten = "";
                    int gia = 0;
                    String xuatxu = "";
                    String gioitinh = "";
                    String idloai = "";
                    String idthuonghieu = "";
                    String hinh = "";
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("IDDONGHO");
                            ten = jsonObject.getString("TENDONGHO");
                            gia = jsonObject.getInt("GIABAN");
                            xuatxu = jsonObject.getString("XUATSU");
                            gioitinh = jsonObject.getString("GIOITINH");
                            idloai = jsonObject.getString("IDLOAI");
                            idthuonghieu = jsonObject.getString("IDTHUONGHIEU");
                            hinh = jsonObject.getString("HINH");
                            dataDHtheoDM.add(new DONGHO(id, ten, xuatxu, gioitinh, idloai, idthuonghieu, hinh, gia));
                            adapter_DHtheoDM.notifyDataSetChanged();
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

    private void catchOnItemListView() {
        listViewDHtheoDM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietActivity.class);
                intent.putExtra("chitiet", dataDHtheoDM.get(position));
                startActivity(intent);
            }
        });
    }
}