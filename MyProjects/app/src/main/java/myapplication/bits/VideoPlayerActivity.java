package myapplication.bits;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import myapplication.bits.R;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private VideoView vvVideoDisplay;
    private SeekBar sbSeekVideo;
    private ImageButton ibPrev;
    private ImageButton ibNext;
    private ImageButton ibPlayPause;
    int progressValue = 0;
    private TextView tvPath;
    private Button btnPath;
    static private boolean isPlaying = true;
    private MediaController videoMediaController;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent incomingIntent = getIntent();

        vvVideoDisplay = (VideoView) findViewById(R.id.vvVideoDisplay);
        sbSeekVideo = (SeekBar) findViewById(R.id.sbSeekVideo);
        sbSeekVideo.setOnSeekBarChangeListener(this);
        ibPrev = (ImageButton) findViewById(R.id.ibPrev);
        ibPrev.setOnClickListener(this);
        ibPlayPause = (ImageButton) findViewById(R.id.ibPlayPause);
        ibPlayPause.setOnClickListener(this);
        ibNext = (ImageButton) findViewById(R.id.ibNext);
        ibNext.setOnClickListener(this);
        tvPath = (TextView) findViewById(R.id.tvPath);
        btnPath = (Button) findViewById(R.id.btnPath);
        btnPath.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnPath:
                selectAudioFile();
                break;

            case R.id.ibPrev:
                videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
                vvVideoDisplay.bringToFront();
                Uri uri = Uri.parse(videoPath);
                vvVideoDisplay.setVideoURI(uri);
                vvVideoDisplay.requestFocus();
                vvVideoDisplay.start();
                Toast.makeText(getApplicationContext(), "Playing form raw folder", Toast.LENGTH_SHORT).show();
                isPlaying = true;
                ibPrev.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prev));
                ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
                break;

            case R.id.ibPlayPause:
                if (isPlaying && ibPlayPause.isPressed()) {
                    vvVideoDisplay.pause();
                    isPlaying = false;
                    ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play));
                } else {
                    vvVideoDisplay.start();
                    isPlaying = true;
                    ibPlayPause.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
                }
                break;

            case R.id.ibNext:
                if (isPlaying && vvVideoDisplay.isPressed()) {
                    vvVideoDisplay.stopPlayback();
                }
        }
    }

    public void selectAudioFile() {
        Intent intentSelectionAction = new Intent(Intent.ACTION_GET_CONTENT);
        intentSelectionAction.setType("video/mp4");
        intentSelectionAction.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentSelectionAction, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            Uri uri = data.getData();
            vvVideoDisplay.setVideoURI(uri);
            tvPath.setText(uri.toString());
            vvVideoDisplay.start();
        } else {
            Toast.makeText(getApplicationContext(), "File not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
