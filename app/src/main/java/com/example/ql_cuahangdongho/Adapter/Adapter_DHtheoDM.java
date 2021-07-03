package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_DHtheoDM extends BaseAdapter {

    Context context;
    ArrayList<DONGHO> dataDH;

    public Adapter_DHtheoDM(Context context, ArrayList<DONGHO> dataDH) {
        this.context = context;
        this.dataDH = dataDH;
    }

    @Override
    public int getCount() {
        return dataDH.size();
    }

    @Override
    public Object getItem(int position) {
        return dataDH.get(position);
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
            convertView = inflater.inflate(R.layout.layout_donghotheodanhmuc, null);
            viewholder.imgDHtheoDM = convertView.findViewById(R.id.imgDHtheoDM);
            viewholder.tvTenDHtheoDM = convertView.findViewById(R.id.tvTenDHtheoDM);
            viewholder.tvGiaDHtheoDM = convertView.findViewById(R.id.tvGiaDHtheoDM);
            viewholder.tvMotaDHtheoDM = convertView.findViewById(R.id.tvMotaDHtheoDM);
            convertView.setTag(viewholder);
        } else {
            viewholder = (VIEWHOLDER) convertView.getTag();
        }
        DONGHO dongho = (DONGHO) getItem(position);
        viewholder.tvTenDHtheoDM.setText(dongho.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.tvGiaDHtheoDM.setText("Giá: " + decimalFormat.format(dongho.getGia()) + " VNĐ");
        viewholder.tvMotaDHtheoDM.setText(dongho.getGioitinh());
        Picasso.with(context).load(Server.loadHinh + dongho.getHinh())
                .placeholder(R.drawable.no_image)
                .into(viewholder.imgDHtheoDM);
        return convertView;
    }

    public class VIEWHOLDER {
        ImageView imgDHtheoDM;
        TextView tvTenDHtheoDM, tvGiaDHtheoDM, tvMotaDHtheoDM;
    }
}
