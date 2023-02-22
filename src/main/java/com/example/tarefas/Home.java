package com.example.tarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button PagAdd = (Button) findViewById(R.id.botaoAdicionar);
        final Button PagLista = (Button) findViewById(R.id.botaoListar);
        final Button Mat = (Button) findViewById(R.id.buttonMat);
        final Button Port = (Button) findViewById(R.id.buttonPort);
        final Button Hist = (Button) findViewById(R.id.btnHist);
        final Button Cien = (Button) findViewById(R.id.btnCien);

        PagLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Home.this, ListarTudo.class);
                startActivity(intent);
                finish();
            }
        });
        PagAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Add.class);
                startActivity(intent);
                finish();
            }
        });

        Mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("Valor", 1);
                Intent it = new Intent(Home.this, Listar.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });

        Port.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("Valor", 2);
                Intent it = new Intent(Home.this, Listar.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });

        Hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("Valor", 3);
                Intent it = new Intent(Home.this, Listar.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });

        Cien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("Valor", 4);
                Intent it = new Intent(Home.this, Listar.class);
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
}