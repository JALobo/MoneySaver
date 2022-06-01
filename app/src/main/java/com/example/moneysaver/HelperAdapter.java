package com.example.moneysaver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter {

    ArrayList<Expenses> expensesArrayList;

    public HelperAdapter(ArrayList<Expenses> expensesArrayList) {
        this.expensesArrayList = expensesArrayList;
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
        viewHolderClass.custoDespesa.setText(expenses.getValExpense());

    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView nomeExpense, descDespesa, custoDespesa;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nomeExpense = itemView.findViewById(R.id.nomeExpense);
            descDespesa = itemView.findViewById(R.id.descDespesa);
            custoDespesa = itemView.findViewById(R.id.custoDespesa);
        }
    }
}
