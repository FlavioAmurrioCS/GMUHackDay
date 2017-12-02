package com.flavio.gmuhackday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }

    public void signUpClick(View view) {
        // IF validFields is true SEND COMMAND TO SERVER WHICH WILL SEND
        // A CONFIRMATION EMAIL TO THE USER EMAIL WHICH HE
        // HAS TO CLICK TO CONTINUE
        boolean validFields = checkFields();
        if (validFields) {
            sendInfoToServer();
            Toast.makeText(this, "Verification Email Sent!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Invalid Field!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkFields() {
        // DO A CHECK FOR ALL THE PARAMETERS TO BE VALID
        return true;
    }

    public void sendInfoToServer() {
        // Send All information to server here for registration
    }
}
