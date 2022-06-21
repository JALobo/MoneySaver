package com.example.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TotalMonetarioAdapter extends RecyclerView.Adapter{

    ArrayList<TotalMonetario> totalMonetarioArrayList;
    Context context;

    public TotalMonetarioAdapter(ArrayList<TotalMonetario> totalMonetarioArrayList, Context context) {
        this.totalMonetarioArrayList = totalMonetarioArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.totalmonetario_layout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;
        TotalMonetario totalMonetario = totalMonetarioArrayList.get(position);
        viewHolderClass.tituloMonetario.setText(totalMonetario.getTitulo());
        viewHolderClass.valorMonetario.setText(SaveDividaETotal.formatCurrency(Double.parseDouble(totalMonetario.getTotalMonetario())));

    }

    @Override
    public int getItemCount() {
        return totalMonetarioArrayList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tituloMonetario, valorMonetario;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tituloMonetario = itemView.findViewById(R.id.tituloTM);
            valorMonetario =  itemView.findViewById(R.id.valorTM);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO APAGAR e update
                    TotalMonetario totalMonetario = totalMonetarioArrayList.get(getAdapterPosition());
                    Intent updateMonetario = new Intent(context,ActivityUpdateTotalMonetario.class);
                    updateMonetario.putExtra("totalMonetario",totalMonetario);
                    context.startActivity(updateMonetario);
                }
            });


        }
    }
}
