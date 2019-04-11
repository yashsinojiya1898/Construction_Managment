package com.example.construction_managment;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Myadapterclass extends RecyclerView.Adapter<Myadapterclass.ViewHolder> {

    ArrayList<SetterGetterClass> mysettergetter;
    Context context;

    public Myadapterclass(Context context, ArrayList<SetterGetterClass> mysettergetter) {
        this.mysettergetter = mysettergetter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sampleofraw,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.mtitle.setText(mysettergetter.get(position).getTitle());
        viewHolder.mdescription.setText(mysettergetter.get(position).getDescription());
        viewHolder.mdate.setText(mysettergetter.get(position).getDate().toString());


        Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        viewHolder.itemView.setAnimation(animation);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mysettergetter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mtitle,mdescription,mdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.tvtitle);
            mdescription = itemView.findViewById(R.id.tvtitledetails);
            mdate = itemView.findViewById(R.id.date);
        }


    }
}

