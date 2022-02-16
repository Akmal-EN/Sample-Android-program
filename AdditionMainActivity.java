package com.example.add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String operation = "addition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showToast("starting " + operation + " subActivity");
        Intent intent = getIntent();
        int[] data = intent.getIntArrayExtra(Intent.EXTRA_TEXT);
        if(data== null){
            showToast("no argument");
            finish();
            return;
        }
        showToast("arguments: " + data[0] + " , " + data[1]);
        Intent result = new Intent();
        result.putExtra(Intent.EXTRA_TEXT,operation);
        result.putExtra("result",handle(data));
        setResult(Activity.RESULT_OK,result);
        finish();

    }

    private int handle(int[] data) {
        return data[0] + data[1];
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}