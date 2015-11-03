package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.security.Permission;
import java.security.Permissions;
import java.util.List;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.Manifest;
import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.adapter.AlunoAdapter;
import br.com.caelum.fj57design.asynctask.EnviaDadosServidor;
import br.com.caelum.fj57design.contextActionBar.ContextActionBar;
import br.com.caelum.fj57design.helper.BuscaHelper;
import br.com.caelum.fj57design.listener.ListenerNavigation;
import br.com.caelum.fj57design.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private List<Aluno> alunos;
    private NavigationView navigationView;
    private ActionBarDrawerToggle iconeMenu;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Toolbar toolbar = criaToolbar();

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        criaEPopulaNavigationView();

        populaDrawerLayout(toolbar);

        final FloatingActionButton botaoAdicionar = (FloatingActionButton) findViewById(R.id.botao_adicionar);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent adiciona = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(adiciona);

            }
        });


        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                final Aluno aluno = (Aluno) lista.getItemAtPosition(position);

                Snackbar.make(botaoAdicionar, "Deseja fazer alguma alteração ? ", Snackbar.LENGTH_LONG)
                        .setAction("Sim", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                                edicao.putExtra("aluno", aluno);
                                startActivity(edicao);
                            }
                        }).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);

                startSupportActionMode(new ContextActionBar(ListaAlunosActivity.this, aluno));
                return true;
            }
        });

    }

    private void populaDrawerLayout(Toolbar toolbar) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        iconeMenu = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0,0);

        drawerLayout.setDrawerListener(iconeMenu);
    }

    @NonNull
    private Toolbar criaToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_info_details);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void criaEPopulaNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new ListenerNavigation(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    public void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        alunos = dao.pegaAlunos();
        dao.close();

        AlunoAdapter adapter = new AlunoAdapter(this, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        switch (id) {

            case R.id.busca:
                final View view = View.inflate(this, R.layout.buscador, null);
                final BuscaHelper helper = new BuscaHelper(view);
                final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).show();

                helper.pegaBotao().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
                        if (helper.devolveBusca() != null) {
                            final List<Aluno> busca = dao.busca(helper.devolveBusca());
                            dao.close();
                            if (busca.size() > 0) {
                                passaPesquisa((Serializable) busca);
                                alertDialog.dismiss();
                            } else {
                                helper.mostraSemResultado();
                            }
                        }
                    }
                });

                return false;


        }

        return super.onOptionsItemSelected(item);
    }

    private void passaPesquisa(Serializable busca) {
        Intent intent = new Intent(ListaAlunosActivity.this, BuscaActivity.class);
        intent.putExtra("alunos", busca);
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ( requestCode == 123 ){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Fazendo servico", Toast.LENGTH_LONG).show();
            }
        }


    }
}
