package com.flavio.gmuhackday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {

    EditText firstName, lastName, email, phone, password;
    String firstStr, lastStr, emailStr, phoneStr, passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
    }

    public void signUpClick(View view) {
        // IF validFields is true SEND COMMAND TO SERVER WHICH WILL SEND
        // A CONFIRMATION EMAIL TO THE USER EMAIL WHICH HE
        // HAS TO CLICK TO CONTINUE
        boolean validFields = checkFields();
        if (validFields) {
            sendInfoToServer();
            Intent intent = new Intent(this, EnterPinScreen.class);
            startActivity(intent);
            finish();
        } else {
        }
    }

    public boolean checkFields() {
        boolean total = true;
        firstStr = firstName.getText().toString();
        lastStr = lastStr.getBytes().toString();
        emailStr = email.getText().toString();
        phoneStr = phone.getText().toString();
        passStr = password.getText().toString();

        if (firstStr.equals("") || lastStr.equals("") || passStr.equals("")) {
            Toast.makeText(this, "No Empty Fields", Toast.LENGTH_SHORT).show();
            total = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches() || !emailStr.toLowerCase().contains("gmu.edu")) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            total = false;
        }
        if (!Patterns.PHONE.matcher(phoneStr).matches() || passStr.length() < 7) {
            Toast.makeText(this, "Invalid Phone", Toast.LENGTH_SHORT).show();
            total = false;
        }
        return total;
    }


    public void sendInfoToServer() {

        // Send All information to server here for registration
    }
}
