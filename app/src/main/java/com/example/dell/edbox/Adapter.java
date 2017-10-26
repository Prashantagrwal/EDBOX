package com.example.dell.edbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.edbox.pojo.JsonClass;
import com.squareup.picasso.Picasso;


public class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder> {


    JsonClass jsonClass;
    Context context;

    public Adapter(JsonClass jsonClass,Context context){
        this.jsonClass=jsonClass;
        this.context=context;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_screen, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {




    Picasso.with(context).load(jsonClass.getData().getChildren().
            get(position).getData().getPreview().getImages().get(0).getSource().getUrl())
            .placeholder(R.drawable.ic_photo_black_48dp)
            .into(holder.imageView);

        holder.title.setText(" "+jsonClass.getData().getChildren().get(position).getData().getTitle());
        holder.comment.setText(" "+jsonClass.getData().getChildren().get(position).getData().getNumComments().toString());
        holder.like.setText(" "+jsonClass.getData().getChildren().get(position).getData().getUps().toString());
        holder.dislike.setText(" "+jsonClass.getData().getChildren().get(position).getData().getDowns().toString());

    }

    @Override
    public int getItemCount() {
        return jsonClass.getData().getChildren().size();

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
