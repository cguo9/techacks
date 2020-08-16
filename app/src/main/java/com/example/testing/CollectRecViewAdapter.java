package com.example.testing;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CollectRecViewAdapter extends RecyclerView.Adapter<CollectRecViewAdapter.ViewHolder> {

    private ArrayList<Cat> cats = new ArrayList<>();

    private Context context;

    public CollectRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.colName.setText(cats.get(position).getName());
//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, cats.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
//            }
//        });

        Glide.with(context)
                .asBitmap()
                .load(cats.get(position).getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void setCats(ArrayList<Cat> cats) {
        this.cats = cats;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView colName;
        private CardView parent;
        private ImageView image;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            colName = itemView.findViewById(R.id.colName);
            parent = itemView.findViewById(R.id.parent);

            image = itemView.findViewById(R.id.image);
        }
    }



}
