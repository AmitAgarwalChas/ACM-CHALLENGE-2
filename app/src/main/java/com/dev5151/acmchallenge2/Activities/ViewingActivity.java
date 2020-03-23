package com.dev5151.acmchallenge2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev5151.acmchallenge2.AmitAdapter;
import com.dev5151.acmchallenge2.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewingActivity extends AppCompatActivity {

    ImageView addButton, backButton;
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<User> list;
    AmitAdapter amitAdapter;
    User deletedUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing);

        reference = FirebaseDatabase.getInstance().getReference().child("users");
        initViews();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AmitActivity.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        displayList();

    }

    private void displayList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<User>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);
                    list.add(user);
                }
                amitAdapter = new AmitAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(amitAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error Occurred...", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                deletedUser = list.get(position);
                list.remove(position);
                amitAdapter.notifyDataSetChanged();
                reference.child(deletedUser.getUserId()).removeValue();
                Snackbar.make(recyclerView, "User removed Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                list.add(position, deletedUser);
                                reference.child(deletedUser.getUserId()).setValue(deletedUser);
                                amitAdapter.notifyDataSetChanged();
                                list.clear();
                            }
                        }).show();
                list.clear();
            }
        }).attachToRecyclerView(recyclerView);

    }


    private void initViews() {
        addButton = findViewById(R.id.btn_add);
        backButton = findViewById(R.id.btn_back);
        recyclerView = findViewById(R.id.recyclerView);
    }
}
