package com.example.ql_cuahangdongho.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.ql_cuahangdongho.R;

public class LienHeActivity extends AppCompatActivity {

    Toolbar toolBarLienHe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);

        toolBarLienHe = findViewById(R.id.toolBarLienHe);
        actionBar();
    }

    private void actionBar() {
        setSupportActionBar(toolBarLienHe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarLienHe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}