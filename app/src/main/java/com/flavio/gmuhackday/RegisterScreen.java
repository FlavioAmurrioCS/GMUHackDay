package com.flavio.gmuhackday;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterScreen extends AppCompatActivity {

    EditText firstName, lastName, email, phone, password, userName;
    String firstStr, lastStr, emailStr, phoneStr, passStr, userStr;
    String apiUrl;
    String API_REGISTER = "https://netcomm.fourguystech.com/api_hackaday/?type=register_user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_submit:
                // do register
            default:
                // default action
                finish();
        }

        return super.onOptionsItemSelected(item);
      
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        userName = findViewById(R.id.userName);
    }

    public void signUpClick(View view) {
        // IF validFields is true SEND COMMAND TO SERVER WHICH WILL SEND
        // A CONFIRMATION EMAIL TO THE USER EMAIL WHICH HE
        // HAS TO CLICK TO CONTINUE
        boolean validFields = checkFields();
//        validFields = true;
        if (validFields) {
//            Toast.makeText(getApplicationContext(), "validFields", Toast.LENGTH_SHORT).show();
            new HttpGetTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, apiUrl);
        }
    }

    private void onFinishGetRequest(String result) {
        if (result.contains("Success")) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EnterPinScreen.class);
            intent.putExtra("userName", userStr);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//        userName.setText(result);
    }

//    private void onFinishGetRequest(String result) {
//        try {
//
//            JSONArray earthquakes = (new JSONArray(result));
//            int len = earthquakes.length();
//            for (int i = 0;i<len; i++) {
//                JSONObject quake = earthquakes.getJSONObject(i);
//                String region = quake.getString("region");
//                String mag = quake.getString("magnitude");
//                String occurred = quake.getString("occurred_at");
//                // get Lat/Long here
//
//                adapter.add(region + "  with magnitude = " + mag + " on " + occurred);
//            }
//            adapter.notifyDataSetChanged();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public boolean checkFields() {
        boolean total = true;
        firstStr = firstName.getText().toString();
        lastStr = lastName.getText().toString();
        emailStr = email.getText().toString();
        phoneStr = phone.getText().toString();
        passStr = password.getText().toString();
        userStr = userName.getText().toString();

        apiUrl = API_REGISTER + "&username=" + userStr + "&password=" + passStr + "&first_name=" + firstStr + "&last_name=" + lastStr + "&email=" + emailStr + "&phone=1" + phoneStr;

        if (firstStr.equals("") || lastStr.equals("") || passStr.equals("") || userStr.equals("")) {
            Toast.makeText(this, "No Empty Fields", Toast.LENGTH_SHORT).show();
            total = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches() || !emailStr.toLowerCase().contains("gmu.edu")) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            total = false;
        }
        if (!Patterns.PHONE.matcher(phoneStr).matches() || phoneStr.length() < 7) {
            Toast.makeText(this, "Invalid Phone", Toast.LENGTH_SHORT).show();
            total = false;
        }
        return total;
    }


    public String sendInfoToServer() {
        String API_REGISTER = "https://netcomm.fourguystech.com/api_hackaday/?type=register_user&";
//        username=famurrio&password=1234&first_name=Flavio&last_name=Amurrio&email=famurrio@gmu.edu&phone=17036553443";
        // Send All information to server here for registration
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(API_REGISTER + "username=" + userStr + "&password=" + passStr + "&first_name=" + firstStr + "&last_name=" + lastStr + "&email=" + emailStr + "&phone=" + phoneStr);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }


    private class HttpGetTask extends AsyncTask<String, Void, String> {
        private HttpGetTask obj;

        public HttpGetTask newInstance() {
            if (this.obj == null) {
                this.obj = new HttpGetTask();
                return obj;
            } else {
                return obj;
            }
        }

        @Override
        protected String doInBackground(String... params) {
//            Toast.makeText(getApplicationContext(), "run Async Task", Toast.LENGTH_SHORT).show();
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