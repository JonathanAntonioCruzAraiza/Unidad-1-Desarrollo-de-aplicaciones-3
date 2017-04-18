package com.example.karla.recyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.karla.recyclerview.adapters.MyAdapter;
import com.example.karla.recyclerview.R;
import com.example.karla.recyclerview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;


    private RecyclerView.Adapter miAdapter;
    private RecyclerView.LayoutManager miLayoutManager;

    private int counter= 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=this.getAllMovies();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        miLayoutManager = new LinearLayoutManager(this);

        //miLayoutManager = new GridLayoutManager(this, 2);
        //miLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        miAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                //Toast.makeText(MainActivity.this, name + " - "+ position, Toast.LENGTH_LONG).show();
                removeMovie(position);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(miLayoutManager);
        recyclerView.setAdapter(miAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.add_name:
               this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Civil War", R.drawable.civilwar));
            add(new Movie("Avatar", R.drawable.avatar));
            add(new Movie("Madagascar", R.drawable.madagascar));
            add(new Movie("Titanic", R.drawable.titanic));
        }};
    }

  private void addMovie(int position){
        movies.add(position, new Movie("New image" + (++counter), R.drawable.peli));
        miAdapter.notifyItemInserted(position);
        miLayoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position){
        movies.remove(position);
        miAdapter.notifyItemRemoved(position);

    }

}
