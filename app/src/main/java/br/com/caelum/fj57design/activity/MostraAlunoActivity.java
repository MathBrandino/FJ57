package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.mapa.Mapa;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 14/09/15.
 */
public class MostraAlunoActivity extends AppCompatActivity {

    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();

        Intent intent = getIntent();
        if (intent.hasExtra("alunos")) {
            alunos = (List<Aluno>) intent.getSerializableExtra("alunos");
            bundle.putSerializable("alunos", (Serializable) alunos);

        }

        Mapa mapa = new Mapa();
        mapa.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mapa_alunos, mapa);
        transaction.commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
