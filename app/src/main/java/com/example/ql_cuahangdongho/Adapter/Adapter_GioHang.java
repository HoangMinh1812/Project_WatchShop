package com.example.ql_cuahangdongho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_cuahangdongho.Activity.GioHangActivity;
import com.example.ql_cuahangdongho.Activity.MainActivity;
import com.example.ql_cuahangdongho.Model.GIOHANG;
import com.example.ql_cuahangdongho.R;
import com.example.ql_cuahangdongho.Util.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_GioHang extends BaseAdapter {

    Context context;
    ArrayList<GIOHANG> dataGH;

    public Adapter_GioHang(Context context, ArrayList<GIOHANG> dataGH) {
        this.context = context;
        this.dataGH = dataGH;
    }

    @Override
    public int getCount() {
        return dataGH.size();
    }

    @Override
    public Object getItem(int position) {
        return dataGH.get(position);
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
            convertView = inflater.inflate(R.layout.layout_donghotheogiohang, null);
            viewholder.imgDHtheoGH = convertView.findViewById(R.id.imgDHtheoGH);
            viewholder.tvTenDHtheoGH = convertView.findViewById(R.id.tvTenDHtheoGH);
            viewholder.tvGiaDHtheoGH = convertView.findViewById(R.id.tvGiaDHtheoGH);
            viewholder.btnMinus = convertView.findViewById(R.id.btnMinus);
            viewholder.btnValues = convertView.findViewById(R.id.btnValues);
            viewholder.btnPlus = convertView.findViewById(R.id.btnPlus);
            convertView.setTag(viewholder);

        } else {
            viewholder = (VIEWHOLDER) convertView.getTag();
        }
        GIOHANG giohang = (GIOHANG) getItem(position);
        viewholder.tvTenDHtheoGH.setText(giohang.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.tvGiaDHtheoGH.setText(decimalFormat.format(giohang.getGia()) + " VNĐ");
        Picasso.with(context).load(Server.loadHinh + giohang.getHinh())
                .placeholder(R.drawable.no_image)
                .into(viewholder.imgDHtheoGH);
        viewholder.btnValues.setText(giohang.getSoluong() + "");
        int sl = Integer.parseInt(viewholder.btnValues.getText().toString());
        if (sl > 10) {
            viewholder.btnPlus.setVisibility(View.INVISIBLE);
            viewholder.btnMinus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewholder.btnMinus.setVisibility(View.INVISIBLE);
        } else if (sl > 1) {
            viewholder.btnMinus.setVisibility(View.VISIBLE);
            viewholder.btnPlus.setVisibility(View.VISIBLE);
        }
        VIEWHOLDER finalViewholder = viewholder;
        viewholder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slNew = Integer.parseInt(finalViewholder.btnValues.getText().toString()) + 1;
                int sl = MainActivity.dataGH.get(position).getSoluong();
                int gia = MainActivity.dataGH.get(position).getGia();
                MainActivity.dataGH.get(position).setSoluong(slNew);
                int giaNew = (gia * slNew) / sl;
                MainActivity.dataGH.get(position).setGia(giaNew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewholder.tvGiaDHtheoGH.setText(decimalFormat.format(giaNew) + " VNĐ");
                GioHangActivity.eventUtil();
                if (slNew > 9) {
                    finalViewholder.btnPlus.setVisibility(View.INVISIBLE);
                    finalViewholder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewholder.btnValues.setText(String.valueOf(slNew));
                } else {
                    finalViewholder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewholder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewholder.btnValues.setText(String.valueOf(slNew));
                }
            }
        });
        viewholder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slNew = Integer.parseInt(finalViewholder.btnValues.getText().toString()) - 1;
                int sl = MainActivity.dataGH.get(position).getSoluong();
                int gia = MainActivity.dataGH.get(position).getGia();
                MainActivity.dataGH.get(position).setSoluong(slNew);
                int giaNew = (gia * slNew) / sl;
                MainActivity.dataGH.get(position).setGia(giaNew);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewholder.tvGiaDHtheoGH.setText(decimalFormat.format(giaNew) + " VNĐ");
                GioHangActivity.eventUtil();
                if (slNew < 2) {
                    finalViewholder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewholder.btnMinus.setVisibility(View.INVISIBLE);
                    finalViewholder.btnValues.setText(String.valueOf(slNew));
                } else {
                    finalViewholder.btnPlus.setVisibility(View.VISIBLE);
                    finalViewholder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewholder.btnValues.setText(String.valueOf(slNew));
                }
            }
        });
        return convertView;
    }

    public class VIEWHOLDER {
        ImageView imgDHtheoGH;
        TextView tvTenDHtheoGH, tvGiaDHtheoGH;
        Button btnMinus, btnValues, btnPlus;
    }
}
