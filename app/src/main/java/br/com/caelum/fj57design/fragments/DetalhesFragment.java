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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prova_detalhada, container, false);

        TextView materia = (TextView) view.findViewById(R.id.detalhe_prova_materia);
        TextView data = (TextView) view.findViewById(R.id.detalhe_prova_data);
        ListView topicos = (ListView) view.findViewById(R.id.lista_provas_detalhes);


        if (getArguments() != null) {
            Prova prova = (Prova) getArguments().getSerializable("prova");
            materia.setText(prova.getMateria());
            data.setText(prova.getData());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());

            topicos.setAdapter(adapter);
        }

        return view;
    }
}
