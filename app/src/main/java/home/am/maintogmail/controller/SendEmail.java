package home.am.maintogmail.controller;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import home.am.maintogmail.model.SendEmailModel;
import home.am.maintogmail.view.constants.AppConstants;

public class SendEmail extends AsyncTask<Void, Void, Void> {
    private SendEmailModel sendEmailModel;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private Session session;
    private ProgressDialog progressDialog;

    public SendEmail(SendEmailModel sendEmailModel, Context context) {
        this.sendEmailModel = sendEmailModel;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();
        // Configuration properties for GMail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp b.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // Create a new session with Authentication
        session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AppConstants.email, AppConstants.password);
                    }
                });
        // Create Mime Message instance
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(AppConstants.email)});
            mimeMessage.setSubject(sendEmailModel.getSubject() + "  " + sendEmailModel.getEmail());
            mimeMessage.setText(sendEmailModel.getMessage());
            // Sending email
            Transport.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait ...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context, "Message send successfully", Toast.LENGTH_SHORT).show();
    }
}
