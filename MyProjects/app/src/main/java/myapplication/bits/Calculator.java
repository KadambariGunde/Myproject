package myapplication.bits;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dell on 11/25/2015.
 */
public class Calculator extends Activity implements  View.OnClickListener  {

        private EditText num1;
        private EditText num2;
        private Button add;
        private Button sub;
        private Button mul;
        private Button div;
        private TextView editTextResult;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calculator_activity);

            num1 = (EditText) findViewById(R.id.etnum1);
            num2 = (EditText) findViewById(R.id.etnum2);
            add = (Button) findViewById(R.id.btAdd);
            add.setOnClickListener(this);
            sub = (Button) findViewById(R.id.btSub);
            sub.setOnClickListener(this);
            mul = (Button) findViewById(R.id.btMul);
            mul.setOnClickListener(this);
            div = (Button) findViewById(R.id.btDiv);
            div.setOnClickListener(this);
            editTextResult = (TextView) findViewById(R.id.tvResult);

        }

        @Override
        public void onClick(View v) {
            double Result ;
            String s1 = num1.getText().toString();
            if(s1.trim().length() == 0){
                s1="0";
            }
            String s2 = num2.getText().toString();
            if(s2.trim().length() == 0){
                s2="0";
            }

            switch (v.getId()) {
                case R.id.btAdd:
                    if(Double.parseDouble(s1) != 0 || Double.parseDouble(s2) != 0) {
                        Result = Double.parseDouble(s1) + Double.parseDouble(s2);
                        editTextResult.setText(String.valueOf(Result));
                    } else {
                        editTextResult.setText("enter numbers");
                    }
                    break;
                case R.id.btSub:
                    if(Double.parseDouble(s1) != 0 || Double.parseDouble(s2) != 0) {
                        Result = Double.parseDouble(s1) - Double.parseDouble(s2);
                        editTextResult.setText(String.valueOf(Result));
                    } else {
                        editTextResult.setText("enter numbers");
                    }
                    break;
                case R.id.btMul:
                        if(Double.parseDouble(s1) != 0 || Double.parseDouble(s2) != 0) {
                            Result = Double.parseDouble(s1) * Double.parseDouble(s2);
                            editTextResult.setText(String.valueOf(Result));
                        }else {
                            editTextResult.setText("enter numbers");
                        }
                    break;
                case R.id.btDiv:
                    if(Double.parseDouble(s2) == 0) {
                            Toast.makeText(getApplicationContext(), " Enter valid input !!",
                                    Toast.LENGTH_LONG).show();
                    }else {
                        Result = 0;
                        Result = Double.parseDouble(s1) / Double.parseDouble(s2);
                        editTextResult.setText(String.valueOf(Result));
                    }
                    break;
            }
        }
        public boolean onCreateOptionMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_calculator, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
