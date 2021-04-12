package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent i = getIntent();
        try {
            JSONObject userObj = new JSONObject(i.getStringExtra("username"));
            TextView welcomeMsg = findViewById(R.id.txtWelcome);
            welcomeMsg.setText("Welcome "+ userObj.get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}