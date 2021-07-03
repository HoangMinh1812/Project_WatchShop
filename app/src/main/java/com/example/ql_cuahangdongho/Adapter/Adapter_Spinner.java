package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Model.MENU;
import com.example.ql_cuahangdongho.Model.SPINNER;
import com.example.ql_cuahangdongho.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_Spinner extends BaseAdapter {

    Context context;
    ArrayList<SPINNER> data;

    public Adapter_Spinner(Context context, ArrayList<SPINNER> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_spinner, null);
        TextView tvSpinner = convertView.findViewById(R.id.tvSpinner);
        SPINNER spinner = data.get(position);
        tvSpinner.setText(spinner.getItem());
        return convertView;

    }
}
