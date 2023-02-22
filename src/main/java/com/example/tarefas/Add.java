package com.example.tarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add extends AppCompatActivity {
    TarefasDAO db = new TarefasDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final EditText materia = (EditText) findViewById(R.id.editMateria);
        final EditText descricao = (EditText) findViewById(R.id.editDesc);
        final Button adicionar = (Button) findViewById(R.id.buttonAd);
        final Button voltar = (Button) findViewById(R.id.botaoVoltar);
        voltar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa t = new Tarefa();
                t.setMateria(materia.getText().toString());
                t.setDesc(descricao.getText().toString());
                db = new TarefasDAO(Add.this);
                db.Salvar(t);
                Toast.makeText(Add.this, "Marcado!", Toast.LENGTH_SHORT).show();
                materia.setText("");
                descricao.setText("");
            }
        });
    }

}