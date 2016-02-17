package myapplication.bits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dell on 11/30/2015.
 */
public class ListViewActivity extends Activity {

   ListView appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);

        appList = (ListView)findViewById(R.id.lvContacts);

        ArrayList<MyMessage> msgList = new ArrayList<MyMessage>();
        MyMessage msg = new MyMessage("Kadambari", "13","Abc ");
        msgList.add(msg);
        msgList.add(msg);
        msgList.add(msg);
        msgList.add(msg);
        msgList.add(msg);

//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,List);
        MyArrayAdapter adapter = new MyArrayAdapter(this,msgList);
        appList.setAdapter(adapter);

        appList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) appList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    }



