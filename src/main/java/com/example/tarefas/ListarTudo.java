package com.example.tarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarTudo extends AppCompatActivity {
    ListView lista;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TarefasDAO db = new TarefasDAO(this);
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tudo);
        lista = (ListView) findViewById(R.id.listT);

        final Button alterar = (Button) findViewById(R.id.btnAlterarT);
        final Button excluir = (Button) findViewById(R.id.btnExcluirT);
        final Button voltar = (Button) findViewById(R.id.btnVoltarT);
        final EditText materia = (EditText) findViewById(R.id.editText1T);
        final EditText descricao = (EditText) findViewById(R.id.editTextT);
        listarTarefas();

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa a = new Tarefa();
                a.setId(Integer.parseInt(id));
                a.setMateria(materia.getText().toString());
                a.setDesc(descricao.getText().toString());
                db = new TarefasDAO(ListarTudo.this);
                db.alterar(a);
                listarTarefas();
                Toast.makeText(ListarTudo.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa a = new Tarefa();
                a.setId(Integer.parseInt(id));
                db = new TarefasDAO(ListarTudo.this);
                db.deletar(a);
                listarTarefas();
                Toast.makeText(ListarTudo.this, "Concluído",
                                Toast.LENGTH_SHORT)
                        .show();
                listarTarefas();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListarTudo.this, Home.class);
                startActivity(it);
                finish();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String conteudo = (String)
                        lista.getItemAtPosition(position);
                String palavra[] = conteudo.split(" - ");
                id = palavra[0];
                materia.setText(palavra[1]);
                descricao.setText(palavra[2]);
            }
        });
    }
    public void listarTarefas()
    {
        List<Tarefa> tarefas = db.listaTarefas();
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(ListarTudo.this, R.layout.row, arrayList);
        lista.setAdapter(adapter);
        for (Tarefa t : tarefas) {
            arrayList.add(t.getId() + " - " + t.getMateria() + " - "
                    + t.getDesc());
            adapter.notifyDataSetChanged();
        }


    }
}