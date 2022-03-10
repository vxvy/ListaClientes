package com.example.listaclientes.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listaclientes.R;
import com.example.listaclientes.pojos_gson.Cliente;

import java.util.List;

public class RvAdapterClients extends RecyclerView.Adapter<RvAdapterClients.ViewHolderClients> {

    private List<Cliente> clientList;
    private Context context;


    public RvAdapterClients(List<Cliente> clientList, Context context) {
        this.clientList = clientList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderClients onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rv = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new ViewHolderClients(rv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClients holder, int position) {
        holder.cliName.setText(clientList.get(position).getNombre());
        holder.cliPhone.setText(clientList.get(position).getTelefono());
        holder.cliPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_CALL,
                        Uri.parse("tel:" + holder.cliPhone.getText().toString()));
                context.startActivity(intent);
            }
        });


        holder.cliEmail.setText(clientList.get(position).getEmail());

        if (clientList.get(position).getVisitado()) {
            holder.cliVisited.setImageResource(R.drawable.visited_yes);
            holder.cliVisited.setImageTintList(ResourcesCompat.getColorStateList(context.getResources(), R.color.md_theme_light_secondary, context.getTheme()));
        } else {
            holder.cliVisited.setImageResource(R.drawable.visited_no);
            holder.cliVisited.setImageTintList(ResourcesCompat.getColorStateList(context.getResources(), R.color.md_theme_light_tertiary, context.getTheme()));
        }
    }


    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ViewHolderClients extends RecyclerView.ViewHolder {

        private TextView cliName;
        private TextView cliPhone;
        private TextView cliEmail;
        private ImageView cliVisited;

        public ViewHolderClients(@NonNull View itemView) {
            super(itemView);
            cliName = itemView.requireViewById(R.id.txv_name);
            cliPhone = itemView.requireViewById(R.id.txv_phone);
            cliEmail = itemView.requireViewById(R.id.txv_email);
            cliVisited = itemView.requireViewById(R.id.iv_visited);

        }
    }
}
