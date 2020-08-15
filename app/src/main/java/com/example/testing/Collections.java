package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Collections extends AppCompatActivity {

    private RecyclerView collectRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        collectRecView = findViewById(R.id.collectRecView);

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Tom", "https://www.hobbydb.com/processed_uploads/subject_photo/subject_photo/image/37012/1518467482-3845-9947/Tom_20Cat_20_Tom_20And_20Jerry__large.jpg"));
        cats.add(new Cat("Felix", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Felix_the_cat.svg/1200px-Felix_the_cat.svg.png"));
        cats.add(new Cat("Hello Kitty", "https://static.miraheze.org/allthetropeswiki/4/4b/Hello_Kitty_Pink_2981.jpg"));


        CollectRecViewAdapter adapter = new CollectRecViewAdapter(this);
        adapter.setCats(cats);

        collectRecView.setAdapter(adapter);
        collectRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}