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

public class ActivityRegisto extends AppCompatActivity {

    EditText email, password, repetirPassword;
    Button create;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);
        email = findViewById(R.id.txtUsernameReg);
        password = findViewById(R.id.txtPasswordReg);
        repetirPassword = findViewById(R.id.txtRepPasswordReg);
        create = findViewById(R.id.btnRegistar);



        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
                //finish();
            }
        });


    }

    private void registerNewUser() {

        // show the visibility of progress bar to show loading

        // Take the value of two edit texts in Strings
        String email, password, repetirPassword;
        email = this.email.getText().toString();
        password = this.password.getText().toString();
        repetirPassword = this.repetirPassword.getText().toString();


        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Por favor inserir nome de utilizador!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Por favor inserir password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(repetirPassword)) {
            Toast.makeText(getApplicationContext(),
                            "Por favor repita a password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (!password.equals(repetirPassword)) {
            Toast.makeText(getApplicationContext(),
                            "As passwords são diferentes!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                            .show();

                    // if the user created intent to login activity
                    Intent intent
                            = new Intent(ActivityRegisto.this,
                            MainActivity.class);
                    startActivity(intent);
                } else {

                    // Registration failed
                    Toast.makeText(
                                    getApplicationContext(),
                                    "O registo falhou!!"
                                            + "Tente mais tarde",
                                    Toast.LENGTH_LONG)
                            .show();

                }
            }
        });
    }
}
