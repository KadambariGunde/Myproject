package myapplication.bits;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationDemoActivity extends Activity implements View.OnClickListener {

    private Button btnNotification;
    private Button btnSound;
    private Button btnVibrate;
    private Button btnToast;
    private Button btnLED;
    private Button btnWakeUpScreen;
    private Button btnNotifyAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo_layout);
        Intent incomingIntent = getIntent();

        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(this);
        btnSound = (Button) findViewById(R.id.btnSound);
        btnSound.setOnClickListener(this);
        btnVibrate = (Button) findViewById(R.id.btnVibrate);
        btnVibrate.setOnClickListener(this);
        btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(this);
        btnLED = (Button) findViewById(R.id.btnLED);
        btnLED.setOnClickListener(this);
        btnWakeUpScreen = (Button) findViewById(R.id.btnWakeUpScreen);
        btnWakeUpScreen.setOnClickListener(this);
        btnNotifyAll = (Button) findViewById(R.id.btnNotifyAll);
        btnNotifyAll.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNotification:
                generateNotification();
                break;

            case R.id.btnSound:
                playSound();
                break;

            case R.id.btnVibrate:
                vibrate();
                break;

            case R.id.btnToast:
                toast();
                break;

            case R.id.btnLED:
                notifyLED();
                break;

            case R.id.btnWakeUpScreen:
                wakeUpScreen();
                break;

            case R.id.btnNotifyAll:
                generateNotification();
                playSound();
                vibrate();
                toast();
                notifyLED();
                wakeUpScreen();
                break;
        }
    }

    private void wakeUpScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "My Tag");
        Toast.makeText(getApplicationContext(), "wakeup screen", Toast.LENGTH_SHORT).show();
        wl.acquire(5000);
        wl.release();
    }

    private void notifyLED() {
        NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notification = new Notification();
        notification.ledARGB = Color.RED;
        notification.ledOnMS = 3000;
        notification.ledOffMS = 1000;
        notification.ledARGB = Color.GREEN;
        notification.ledOnMS = 3000;
        notification.ledOffMS = 1000;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notif.notify(1, notification);
         Toast.makeText(getApplicationContext(), "LED Notification", Toast.LENGTH_SHORT).show();

    }

    private void toast() {
        Toast toast = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Toast toast1 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.BOTTOM, 0, 0);
        toast1.show();
        Toast toast2 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast2.setGravity(Gravity.RIGHT, 0, 0);
        toast2.show();
        Toast toast3 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast3.setGravity(Gravity.TOP, 0, 0);
        toast3.show();
        Toast toast4 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast4.setGravity(Gravity.LEFT, 0, 0);
        toast4.show();
        Toast toast5 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast5.setGravity(Gravity.START, 0, 0);
        toast5.show();
        Toast toast6 = Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT);
        toast6.setGravity(Gravity.END, 0, 0);
        toast6.show();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 300, 0};
        vibrator.vibrate(pattern, 2);
    }

    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.windows);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    private void generateNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.drawable.jnext);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.windows);
        builder.setLargeIcon(icon);
        builder.setAutoCancel(true);
        builder.setTicker("Breaking News....!!!");
        builder.setContentInfo("10");
        builder.setContentText("People of India following traffic rules");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(getBaseContext(), NotificationResultActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Traffic news");

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
