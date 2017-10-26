package com.example.dell.edbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.edbox.realm.RealmPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 26/10/2017.
 */

public class RealmAdapter extends  RecyclerView.Adapter<RealmAdapter.ViewHolder> {


    ArrayList<RealmPojo> jsonClass;
    Context context;

    public RealmAdapter(ArrayList<RealmPojo> jsonClass, Context context){
        this.jsonClass=jsonClass;
        this.context=context;
    }

    @Override
    public RealmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_screen, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RealmAdapter.ViewHolder holder, int position) {




        Picasso.with(context).load(jsonClass.get(position).getImage_url())
                .placeholder(R.drawable.ic_photo_black_48dp)
                .into(holder.imageView);

        holder.title.setText(" "+ jsonClass.get(position).getTitle());
        holder.comment.setText(" "+ jsonClass.get(position).getComment());
        holder.like.setText(" "+ jsonClass.get(position).getLikes());
        holder.dislike.setText(" "+ jsonClass.get(position).getDislikes());

    }

    @Override
    public int getItemCount() {
        return jsonClass.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,comment,like,dislike;
        public ViewHolder(View itemView) {
            super(itemView);

            title= (TextView) itemView.findViewById(R.id.title);
            comment= (TextView) itemView.findViewById(R.id.comment);
            like= (TextView) itemView.findViewById(R.id.likes);
            dislike= (TextView) itemView.findViewById(R.id.dislikes);
            imageView= (ImageView) itemView.findViewById(R.id.image_view);

        }
    }

}
