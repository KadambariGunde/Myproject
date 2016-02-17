package myapplication.bits;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MotiveMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMessage;
    protected ImageButton ibPrev;
    protected ImageButton ibNext;
    protected ImageView ivMotive;
    protected TextView tvMessageNumber;
    private int index = 0;
    protected int size;

    ArrayList<String> message = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motive_message_layout);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        ibNext = (ImageButton) findViewById(R.id.ibNext);
        ibNext.setOnClickListener(this);
        ibPrev = (ImageButton) findViewById(R.id.ibPrev);
        ibPrev.setOnClickListener(this);
        tvMessageNumber = (TextView) findViewById(R.id.tvMessageNumber);

        Intent incomingIntent = getIntent();
        initialise();
        tvMessage.setText(message.get(index));
        size = message.size();
        tvMessageNumber.setText(index + 1 + " / " + size);
        ivMotive = (ImageView) findViewById(R.id.ivMotive);

        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
        anim.setTarget(ivMotive);
        anim.setDuration(4000);
        anim.start();
    }

    private void initialise() {
        message.add("Stop chasing the money and start chasing the passion.");
        message.add("Opportunities don't happen. You create them.");
        message.add("Try not to become a person of success, but rather try to become a person of value.");
        message.add("A successful person is one who can lay a firm foundation with the bricks others have thrown at him.");
        message.add("It is not the strongest of the species that survive, nor the most intelligent," +
                " but the one most responsive to change.");
    }

    @Override
    public void onClick(View v) {

        if (index >= size - 1 && ibNext.isPressed()) {
            Toast.makeText(this, "You reached to the end of the Mssages !!", Toast.LENGTH_SHORT).show();
            index = -1;
        }
        if (index <= 0 && ibPrev.isPressed()) {
            index = size;
        }
        switch (v.getId()) {

            case R.id.ibPrev:
                index--;
                tvMessage.setText(message.get(index));
                tvMessageNumber.setText(index + 1 + " / " + size);
                break;

            case R.id.ibNext:
                index++;
                tvMessage.setText(message.get(index));
                tvMessageNumber.setText(index + 1 + " / " + size);
                break;
        }
    }
}