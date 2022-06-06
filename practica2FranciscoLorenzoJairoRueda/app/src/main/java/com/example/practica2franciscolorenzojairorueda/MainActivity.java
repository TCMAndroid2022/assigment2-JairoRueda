package com.example.practica2franciscolorenzojairorueda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private EditText eTText;
    private RequestQueue queue;
    private String url = "https://random-word-api.herokuapp.com/word";
    private String rnd;
    private String nickname;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(getApplicationContext());
        eTText = findViewById(R.id.nickname);

        start = findViewById(R.id.start);


        start.setOnClickListener(v->{
            rnd = rnd.replaceAll("\\W+","");
            nickname = eTText.getText().toString();

            if (nickname.length()>0){
                Intent intent = new Intent(getApplicationContext(), GameAct.class);
                intent.putExtra("Word",rnd);
                intent.putExtra("NickName",nickname);
                startActivity(intent);
            }
        });

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, response -> rnd = response, error -> {

        }

        );
        queue.add(stringRequest);
    }




}