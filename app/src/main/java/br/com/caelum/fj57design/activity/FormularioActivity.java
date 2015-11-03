package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.camera.IniciadorCamera;
import br.com.caelum.fj57design.helper.FormularioHelper;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class FormularioActivity extends AppCompatActivity {

    private static int CODE = 123;
    private FormularioHelper helper;
    private FloatingActionButton camera;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        helper = new FormularioHelper(this);

        Intent edicao = getIntent();

        if (edicao.hasExtra("aluno")) {
            Aluno aluno = (Aluno) edicao.getSerializableExtra("aluno");
            helper.colocaAlunoFormulario(aluno);
        }

        camera = helper.getCamera();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciadorCamera iniciadorCamera = new IniciadorCamera();

                caminhoFoto = iniciadorCamera.inicia(FormularioActivity.this, CODE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("aluno", helper.pegaAlunoFormulario());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        helper.colocaAlunoFormulario((Aluno) savedInstanceState.getSerializable("aluno"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CODE) {
                helper.carregaFoto(caminhoFoto);



            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem salvar = menu.add("Salvar");
        salvar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        salvar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Aluno aluno = helper.pegaAlunoFormulario();
                if (helper.validaNome()) {
                    AlunoDao dao = new AlunoDao(FormularioActivity.this);

                    if (aluno.getId() == null) {
                        dao.insere(aluno);
                    } else {
                        dao.altera(aluno);
                    }
                    dao.close();
                    finish();
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
