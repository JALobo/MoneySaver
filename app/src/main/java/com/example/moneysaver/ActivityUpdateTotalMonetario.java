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

public class ActivityUpdateTotalMonetario extends AppCompatActivity {

    private EditText tituloUPD, valorUPD;
    private String titulo, valor;
    private Button updateTotal, apagarTotal, sair;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_total_monetario);
        TotalMonetario totalMonetario = (TotalMonetario) getIntent().getSerializableExtra("totalMonetario");
        db = FirebaseFirestore.getInstance();

        tituloUPD = findViewById(R.id.editTextUpdateTitulo);
        valorUPD = findViewById(R.id.editTextUpdateValorMonetario);
        updateTotal = findViewById(R.id.btnAtualizarTotalMonetario);
        apagarTotal = findViewById(R.id.btnApagarTotalMonetario);
        sair = findViewById(R.id.btnVoltarUpdateTotalMonetario);

        tituloUPD.setText(totalMonetario.getTitulo());
        valorUPD.setText(totalMonetario.getTotalMonetario());

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        updateTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo = tituloUPD.getText().toString();
                valor = valorUPD.getText().toString();
                if (TextUtils.isEmpty(titulo)) {
                    tituloUPD.setError("Porfavor escreva um titulo");
                } else if (TextUtils.isEmpty(valor)) {
                    valorUPD.setError("Por favor escreva valor");
                }else{
                    updateTotal(totalMonetario, titulo,valor);

                }

            }
        });

        apagarTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Apagar
                deleteTotal(totalMonetario);
            }
        });

    }

    private void voltar() {
        finish();
    }

    private void deleteTotal(TotalMonetario tM){
        db.collection("TotalMonetario").document(tM.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ActivityUpdateTotalMonetario.this, "O total foi apagado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityUpdateTotalMonetario.this, "Ocorreu uma falha a apagar o total.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateTotal(TotalMonetario tM, String titulo, String valor){

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getCurrentUser().getUid();

        TotalMonetario updatedMonetario = new TotalMonetario(uid, valor, titulo);
        db.collection("TotalMonetario").document(tM.getId()).set(updatedMonetario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ActivityUpdateTotalMonetario.this, "O total foi atualizado.", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityUpdateTotalMonetario.this, "Falha ao atualizar", Toast.LENGTH_SHORT).show();

            }
        });

    }
}