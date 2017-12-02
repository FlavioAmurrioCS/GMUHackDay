package com.flavio.gmuhackday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPinScreen extends AppCompatActivity {
    EditText verPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_screen);
        verPin = findViewById(R.id.verPin);
    }

    public void verifyPinClick(View view) {
        String pinStr = verPin.getText().toString();

        if (!getPinResponse(pinStr)) {
            Toast.makeText(this, "Invalid Pin", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Verification Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AdViewActivity.class);
            startActivity(intent);
        }

    }

    public boolean getPinResponse(String str) {
        return true;
    }
}
