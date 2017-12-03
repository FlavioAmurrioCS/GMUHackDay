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

public class MainActivity extends AppCompatActivity {
    EditText mUserName, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserName = findViewById(R.id.mUserName);
        mPassword = findViewById(R.id.mPassword);
    }

    public void registerClick(View view) {
        Intent intent = new Intent(this, RegisterScreen.class);
        startActivity(intent);
    }

    public void loginClick(View view) {
        String username = mUserName.getText().toString();
        String pass = mPassword.getText().toString();
        String apiUrl = "https://netcomm.fourguystech.com/api_hackaday/?type=login_user&username=" + username + "&password=" + pass;
        new HttpGetTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, apiUrl);
    }

    public void onFinishGetRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        if (result.contains(": false,")) {
            Intent intent = new Intent(this, AdViewActivity.class);
            startActivity(intent);
        }

    }

    public static String apiCall(String urlAddress) {
        StringBuffer data = new StringBuffer();
        BufferedReader br = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new
                    URL(urlAddress).openConnection();
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public void showApiCall(View view) {
        String API_REGISTER = "https://netcomm.fourguystech.com/api_hackaday/?type=register_user&username=famurrio&password=1234&first_name=Flavio&last_name=Amurrio&email=famurrio@gmu.edu&phone=17036553443";
        String result = apiCall(API_REGISTER);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
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
            } catch (Exception e) {

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
