package com.miguel.drone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    DatabaseReference configurar_estado;
    TextView datos_sensores;
    String posicionActual = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos_sensores = findViewById(R.id.Text_datos_sensores);
        Permisos();
        FirebaseApp.initializeApp(this);
        this.escucharPosicionActual();
    }

    private void escucharPosicionActual() {
        configurar_estado = FirebaseDatabase.getInstance().getReference().child("Configuraciones/Estado");
        configurar_estado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                posicionActual = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                posicionActual = "0";
            }
        });
    }

    public void moverPosicionA(View view) {
        // ya estamos en la posicion 1 entonces no debemos actualizar
        if (this.posicionActual == "1") {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Arranque", "1");
        configurar_estado.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast toast = Toast.makeText(getApplicationContext(), "Posicion actualizada: Posicion 1", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 30);
                toast.show();
            }
        });
    }

    public void moverPosicionB(View view) {
        // ya estamos en la posicion 2 entonces no debemos actualizar
        if (this.posicionActual == "2") {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Arranque", "2");
        configurar_estado.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast toast = Toast.makeText(getApplicationContext(), "Posicion actualizada: Posicion 2", Toast.LENGTH_SHORT);
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