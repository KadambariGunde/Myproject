package myapplication.bits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import myapplication.helloworld.GoogleMapsActivity;

/**
 * Created by Dell on 12/7/2015.
 */
public class ActivityNavigator extends AppCompatActivity implements View.OnClickListener {

    private Button btnGreetUser;
    private ImageButton ibCalculator;
    private Button btnJokes;
    private Button btnMusic;
    private Button btnFelightNews;
    private Button btnCompass;
    private Button btnToDoList;
    private Button btnAlgorithmBenchmarker;
    private Button btnMotiveMessage;
    private Button btnPhotoshop;
    private Button btnVideoPlayer;
    private Button btnGoogleNews;
    private Button btnContextMenu;
    private Button btnMenusDemo;
    private ImageButton btnTreeLife;
    private Button btnCallLogger;
    private Button btnNotification;
    private Button btnSendSMS;
    private Button btnWeb;
    private Button btnColorList;
    private Button btnSensorList;
    private Button btnReadSensorData;
    private Button btnSharedPreference;
    private Button btnUserDetails;
    private Button btnGoogleMap;;
    private Button btnRatingBar;
    public static final String NEWS_TYPE = "newsType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_layout);
        btnGreetUser = (Button) findViewById(R.id.btnGreetUser);
        btnGreetUser.setOnClickListener(this);
        ibCalculator = (ImageButton) findViewById(R.id.ibCalculator);
        ibCalculator.setOnClickListener(this);
        btnJokes = (Button) findViewById(R.id.btnJokes);
        btnJokes.setOnClickListener(this);
        btnMusic = (Button) findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(this);
        btnFelightNews = (Button) findViewById(R.id.btnFelightNews);
        btnFelightNews.setOnClickListener(this);
        btnCompass = (Button) findViewById(R.id.btnCompass);
        btnCompass.setOnClickListener(this);
        btnToDoList = (Button) findViewById(R.id.btnToDoList);
        btnToDoList.setOnClickListener(this);
        btnAlgorithmBenchmarker = (Button) findViewById(R.id.btnAlgorithmBenchmarker);
        btnAlgorithmBenchmarker.setOnClickListener(this);
        btnMotiveMessage = (Button) findViewById(R.id.btnMotiveMessage);
        btnMotiveMessage.setOnClickListener(this);
        btnPhotoshop = (Button) findViewById(R.id.btnPhotoshop);
        btnPhotoshop.setOnClickListener(this);
        btnVideoPlayer = (Button) findViewById(R.id.btnVideoPlayer);
        btnVideoPlayer.setOnClickListener(this);
        btnGoogleNews = (Button) findViewById(R.id.btnGoogleNews);
        btnGoogleNews.setOnClickListener(this);
        btnContextMenu = (Button) findViewById(R.id.btnContextMenu);
        btnContextMenu.setOnClickListener(this);
        btnMenusDemo = (Button) findViewById(R.id.btnMenusDemo);
        btnMenusDemo.setOnClickListener(this);
        btnTreeLife = (ImageButton) findViewById(R.id.btnTreeLife);
        btnTreeLife.setOnClickListener(this);
        btnCallLogger = (Button) findViewById(R.id.btnCallLogger);
        btnCallLogger.setOnClickListener(this);
        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(this);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(this);
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(this);
        btnColorList = (Button) findViewById(R.id.btnColorList);
        btnColorList.setOnClickListener(this);
        btnSensorList = (Button) findViewById(R.id.btnSensorList);
        btnSensorList.setOnClickListener(this);
        btnReadSensorData = (Button) findViewById(R.id.btnReadSensorData);
        btnReadSensorData.setOnClickListener(this);
        btnSharedPreference = (Button) findViewById(R.id.btnSharedPreference);
        btnSharedPreference.setOnClickListener(this);
        btnUserDetails = (Button) findViewById(R.id.btnUserDetails);
        btnUserDetails.setOnClickListener(this);
        btnGoogleMap = (Button) findViewById(R.id.btnGoogleMap);
        btnGoogleMap.setOnClickListener(this);
        btnRatingBar = (Button) findViewById(R.id.btnRatingBar);
        btnRatingBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnGreetUser:
                Intent intentGreetUser = new Intent(getBaseContext(), GreetUserActivity.class);
                startActivity(intentGreetUser);
                break;

            case R.id.ibCalculator:
                Intent intentCalculator = new Intent(getBaseContext(), Calculator.class);
                startActivity(intentCalculator);
                break;

            case R.id.btnJokes:
                Intent intentJoke = new Intent(getBaseContext(), JokeAppActivity.class);
                startActivity(intentJoke);
                break;

            case R.id.btnMusic:
                Intent intentMusic = new Intent(getBaseContext(), GoogleMusicActivity.class);
                startActivity(intentMusic);
                break;

            case R.id.btnFelightNews:
                Intent intentFelightNews = new Intent(getBaseContext(), NewsActivity.class);
                intentFelightNews.putExtra("newsType", "Felight News");
                startActivity(intentFelightNews);
                break;

            case R.id.btnCompass:
                Intent intentCompass = new Intent(getBaseContext(), CompassDemoActivity.class);
                intentCompass.putExtra("newsType", "Compass");
                startActivity(intentCompass);
                break;

            case R.id.btnToDoList:
                Intent intentToDoList = new Intent(getBaseContext(), ToDoListActivity.class);
                intentToDoList.putExtra("newsType", "To Do List");
                startActivity(intentToDoList);
                break;

            case R.id.btnAlgorithmBenchmarker:
                Intent intentAlgorithmBenchmarker = new Intent(getBaseContext(), AlgorithmBenchmarker.class);
                startActivity(intentAlgorithmBenchmarker);
                break;

            case R.id.btnMotiveMessage:
                Intent intentMotiveMessage = new Intent(getBaseContext(), MotiveMessageActivity.class);
                startActivity(intentMotiveMessage);
                break;

            case R.id.btnPhotoshop:
                Intent intentPhotoshop = new Intent(getBaseContext(), PhotoshopActivity.class);
                startActivity(intentPhotoshop);
                break;

            case R.id.btnVideoPlayer:
                Intent intentVideoPlayer = new Intent(getBaseContext(), VideoPlayerActivity.class);
                intentVideoPlayer.putExtra("newsType", "VideoPlayer");
                startActivity(intentVideoPlayer);
                break;

            case R.id.btnGoogleNews:
                Intent intentNewsActivity = new Intent(getBaseContext(), NewsActivity.class);
                intentNewsActivity.putExtra("newsType", "Google News");
                startActivity(intentNewsActivity);
                break;

            case R.id.btnContextMenu:
                Intent intentContextMenu = new Intent(getBaseContext(), ContextMenuDemo.class);
                startActivity(intentContextMenu);
                break;

            case R.id.btnMenusDemo:
                Intent intentMenusDemo = new Intent(getBaseContext(), MenusDemoActivity.class);
                startActivity(intentMenusDemo);
                break;

            case R.id.btnTreeLife:
                Intent intentTreeLife = new Intent(getBaseContext(), TreeLifeActivity.class);
                startActivity(intentTreeLife);
                break;

            case R.id.btnCallLogger:
                Intent intentCallLogger = new Intent(getBaseContext(), CallLoggerActivity.class);
                startActivity(intentCallLogger);
                break;

            case R.id.btnNotification:
                Intent intentNotification = new Intent(getBaseContext(), NotificationDemoActivity.class);
                startActivity(intentNotification);
                break;

            case R.id.btnSendSMS:
                Intent intentSendSMS = new Intent(getBaseContext(), SmsSenderActivity.class);
                startActivity(intentSendSMS);
                break;

            case R.id.btnWeb:
                Intent intentWeb = new Intent(getBaseContext(), WebViewActivity.class);
                startActivity(intentWeb);
                break;

            case R.id.btnColorList:
                Intent intentColor = new Intent(getBaseContext(), ColorListActivity.class);
                startActivity(intentColor);
                break;

            case R.id.btnSensorList:
                Intent intentBroadcastReceiver = new Intent(getBaseContext(), SensorListActivity.class);
                startActivity(intentBroadcastReceiver);
                break;

            case R.id.btnReadSensorData:
                Intent intentSensorData = new Intent(getBaseContext(), ReadSensorDataActivity.class);
                startActivity(intentSensorData);
                break;

            case R.id.btnSharedPreference:
                Intent intentSharedPreference = new Intent(getBaseContext(), SharedPreferenceDemoActivity.class);
                startActivity(intentSharedPreference);
                break;

            case R.id.btnUserDetails:
                Intent intentUserDetails = new Intent(getBaseContext(), UserDetailsActivity.class);
                startActivity(intentUserDetails);
                break;

            case R.id.btnGoogleMap:
                Intent intentGoogleMap = new Intent(getBaseContext(), GoogleMapsActivity.class);
                startActivity(intentGoogleMap);

            case R.id.btnRatingBar:
                Intent intentRating = new Intent(getBaseContext(), AsyncTaskDemoActivity.class);
                startActivity(intentRating);
                break;
        }
    }
}