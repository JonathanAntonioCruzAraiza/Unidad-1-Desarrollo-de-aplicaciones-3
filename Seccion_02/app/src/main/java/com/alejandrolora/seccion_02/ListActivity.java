package com.alejandrolora.seccion_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

        names = new ArrayList<String>();
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Rubén");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Rubén");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Rubén");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Rubén");
        names.add("Santiago");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Clicked: "+names.get(position), Toast.LENGTH_LONG).show();
            }
        });

        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        lv.setAdapter(myAdapter);
    }
}

