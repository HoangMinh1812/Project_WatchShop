package com.example.ql_cuahangdongho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.CheckConnection;
import com.example.ql_cuahangdongho.Util.Server;

public class KhachHangActivity extends AppCompatActivity {

    EditText etTenKH, etSdtKH, etEmailKH;
    Button btnXacNhan, btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        anhXa();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            eventButton();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void anhXa()
    {
        etTenKH = findViewById(R.id.etTenKH);
        etSdtKH = findViewById(R.id.etSdtKH);
        etEmailKH = findViewById(R.id.etEmailKH);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnTroVe = findViewById(R.id.btnTroVe);

    }

    private void eventButton(){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTenKH.getText().toString().trim();
                String sdt = etSdtKH.getText().toString().trim();
                String email = etEmailKH.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0 && email.length()>0)
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlKH, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }else {
                    CheckConnection.showToast_Short(getApplicationContext(), "Hãy kiểm tra lại dữ liệu");
                }

            }
        });
    }
}