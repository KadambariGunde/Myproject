package myapplication.bits;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

/**
 * Created by Dell on 12/5/2015.
 */
public class GoogleMusicActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private MediaPlayer mediaPlayer;
    private static boolean isPlaying;
    private VideoView vvVideoDisplay;
    private SeekBar sbSeekVideo;
    private ImageButton ibPrev;
    private ImageButton ibNext;
    private ImageButton ibPlayPause;
    int progressValue = 0;
    private TextView tvPath;
    private Button btnPath;
    private MediaController videoMediaController;
    String videoUrl = "http://gdata.youtube.com/feeds/api/videos";
    private SensorManager sensorManager;
    private Sensor ProximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_music_layout);

        vvVideoDisplay = (VideoView) findViewById(R.id.vvVideoDisplay);
        sbSeekVideo = (SeekBar) findViewById(R.id.sbSeekVideo);
        sbSeekVideo.setOnSeekBarChangeListener(this);
        ibPrev = (ImageButton) findViewById(R.id.ibPrev);
        ibPrev.setOnClickListener(this);
        ibPlayPause = (ImageButton) findViewById(R.id.ibPlayPause);
        ibNext = (ImageButton) findViewById(R.id.ibNext);
        ibNext.setOnClickListener(this);
        tvPath = (TextView) findViewById(R.id.tvPath);
        btnPath = (Button) findViewById(R.id.btnPath);
        btnPath.setOnClickListener(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (ProximitySensor == null) {
            Utils.toastIt(getApplicationContext(), "Proximity sensor is not present!");
        } else {
            Utils.toastIt(getApplicationContext(), "Proximity sensor is present!");
            sensorManager.registerListener(proximitySensorEventListener, ProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // Playing song using proximity sensor
    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    createMediaPlayer(R.raw.kalimba);
                    mediaPlayer.start();
                    isPlaying = true;
//                    play();

//                    if (event.values[0] == 1)
//                        createMediaPlayer(R.raw.kalimba);
//                    mediaPlayer.start();
//                    if (event.values[0] == 2)
//                        createMediaPlayer(R.raw.maid);
//                    mediaPlayer.start();
                }
            }
    };
//
//        public void play() {
//            if (mediaPlayer.isPlaying()) {
//                Toast.makeText(getApplicationContext(), "playing", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Preparing...", Toast.LENGTH_SHORT).show();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
//                try {
//                    mediaPlayer.setDataSource("content://media/external/audio/media/45930");
//                } catch (Exception e) {
//                    Utils.toastIt(getBaseContext(), "wrong Url");
//                }
//                mediaPlayer.start();
//            }
//        }

            public void selectAudioFile() {
        Intent intentSelectionAction = new Intent(Intent.ACTION_GET_CONTENT);
        intentSelectionAction.setType("audio/mp3");
        intentSelectionAction.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentSelectionAction, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            Uri uri = data.getData();
            createMediaPlayer(-1);
            try {
                mediaPlayer.setDataSource(this, uri);
                tvPath.setText(uri.toString());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getApplicationContext(), "File not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnPath:
                selectAudioFile();
                break;

            case R.id.ibPrev:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                createMediaPlayer(R.raw.maid);
                Toast.makeText(getApplicationContext(), "Playing form raw folder", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                isPlaying = true;
                ibPrev.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prev));
                ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
                break;

            case R.id.ibPlayPause:
                if (isPlaying && ibPlayPause.isPressed()) {
                    mediaPlayer.pause();
                    isPlaying = false;
                    ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play));
                } else {
                    mediaPlayer.start();
                    isPlaying = true;
                    ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
                }
                break;

            case R.id.ibNext:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                //String url = "http://www.mp3123.com/Hindi/Phantm/Afghan%20Jalebi%20(Ya%20Baba)%20[Mp3Visit.Com].mp3";
                playVideo();
                Toast.makeText(getApplicationContext(), "Playing form online resource", Toast.LENGTH_SHORT).show();
                isPlaying = true;
                ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
                break;
        }
    }

    //playing video
    public void playVideo() {

        vvVideoDisplay.bringToFront();
        vvVideoDisplay.setVideoURI(Uri.parse(videoUrl));
        videoMediaController = new MediaController(this);
        vvVideoDisplay.requestFocus();
        vvVideoDisplay.start();
    }

    public void createMediaPlayer(int resId) {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        if (resId == -1) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer = MediaPlayer.create(this, resId);
        }
        ibPlayPause.setOnClickListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBar.setEnabled(true);
        seekBar.setMax(50);
        progressValue = progress;
        sbSeekVideo.setProgress(progressValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onBackPressed() {
        if(isPlaying){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        finish();
    }
}
