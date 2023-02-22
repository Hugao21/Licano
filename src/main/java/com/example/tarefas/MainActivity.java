package com.example.tarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        boolean b = handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarHome();
            }
        }, 3000);
    }

    private  void mostrarHome(){
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish();
    }
}