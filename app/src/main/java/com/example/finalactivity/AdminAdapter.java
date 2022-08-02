package com.example.finalactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {

    Context context;
    ArrayList<AdminUser> adminUserArrayList;

    public AdminAdapter(Context context, ArrayList<AdminUser> adminUserArrayList) {
        this.context = context;
        this.adminUserArrayList = adminUserArrayList;
    }

    @NonNull
    @Override
    public AdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.admin_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.MyViewHolder holder, int position) {
        AdminUser adminUser = adminUserArrayList.get(position);

        holder.fName.setText(adminUser.fName);
        holder.email.setText(adminUser.email);
        holder.password.setText(adminUser.password);

    }

    @Override
    public int getItemCount() {
        return adminUserArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView fName, email, password;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fName = itemView.findViewById(R.id.admin_name);
            email = itemView.findViewById(R.id.admin_email);
            password = itemView.findViewById(R.id.admin_password);
        }
    }
}
