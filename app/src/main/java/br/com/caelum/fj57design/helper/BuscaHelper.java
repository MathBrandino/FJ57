package br.com.caelum.fj57design.helper;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import br.com.caelum.fj57design.R;

/**
 * Created by matheus on 15/09/15.
 */
public class BuscaHelper {

    private EditText busca;
    private FloatingActionButton actionButton;

    private View view;

    public BuscaHelper(View view) {
        this.view = view;

        busca = (EditText) view.findViewById(R.id.nome_busca);
        actionButton = (FloatingActionButton) view.findViewById(R.id.buscador);
    }

    private boolean validaBusca() {
        if (busca.getText().toString().trim().isEmpty()) {
            TextInputLayout inputLayout = (TextInputLayout) busca.getParent();
            inputLayout.setError("Busca não pode ser vazia");
            return false;
        }
        return true;
    }

    public String devolveBusca() {
        if (validaBusca()) {
            return busca.getText().toString().trim();
        }
        return null;
    }

    public FloatingActionButton pegaBotao() {
        return actionButton;
    }

    public void mostraSemResultado() {
        TextInputLayout parent = (TextInputLayout) busca.getParent();
        parent.setError("Não foi encontrado nenhum aluno com essa busca :(");
    }
}
