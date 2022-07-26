package com.example.finalactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    ArrayList<firebasemodel> noteArrayList;
    String UserID;

    public NoteAdapter(Context context, ArrayList<firebasemodel> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notes_layout,parent, false);
        return new NoteAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        firebasemodel fireBasemodel = noteArrayList.get(position);

        holder.title.setText(fireBasemodel.title);
        holder.content.setText(fireBasemodel.content);
        holder.date.setText(fireBasemodel.date);

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView title, content, date;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notetitle);
            content = itemView.findViewById(R.id.noteContent);
            date = itemView.findViewById(R.id.notedate);
        }
    }
}
