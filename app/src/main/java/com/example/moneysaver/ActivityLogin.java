package com.example.moneysaver;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {
    Button entrar;
    EditText email, password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        entrar = findViewById(R.id.btnEntrar);
        email = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

        mAuth = FirebaseAuth.getInstance();


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUserAccount();

            }
        });

    }

    private void loginUserAccount() {

        String email, password;
        email = this.email.getText().toString();
        password = this.password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Por favor escreva email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Por favor escreva password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                                    "Login successful!!",
                                    Toast.LENGTH_LONG)
                            .show();

                    // if sign-in is successful
                    // intent to home activity
                    Intent intent
                            = new Intent(ActivityLogin.this,
                            ActivityMenuPrincipal.class);
                    startActivity(intent);
                } else {

                    // sign-in failed
                    Toast.makeText(getApplicationContext(),
                                    "Login failed!!",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }
}
