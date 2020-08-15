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
        cats.add(new Cat("Tom", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Felix", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Hello Kitty", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));

        for (int i = 0; i < MainActivity.found.length; i++){
            if (MainActivity.found[i] == true){
                switch(i) {
                    case 0:
                        cats.get(i).setImageUrl("https://www.hobbydb.com/processed_uploads/subject_photo/subject_photo/image/37012/1518467482-3845-9947/Tom_20Cat_20_Tom_20And_20Jerry__large.jpg");
                        break;
                    case 1:
                        cats.get(i).setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Felix_the_cat.svg/1200px-Felix_the_cat.svg.png");
                        break;
                    case 2:
                        cats.get(i).setImageUrl("https://static.miraheze.org/allthetropeswiki/4/4b/Hello_Kitty_Pink_2981.jpg");
                        break;
                }
            }
        }

        CollectRecViewAdapter adapter = new CollectRecViewAdapter(this);
        adapter.setCats(cats);

        collectRecView.setAdapter(adapter);
        collectRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}