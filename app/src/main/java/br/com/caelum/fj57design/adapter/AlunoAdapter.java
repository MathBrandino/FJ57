package br.com.caelum.fj57design.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.caelum.fj57design.activity.ListaAlunosActivity;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class AlunoAdapter extends BaseAdapter {

    private ListaAlunosActivity activity;
    private List<Aluno> alunos;

    public AlunoAdapter(ListaAlunosActivity activity, List<Aluno> alunos) {

        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
