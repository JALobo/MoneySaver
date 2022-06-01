package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ActivityGerirDespesas extends AppCompatActivity {

    EditText editTextTextNome,editTextTextDescricao,editTextValor;
    Button btnAdicionarDespesa, btnVoldarGerirDespesas;

    //TODO Substituir isto depois pelo o que que vem da base de dados
    //Para ter o ListView a funcionar
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerir_despesas);
        btnAdicionarDespesa = findViewById(R.id.btnAdicionarDespesa);
        btnVoldarGerirDespesas = findViewById(R.id.btnVoldarGerirDespesas);
        editTextTextNome =  findViewById(R.id.editTextTextNome);
        editTextTextDescricao = findViewById(R.id.editTextTextDescricao);
        editTextValor = findViewById(R.id.editTextValor);

        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        btnVoldarGerirDespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnAdicionarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextTextNome.getText()==null) {
                    editTextTextNome.setError("Ponha um nome da despesa");
                } else if (editTextTextDescricao.getText()==null) {
                    editTextTextDescricao.setError("Ponha uma descrição");
                } else if (editTextValor.getText()==null) {
                    editTextValor.setError("Ponha um custo");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(editTextTextNome.getText().toString(), editTextTextDescricao.toString(),editTextValor.toString() );
                }

            }
        });


    }
    private void addDataToFirestore(String nome, String decricao, String valor) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCourses = db.collection("Expenses");

        // adding our data to our courses object class.
        //Courses courses = new Courses(courseName, courseDescription, courseDuration);
        Expenses expenses = new Expenses(nome,decricao,valor);

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(expenses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(ActivityGerirDespesas.this, "A sua despesa foi adicionada á fireStore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(ActivityGerirDespesas.this, "Não foi possivel adicionar despesa \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void voltar() {
        finish();
    }
}