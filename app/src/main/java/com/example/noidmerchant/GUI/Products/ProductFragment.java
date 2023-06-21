package com.example.noidmerchant.GUI.Products;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noidmerchant.Adapter.Product;
import com.example.noidmerchant.Adapter.ProductAdapter;
import com.example.noidmerchant.databinding.FragmentProductBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class ProductFragment extends Fragment  {
    FragmentProductBinding binding;
    ArrayList<Product> list = new ArrayList<>();
    public ProductFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        ProductAdapter adapter = new ProductAdapter(list,getContext());
        binding.rcvProd.setAdapter(adapter);
        binding.rcvProd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0){ binding.addProd.hide();
            } else { binding.addProd.show(); }
            super.onScrolled(recyclerView, dx, dy);
            }
        });

        String[] categoryKeys = {"-NX9r4LR1f6twnVU28_R", "-NX9ppXXBjY8T0_6wBy5", "-NXDtH8cDFrHbWFztoFk",
                                "-NXE1ZviRaG07k3xa_rl", "-NXOrczCnri2fMR2gUWP", "-NXOrczHNuTFoEhXKwfe",
                                "-NXPMMB0Gglb68aK9Svr"};

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("sanpham");
        Query[] queries = new Query[categoryKeys.length];

        for (int i = 0; i < categoryKeys.length; i++) {
            queries[i] = databaseRef.orderByChild("madm").equalTo(categoryKeys[i]);
            queries[i].addChildEventListener(new ChildEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String name = snapshot.child("tensp").getValue(String.class);
                    String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                    String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                    String quant = String.valueOf(snapshot.child("soluongsp").getValue(Long.class));
                    String des = snapshot.child("motasp").getValue(String.class);
                    String madm = snapshot.child("madm").getValue(String.class);
                    String key = snapshot.getKey();
                    Product product = new Product(name, price, imageUrl,quant,des,key,madm);
                    list.add(product);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    list.clear();
                    databaseRef.removeEventListener(this);
                    databaseRef.addChildEventListener(this);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    databaseRef.removeEventListener(this);
                    databaseRef.addChildEventListener(this);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvProd.setLayoutManager(layoutManager);
        binding.addProd.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProductAdd.class)));
        return binding.getRoot();

                         /////////////////////code tao comment dung xoa lam on//////////////////////////////
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    String name = data.child("tensp").getValue(String.class);
//                    String price = String.valueOf(data.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
//                    String imageUrl = data.child("hinhsp").getValue(String.class);
//                    Product product = new Product(name, price, imageUrl);
//                    list.add(product);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        database.getReference().child("sanpham").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    Product product = dataSnapshot.getValue(Product.class);
//                    product.getCategoryID(dataSnapshot.getKey());
//                    list.add(product);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(getActivity(), ProductDetails.class);
//        startActivity(intent);
//    }
}