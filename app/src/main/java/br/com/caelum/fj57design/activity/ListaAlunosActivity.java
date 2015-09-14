package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.adapter.AlunoAdapter;
import br.com.caelum.fj57design.asynctask.EnviaDadosServidor;
import br.com.caelum.fj57design.contextActionBar.ContextActionBar;
import br.com.caelum.fj57design.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

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

                ContextActionBar actionBar = new ContextActionBar(ListaAlunosActivity.this, aluno);

                startSupportActionMode(actionBar);
                return true;
            }
        });

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {

            case R.id.menu_prova:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);

                return false;

            case R.id.menu_media:

                new EnviaDadosServidor(this).execute(); // MANEIRA CORRETA DE SER FEITO

                /*new Thread(){
                    @Override
                    public void run() {
                        AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
                        List<Aluno> alunos = dao.pegaAlunos();
                        dao.close();

                        AlunoConverter converter = new AlunoConverter();

                        String json = converter.toJson(alunos);

                        WebClient webClient = new WebClient();

                        String resposta = webClient.post(json);

                        Log.d("Resposta", resposta);

                        //Toast.makeText(ListaAlunosActivity.this, resposta, Toast.LENGTH_LONG).show(); //GERANDO A EXCEPTION !!!

                    }
                }.start();*/


                return false;

            case R.id.menu_lista_mapa:
                Intent intent = new Intent(this, MostraAlunoActivity.class);
                intent.putExtra("alunos", (Serializable) alunos);
                startActivity(intent);

                return false;

        }

        return super.onOptionsItemSelected(item);
    }
}
