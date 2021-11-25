package com.example.mathgeksfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RankAdapter extends FirestoreRecyclerAdapter<Rank, RankAdapter.RankHolder> {

    public RankAdapter(@NonNull FirestoreRecyclerOptions<Rank> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RankHolder holder, int position, @NonNull Rank model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewPriority.setText(model.getPriority() + "");
    }

    @NonNull
    @Override
    public RankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item,
                parent,false);
        return new RankHolder(view);
    }

    class RankHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;

        public RankHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
