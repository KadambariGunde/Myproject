package myapplication.bits;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Dell on 11/24/2015.
 */
public class GreetUserActivity extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Button btnGreet;
    private EditText etName;
    private TextView tvGreetUser;
    private TextToSpeech mTts;
    private static final String TAG = "GreetUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greet_user_activity);

        btnGreet = (Button)findViewById(R.id.btnGreet);
        btnGreet.setOnClickListener(this);
        etName = (EditText)findViewById(R.id.etName);
        tvGreetUser = (TextView)findViewById(R.id.tvGreetUser);
        mTts = new TextToSpeech(this,this  // TextToSpeech.OnInitListener
        );
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnGreet:

              String s =(etName.getText().toString());
                if(s.length() == 0 ){
                    tvGreetUser.setText("Enter Your Name");
                }else{
                    tvGreetUser.setText(" Welcome " +s);
                }
                break;
        }
        sayHello();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Lanuage data is missing or the language is not supported.
                Log.e(TAG, "Language is not available.");
            } else {
                btnGreet.setEnabled(true);
                // Greet the user.
                sayHello();
            }
        } else {
            // Initialization failed.
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void sayHello() {
        Log.i(TAG, "in say Hello");
        mTts.speak(tvGreetUser.getText().toString(),TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
                null, null);
    }

    @Override
    public void onDestroy() {
        System.out.println("Activity Life cycle:onDestroy called");
        // Don't forget to shutdown!
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("Activity Life cycle:onStart called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("Activity Life cycle:onResume called");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("Activity Life cycle:onPause called");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("Activity Life cycle:onRestart called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("Activity Life cycle:onStop called");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
