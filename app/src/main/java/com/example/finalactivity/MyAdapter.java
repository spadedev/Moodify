package com.example.finalactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalactivity.Journal;
import com.example.finalactivity.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends FirestoreRecyclerAdapter<Journal, MyAdapter.MyViewHolder> {
    
    private OnItemClickListener listener;

    public MyAdapter (@NonNull FirestoreRecyclerOptions<Journal> options) {
        
        super(options);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_itemview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Journal model) {
        holder.journaltitleOutput.setText(model.getTitle());
        holder.descriptionOutput.setText(model.getDescription());
        String formatedTime = DateFormat.getDateTimeInstance().format(model.getCreatedTime());
        holder.timeOutput.setText(formatedTime);

    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView journaltitleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            journaltitleOutput = itemView.findViewById(R.id.journaltitleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);

        void onItemClick(DocumentSnapshot snapshot, int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}