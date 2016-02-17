package myapplication.bits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import myapplication.bits.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView wvWeb;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        wvWeb = (WebView)findViewById(R.id.wvWeb);
        pbProgress = (ProgressBar)findViewById(R.id.pbProgress);
        Intent incomingIntent= getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pbProgress.setEnabled(true);
        wvWeb.setWebViewClient(new WebViewClient());
        wvWeb.loadUrl("https://www.google.com");
        pbProgress.setVisibility(View.INVISIBLE);
        wvWeb.setWebChromeClient(new WebChromeClient());
    }
}
