package com.flavio.gmuhackday;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EnterPinScreen extends AppCompatActivity {
    EditText verPin;
    String userStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_screen);
        verPin = findViewById(R.id.verPin);
        Bundle bundle = getIntent().getExtras();
        userStr = bundle.getString("userName");
    }

    public void verifyPinClick(View view) {
        String pinStr = verPin.getText().toString();
        String apiUrl = "https://netcomm.fourguystech.com/api_hackaday/?type=validate_user&username=" + userStr + "&code=" + pinStr;

        new HttpGetTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, apiUrl);
    }


    public void onFinishGetRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        if(result.contains("\"error\":false"))
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuffer data = new StringBuffer();
            BufferedReader br = null;
            try {
                HttpURLConnection conn = (HttpURLConnection) new
                        URL(params[0]).openConnection();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String rawData;
                while ((rawData = br.readLine()) != null) {
                    data.append(rawData);
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (br != null)
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return data.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            //          Toast.makeText(getApplicationContext(),"String returned" + result.length(),Toast.LENGTH_SHORT).show();
            onFinishGetRequest(result);
        }
    }


}
