package br.com.caelum.fj57design.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import br.com.caelum.fj57design.R;
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

        View view;

        if( convertView == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        } else {
            view = convertView;
        }

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        TextView site = (TextView) view.findViewById(R.id.item_site);
        ImageView foto = (ImageView) view.findViewById(R.id.item_foto);

        Aluno aluno = (Aluno) getItem(position);
        nome.setText(aluno.getNome());
        site.setText(aluno.getSite());

        Bitmap bm;
        if (aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.user);
        }

        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);

        foto.setImageBitmap(bm);

        return view;
    }
}
