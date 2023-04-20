package com.example.recognizespeechapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    private Button button;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    );
                    intent.putExtra(
                            RecognizerIntent.EXTRA_PROMPT,
                            "RecognizeSpeechApp"
                    );
                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String str = "";
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (int i = 0; i < results.size(); i++){
                str += results.get(i)+ " ";
            }
            editText.setText(str);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}