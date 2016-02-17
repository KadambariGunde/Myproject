package myapplication.bits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Dell on 12/7/2015.
 */
public class NewsActivity extends AppCompatActivity {

    private TextView tvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_layout);

        tvNews = (TextView) findViewById(R.id.tvNews);
        Intent incomingIntent = getIntent();
        setTitle(incomingIntent.getStringExtra("newsType"));
        tvNews.setText("Welcome to " + incomingIntent.getStringExtra("newsType"));
    }
}
