//package com.flavio.gmuhackday;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//public class ApiCall extends AsyncTask<Void, Void, String> {
//
//    final static String API_REGISTER = "https://netcomm.fourguystech.com/api_hackaday/?type=register_user" +
//            "&username=famurrio" +
//            "&password=1234" +
//            "&first_name=Flavio" +
//            "&last_name=Amurrio" +
//            "&email=famurrio@gmu.edu" +
//            "&phone=17036553443";
//    final static String API_LOGIN = "https://netcomm.fourguystech.com/api_hackaday/?type=user_login&username=userhere&password=passwordhere123";
//    final static String API_VALIDATE = "https://netcomm.fourguystech.com/api_hackaday/?type=validate_user&username=userhere&code=123456";
//
//    String apiURL;
//
//    public ApiCall(String apiURL, String userName) {
//        this.apiURL = apiURL;
//    }
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        try {
//            URL url = new URL(API_URL + "email=" + "Test@gmail.com" + "&apiKey=" + API_KEY);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            try {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                bufferedReader.close();
//                return stringBuilder.toString();
//            } finally {
//                urlConnection.disconnect();
//            }
//        } catch (Exception e) {
//            Log.e("ERROR", e.getMessage(), e);
//            return null;
//        }
//    }
//
//    protected void onPostExecute(String response) {
//        if (response == null) {
//            response = "THERE WAS AN ERROR";
//        }
////        progressBar.setVisibility(View.GONE);
//        Log.i("INFO", response);
////        responseView.setText(response);
//        // TODO: check this.exception
//        // TODO: do something with the feed
//
////            try {
////                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
////                String requestID = object.getString("requestId");
////                int likelihood = object.getInt("likelihood");
////                JSONArray photos = object.getJSONArray("photos");
////                .
////                .
////                .
////                .
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
//    }
//}
//}
