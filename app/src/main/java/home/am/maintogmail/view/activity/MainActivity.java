package home.am.maintogmail.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import home.am.maintogmail.R;
import home.am.maintogmail.controller.SendEmail;
import home.am.maintogmail.model.SendEmailModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    //Views
    private Button send;
    private EditText message, email, subject;
    //Object
    private SendEmailModel sendEmailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


    }

    private void initViews() {
        send = findViewById(R.id.btnSendID);
        message = findViewById(R.id.edNameID);
        email = findViewById(R.id.edEmailID);
        subject = findViewById(R.id.edSubjectID);
        send.setOnClickListener(this);
    }

    private boolean validation(SendEmailModel sendEmailModel) {
        String email = sendEmailModel.getEmail();
        String message = sendEmailModel.getMessage();
        String subject = sendEmailModel.getSubject();

        if (email.length() > 6 && email.contains("@") && subject.length() > 0 && message.length() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendID:
                sendEmailModel = new SendEmailModel(email.getText().toString(), subject.getText().toString(), message.getText().toString());
                boolean isValid = validation(sendEmailModel);
                if(isValid){
                    SendEmail sendEmail = new SendEmail(sendEmailModel,MainActivity.this);
                    sendEmail.execute();
                } else {
                    Toast.makeText(this, "Incorrect Action !!! please try again ", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
