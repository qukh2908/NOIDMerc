package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    ArrayList<Product> list;
    Context context;

    public ProductAdapter(ArrayList<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = list.get(position);
        holder.edtName.setText(product.getName());
        double priceDouble = Double.parseDouble(product.getPrice());
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedGiatiensp = decimalFormat.format(priceDouble);
        holder.edtPrice.setText(formattedGiatiensp);
        String imageUrl = product.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.imgProd);
        } else {
            // Handle case when imageUrl is empty or null
            // For example, you can set a default image to ImageView
            holder.imgProd.setImageResource(R.drawable.noidea);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProd;
        private TextView edtName, edtPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProd = itemView.findViewById(R.id.img_ds);
            edtName = itemView.findViewById(R.id.txt_sp);
            edtPrice = itemView.findViewById(R.id.txt_gia);
        }
    }
}
