package br.com.caelum.fj57design.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class AlunoDao extends SQLiteOpenHelper {

    private static final String DATABASE = "CadastroCaelum";
    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";

    public AlunoDao(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABELA + " (" +
                "id integer primary key , " +
                "nome text not null, " +
                "telefone text , " +
                "endereco text , " +
                "caminhoFoto text, " +
                "site text , " +
                "nota real );";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Aluno> pegaAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "Select * from " + TABELA + " ;";
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(aluno);
        }

        return alunos;
    }

    public void insere(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("caminhoFoto", aluno.getCaminhoFoto());
        values.put("telefone", aluno.getTelefone());
        values.put("nota", aluno.getNota());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void altera(Aluno aluno) {

        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("caminhoFoto", aluno.getCaminhoFoto());
        values.put("telefone", aluno.getTelefone());
        values.put("nota", aluno.getNota());

        String[] id = {String.valueOf(aluno.getId())};
        getWritableDatabase().update(TABELA, values, "id=?", id);
    }

    public void deleta(Aluno aluno) {
        String[] id = {String.valueOf(aluno.getId())};
        getWritableDatabase().delete(TABELA, "id=?", id);
    }

    public boolean isAluno(String telefone) {
        String sql = " Select * from " + TABELA + " where telefone = ?";

        String[] args = {telefone};
        Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        if (cursor.moveToFirst()) {
            return true;
        }

        return false;
    }

    public List<Aluno> busca(String nome) {
        String sql = "Select * from " + TABELA + " where nome like ?  ";
        List<Aluno> alunos = new ArrayList<>();

        String[] nomes = {nome};
        Cursor cursor = getReadableDatabase().rawQuery(sql, nomes);

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota  ")));

            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }
}
