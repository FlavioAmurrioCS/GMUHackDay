package com.flavio.gmuhackday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AdViewActivity extends AppCompatActivity {
    ListView adListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);
        adListView = findViewById(R.id.adListView);

        ArrayList<String> aList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            aList.add("Ad #" + i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aList);
        adListView.setAdapter(adapter);

    }
}
