package com.example.mathgeksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;

public class    ProductActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mfireStoreList;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        firebaseFirestore = FirebaseFirestore.getInstance();

        mfireStoreList = findViewById(R.id.firestorelist);

        Query query = firebaseFirestore.collection("USERS");

        FirestoreRecyclerOptions<ProductsModel> options = new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query, ProductsModel.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.listname.setText(model.getNAME());
                holder.listdesc.setText(model.getEMAIL_ID());
                holder.listpriority.setText(model.getTOTAL_SCORE() + "");
            }
        };

         mfireStoreList.setHasFixedSize(true);
         mfireStoreList.setLayoutManager(new LinearLayoutManager(this));
         mfireStoreList.setAdapter(adapter);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        private TextView listname;
        private TextView listdesc;
        private TextView listpriority;
        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            listname = itemView.findViewById(R.id.list_name);
            listdesc = itemView.findViewById(R.id.list_price);
            listpriority = itemView.findViewById(R.id.list_priority);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}