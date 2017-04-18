package com.alejandrolora.seccion_01_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setLogo(R.mipmap.ic_confirm);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etName = (EditText) findViewById(R.id.etName);
        btNext = (Button) findViewById(R.id.buttonToSecondActivity);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                if (name != null && !name.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "The name is mandatory, please type it", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
