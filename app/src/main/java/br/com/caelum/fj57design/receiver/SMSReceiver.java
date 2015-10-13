package br.com.caelum.fj57design.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import br.com.caelum.fj57design.Dao.AlunoDao;
import br.com.caelum.fj57design.R;


/**
 * Created by matheus on 09/09/15.
 */
public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");

        byte[] pdu = (byte[]) pdus[0];

        String format = (String) intent.getSerializableExtra("format");
        SmsMessage sms = SmsMessage.createFromPdu(pdu, format);

        String telefone = sms.getOriginatingAddress();

        AlunoDao dao = new AlunoDao(context);

        if (dao.isAluno(telefone)) {
            MediaPlayer player = MediaPlayer.create(context, R.raw.hangouts_incoming_call);
            player.start();
        }
    }
}
