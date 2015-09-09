package br.com.caelum.fj57design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.camera.IniciadorCamera;
import br.com.caelum.fj57design.helper.FormularioHelper;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private FloatingActionButton camera;
    private static int CODE = 123;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CODE) {
                helper.carregaFoto(caminhoFoto);
            }
        }

    }
}
