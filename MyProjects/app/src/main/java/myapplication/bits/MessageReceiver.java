package myapplication.bits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Trainer on 12/15/2015.
 */
public class MessageReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("bitsnsweets","sms received.. bits");

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber, null, "I receiced your sms.. thank you", null, null);

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("bitsnsweets", "senderNum: "+ senderNum + "; message: " + message);


                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}
