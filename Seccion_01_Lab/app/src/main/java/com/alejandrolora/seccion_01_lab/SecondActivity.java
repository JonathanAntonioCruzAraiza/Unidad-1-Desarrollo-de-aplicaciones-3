package com.alejandrolora.seccion_01_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private SeekBar sbAge;
    private TextView tvAge;
    private Button btNext;
    private RadioButton rbGreeter;
    private RadioButton rbFarewell;


    private String name = "";
    private int age = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;

    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
        }

        sbAge = (SeekBar) findViewById(R.id.sb_age);
        tvAge = (TextView) findViewById(R.id.tv_current_age);
        btNext = (Button) findViewById(R.id.bt_to_third_activity);
        rbGreeter = (RadioButton) findViewById(R.id.rb_greeter);
        rbFarewell = (RadioButton) findViewById(R.id.rb_farewell);


        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                age = currentAge;
                tvAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                age = seekBar.getProgress();
                tvAge.setText(age + "");

                if (age > MAX_AGE) {
                    btNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is: "+MAX_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else if (age < MIN_AGE) {
                    btNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is: "+MIN_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else {
                    btNext.setVisibility(View.VISIBLE);
                }
            }
        });


        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);

                int option = (rbGreeter.isChecked()) ? GREETER_OPTION : FAREWELL_OPTION;
                intent.putExtra("option", option);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, sbAge.getProgress()+"", Toast.LENGTH_LONG).show();
            }
        });


    }
}
