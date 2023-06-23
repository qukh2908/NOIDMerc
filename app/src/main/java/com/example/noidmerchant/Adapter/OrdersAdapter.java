package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.GUI.Orders.OrdersDetailActivity;
import com.example.noidmerchant.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersAdapter extends  RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>{
    private ArrayList<DBOrder> ordersList;
    private Context context;
    public OrdersAdapter(Context context, ArrayList<DBOrder> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }
    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        return new OrdersViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        DBOrder order = ordersList.get(position);
        holder.txtMaDH.setText(order.getMadh());
        holder.txtTime.setText(order.getThoigiandh());
        holder.txtTinhTrang.setText(order.getTinhtrang());
        switch (order.getTinhtrang()) {
            default:
                holder.table_order.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case "Đã hủy":
                holder.table_order.setBackgroundColor(Color.parseColor("#FF9191"));
                break;
            case "Đã giao":
                holder.table_order.setBackgroundColor(Color.parseColor("#BBFF91"));
                break;
            case "Đang giao":
                holder.table_order.setBackgroundColor(Color.parseColor("#FFF48D"));
                break;
        }
        double updatedPrice = order.getTongtiendh();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(updatedPrice);
        holder.txtGiaDH.setText(formattedPrice);
        holder.txtNamekh.setText(order.getTenkh());
        holder.cardView.setOnClickListener(view -> onClickOrder(order));
    }
    private void onClickOrder (DBOrder DBOrder){
        Intent intent = new Intent(context, OrdersDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dathang", DBOrder);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return ordersList.size();
    }
    public class OrdersViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNamekh,txtGiaDH,txtMaDH,txtTime,txtTinhTrang;
        private CardView cardView;
        private TableLayout table_order;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamekh = itemView.findViewById(R.id.txt_namekh);
            txtGiaDH = itemView.findViewById(R.id.txt_giadh);
            txtMaDH = itemView.findViewById(R.id.txt_madh);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtTinhTrang = itemView.findViewById(R.id.txt_tinhtrangdh);
            cardView = itemView.findViewById(R.id.card_order);
            table_order = itemView.findViewById(R.id.table_order);
        }
    }
}
