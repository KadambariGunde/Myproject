package myapplication.bits;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import myapplication.bits.R;

public class TreeLifeActivity extends Activity {

    ListView treeList;
    ImageButton ibSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_life_layout);
        Intent incomingIntent = getIntent();
        treeList = (ListView) findViewById(R.id.lvtree);
        ibSlider = (ImageButton) findViewById(R.id.ibSlider);
        String[] treeTypes = {"Home", "Medicinal Trees", "Fruit Trees", "Food", "Health", "Gardening"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, treeTypes);

        treeList.setAdapter(adapter);

        treeList.setSelector(android.R.color.holo_blue_dark);

        ibSlider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

        treeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) treeList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

