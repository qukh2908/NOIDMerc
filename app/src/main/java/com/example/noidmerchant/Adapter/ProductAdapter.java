package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Database.DBProductToDisplay;
import com.example.noidmerchant.GUI.Products.ProductDetails;
import com.example.noidmerchant.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    ArrayList<DBProductToDisplay> list;
    Context context;
//    private final RecyclerViewInterface recyclerViewInterface;


    public ProductAdapter(ArrayList<DBProductToDisplay> list, Context context) {
        this.list = list;
        this.context = context;
//        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final DBProductToDisplay DBProductToDisplay = list.get(position);
        holder.edtName.setText(DBProductToDisplay.getName());
        double priceDouble = Double.parseDouble(DBProductToDisplay.getPrice());
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedGiatiensp = decimalFormat.format(priceDouble);
        holder.edtPrice.setText(formattedGiatiensp);
        String imageUrl = DBProductToDisplay.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.imgProd);
        } else {
            holder.imgProd.setImageResource(R.mipmap.ic_launcher);
        }
        holder.cardView.setOnClickListener(view -> onClickDetail(DBProductToDisplay));
    }
    private void onClickDetail(DBProductToDisplay DBProductToDisplay){
        Intent intent = new Intent(context, ProductDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sanpham", DBProductToDisplay);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

//    private void onClickGoToDetail(Product product){
//        Intent intent = new Intent(context, DetailsProduct.class);
//        context.startActivity(intent);
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgProd;
        private final TextView edtName;
        private final TextView edtPrice;
        private CardView cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProd = itemView.findViewById(R.id.img_ds);
            cardView = itemView.findViewById(R.id.cardView);
            edtName = itemView.findViewById(R.id.txt_sp);
            edtPrice = itemView.findViewById(R.id.txt_gia);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (recyclerViewInterface != null) {
//                        int pos = ProductViewHolder.this.getBindingAdapterPosition();
//                        if (pos != RecyclerView.NO_POSITION) {
//                            recyclerViewInterface.onItemClick(pos);
//                        }
//                    }
//                }
//            });
        }
    }
}
