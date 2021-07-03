package com.example.ql_cuahangdongho.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.Adapter.Adapter_DongHo;
import com.example.ql_cuahangdongho.Adapter.Adapter_Menu;
import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.GIOHANG;
import com.example.ql_cuahangdongho.Model.MENU;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.CheckConnection;
import com.example.ql_cuahangdongho.Util.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolBarTrangChu;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ListView listViewMenu;
    ArrayList<MENU> dataMenu;
    Adapter_Menu adapter_menu;

    GridView gridViewDH;
    ArrayList<DONGHO> dataDH = new ArrayList<>();
    Adapter_DongHo adapter_dongHo;

    public static ArrayList<GIOHANG> dataGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            actionBar();
            actionViewFlipper();
            getDongHo();
            catchOnItemListView();
            catchOnItemGridView();
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
        toolBarTrangChu = findViewById(R.id.toolBarTrangChu);
        viewFlipper = findViewById(R.id.viewFlipper);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        listViewMenu = findViewById(R.id.listViewMenu);
        dataMenu = loadMenu();
        adapter_menu = new Adapter_Menu(getApplicationContext(), dataMenu);
        listViewMenu.setAdapter(adapter_menu);

        gridViewDH = findViewById(R.id.gridViewDH);
        adapter_dongHo = new Adapter_DongHo(getApplicationContext(), dataDH);
        gridViewDH.setAdapter(adapter_dongHo);

        if (dataGH != null) {

        } else {
            dataGH = new ArrayList<>();
        }

    }

    public ArrayList<MENU> loadMenu() {
        dataMenu = new ArrayList<>();
        dataMenu.add(new MENU("Trang Chủ", R.drawable.ic_baseline_home_24));
        dataMenu.add(new MENU("Danh Mục", R.drawable.ic_baseline_category_24));
        dataMenu.add(new MENU("Sản Phẩm Yêu Thích", R.drawable.ic_baseline_toys_24));
        dataMenu.add(new MENU("Giỏ hàng", R.drawable.ic_baseline_shopping_cart_24));
        dataMenu.add(new MENU("Tài Khoản", R.drawable.ic_baseline_account_circle_24));
        dataMenu.add(new MENU("Liên Hệ", R.drawable.ic_baseline_call_24));
        dataMenu.add(new MENU("Địa Chỉ", R.drawable.ic_baseline_map_24));
        return dataMenu;
    }

    private void actionBar() {
        setSupportActionBar(toolBarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarTrangChu.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolBarTrangChu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void actionViewFlipper() {
        ArrayList<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.banner1);
        mangquangcao.add(R.drawable.banner2);
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void getDongHo() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlDH, new Response.Listener<JSONArray>() {
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
                            dataDH.add(new DONGHO(id, ten, xuatxu, gioitinh, idloai, idthuonghieu, hinh, gia));
                            adapter_dongHo.notifyDataSetChanged();
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
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), DanhMucActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), SPYTActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), KhachHangActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), LienHeActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(getApplicationContext(), DiaChiActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void catchOnItemGridView() {
        gridViewDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietActivity.class);
                intent.putExtra("chitiet", dataDH.get(position));
                startActivity(intent);
            }
        });
    }

}