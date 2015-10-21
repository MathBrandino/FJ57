package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 15/09/15.
 */
public class BuscaActivity extends AppCompatActivity {

    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        alunos = new ArrayList<>();
        Intent intent = getIntent();

        if (intent.hasExtra("alunos")) {
            alunos = (List<Aluno>) intent.getSerializableExtra("alunos");
        }

        final ListView listaAlunos = new ListView(this);

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                Snackbar.make(listaAlunos, "Ir para o aluno ?", Snackbar.LENGTH_LONG).setAction("Sim", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent visualiza = new Intent(BuscaActivity.this, FormularioActivity.class);
                        visualiza.putExtra("aluno", aluno);
                        startActivity(visualiza);
                        finish();
                    }
                }).show();
            }
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(toolbar);
        linearLayout.addView(listaAlunos);
        linearLayout.setVisibility(View.VISIBLE);
        listaAlunos.setVisibility(View.VISIBLE);
        setContentView(linearLayout);
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
