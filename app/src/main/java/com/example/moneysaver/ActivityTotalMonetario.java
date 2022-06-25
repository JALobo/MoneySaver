package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActivityTotalMonetario extends AppCompatActivity {

    private Double valorTotalMonetario;
    private Button btnAdicionar, btnVoltar;
    private TextView txtTotalInserido;
    private RecyclerView recyclerView;
    private ArrayList<TotalMonetario> totalMonetarioArrayList;
    private TotalMonetarioAdapter totalMonetarioAdapter;


    private FirebaseFirestore db;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_monetario);
        totalMonetarioArrayList = new ArrayList<>();
        btnAdicionar = findViewById(R.id.btnAdicionarTM);

        loadingPB = findViewById(R.id.idProgressBar);
        btnVoltar = findViewById(R.id.btnVoltarTotalMonetario);
        txtTotalInserido = findViewById(R.id.txtTotalInserido);


        //txtTotalInserido.setText(SaveDividaETotal.getTotalFormated());

        //----------Tentativa de por o Recicler view  a funcionar-------------

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalMonetarioAdapter = new TotalMonetarioAdapter(totalMonetarioArrayList, this);
        recyclerView.setAdapter(totalMonetarioAdapter);

        //-----------------------


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActivityIntroduzirValores();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        db = FirebaseFirestore.getInstance();
        totalMonetarioArrayList.clear();
        valorTotalMonetario = 0.0;
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getCurrentUser().getUid();
        db.collection("TotalMonetario").whereEqualTo("userId",uid).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    loadingPB.setVisibility(View.GONE);
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        TotalMonetario tM = d.toObject(TotalMonetario.class);
                        tM.setId(d.getId());
                        totalMonetarioArrayList.add(tM);
                        valorTotalMonetario += Double.parseDouble(tM.getTotalMonetario());

                    }
                    SaveDividaETotal.setTotal(valorTotalMonetario.toString());
                    txtTotalInserido.setText(SaveDividaETotal.getTotalFormated());
                    totalMonetarioAdapter.notifyDataSetChanged();
                } else {
                    loadingPB.setVisibility(View.GONE);
                    // if the snapshot is empty we are displaying a toast message.
                    Toast.makeText(ActivityTotalMonetario.this, "Não foram encontrados dados", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityTotalMonetario.this, "Falha ao descarregar dados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void voltar() {
        finish();
    }

    private void goActivityIntroduzirValores() {
        Intent introduzirValores = new Intent(this, IntroduzirValores.class);
        startActivity(introduzirValores);
    }



}