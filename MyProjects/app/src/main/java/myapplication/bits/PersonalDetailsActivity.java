package myapplication.bits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import myapplication.bits.R;

public class PersonalDetailsActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etContact;
    private EditText etAddress;
    private SharedPreferences sharedPreferences;
    DbAdapter mDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        Intent intent = getIntent();
        mDbAdapter = new DbAdapter(getBaseContext());

        etName = ((EditText) findViewById(R.id.etName));
        etEmail = ((EditText) findViewById(R.id.etEmail));
        etContact = ((EditText) findViewById(R.id.etContact));
        etAddress = ((EditText) findViewById(R.id.etAddress));
    }

    public void saveUserInfo(View view) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String contact = etContact.getText().toString();
        String address = etAddress.getText().toString();

//        if (name.length() == 0 || email.length() == 0 || contact.length() == 0 || address.length() == 0) {
//            Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();

            mDbAdapter.open();
            if (mDbAdapter.insertUser(etName.getText().toString(), etEmail.getText().toString())) {
                Toast.makeText(this, "User saved successfully", Toast.LENGTH_LONG).show();
                mDbAdapter.close();
            }
//        } else {
//            Toast.makeText(this, "Some issues to save User, contact app developer", Toast.LENGTH_LONG).show();
//        }
    }

    public void showUsers(View view) {
        mDbAdapter.open();
        Cursor cursor = mDbAdapter.getUsers();
        if (cursor.moveToFirst()) {
            do {
                Toast.makeText(getBaseContext(), cursor.getString(0) + " " + cursor.getString(1), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No recodrs found", Toast.LENGTH_LONG).show();
        }
        mDbAdapter.close();
    }


}
