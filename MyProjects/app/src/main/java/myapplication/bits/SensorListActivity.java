package myapplication.bits;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myapplication.bits.R;

public class SensorListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvSensor;
    ArrayList<String> sensorDetailsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_list_layout);
        lvSensor = (ListView) findViewById(R.id.lvSensor);
        lvSensor.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        lvSensor.setBackgroundColor(Color.BLACK);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, sensorDetailsList);
        lvSensor.setAdapter(adapter);
        for (Sensor sensor : sensorList) {
            sensorDetailsList.add(sensor.getName() + " " + sensor.getType() + " " + sensor.getVendor() + " " + sensor.getPower());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
        setTitle(((TextView) view).getText());
    }
}
