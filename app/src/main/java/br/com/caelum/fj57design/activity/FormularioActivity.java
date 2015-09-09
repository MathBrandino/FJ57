package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.helper.FormularioHelper;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class FormularioActivity extends AppCompatActivity{

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent edicao = getIntent();

        if(edicao.hasExtra("aluno")){
            Aluno aluno = (Aluno) edicao.getSerializableExtra("aluno");
            helper.colocaAlunoFormulario(aluno);
        }

        

    }
}
