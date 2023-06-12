package com.example.noidmerchant.GUI.Products;

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
import com.example.noidmerchant.Interface.RecyclerViewInterface;
import com.example.noidmerchant.databinding.FragmentProductBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class ProductFragment extends Fragment implements RecyclerViewInterface {
    FragmentProductBinding binding;
    ArrayList<Product> list = new ArrayList<>();

//    FirebaseDatabase database;
//    String uid ;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String cafe = "-NX9r4LR1f6twnVU28_R";
        String smoothies = "-NX9ppXXBjY8T0_6wBy5";
        String tea = "-NXDtH8cDFrHbWFztoFk";
        String milktea = "-NXE1ZviRaG07k3xa_rl";
        String Snack = "-NXOrczCnri2fMR2gUWP";
        String pakage = "-NXOrczCnri2fMR2gUWP";
        String other = "-NXPMMB0Gglb68aK9Svr";
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("sanpham");
        Query query = databaseRef.orderByChild("madm").equalTo(cafe);
        Query query1 = databaseRef.orderByChild("madm").equalTo(smoothies);
        Query query2 = databaseRef.orderByChild("madm").equalTo(tea);
        Query query3 = databaseRef.orderByChild("madm").equalTo(milktea);
        Query query4 = databaseRef.orderByChild("madm").equalTo(Snack);
        Query query5 = databaseRef.orderByChild("madm").equalTo(pakage);
        Query query6 = databaseRef.orderByChild("madm").equalTo(other);

        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false);
        ProductAdapter adapter = new ProductAdapter(list,getContext(),this);
        binding.rcvProd.setAdapter(adapter);
        binding.rcvProd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    binding.addProd.hide();
                }else {
                    binding.addProd.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //item smoothies
        query1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item cafe
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp"). getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item tea
        query2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item milktea
        query3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item snack
        query4.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item package
        query5.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //item other
        query6.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name = snapshot.child("tensp").getValue(String.class);
                String price = String.valueOf(snapshot.child("giasp").getValue(Long.class)); // đối với dạng số "50000" // dạng string
                String imageUrl = snapshot.child("hinhsp").getValue(String.class);
                Product product = new Product(name, price, imageUrl);
                list.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvProd.setLayoutManager(layoutManager);
        binding.addProd.setOnClickListener(v -> startActivity(new Intent(getActivity(),ProductsAdd.class)));
        return binding.getRoot();
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        startActivity(intent);
    }
}