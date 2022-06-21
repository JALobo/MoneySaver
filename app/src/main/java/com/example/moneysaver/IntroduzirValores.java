package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class IntroduzirValores extends AppCompatActivity {

    EditText titulo, valor;
    Button inserirTotal,voltar;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduzir_valores);
        titulo = findViewById(R.id.editTextAdicionarTitulo);
        valor = findViewById(R.id.editTextAdicionarValorMonetario);
        inserirTotal=findViewById(R.id.btnAddValor);
        voltar = findViewById(R.id.btnVoltarAdicionarValor);
        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        inserirTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titulo.getText()==null) {
                    titulo.setError("Ponha um nome da despesa");
                } else if (valor.getText()==null) {
                    valor.setError("Ponha uma descrição");
                }else{
                    addValorMonetarioToFireStore(titulo.getText().toString(),valor.getText().toString());
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
    }
    private void voltar() {
        finish();
    }

    private  void addValorMonetarioToFireStore(String titulo,String valor){
        CollectionReference dbTotalMonetario = db.collection("TotalMonetario");
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getCurrentUser().getUid();

        TotalMonetario totalMonetario = new TotalMonetario(uid,valor,titulo);
        dbTotalMonetario.add(totalMonetario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(IntroduzirValores.this, "O seu total foi adicionado á fireStore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(IntroduzirValores.this, "Não foi possivel adicionar total", Toast.LENGTH_SHORT).show();
            }
        });
    }
}