package com.example.karla.recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karla.recyclerview.R;
import com.example.karla.recyclerview.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by karla on 15/04/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

   public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
       this.movies = movies;
       this.layout = layout;
       this.itemClickListener = listener;
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position),  itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         public TextView tvName;
        public ImageView ivPoster;


        public ViewHolder(View itemView) {
            super(itemView);
           // this.tvName=(TextView) itemView.findViewById(R.id.tv_name);
            tvName = (TextView)itemView.findViewById(R.id.tv_title);
            ivPoster = (ImageView)itemView.findViewById(R.id.iv_poster);
        }

        public void bind(final Movie movie,  final OnItemClickListener listener) {
            //this.tvName.setText(name);

            tvName.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(ivPoster);
            //ivPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(movie, getAdapterPosition());

                }
            });
        }


    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie, int position);
    }
}
