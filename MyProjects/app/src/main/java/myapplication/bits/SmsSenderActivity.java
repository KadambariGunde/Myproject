package myapplication.bits;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsSenderActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber;
    EditText etMsg;
    Button btnSendSMS;
    private Button btnAddContact;
    private Button btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int REQ_CODE_CONTACT_INPUT = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_sender_layout);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etMsg = (EditText) findViewById(R.id.etMsg);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        btnAddContact = (Button) findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(this);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(this);
    }

    public void sendSMS(View view) {
        String number = etNumber.getText().toString();
        String msg = etMsg.getText().toString();

        if (btnSendSMS.isPressed()) {

            if (number.isEmpty()) {
                if (number.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Enter contact number", Toast.LENGTH_SHORT).show();
                }
            }
            if (msg.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter message text", Toast.LENGTH_SHORT).show();
            } else {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(number, null, msg, null, null);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAddContact:
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                // Show user only contacts w/ phone numbers
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContactIntent, REQ_CODE_CONTACT_INPUT);
                break;

            case R.id.btnSpeak:
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
//                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Sorry! Your device doesn't support speech input");
                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Click on speak button", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_CONTACT_INPUT:
                if (data != null) {
                    Uri contactUri = data.getData();
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                    ContentResolver contentResolver = getContentResolver();
                    Cursor cursor = contentResolver.query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();
                    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(column);
                    Toast.makeText(this, etNumber.getText().toString(), Toast.LENGTH_SHORT).show();
                    etNumber.setText(number);
                    cursor.close();
                } else {
                    Utils.toastIt(this, "contact not selected");
                }
                break;
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    etMsg.setText(result.get(0));
                }
                break;
            }
        }
    }
}