package myapplication.bits;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReadSensorDataActivity extends AppCompatActivity implements SensorEventListener {

    private static final float SHAKE_THRESHOLD = 1000;
    private TextView tvSensorData;
    private SensorManager sensorManager;
    private Sensor sensor;
    private long lastUpdate;
    private float x;
    private float y;
    private float z;
    private float last_x;
    private float last_y;
    private float last_z;
    MediaPlayer mediaplayer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_sensor_data_activity_layout);
        Intent intent = getIntent();
        tvSensorData = (TextView) findViewById(R.id.tvSensorData);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
        mediaplayer.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //tvSensorData.setText("X : " + event.values[0] + " Y : " + event.values[1] + " Z : " + event.values[2]);

        long curTime = System.currentTimeMillis();
        // only allow one update every 100ms.
        if ((curTime - lastUpdate) > 100) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
                tvSensorData.setText("shake detected \n speed: " + speed);
                Toast.makeText(this, "shake detected \n speed: " + speed, Toast.LENGTH_SHORT).show();
                //Play sound
                mediaplayer = MediaPlayer.create(getBaseContext(),R.raw.achu);
                mediaplayer.start();
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
