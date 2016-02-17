package myapplication.bits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import myapplication.bits.R;

public class ToDoListActivity extends Activity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        // Setup remove listener method call
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if (etNewItem.length() != 0 && btnAddItem.isPressed()) {
            itemsAdapter.add(itemText);
            etNewItem.setText("");
            writeItems();
        } else {
            Utils.toastIt(getBaseContext(), "Please enter new item");
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
