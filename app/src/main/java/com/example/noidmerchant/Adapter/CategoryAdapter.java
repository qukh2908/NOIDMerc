package com.example.noidmerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private ArrayList<Category> list;
    Context context;

    public CategoryAdapter(ArrayList<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danhmuc,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = list.get(position);
        holder.txtName.setText(category.getTendm());
    }

    @Override
    public int getItemCount() {
            return list.size();

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCt;
        private TextView txtName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);

        }
    }
}
