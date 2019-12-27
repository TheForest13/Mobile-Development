package ru.mirea.komanda.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
    }

    public void Click(View view) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("key", tv1.getText());
        startActivity(intent);
    }
}
