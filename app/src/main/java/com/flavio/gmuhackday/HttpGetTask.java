package com.flavio.gmuhackday;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Flavio on 12/2/2017.
 */

public class HttpGetTask extends AsyncTask<String, Void, String> {
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
        } catch (MalformedURLException e1) {e1.printStackTrace();
        } catch (IOException e1) {e1.printStackTrace();
        } finally {
            if (br != null)
                try {  br.close();
                } catch (IOException e) {e.printStackTrace();}
        }
        return data.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        //          Toast.makeText(getApplicationContext(),"String returned" + result.length(),Toast.LENGTH_SHORT).show();
//        onFinishGetRequest(result);
    }
}
