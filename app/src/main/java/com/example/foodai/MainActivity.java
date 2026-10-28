package com.example.foodai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btnScan);
        Button btnManual = findViewById(R.id.btnManual);

        // "Let's scan" dabane par Camera screen khulegi
        btnScan.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CameraActivity.class)));

        // Manual logging abhi ke liye sirf message
        btnManual.setOnClickListener(v ->
                Toast.makeText(this, "Manual log jald aayega", Toast.LENGTH_SHORT).show());
    }
}