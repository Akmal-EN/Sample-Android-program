package com.example.final_project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode()== Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    if(data!=null)
                        handleResult(data);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // clock
        Chronometer chronometer = findViewById(R.id.chronometer);
        chronometer.start();


    }

    public void revertText(View view) {
       Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void handleResult(Intent result)
    {
        TextView operationText = findViewById(R.id.operation);
        TextView resultText = findViewById(R.id.result);
        operationText.setText(result.getStringExtra(Intent.EXTRA_TEXT));
         resultText.setText(String.valueOf(result.getIntExtra("result",0)));

    }

    public void Run (View view) {

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, gatherData());
        intent.setAction("Add");
        intent.setType("text/plain");
        launcher.launch(intent);

    }

    private int[] gatherData() {
        TextView arg1 = findViewById(R.id.arg1);
        TextView arg2 = findViewById(R.id.arg2);
        return new int[]
                {
                        getNumber(arg1),
                        getNumber(arg2)
                };
    }



    private int getNumber(TextView arg1) {
        String text = arg1.getText().toString();

        int n = 0;
        if(text.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
        {
            n = Integer.parseInt(text);
        }

        return  n;
    }



}





