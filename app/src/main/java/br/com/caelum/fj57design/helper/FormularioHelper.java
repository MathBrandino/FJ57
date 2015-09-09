package br.com.caelum.fj57design.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.activity.FormularioActivity;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 09/09/15.
 */
public class FormularioHelper {

    private FormularioActivity activity;
    private ImageView foto;
    private EditText nome;
    private FloatingActionButton camera;
    private EditText telefone;
    private EditText site;
    private EditText endereco;
    private RatingBar nota;

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

    public Aluno pegaAlunoFormulario(){

        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setCaminhoFoto((String) foto.getTag());

        return aluno;
    }

    public void colocaAlunoFormulario(Aluno  aluno){
        this.aluno = aluno;

        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setRating((float) aluno.getNota());

        if (aluno.getCaminhoFoto() != null) {
            carregaFoto(aluno.getCaminhoFoto());
        }

    }

    public void carregaFoto(String caminhoFoto) {

        Bitmap bm = BitmapFactory.decodeFile(caminhoFoto);
        bm = Bitmap.createScaledBitmap(bm, 200, 200, true );
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setImageBitmap(bm);
        foto.setTag(caminhoFoto);

    }
}
