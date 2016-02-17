package myapplication.bits;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu;

import static android.view.View.*;

/**
 * Created by Dell on 12/9/2015.
 */
public class ContextMenuDemo extends AppCompatActivity {

    private final static int DEFAULT_SELECTION_LEN = 10;
    String text = "This is context menu..!!";
    private ClipboardManager clipboard;
    private EditText etText;
    private TextView tvCopyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu_layout);
        etText = (EditText) findViewById(R.id.etText);
        registerForContextMenu(etText);
        Intent incomingIntent = getIntent();
        etText.setText(text);

        etText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etText.setCursorVisible(true);
                etText.setSelection(0, etText.length());
                etText.setHighlightColor(0x40FF00FF);
            }
        });

        etText.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                // Copying to Clipboard
                openContextMenu(v);
                int start = etText.getSelectionStart();
                int end = etText.getSelectionEnd();
               final String selectedString = etText.getText().toString().substring(start, end);
                ClipData copy = ClipData.newPlainText("text", selectedString);
                clipboard.setPrimaryClip(copy);

                // Pasting data
                ClipData pasteText = clipboard.getPrimaryClip();
                clipboard.setPrimaryClip(pasteText);
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Cut");
        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0, v.getId(), 0, "Paste");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Cut") {
            Toast.makeText(getApplicationContext(), "Cut", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Copy") {
            Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Paste") {
            Toast.makeText(getApplicationContext(), "Paste", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }
}
