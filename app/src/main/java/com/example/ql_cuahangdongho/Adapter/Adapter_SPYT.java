package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Model.DONGHO;
import com.example.ql_cuahangdongho.Model.SANPHAMYEUTHICH;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_SPYT extends BaseAdapter {

    Context context;
    ArrayList<SANPHAMYEUTHICH> dataSPYT;

    public Adapter_SPYT(Context context, ArrayList<SANPHAMYEUTHICH> dataSPYT) {
        this.context = context;
        this.dataSPYT = dataSPYT;
    }

    @Override
    public int getCount() {
        return dataSPYT.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSPYT.get(position);
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
            convertView = inflater.inflate(R.layout.layout_spyt, null);
            viewholder.imgSPYT = convertView.findViewById(R.id.imgSPYT);
            viewholder.tvTenSPYT = convertView.findViewById(R.id.tvTenSPYT);
            viewholder.tvGiaSPYT = convertView.findViewById(R.id.tvGiaSPYT);
            viewholder.tvMotaSPYT = convertView.findViewById(R.id.tvMotaSPYT);
            convertView.setTag(viewholder);
        } else {
            viewholder = (VIEWHOLDER) convertView.getTag();
        }
        SANPHAMYEUTHICH spyt = dataSPYT.get(position);
        viewholder.tvTenSPYT.setText(spyt.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.tvGiaSPYT.setText("Giá: " + decimalFormat.format(spyt.getGia()) + " VNĐ");
        viewholder.tvMotaSPYT.setText(spyt.getMota());
        Picasso.with(context).load(Server.loadHinh + spyt.getHinh())
                .placeholder(R.drawable.no_image)
                .into(viewholder.imgSPYT);
        return convertView;
    }

    public class VIEWHOLDER
    {
        ImageView imgSPYT;
        TextView tvTenSPYT, tvGiaSPYT, tvMotaSPYT;
    }
}
