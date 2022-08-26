package com.example.booklibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class customAdapter extends RecyclerView.Adapter<customAdapter.viewHolder> {
    public customAdapter(Activity activity, Context context, ArrayList id, ArrayList title, ArrayList author, ArrayList pages) {
        this.id = id;
        this.activity = activity;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.context = context;
    }

    private ArrayList id, title, author, pages;
    private Context context;
    Activity activity;
    int position;
    Animation anim;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclar, parent,
                false);

//        LayoutInflater inflater=LayoutInflater.from(context);
//        View view1 =inflater.inflate(R.layout.recyclar,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvid.setText(String.valueOf(id.get(position)));
        holder.tvtitle.setText(String.valueOf(title.get(position)));
        holder.tvauthor.setText(String.valueOf(author.get(position)));
        holder.tvpages.setText(String.valueOf(pages.get(position)));

//        holder.row.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // if we comment it then the code will also work
//               int position=holder.getAdapterPosition();
//                Intent intent = new Intent(context, updateActivity.class);
//                intent.putExtra("id", String.valueOf(id.get(position)));
//                intent.putExtra("title", String.valueOf(title.get(position)));
//                intent.putExtra("author", String.valueOf(author.get(position)));
//                intent.putExtra("pages", String.valueOf(pages.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if we comment it then the code will also work
               int position=holder.getAdapterPosition();
                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("author", String.valueOf(author.get(position)));
                intent.putExtra("pages", String.valueOf(pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvid, tvtitle, tvauthor, tvpages;
        LinearLayout row;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.idRv);
            tvtitle = itemView.findViewById(R.id.bookRv);
            tvauthor = itemView.findViewById(R.id.authorRv);
            tvpages = itemView.findViewById(R.id.pagesRv);
            row = itemView.findViewById(R.id.row);
            //translate animation
            anim = AnimationUtils.loadAnimation(context,R.anim.anim);
            row.setAnimation(anim);

        }
    }
}
