package com.alejandrolora.seccion_02_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import com.alejandrolora.seccion_02_lab.R;
import com.alejandrolora.seccion_02_lab.adapters.FruitAdapter;
import com.alejandrolora.seccion_02_lab.models.Fruit;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView ll;
    private GridView gv;
    private FruitAdapter faListView;
    private FruitAdapter faGridView;
    private List<Fruit> fruits;
    private MenuItem miListView;
    private MenuItem miGridView;
    private int counter = 0;
    private final int SWITCH_TO_LIST_VIEW = 0;
    private final int SWITCH_TO_GRID_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.enforceIconBar();
        this.fruits = getAllFruits();
        this.ll = (ListView) findViewById(R.id.lv);
        this.gv = (GridView) findViewById(R.id.gv);
        this.ll.setOnItemClickListener(this);
        this.gv.setOnItemClickListener(this);
        this.faListView = new FruitAdapter(this, R.layout.list_view_item_fruit, fruits);
        this.faGridView = new FruitAdapter(this, R.layout.grid_view_item_fruit, fruits);
        this.ll.setAdapter(faListView);
        this.gv.setAdapter(faGridView);
        registerForContextMenu(this.ll);
        registerForContextMenu(this.gv);
    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.clickFruit(fruits.get(position));
    }

    private void clickFruit(Fruit fruit) {
        if(fruit.getOrigin().equals("Unknown"))
            Toast.makeText(this, "Sorry, we don't have many info about " + fruit.getName(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "The best fruit from " + fruit.getOrigin() + " is " + fruit.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        this.miListView = menu.findItem(R.id.list_view);
        this.miGridView = menu.findItem(R.id.grid_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                this.addFruit(new Fruit("Added nº" + (++counter), R.mipmap.ic_new_fruit, "Unknown"));
                return true;
            case R.id.list_view:
                this.switchListGridView(this.SWITCH_TO_LIST_VIEW);
                return true;
            case R.id.grid_view:
                this.switchListGridView(this.SWITCH_TO_GRID_VIEW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu_fruits, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_fruit:
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void switchListGridView(int option) {
        if (option == SWITCH_TO_LIST_VIEW) {

            if (this.ll.getVisibility() == View.INVISIBLE) {

                this.gv.setVisibility(View.INVISIBLE);
                this.miGridView.setVisible(true);

                this.ll.setVisibility(View.VISIBLE);
                this.miListView.setVisible(false);
            }
        } else if (option == SWITCH_TO_GRID_VIEW) {

            if (this.gv.getVisibility() == View.INVISIBLE) {

                this.ll.setVisibility(View.INVISIBLE);
                this.miListView.setVisible(true);
                this.gv.setVisibility(View.VISIBLE);
                this.miGridView.setVisible(false);
            }
        }
    }

    private List<Fruit> getAllFruits() {
        List<Fruit> list = new ArrayList<Fruit>() {{
            add(new Fruit("Banana", R.mipmap.ic_banana, "Gran Canaria"));
            add(new Fruit("Strawberry", R.mipmap.ic_strawberry, "Huelva"));
            add(new Fruit("Orange", R.mipmap.ic_orange, "Sevilla"));
            add(new Fruit("Apple", R.mipmap.ic_apple, "Madrid"));
            add(new Fruit("Cherry", R.mipmap.ic_cherry, "Galicia"));
            add(new Fruit("Pear", R.mipmap.ic_pear, "Zaragoza"));
            add(new Fruit("Raspberry", R.mipmap.ic_raspberry, "Barcelona"));
        }};
        return list;
    }

    private void addFruit(Fruit fruit) {
        this.fruits.add(fruit);
        this.faListView.notifyDataSetChanged();
        this.faGridView.notifyDataSetChanged();
    }

    private void deleteFruit(int position) {
        this.fruits.remove(position);
        this.faListView.notifyDataSetChanged();
        this.faGridView.notifyDataSetChanged();
    }
}
