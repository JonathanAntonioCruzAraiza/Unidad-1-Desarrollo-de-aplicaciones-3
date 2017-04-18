


package com.example.jona.seccion3lab.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.jona.seccion3lab.R;
import com.example.jona.seccion3lab.models.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;


    public FruitAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        holder.bind(fruits.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        public TextView tvName;
        public TextView tvDescription;
        public TextView tvQuantity;
        public ImageView ivBackground;

    public ViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_Name);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_Description);
        tvQuantity = (TextView) itemView.findViewById(R.id.tv_Quantity);
        ivBackground = (ImageView) itemView.findViewById(R.id.iv_Background);

        itemView.setOnCreateContextMenuListener(this);
    }

    public void bind(final Fruit fruit, final OnItemClickListener listener) {

        this.tvName.setText(fruit.getName());
        this.tvDescription.setText(fruit.getDescription());
        this.tvQuantity.setText(fruit.getQuantity() + "");

        if (fruit.getQuantity() == fruit.LIMIT_QUANTITY) {
            tvQuantity.setTextColor(ContextCompat.getColor(activity, R.color.colorAlert));
            tvQuantity.setTypeface(null, Typeface.BOLD);
        } else {
            tvQuantity.setTextColor(ContextCompat.getColor(activity, R.color.defaultTextColor));
            tvQuantity.setTypeface(null, Typeface.NORMAL);
        }

        Picasso.with(activity).load(fruit.getImgBackground()).fit().into(this.ivBackground);

        this.ivBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(fruit, getAdapterPosition());
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Fruit fruitSelected = fruits.get(this.getAdapterPosition());

        contextMenu.setHeaderTitle(fruitSelected.getName());
        contextMenu.setHeaderIcon(fruitSelected.getImgIcon());

        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.context_menu_fruit, contextMenu);


        for (int i = 0; i < contextMenu.size(); i++)
            contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete_fruit:
                fruits.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                return true;
            case R.id.reset_fruit_quantity:
                fruits.get(getAdapterPosition()).resetQuantity();
                notifyItemChanged(getAdapterPosition());
                return true;
            default:
                return false;
        }
    }
}

    public interface OnItemClickListener{
        void onItemClick(Fruit fruit, int position);
    }
}
