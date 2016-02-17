package myapplication.bits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import myapplication.bits.R;

public class MenusDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus_demo_layout);
        Intent incomingIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Help");
        menu.add("About");
        menu.add("Settings");
        getMenuInflater().inflate(R.menu.menu_menus_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(this," You selected " +item.getTitle(),Toast.LENGTH_SHORT).show();

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(item.getTitle().equals("Help")){
            Intent intentHelp = new Intent(getBaseContext(), NewsActivity.class);
            intentHelp.putExtra("newsType","Help");
            startActivity(intentHelp);
        }
        if(item.getTitle().equals("About")){
            Intent intentAbout = new Intent(getBaseContext(), NewsActivity.class);
            intentAbout.putExtra("newsType", "About");
            startActivity(intentAbout);
        }
        if (item.getTitle().equals("Settings")){
            Intent intentSettings = new Intent(getBaseContext(), NewsActivity.class);
            intentSettings.putExtra("newsType","Settings");
            startActivity(intentSettings);
        }

        return super.onOptionsItemSelected(item);
    }
}
