package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends  RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>{
    private ArrayList<Orders> list;
    private Context context;




    public OrdersAdapter(Context context, ArrayList<Orders> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        Orders order = list.get(position);
        holder.txtMaDH.setText(order.madh);
        holder.txtTime.setText(order.thoigiandh);
        holder.txtTinhTrang.setText(order.tinhtrang);
        double updatedPrice = order.getTongtiendh();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(updatedPrice);
        holder.txtGiaDH.setText(formattedPrice);
        holder.txtNamekh.setText(order.makh);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNamekh,txtGiaDH,txtMaDH,txtTime,txtTinhTrang;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamekh = itemView.findViewById(R.id.txt_namekh);
            txtGiaDH = itemView.findViewById(R.id.txt_giadh);
            txtMaDH = itemView.findViewById(R.id.txt_madh);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtTinhTrang = itemView.findViewById(R.id.txt_tinhtrangdh);

        }
    }
}
