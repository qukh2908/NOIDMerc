package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.GUI.Orders.OrdersDetailActivity;
import com.example.noidmerchant.GUI.Products.ProductDetails;
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
        final Orders order = list.get(position);
        holder.txtMaDH.setText(order.madh);
        holder.txtTime.setText(order.thoigiandh);
        holder.txtTinhTrang.setText(order.tinhtrang);
        double updatedPrice = order.getTongtiendh();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(updatedPrice);
        holder.txtGiaDH.setText(formattedPrice);
        holder.txtNamekh.setText(order.tenkh);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOrder(order);
            }
        });
    }
    private void onClickOrder (Orders orders){
        Intent intent = new Intent(context, OrdersDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dathang",orders);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class OrdersViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNamekh,txtGiaDH,txtMaDH,txtTime,txtTinhTrang;
        private CardView cardView;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamekh = itemView.findViewById(R.id.txt_namekh);
            txtGiaDH = itemView.findViewById(R.id.txt_giadh);
            txtMaDH = itemView.findViewById(R.id.txt_madh);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtTinhTrang = itemView.findViewById(R.id.txt_tinhtrangdh);
            cardView = itemView.findViewById(R.id.card_order);
        }
    }
}
