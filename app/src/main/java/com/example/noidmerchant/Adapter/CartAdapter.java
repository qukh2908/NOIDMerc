package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList<Cart>list;
    private Context context;

    public CartAdapter(ArrayList<Cart> list, Context context) {
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
        Cart cart = list.get(position);
        holder.txtTensp.setText(cart.getTensp());
        holder.txtSL.setText(String.valueOf(cart.getSoluong()));
        double updatedPrice = cart.getGiasp();
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(updatedPrice);
        holder.txtGia.setText(formattedPrice);
    }

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
