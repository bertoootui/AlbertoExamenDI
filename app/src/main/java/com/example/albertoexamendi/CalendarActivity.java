package com.example.albertoexamendi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CalendarActivity extends AppCompatActivity {
    FloatingActionButton butback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        butback = findViewById(R.id.butback);
        butback.setOnClickListener(v -> {
            Intent i = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });

    }
}
