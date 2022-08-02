package com.example.finalactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {
    ArrayList<Model> mList;
    Context context;
    public MyAdapter2(Context context,ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {
        Model model = mList.get(position);

        holder.descri.setText(model.getDescription());
        holder.time.setText(model.getTime());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView time,descri;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.date_text);
            descri=itemView.findViewById(R.id.daily_desc);
        }
    }
}
