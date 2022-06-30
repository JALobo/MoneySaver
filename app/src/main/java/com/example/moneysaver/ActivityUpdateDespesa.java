package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityUpdateDespesa extends AppCompatActivity {
    private EditText nomeUp, descUp, cusUp;
    private String nome, descricao, custo;
    private FirebaseFirestore db;
    Button updateExpense, sair, apagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_despesa);
        Expenses expenses = (Expenses) getIntent().getSerializableExtra("expenses");
        db = FirebaseFirestore.getInstance();

        nomeUp = findViewById(R.id.editTextUpdateNome);
        descUp = findViewById(R.id.editTextUpdateDescicao);
        cusUp = findViewById(R.id.editTextUpdateCusto);
        updateExpense = findViewById(R.id.btnAtualizarDespesa);
        sair = findViewById(R.id.btnVoltarAtualizarDespesa);
        apagar = findViewById(R.id.btnApagarDespesa);

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
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteExpense(expenses);
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
        //Para saber qual a atualizar
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getCurrentUser().getUid();

        Expenses updatedExpense = new Expenses(nome, descricao, valor,uid);
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
    private void deleteExpense(Expenses exp){
        db.collection("Expenses").document(exp.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityUpdateDespesa.this, "A despesa foi apagada", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ActivityUpdateDespesa.this, "Ocorreu uma falha a apagar a despesa. ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}