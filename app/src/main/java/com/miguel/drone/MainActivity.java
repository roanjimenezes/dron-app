package com.miguel.drone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item1:
                        // Action when item 1 is selected
                        Log.d("TAG", "boton a");
                        return true;
                    case R.id.menu_item2:
                        // Action when item 2 is selected
                        Log.d("TAG", "boton b");
                        return true;
                    case R.id.menu_item3:
                        // Action when item 3 is selected
                        Log.d("TAG", "resetear");
                        return true;
                }
                return false;
            }
        });
        Permisos();
        FirebaseApp.initializeApp(this);
        Iniciar_firebase(findViewById(R.id.bottom_navigation_view));
    }

    public void Iniciar_firebase(View view) {
        DatabaseReference rfBtn = FirebaseDatabase.getInstance().getReference().child("Configuraciones/Estado");
        String V = "1";
        HashMap hashMap = new HashMap();
        hashMap.put("Arranque", V);
        rfBtn.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast toast = Toast.makeText(getApplicationContext(), "Virtual Button ON", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 30);
                toast.show();
            }
        });

    }

    private void Permisos() {

        int PermInternet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int PermNetwork = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int PermWifi = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);

        if (PermInternet == 1) {
            Toast.makeText(this, "Permiso a Internet Concedido " + PermInternet, Toast.LENGTH_SHORT).show();
        }
        if (PermNetwork == 1) {
            Toast.makeText(this, "Permiso a Internet Concedido " + PermInternet, Toast.LENGTH_SHORT).show();
        }
        if (PermWifi == 1) {
            Toast.makeText(this, "Permiso a Internet Concedido " + PermInternet, Toast.LENGTH_SHORT).show();
        }
    }
}