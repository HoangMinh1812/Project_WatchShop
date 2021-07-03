package com.example.ql_cuahangdongho.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.Adapter.Adapter_DHtheoDM;
import com.example.ql_cuahangdongho.Adapter.Adapter_SPYT;
import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.SANPHAMYEUTHICH;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SPYTActivity extends AppCompatActivity {

    Toolbar toolbarSPYT;
    ListView listViewSPYT;
    ArrayList<SANPHAMYEUTHICH> dataSPYT;
    Adapter_SPYT adapter_spyt;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_y_t);

        anhXa();
        actionBar();
        getIdKH();
        getSPYT(id);
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

    private void anhXa()
    {
        toolbarSPYT = findViewById(R.id.toolBarSPYT);
        listViewSPYT = findViewById(R.id.listViewSPYT);
        dataSPYT = new ArrayList<>();
        adapter_spyt = new Adapter_SPYT(getApplicationContext(), dataSPYT);
        listViewSPYT.setAdapter(adapter_spyt);
    }

    private void actionBar() {
        setSupportActionBar(toolbarSPYT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSPYT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIdKH() {
        Intent intent = getIntent();
        id = (String) intent.getSerializableExtra("DHtheoDM");
    }


    private void getSPYT(String idKH) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.urlSPYT + idKH, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    String id = "";
                    String ten = "";
                    int gia = 0;
                    String gioitinh = "";
                    String hinh = "";
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("IDDONGHO");
                            ten = jsonObject.getString("TENDONGHO");
                            gia = jsonObject.getInt("GIABAN");
                            gioitinh = jsonObject.getString("GIOITINH");
                            hinh = jsonObject.getString("HINH");
                            dataSPYT.add(new SANPHAMYEUTHICH(id, ten, gioitinh, hinh, gia));
                            adapter_spyt.notifyDataSetChanged();
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
}