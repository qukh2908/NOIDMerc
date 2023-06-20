package com.example.noidmerchant;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.noidmerchant.GUI.Common.InformationsActivity;
import com.example.noidmerchant.GUI.Common.SettingsActivity;
import com.example.noidmerchant.GUI.Imports.BuyProductActivity;
import com.example.noidmerchant.GUI.Orders.OrdersActivity;
import com.example.noidmerchant.GUI.Products.ProductsActivity;
import com.example.noidmerchant.GUI.Statistics.StatisticsActivity;
import com.example.noidmerchant.databinding.ActivityMainBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ordRef = database.getReference("dathang");

    @Override
    public void onStart() {
        super.onStart();
        //Xem đã có quyền thông báo hay chưa
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            int POST_NOTIFICATIONS_CODE = 1;
            checkPermission(Manifest.permission.POST_NOTIFICATIONS, POST_NOTIFICATIONS_CODE);
        }
        ordRef.orderByKey().addChildEventListener(new ChildEventListener() {
            int nofiId = 0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                String status = Objects.requireNonNull(snapshot.child("tinhtrang").getValue()).toString();
                if(status.equals("Đang chờ xác nhận")) {
                    assert key != null;
                    createNotification("Đơn hàng: " + key.substring(15) + " đang chờ bạn", nofiId++);
                }
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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.noidmerchant.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.productCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductsActivity.class)));

        binding.ordersCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrdersActivity.class)));

        binding.statisticsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StatisticsActivity.class)));

        binding.helpCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BuyProductActivity.class)));

        binding.informationCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InformationsActivity.class)));

        binding.settingsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        } else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void createNotification(String text, int id) {
        String CHANNEL_ID = "NEW_ORDER";
        {
            CharSequence name = "NOID Merchant";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, OrdersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_giaohang)
                .setContentTitle("Khách hàng đang chờ nè!")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
