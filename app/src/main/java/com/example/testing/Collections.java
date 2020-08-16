package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
        cats.add(new Cat("Pusheen", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Garfield", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Cat in the Hat", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Puss in Boots", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("Cheshire Cat", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));
        cats.add(new Cat("CatDog", "https://icon-library.com/images/white-question-mark-icon-png/white-question-mark-icon-png-11.jpg"));



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
                    case 3:
                        cats.get(i).setImageUrl("https://www.kindpng.com/picc/m/41-415250_how-to-draw-pusheen-the-cat-cartoon-cat.png");
                        break;
                    case 4:
                        cats.get(i).setImageUrl("https://vignette.wikia.nocookie.net/character-stats-and-profiles/images/0/09/Garfield.png/revision/latest/scale-to-width-down/340?cb=20170701143047");
                        break;
                    case 5:
                        cats.get(i).setImageUrl("https://www.seussville.com/wp-content/uploads/2019/11/character-Cat-in-the-Hat.png");
                        break;
                    case 6:
                        cats.get(i).setImageUrl("https://static.onecms.io/wp-content/uploads/sites/20/2011/10/puss-boots-300.jpg");
                        break;
                    case 7:
                        cats.get(i).setImageUrl("https://i.pinimg.com/originals/ec/1f/95/ec1f9587e0bb9bb9a24b320ccd46972e.jpg");
                        break;
                    case 8:
                        cats.get(i).setImageUrl("https://www.netclipart.com/pp/m/427-4274539_cat-dog-png-cartoon-catdog.png");
                        break;
                }
            }
        }

//        Button btn_gobacktime;
//        btn_gobacktime = findViewById(R.id.gobacktime);
//        btn_gobacktime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Collections.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        ImageButton imgbtn_h2;
        imgbtn_h2 = (ImageButton) findViewById(R.id.imgbtn_home2);
        imgbtn_h2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Collections.this, HomePage.class);
                startActivity(intent);
            }
        });

        ImageButton imgbtn_s2;
        imgbtn_s2 = (ImageButton) findViewById(R.id.imgbtn_study2);
        imgbtn_s2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Collections.this, MainActivity.class);
                startActivity(intent);
            }
        });

        CollectRecViewAdapter adapter = new CollectRecViewAdapter(this);
        adapter.setCats(cats);

        collectRecView.setAdapter(adapter);
        collectRecView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}