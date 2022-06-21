package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ActivityDespesas extends AppCompatActivity {

    private Double totalValDespesa;//Valores declarados aqui para evitar quebrar o programa
    private Button btnVoltarEditDespesa, btnAdicionarDespesa;
    private TextView txtTotalInserido, txtTotalDespesas, txtTotalFinal;
    private RecyclerView recyclerView;
    private ArrayList<Expenses> expenses;
    private HelperAdapter helperAdapter;
    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;

    private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        loadingPB = findViewById(R.id.idProgressBar);
        expenses = new ArrayList<>();

        btnVoltarEditDespesa = findViewById(R.id.btnVoltarEditDespesa);
        btnAdicionarDespesa = findViewById(R.id.btnUpdateDespesa);

        txtTotalDespesas = findViewById(R.id.txtTotalDespesas);
        txtTotalInserido = findViewById(R.id.txtTotalInserido);
        txtTotalFinal = findViewById(R.id.txtTotalFinal);


        //----------Tentativa de por o Recicler view  a funcionar-------------

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helperAdapter = new HelperAdapter(expenses, this);
        recyclerView.setAdapter(helperAdapter);

        //-----------------------

        btnVoltarEditDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnAdicionarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActivityGerirDespesas();
            }
        });

    }

    private void goActivityGerirDespesas() {
        Intent gerirDespesas = new Intent(this, ActivityGerirDespesas.class);
        startActivity(gerirDespesas);
    }

    private void voltar() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Por a firebase a funcionar---------------------
        txtTotalInserido.setText(SaveDividaETotal.getTotalFormated());
        expenses.clear();
        totalValDespesa = 0.0;
        //valorFinal = 0.0;
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String uid = fAuth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        db.collection("Expenses").whereEqualTo("userId",uid).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // after getting the data we are calling on success method
                // and inside this method we are checking if the received
                // query snapshot is empty or not.
                if (!queryDocumentSnapshots.isEmpty()) {
                    // if the snapshot is not empty we are
                    // hiding our progress bar and adding
                    // our data in a list.
                    loadingPB.setVisibility(View.GONE);
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        // after getting this list we are passing
                        // that list to our object class.
                        Expenses e = d.toObject(Expenses.class);
                        // below is the updated line of code which we have to
                        // add to pass the document id inside our modal class.
                        // we are setting our document id with d.getId() method
                        e.setId(d.getId());

                        // and we will pass this object class
                        // inside our arraylist which we have
                        // created for recycler view.
                        expenses.add(e);
                        totalValDespesa += Double.parseDouble(e.getValExpense());
                    }
                    SaveDividaETotal.setDivida(totalValDespesa.toString());
                    txtTotalDespesas.setText(SaveDividaETotal.getDividaFormated());
                   //valorFinal = Double.parseDouble(SaveDividaETotal.getTotal()) - totalValDespesa;
                    //txtTotalFinal.setText(valorFinal.toString());
                    txtTotalFinal.setText(SaveDividaETotal.getValorFinal());
                    // after adding the data to recycler view.
                    // we are calling recycler view notifuDataSetChanged
                    // method to notify that data has been changed in recycler view.
                    helperAdapter.notifyDataSetChanged();
                } else {
                    loadingPB.setVisibility(View.GONE);
                    // if the snapshot is empty we are displaying a toast message.
                    Toast.makeText(ActivityDespesas.this, "Não foram encontrados dados", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(ActivityDespesas.this, "Falha ao descarregar dados.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}