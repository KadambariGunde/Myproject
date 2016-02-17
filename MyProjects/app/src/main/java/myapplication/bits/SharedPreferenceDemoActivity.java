package myapplication.bits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.lang.ref.PhantomReference;

import myapplication.bits.R;

public class SharedPreferenceDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout myLayout;
    private final String MY_PREF = "MyPref";
    private final String BG_KEY = "bg_key";
    private SharedPreferences preferences;
    private RadioButton rbWhite;
    private RadioButton rbRed;
    private RadioButton rbBlack;
    private RadioButton rbBlue;
    private RadioButton rbGreen;
    private RadioButton rbLightGray;
    private RadioButton rbYellow;
    private RadioButton rbDarkGray;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference_demo_layout);
        Intent intent = getIntent();
        myLayout = (RelativeLayout) findViewById(R.id.myLayout);

        rbWhite = (RadioButton) findViewById(R.id.rbWhite);
        rbWhite.setOnClickListener(this);
        rbRed = (RadioButton) findViewById(R.id.rbRed);
        rbRed.setOnClickListener(this);
        rbBlack = (RadioButton) findViewById(R.id.rbBlack);
        rbBlack.setOnClickListener(this);
        rbBlue = (RadioButton) findViewById(R.id.rbBlue);
        rbBlue.setOnClickListener(this);
        rbGreen = (RadioButton) findViewById(R.id.rbGreen);
        rbGreen.setOnClickListener(this);
        rbDarkGray = (RadioButton) findViewById(R.id.rbDarkGray);
        rbDarkGray.setOnClickListener(this);
        rbYellow = (RadioButton) findViewById(R.id.rbYellow);
        rbYellow.setOnClickListener(this);
        rbLightGray = (RadioButton) findViewById(R.id.rbLightGray);
        rbLightGray.setOnClickListener(this);
        preferences = getSharedPreferences(MY_PREF, MODE_PRIVATE);

        myLayout.setBackgroundColor(preferences.getInt(BG_KEY, Color.RED));
    }

    @Override
    public void onClick(View v) {

        editor = preferences.edit();
        switch (v.getId()) {

            case R.id.rbWhite:
                myLayout.setBackgroundColor(Color.WHITE);
                editor.putInt(BG_KEY, Color.WHITE);
                editor.commit();
                break;

            case R.id.rbRed:
                myLayout.setBackgroundColor(Color.RED);
                editor.putInt(BG_KEY, Color.RED);
                editor.commit();
                break;

            case R.id.rbBlack:
                myLayout.setBackgroundColor(Color.BLACK);
                editor.putInt(BG_KEY, Color.BLACK);
                editor.commit();
                break;

            case R.id.rbBlue:
                myLayout.setBackgroundColor(Color.BLUE);
                editor.putInt(BG_KEY, Color.BLUE);
                editor.commit();
                break;

            case R.id.rbGreen:
                myLayout.setBackgroundColor(Color.GREEN);
                editor.putInt(BG_KEY, Color.GREEN);
                editor.commit();
                break;

            case R.id.rbDarkGray:
                myLayout.setBackgroundColor(Color.DKGRAY);
                editor.putInt(BG_KEY, Color.DKGRAY);
                editor.commit();
                break;

            case R.id.rbYellow:
                myLayout.setBackgroundColor(Color.YELLOW);
                editor.putInt(BG_KEY, Color.YELLOW);
                editor.commit();
                break;

            case R.id.rbLightGray:
                myLayout.setBackgroundColor(Color.LTGRAY);
                editor.putInt(BG_KEY, Color.LTGRAY);
                editor.commit();
                break;
        }
    }
}
