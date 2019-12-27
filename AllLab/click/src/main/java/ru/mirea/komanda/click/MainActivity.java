package ru.mirea.komanda.click;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.Text);
    }


    public void clickOK(View view) {
        text.setText("Нажали ок");
    }

    public void clickCancel(View view) {
        text.setText("Нажали Cancel");
    }
}
