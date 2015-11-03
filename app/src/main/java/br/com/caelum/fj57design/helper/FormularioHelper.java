package br.com.caelum.fj57design.helper;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.test.mock.MockContentResolver;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.activity.FormularioActivity;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class FormularioHelper {

    private ImageView foto;
    private EditText nome;
    private FloatingActionButton camera;
    private EditText telefone;
    private EditText site;
    private EditText endereco;
    private RatingBar nota;
    private FormularioActivity activity;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;

        foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        camera = (FloatingActionButton) activity.findViewById(R.id.formulario_foto_camera);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);

        aluno = new Aluno();
    }

    public Aluno pegaAlunoFormulario() {

        aluno.setNome(nome.getText().toString().trim());
        aluno.setEndereco(endereco.getText().toString().trim());
        aluno.setSite(site.getText().toString().trim());
        aluno.setTelefone(telefone.getText().toString().trim());
        aluno.setCaminhoFoto((String) foto.getTag());
        aluno.setNota(nota.getRating() * 2);

        return aluno;
    }

    public void colocaAlunoFormulario(Aluno aluno) {
        this.aluno = aluno;

        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setRating((float) aluno.getNota() / 2);

        if (aluno.getCaminhoFoto() != null) {
            carregaFoto(aluno.getCaminhoFoto());
        }

    }

    public FloatingActionButton getCamera() {
        return camera;
    }


    public void carregaFoto(String caminhoFoto) {
        
        Bitmap bm = BitmapFactory.decodeFile(caminhoFoto);
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setImageBitmap(bm);
        foto.setRotation(90);
        foto.setTag(caminhoFoto);

    }

    private void mostraErroNome() {
        TextInputLayout inputLayout = (TextInputLayout) nome.getParent();
        inputLayout.setError("Nome não pode estar vazio ");
    }

    public boolean validaNome() {
        if (nome.getText().toString().trim().isEmpty()) {
            mostraErroNome();
            return false;
        } else {
            return true;
        }
    }
}
