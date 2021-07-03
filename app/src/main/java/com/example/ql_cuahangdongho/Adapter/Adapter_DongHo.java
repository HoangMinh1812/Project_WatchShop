package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.MENU;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_DongHo extends BaseAdapter {

    Context context;
    ArrayList<DONGHO> data;

    public Adapter_DongHo(Context context, ArrayList<DONGHO> data) {
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
            convertView = inflater.inflate(R.layout.layout_dongho, null);
            viewholder.imgDongHo = convertView.findViewById(R.id.imgDH);
            viewholder.tvTenDongHo = convertView.findViewById(R.id.tvTenDH);
            viewholder.tvGiaDongHo = convertView.findViewById(R.id.tvGiaDH);
            convertView.setTag(viewholder);
        } else {
            viewholder = (VIEWHOLDER) convertView.getTag();
        }
        DONGHO dongho = data.get(position);
        viewholder.tvTenDongHo.setText(dongho.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.tvGiaDongHo.setText("Giá: " + decimalFormat.format(dongho.getGia()) + " VNĐ");
        Picasso.with(context).load(Server.loadHinh + dongho.getHinh())
                .placeholder(R.drawable.no_image)
                .into(viewholder.imgDongHo);
        return convertView;
    }


    public class VIEWHOLDER {
        ImageView imgDongHo;
        TextView tvTenDongHo, tvGiaDongHo;
    }
}
