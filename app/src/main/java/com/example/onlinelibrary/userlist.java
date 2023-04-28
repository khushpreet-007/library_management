package com.example.onlinelibrary;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class userlist extends AppCompatActivity {

        RecyclerView recyclerView;
        DatabaseReference database;
        MyAdapter myAdapter;
        ArrayList<User> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_userlist);

            recyclerView = findViewById(R.id.recycleview);
            database = FirebaseDatabase.getInstance().getReference();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(userlist.this));

            list = new ArrayList<> ();
            myAdapter = new MyAdapter(userlist.this, list);
            recyclerView.setAdapter(myAdapter);

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();

                    for(DataSnapshot bookinfo: snapshot.child("bookinfo").getChildren()){
                        String getname = bookinfo.child("name1").getValue(String.class);
                        String getauthor = bookinfo.child("id1").getValue(String.class);
//                        User user = dataSnapshot.getValue(User.class);
                        User book = new User(getname, getauthor);

                        list.add(book);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    public void buttonClicked(View view) {

        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "Thread Name 2: " + Thread.currentThread().getName());
                synchronized (this) {
                    try {
                        wait(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(userlist.this, "Download finished...", Toast.LENGTH_SHORT).show();
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(userlist.this, "10 seconds passed since download was finished...", Toast.LENGTH_SHORT).show();
                    }
                }, 10000);

                Log.i(TAG, "run: Download finished.");

            }
        };
//        runnable.run();
        Log.i(TAG, "Thread Name 1: " + Thread.currentThread().getName());
        Thread thread = new Thread(runnable);
        thread.start();

    }



}