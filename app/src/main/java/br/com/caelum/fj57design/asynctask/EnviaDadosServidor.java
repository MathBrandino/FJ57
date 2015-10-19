package br.com.caelum.fj57design.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.converter.AlunoConverterGson;
import br.com.caelum.fj57design.modelo.Aluno;
import br.com.caelum.fj57design.servidor.WebClient;

/**
 * Created by matheus on 10/09/15.
 */
public class EnviaDadosServidor extends AsyncTask<String, Void, String> {


    private Context context;
    private ProgressDialog progressDialog;

    public EnviaDadosServidor(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(context, "Aguarde ...", "Enviando dados para o servidor", false, false);

    }

    @Override
    protected void onPostExecute(String media) {
        super.onPostExecute(media);

        progressDialog.dismiss();
        Toast.makeText(context, media, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... params) {

        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.pegaAlunos();
        dao.close();

        // AlunoConverter converter = new AlunoConverter();
        AlunoConverterGson converter = new AlunoConverterGson();
        String gson = converter.toJson(alunos);
        WebClient webClient = new WebClient();
        Log.i("g", gson);
        String resposta = webClient.post(gson);

        //String resp = new WebClient().post(new AlunoConverter().toJson(alunos));

        return resposta;
    }
}
