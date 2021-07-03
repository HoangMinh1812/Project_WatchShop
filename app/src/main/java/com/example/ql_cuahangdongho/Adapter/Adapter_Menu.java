package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Model.MENU;
import com.example.ql_cuahangdongho.R;

import java.util.ArrayList;

public class Adapter_Menu extends BaseAdapter {

    Context context;
    ArrayList<MENU> data;

    public Adapter_Menu(Context context, ArrayList<MENU> data) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VIEWHOLDER viewholder = null;
        if(convertView == null)
        {
            viewholder = new VIEWHOLDER();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_menu, null);
            viewholder.imgMenu = convertView.findViewById(R.id.imgMenu);
            viewholder.tvMenu = convertView.findViewById(R.id.tvMenu);
            convertView.setTag(viewholder);
        }
        else
        {
            viewholder= (VIEWHOLDER) convertView.getTag();
        }
        MENU menu = (MENU) getItem(position);
        viewholder.tvMenu.setText(menu.getTenloai());
        viewholder.imgMenu.setImageResource(menu.getHinh());
        return convertView;
    }

    public class VIEWHOLDER
    {
        ImageView imgMenu;
        TextView tvMenu;
    }
}
