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

public class Listar extends AppCompatActivity {
    ListView lista;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TarefasDAO db = new TarefasDAO(this);
    String id;
    String mate;
    EditText materia;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        lista = (ListView) findViewById(R.id.list);

        final Button alterar = (Button) findViewById(R.id.btnAlterar);
        final Button excluir = (Button) findViewById(R.id.btnExcluir);
        final Button voltar = (Button) findViewById(R.id.btnVoltar);
       materia = (EditText) findViewById(R.id.editText1);
        final EditText descricao = (EditText) findViewById(R.id.editText);
        listarTarefas();



        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa a = new Tarefa();
                a.setId(Integer.parseInt(id));
                a.setMateria(materia.getText().toString());
                a.setDesc(descricao.getText().toString());
                db = new TarefasDAO(Listar.this);
                db.alterar(a);
                listarTarefas();
                Toast.makeText(Listar.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                listarTarefas();
                materia.setText("");
                descricao.setText("");

            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa a = new Tarefa();
                a.setId(Integer.parseInt(id));
                db = new TarefasDAO(Listar.this);
                db.deletar(a);
                listarTarefas();
                Toast.makeText(Listar.this, "Concluído",
                                Toast.LENGTH_SHORT)
                        .show();
                listarTarefas();

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Listar.this, Home.class);
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
        bundle = getIntent().getExtras();
        int valor = bundle.getInt("Valor");
        switch (valor)
        {
            case 1:
                mate = "Matematica";
                break;

            case 2:
                mate = "Portugues";
                break;
            case 3:
                mate = "Historia";
                break;
            case 4:
                mate = "Ciencias";
                break;

            default:
                break;
        }
        List<Tarefa> tarefas = db.listaMateria(mate);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Listar.this, R.layout.row, arrayList);
        lista.setAdapter(adapter);
        for (Tarefa t : tarefas) {
            arrayList.add(t.getId() + " - " + t.getMateria() + " - "
                    + t.getDesc());
            adapter.notifyDataSetChanged();
        }


    }

}