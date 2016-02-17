package myapplication.bits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dell on 12/13/2015.
 */
public class CallLoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_logger_layout);
        Intent incomingIntent = getIntent();

    }
}
