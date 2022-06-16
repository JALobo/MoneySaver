package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityUpdateDespesa extends AppCompatActivity {
    private EditText nomeUp, descUp, cusUp;
    private String nome, descricao, custo;
    private FirebaseFirestore db;
    Button updateExpense, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_despesa);
        Expenses expenses = (Expenses) getIntent().getSerializableExtra("expenses");
        db = FirebaseFirestore.getInstance();

        nomeUp = findViewById(R.id.editTextUpdateNome);
        descUp = findViewById(R.id.editTextUpdateDescricao);
        cusUp = findViewById(R.id.editTextUpdateValor);
        updateExpense = findViewById(R.id.btnUpdateDespesa);
        sair = findViewById(R.id.btnVoldarUpdateDespesas);

        nomeUp.setText(expenses.getNameExpense());
        descUp.setText(expenses.getDesExpense());
        cusUp.setText(expenses.getValExpense());

        updateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = nomeUp.getText().toString();
                descricao = descUp.getText().toString();
                custo = cusUp.getText().toString();

                if (TextUtils.isEmpty(nome)) {
                    nomeUp.setError("Porfavor escreva um nome");
                } else if (TextUtils.isEmpty(descricao)) {
                    descUp.setError("Por favor escreva uma descrição");
                } else if (TextUtils.isEmpty(custo)) {
                    cusUp.setError("Por favor insira um valor");
                } else {
                    updateExpenses(expenses, nome, descricao, custo);
                }

            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }


    private void updateExpenses(Expenses expe, String nome, String descricao, String valor) {
        Expenses updatedExpense = new Expenses(nome, descricao, valor);
        db.collection("Expenses").document(expe.getId()).set(updatedExpense).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // on successful completion of this process
                // we are displaying the toast message.
                Toast.makeText(ActivityUpdateDespesa.this, "A despesa foi atualizada", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityUpdateDespesa.this, "Falha ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void voltar() {
        finish();
    }
}