package br.com.caelum.fj57design.mapa;

import android.graphics.Color;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj57design.gps.AtualizadorDeLocalizacao;
import br.com.caelum.fj57design.modelo.Aluno;

/**
 * Created by matheus on 14/09/15.
 */
public class Mapa extends SupportMapFragment {
    private List<Aluno> alunos;
    private AtualizadorDeLocalizacao localizacao;


    @Override
    public void onResume() {
        super.onResume();
        localizacao = new AtualizadorDeLocalizacao(getActivity(), this);
        alunos = new ArrayList<>();
        if (getArguments() != null) {
            alunos = (List<Aluno>) getArguments().getSerializable("alunos");
        }

        final Localizador localizador = new Localizador(getActivity());

        final LatLng[] lng = {localizador.pegaCoordenadas(alunos.get(0).getEndereco())};

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {

                map.clear();
                for (Aluno aluno : alunos) {

                    if (!aluno.getEndereco().trim().isEmpty()) {
                        LatLng latLng = localizador.pegaCoordenadas(aluno.getEndereco());
                        List<LatLng> list = new ArrayList<LatLng>();
                        list.add(latLng);
                        list.add(lng[0]);
                        map.addPolyline(new PolylineOptions().color(Color.BLUE).addAll(list));
                        map.addMarker(new MarkerOptions().title(aluno.getNome()).position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                        lng[0] = latLng;
                    }

                    new AtualizadorDeLocalizacao(getActivity(), Mapa.this);

                }
            }
        });


    }

    public void localizaNoMapa(final LatLng latLng) {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Estou aqui"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
            }
        });
    }
}
