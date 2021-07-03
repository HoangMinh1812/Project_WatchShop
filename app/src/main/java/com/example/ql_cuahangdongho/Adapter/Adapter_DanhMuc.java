package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Model.DANHMUC;
import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.MENU;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_DanhMuc extends BaseAdapter {

    Context context;
    ArrayList<DANHMUC> data;

    public Adapter_DanhMuc(Context context, ArrayList<DANHMUC> data) {
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
        if (convertView == null) {
            viewholder = new VIEWHOLDER();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_danhmuc, null);
            viewholder.imgDM = convertView.findViewById(R.id.imgDM);
            viewholder.tvIdDM = convertView.findViewById(R.id.tvIdDM);
            viewholder.tvTenDM = convertView.findViewById(R.id.tvTenDM);
            convertView.setTag(viewholder);
        } else {
            viewholder = (VIEWHOLDER) convertView.getTag();
        }
        DANHMUC danhmuc = (DANHMUC) getItem(position);
        viewholder.tvIdDM.setText(danhmuc.getIddm());
        viewholder.tvTenDM.setText(danhmuc.getTendm());
        Picasso.with(context).load(Server.loadHinh + danhmuc.getHinhdm())
                .placeholder(R.drawable.no_image)
                .into(viewholder.imgDM);
        return convertView;
    }

    public class VIEWHOLDER {
        ImageView imgDM;
        TextView tvIdDM, tvTenDM;
    }
}
