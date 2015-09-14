package br.com.caelum.fj57design.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.fj57design.R;
import br.com.caelum.fj57design.activity.ProvasActivity;
import br.com.caelum.fj57design.modelo.Prova;


/**
 * Created by matheus on 14/09/15.
 */
public class ProvasFragment extends Fragment {

    private ListView listaProvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.provas_fragment, container, false);

        listaProvas = (ListView) view.findViewById(R.id.lista_provas);

        List<Prova> provas = new ArrayList<>();

        Prova p1 = new Prova("Java", "07/02/2016");
        p1.setTopicos(Arrays.asList("Atributos", "Métodos", "Polimorfismo", "Herança", "Composição", "Encapsulamento"));
        Prova p2 = new Prova("Android", "08/02/2016");
        p2.setTopicos(Arrays.asList("Views", "AsyncTask", "Activity", "ClickListener", "Fragments", "Espresso"));

        Prova p3 = new Prova("Java para Web", "09/02/2016");
        p3.setTopicos(Arrays.asList("Servlet", "JSF", "Unit Tests", "JPA", "Spring", "Hibernate"));

        provas.add(p1);
        provas.add(p2);
        provas.add(p3);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas);

        listaProvas.setAdapter(adapter);

        listaProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) listaProvas.getItemAtPosition(position);

                mudaFragment(prova);
            }
        });

        return view;

    }

    private void mudaFragment(Prova prova) {

        Bundle arguments = new Bundle();
        arguments.putSerializable("prova", prova);

        DetalhesFragment detalhesFragment = new DetalhesFragment();
        detalhesFragment.setArguments(arguments);
        ProvasActivity activity = (ProvasActivity) getActivity();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        if (activity.isTablet()) {
            transaction.replace(R.id.frame_provas_detalhada, detalhesFragment);
            transaction.commit();
        } else {
            transaction.replace(R.id.frame_provas, detalhesFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
