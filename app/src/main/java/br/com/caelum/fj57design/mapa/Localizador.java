package br.com.caelum.fj57design.mapa;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by matheus on 14/09/15.
 */
public class Localizador {

    private android.location.Geocoder geo;

    public Localizador(Context context) {
        geo = new Geocoder(context, Locale.getDefault());
    }

    public LatLng pegaCoordenadas(String endereco){
        try {
            List<Address> listaEnderecos;
            listaEnderecos = geo.getFromLocationName(endereco, 1);
            if (!listaEnderecos.isEmpty()) {
                Address address = listaEnderecos.get(0);
                return new LatLng(address.getLatitude(), address.getLongitude());
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

    }
}
