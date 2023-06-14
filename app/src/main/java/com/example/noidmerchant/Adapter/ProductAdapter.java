package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Interface.RecyclerViewInterface;
import com.example.noidmerchant.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    ArrayList<Product> list;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;


    public ProductAdapter(ArrayList<Product> list, Context context,RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view,recyclerViewInterface);
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
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickGoToDetail(product);
//            }
//        });
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

        public ProductViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imgProd = itemView.findViewById(R.id.img_ds);
//            CardView cardView = itemView.findViewById(R.id.cardView);
            edtName = itemView.findViewById(R.id.txt_sp);
            edtPrice = itemView.findViewById(R.id.txt_gia);

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null)
                {
                    int  pos = getBindingAdapterPosition();
                    if (pos!= RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            });
        }
    }
}
