package br.com.caelum.fj57design.mapa;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 14/09/15.
 */
public class Mapa extends SupportMapFragment {
    private List<Aluno> alunos;

    @Override
    public void onResume() {
        super.onResume();
        alunos = new ArrayList<>();
        if (getArguments() != null) {
            alunos = (List<Aluno>) getArguments().getSerializable("alunos");
        }

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                for (Aluno aluno : alunos) {
                    Localizador localizador = new Localizador(getActivity());
                    LatLng latLng = localizador.pegaCoordenadas(aluno.getEndereco());
                    map.addMarker(new MarkerOptions().title(aluno.getNome()).position(latLng));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

                }
            }
        });


    }
}
