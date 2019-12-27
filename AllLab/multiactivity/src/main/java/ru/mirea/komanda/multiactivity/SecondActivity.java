package ru.mirea.komanda.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv2=findViewById(R.id.tv2);
        Intent intent = getIntent();
        String text =intent.getStringExtra("key");
        tv2.setText(text);
    }
}

