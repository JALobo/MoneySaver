package com.example.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter {

    ArrayList<Expenses> expensesArrayList;
    Context context;

    public HelperAdapter(ArrayList<Expenses> expensesArrayList, Context context) {
        this.expensesArrayList = expensesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.despesa_layout, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;
        Expenses expenses = expensesArrayList.get(position);
        viewHolderClass.nomeExpense.setText(expenses.getNameExpense());
        viewHolderClass.descDespesa.setText(expenses.getDesExpense());
       // viewHolderClass.custoDespesa.setText(expenses.getValExpense());
        viewHolderClass.custoDespesa.setText(SaveDividaETotal.formatCurrency(Double.parseDouble(expenses.getValExpense())));

    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder  {//TODO update
        TextView nomeExpense, descDespesa, custoDespesa;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nomeExpense = itemView.findViewById(R.id.nomeExpense);
            descDespesa = itemView.findViewById(R.id.descDespesa);
            custoDespesa = itemView.findViewById(R.id.custoDespesa);
            //Para ser possivel clickar nas despesas
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//Para fazer update á despesa
                    Expenses expenses = expensesArrayList.get(getAdapterPosition());
                    Intent updateDespesas = new Intent(context, ActivityUpdateDespesa.class);
                    updateDespesas.putExtra("expenses", expenses);
                    context.startActivity(updateDespesas);
                }
            });
        }


    }
}
