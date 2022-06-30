package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityMenuPrincipal extends AppCompatActivity {

    Button totalMonetario, despesas, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        totalMonetario = findViewById(R.id. btnTotalMonetario);
        despesas = findViewById(R.id.btnDespesas);
        logoutBtn = findViewById(R.id.idBtnLogout);

        totalMonetario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent do total monetário
                goActivityTotalMonetario();
            }
        });

        despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent das despesas
                goActivityDespesas();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent de logout
                goMainActivity();
            }
        });

    }


    private void goActivityTotalMonetario() {
        Intent totalMonetario = new Intent(this, ActivityTotalMonetario.class);
        startActivity(totalMonetario);
    }

    private void goActivityDespesas() {
        Intent despesas = new Intent(this, ActivityDespesas.class);
        startActivity(despesas);
    }

    private void goMainActivity() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

}