package com.example.administrator.xiangou.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.xiangou.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

                    Toast.makeText(Main2Activity.this, "this is 2", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Main2Activity.this,MainActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(Main2Activity.this, "this is 2 to dead", Toast.LENGTH_SHORT).show();
    }
}
