package br.com.caelum.fj57design.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.modelo.Prova;
/**
 * Created by matheus on 14/09/15.
 */
public class DetalhesFragment extends Fragment {

    private Prova prova;
    private TextView materia;
    private TextView data;
    private ListView topicos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prova_detalhada, container, false);

        encontraViews(view);

        if (getArguments() != null) {
            prova = (Prova) getArguments().getSerializable("prova");
            populaDetalhes(materia, data, topicos);
        }

        return view;
    }

    private void encontraViews(View view) {
        materia = (TextView) view.findViewById(R.id.detalhe_prova_materia);
        data = (TextView) view.findViewById(R.id.detalhe_prova_data);
        topicos = (ListView) view.findViewById(R.id.lista_provas_detalhes);
    }

    private void populaDetalhes(TextView materia, TextView data, ListView topicos) {

        materia.setText(prova.getMateria());
        data.setText(prova.getData());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());

        topicos.setAdapter(adapter);
    }
}