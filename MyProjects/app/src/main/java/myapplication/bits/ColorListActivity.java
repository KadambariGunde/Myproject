package myapplication.bits;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import myapplication.bits.R;

public class ColorListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvColor;
    ArrayList<String> color = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);
        Intent intent = getIntent();

        lvColor = (ListView) findViewById(R.id.lvColor);
        lvColor.setOnItemClickListener(this);
        color.add("Red");
        color.add("Blue");
        color.add("Green");
        color.add("Orange");
        color.add("Cyan");
        color.add("Gray");
        color.add("Black");
        color.add("Brown");
        color.add("White");
        color.add("Pink");
        color.add("Yellow");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, color);
        lvColor.setAdapter(adapter);
        lvColor.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//        setTitle(((TextView) view).getText());
        if (((TextView) view).getText().equals("Red")) {
            setTitle(Html.fromHtml("<font Color = 'RED'>Red</font>"));
        }
        if (((TextView) view).getText().equals("Blue")) {
            setTitle(Html.fromHtml("<font Color = 'BLUE'>Blue</font>"));
        }
        if (((TextView) view).getText().equals("Green")) {
            setTitle(Html.fromHtml("<font Color = '#008000'> #Green</font>"));
        }
        if (((TextView) view).getText().equals("Orange")) {
            setTitle(Html.fromHtml("<font Color = '#FFA500'> Orange</font>"));
        }
        if (((TextView) view).getText().equals("Cyan")) {
            setTitle(Html.fromHtml("<font Color = '#00FFFF'> Cyan</font>"));
        }
        if (((TextView) view).getText().equals("Gray")) {
            setTitle(Html.fromHtml("<font Color = '#808080'> Gray</font>"));
        }
        if (((TextView) view).getText().equals("Black")) {
            setTitle(Html.fromHtml("<font Color = 'BLACK'> Black</font>"));
        }
        if (((TextView) view).getText().equals("Brown")) {
            setTitle(Html.fromHtml("<font Color = '#A52A2A'> Brown</font>"));
        }
        if (((TextView) view).getText().equals("White")) {
            setTitle(Html.fromHtml("<font Color = 'WHITE'> White</font>"));
        }
        if (((TextView) view).getText().equals("Pink")) {
            setTitle(Html.fromHtml("<font Color = '#ff69b4'> Pink</font>"));
        }
        if (((TextView) view).getText().equals("Yellow")) {
            setTitle(Html.fromHtml("<font Color = 'YELLOW'> Yellow</font>"));
        }
    }
}
