package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Database.DBOrder;
import com.example.noidmerchant.Database.DBProductsInOrder;
import com.example.noidmerchant.GUI.Orders.OrdersDetailActivity;
import com.example.noidmerchant.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsInOrderAdapter extends RecyclerView.Adapter<ProductsInOrderAdapter.CartViewHolder> {
    ArrayList<DBProductsInOrder>list;
    Context context;

    public ProductsInOrderAdapter(ArrayList<DBProductsInOrder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_product,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        DBProductsInOrder DBProductsInOrder = list.get(position);
        holder.txtTensp.setText(DBProductsInOrder.getTensp());
        holder.txtSL.setText("x" + DBProductsInOrder.getSoluong());
        double updatedPrice = DBProductsInOrder.getGiasp();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(updatedPrice);
        holder.txtGia.setText(formattedPrice);
    }
//    private void onClickOrder (DBOrder DBOrder) {
//        Intent intent = new Intent(context, OrdersDetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("dathang", DBOrder);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
//    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTensp;
        private TextView txtSL;
        private TextView txtGia;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTensp = itemView.findViewById(R.id.txt_sp);
            txtSL = itemView.findViewById(R.id.txt_sl);
            txtGia = itemView.findViewById(R.id.txt_giachitiet);
        }
    }
}
