package myapplication.bits;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Dell on 11/27/2015.
 */
public class MailerActivity extends Activity implements View.OnClickListener{

        private EditText etEmailId;
        private EditText etPassword;
        private Button btnSubmit;
        private EditText tvInvalidEmail;
        private EditText tvInvalidPassword;

    @Override
        protected void onCreate(Bundle savedInstanceState ){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mymailer_activity);

            etEmailId = (EditText)findViewById(R.id.etEmailId);
            etPassword = (EditText)findViewById(R.id.etPassword);
            btnSubmit = (Button)findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(this);
            etPassword = (EditText)findViewById(R.id.etPassword);
            tvInvalidEmail = (EditText)findViewById(R.id.tvInvalidEmail);
            tvInvalidPassword = (EditText)findViewById(R.id.tvInvalidPassword);
        }

    @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnSubmit:

                    String EmailId = etEmailId.getText().toString().trim();
                    String Password = etPassword.getText().toString();
                  //  String InvalidEmail = etInvalidEmail.getText().toString();
                  //  String InvalidPassword = etInvalidPassword.getText().toString();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    if(EmailId.isEmpty()) {
                        if (Password.isEmpty()) {
                            tvInvalidEmail.setVisibility(View.VISIBLE);
                            tvInvalidEmail.setText(" Enter valid Email Id !! ");
                        }
                    }
                    if(EmailId.matches(emailPattern)) {
                            tvInvalidEmail.setVisibility(View.GONE);
                        if (Password.isEmpty()) {
                            tvInvalidPassword.setVisibility(View.VISIBLE);
                            tvInvalidPassword.setText(" Enter valid Password !! ");
                        }
                    }else{
                        tvInvalidEmail.setVisibility(View.VISIBLE);
                        tvInvalidEmail.setText(" Enter valid Email Id !! ");
                    }
                    if(btnSubmit.isPressed()) {
                        if (EmailId.matches(emailPattern)) {

                            if (!EmailId.isEmpty()) {
                                    tvInvalidEmail.setVisibility(View.GONE);
                                if (!Password.isEmpty()) {
                                    tvInvalidPassword.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), " Login Successfully !! ",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                   break;
            }
        }
}
