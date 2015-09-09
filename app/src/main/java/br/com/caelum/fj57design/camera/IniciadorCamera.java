package br.com.caelum.fj57design.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by matheus on 09/09/15.
 */
public class IniciadorCamera {

    public String inicia(Activity activity, int CODE){

        String arquivoFoto = activity.getExternalFilesDir(null) + "/" + System.currentTimeMillis() +".jpg";
        File localFoto = new File(arquivoFoto);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(localFoto) );
        activity.startActivityForResult(camera, CODE);


        return arquivoFoto;
    }
}
