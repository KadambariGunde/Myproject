package myapplication.bits;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import myapplication.bits.R;

public class SplashScreenActivity extends AppCompatActivity {


    private ImageView ivElectricity;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        ivElectricity = (ImageView) findViewById(R.id.ivElectricity);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        // Splash Screen
        Handler handler = new Handler();
        Runnable obj = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), ActivityNavigator.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(obj, 5000);
    }

    // Animating Image

    protected void onStart() {
        super.onStart();
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
        ivElectricity.startAnimation(animation);
        animation.setDuration(2000);

        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
        anim.setTarget(ivElectricity);
        anim.setDuration(3000);
        anim.start();

        //Animating Text
        ObjectAnimator rotationAnimaiton = ObjectAnimator.ofFloat(tvMessage, "rotation", 0, 360, 0, 360, 90, 0);
        rotationAnimaiton.setDuration(1000);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(tvMessage, "alpha", 0.5f, 0.9f, 0, 1.0f);
        alphaAnimation.setDuration(2000);
        ObjectAnimator moveAnimation = ObjectAnimator.ofFloat(tvMessage, "move", 0, 100, 0, -100, 0, 100);
        moveAnimation.setDuration(2000);
        AnimatorSet set = new AnimatorSet();
        set.play(rotationAnimaiton).after(moveAnimation).with(alphaAnimation);
        set.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
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
