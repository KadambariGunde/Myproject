package myapplication.bits;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dell on 12/4/2015.
 */
public class JokeAppActivity extends Activity implements View.OnClickListener {

    private TextView tvJoke;
    private int index = 0;
    protected ImageButton ibPrev;
    protected ImageButton ibNext;
    protected TextView tvJokeNumber;
    protected int size;
    protected ImageView ivSmiley;

    ArrayList<String> joke = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_app_layout);

        tvJoke = (TextView) findViewById(R.id.tvJoke);
        ibNext = (ImageButton) findViewById(R.id.ibNext);
        ibNext.setOnClickListener(this);
        ibPrev = (ImageButton) findViewById(R.id.ibPrev);
        ibPrev.setOnClickListener(this);
        tvJokeNumber = (TextView) findViewById(R.id.tvMessageNumber);
        initialise();
        tvJoke.setText(joke.get(index));
        size = joke.size();
        tvJokeNumber.setText(index + 1 + " / " + size);
        ivSmiley = (ImageView) findViewById(R.id.ivSmiley);

        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
        anim.setTarget(ivSmiley);
        anim.setDuration(4000);
        anim.start();
    }


    private void initialise() {
        joke.add("Salesman:This computer will\n" + "cut your workload by 50%.\n" + "\n" + "Santa:That is great,\n"
                + "I will take two of them:p");
        joke.add("Man: Doctor, will I be able to play the piano after the operation?\n" +
                "Doctor: Yes, of course.\n" +
                "Man: Great! I never could before! ");
        joke.add("Teacher: Tell me a sentence that starts with an \"I\". \n" +
                "Student: I is the...\n" +
                "Teacher: Stop! Never put 'is' after an \"I\". Always put 'am' after an \"I\".\n" +
                "Student: OK. I am the ninth letter of the alphabet. ");
        joke.add("Teacher: How can we get some clean water?\n" +
                "Student: Bring the water from the river and wash it.");
        joke.add("Q: What's a teacher's favorite nation? \n A: Expla-nation. \n" +
                "\n");
    }

    @Override
    public void onClick(View v) {

        if (index >= size - 1 && ibNext.isPressed()) {
            Toast.makeText(this, "You reached to the end of the Jokes!!", Toast.LENGTH_SHORT).show();
            index = -1;
        }
        if (index <= 0 && ibPrev.isPressed()) {
            index = size;
        }
        switch (v.getId()) {

            case R.id.ibPrev:
                index--;
                tvJoke.setText(joke.get(index));
                tvJokeNumber.setText(index + 1 + " / " + size);
                break;

            case R.id.ibNext:
                index++;
                tvJoke.setText(joke.get(index));
                tvJokeNumber.setText(index + 1 + " / " + size);
                break;
        }
    }
}
