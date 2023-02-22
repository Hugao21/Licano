package com.example.tarefas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class TarefasDAO extends SQLiteOpenHelper{

    private static String database = "escola";
    private static int versao = 1;

    public TarefasDAO(Context c) {
        super(c, database, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tarefa("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "materia TEXT,"
                + "descricao TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String sql = "drop table if exists tarefa";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void Salvar(Tarefa t) {
        ContentValues v = new ContentValues();
        v.put("materia",t.getMateria());
        v.put("descricao",t.getDesc());
        getWritableDatabase().insert("tarefa",null, v);
    }

    public List<Tarefa> listaTarefas() {
            List<Tarefa> tarefas = new ArrayList<Tarefa>();
            String query = "SELECT * FROM tarefa";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    Tarefa t = new Tarefa();
                    t.setId(Integer.parseInt(c.getString(0)));
                    t.setMateria(c.getString(1));
                    t.setDesc(c.getString(2));
                    tarefas.add(t);
                } while (c.moveToNext());
            }
            return tarefas;

        }
    public List<Tarefa> listaMateria(String mate) {
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        String query = "SELECT * FROM tarefa WHERE materia ='"+mate+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Tarefa t = new Tarefa();
                t.setId(Integer.parseInt(c.getString(0)));
                t.setMateria(c.getString(1));
                t.setDesc(c.getString(2));
                tarefas.add(t);
            } while (c.moveToNext());
        }
        return tarefas;

    }

    public void alterar(Tarefa a) {
        ContentValues v = new ContentValues();
        v.put("Materia", a.getMateria());
        v.put("Descricao", a.getDesc());
        v.put("id", a.getId());
        getWritableDatabase().update("tarefa", v, "id=" + a.getId(),
                null);
    }
    // deletar
    public void deletar(Tarefa a) {
        this.getWritableDatabase().delete("tarefa", "id=" +
                a.getId(), null);
    }
}

