package br.com.caelum.fj57design.gps;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by matheus on 17/09/15.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks {

    private AtualizadorDeLocalizacao localizacao;

    public Configurador(AtualizadorDeLocalizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50);

        localizacao.inicia(request);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
