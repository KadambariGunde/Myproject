package myapplication.bits;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.windows);
        mediaPlayer.start();
        generateNotification();
        return super.onStartCommand(intent, flags, startId);
    }
    private void generateNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.drawable.jnext);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.windows);
        builder.setLargeIcon(icon);
        builder.setAutoCancel(true);
        builder.setTicker("Phone boot");
        builder.setContentInfo("10");
        builder.setContentText("Phone boot complete");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(getBaseContext(), NotificationResultActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Boot");

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }
}
