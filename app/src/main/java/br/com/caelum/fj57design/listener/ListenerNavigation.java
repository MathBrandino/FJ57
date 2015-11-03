package br.com.caelum.fj57design.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.activity.ListaAlunosActivity;
import br.com.caelum.fj57design.activity.MostraAlunoActivity;
import br.com.caelum.fj57design.activity.ProvasActivity;
import br.com.caelum.fj57design.asynctask.EnviaDadosServidor;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 03/11/15.
 */
public class ListenerNavigation implements NavigationView.OnNavigationItemSelectedListener {
    private ListaAlunosActivity activity;
    public ListenerNavigation(ListaAlunosActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        
        switch (menuItem.getItemId()){
            case R.id.enviar_notas :

                new EnviaDadosServidor(activity).execute();
                return true;
            case R.id.mapa_alunos :
                Intent intent = new Intent(activity, MostraAlunoActivity.class);
                AlunoDao dao = new AlunoDao(activity);
                intent.putExtra("alunos", (Serializable) dao.pegaAlunos());
                dao.close();
                activity.startActivity(intent);
                return true;
            case R.id.provas:
                activity.startActivity(new Intent(activity, ProvasActivity.class));
        }
        
        return false;
    }
}
