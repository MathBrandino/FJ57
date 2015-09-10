package br.com.caelum.fj57design.contextActionBar;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.activity.ListaAlunosActivity;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class ContextActionBar implements android.support.v7.view.ActionMode.Callback {

    private ListaAlunosActivity activity;
    private Aluno aluno;

    public ContextActionBar(ListaAlunosActivity activity, Aluno aluno) {
        this.activity = activity;
        this.aluno = aluno;
    }

    @Override
    public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {

        activity.getMenuInflater().inflate(R.menu.action_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_deletar :

                AlunoDao dao = new AlunoDao(activity);
                dao.deleta(aluno);
                dao.close();
                activity.carregaLista();

                return false;

            case R.id.menu_mapa :

                Intent mapa = new Intent(Intent.ACTION_VIEW);

                mapa.setData(Uri.parse("geo:0,0?z=14&q=" + Uri.encode(aluno.getEndereco())));
                item.setIntent(mapa);

                return false;

            case R.id.menu_ligar:

                Intent ligar = new Intent(Intent.ACTION_CALL);
                ligar.setData(Uri.parse("tel:" + aluno.getTelefone()));
                item.setIntent(ligar);

                return false;

            case R.id.menu_mensagem:

                Intent mensagem = new Intent(Intent.ACTION_SEND);
                mensagem.setType("text/*");
                mensagem.putExtra(Intent.EXTRA_SUBJECT, "APP Caelum");
                mensagem.putExtra(Intent.EXTRA_TEXT, "Sua nota é :"+ aluno.getNota());
                item.setIntent(Intent.createChooser(mensagem, "Escolha por gentileza"));

                return false;

            case R.id.menu_site:

                Intent site = new Intent(Intent.ACTION_VIEW);
                site.setData(Uri.parse("http:" + aluno.getSite()));
                item.setIntent(site);
                return false;

            default:

                return true;

        }
    }


    @Override
    public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {

    }
}
