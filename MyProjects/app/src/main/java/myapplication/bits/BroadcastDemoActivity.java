package myapplication.bits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dell on 12/17/2015.
 */
public class BroadcastDemoActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,MusicService.class);
        context.startService(intent1);

    }
}
