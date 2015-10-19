package br.com.caelum.fj57design.gps;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.fj57design.mapa.Mapa;

/**
 * Created by matheus on 17/09/15.
 */
public class AtualizadorDeLocalizacao implements LocationListener {

    private Mapa mapFragment;
    private GoogleApiClient client;

    public AtualizadorDeLocalizacao(Context context, Mapa mapFragment) {
        Configurador configurador = new Configurador(this);
        this.client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(configurador)
                .build();
        this.client.connect();

        this.mapFragment = mapFragment;
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);

        mapFragment.localizaNoMapa(latLng);
    }


    public void inicia(LocationRequest request) {
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);

    }

    public void cancela() {
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        client.disconnect();
    }


}
