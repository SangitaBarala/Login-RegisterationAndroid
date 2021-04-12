package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    TextView errorMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edEmail = findViewById(R.id.txtEmail);
        edPassword = findViewById(R.id.txtPwd);
    }

    public void LoginClick(View v) throws JSONException {
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String url = "https://android.parthrai.ca/api/android/login";
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject payload = new JSONObject();
        payload.put("email",email);
        payload.put("password",password);

        JSONArray array_payload = new JSONArray();
        array_payload.put(payload);

        JsonArrayRequest loginRequest = new JsonArrayRequest(Request.Method.POST, url, array_payload, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length()== 0){
                    errorMsg.setText("Check login credentials");
                    return;
                }
                try {
                   JSONObject user = response.getJSONObject(0);
                   // Log.d("name is--", user.get("name").toString());
                    dashboardIntent(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error--", error.toString());
            }
        });
        queue.add(loginRequest);
    }
    public void btnRegister(View v){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void dashboardIntent(JSONObject user){
        Intent intent = new Intent(this,Dashboard.class);
        intent.putExtra("username", user.toString());
        startActivity(intent);
    }
}