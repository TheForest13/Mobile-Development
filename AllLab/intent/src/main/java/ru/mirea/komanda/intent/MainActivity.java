package ru.mirea.komanda.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Click(View view) {
        Uri address = Uri.parse("https://www.google.com/search?q=%D0%BF%D0%BE%D1%81%D1%82%D0%B0%D0%B2%D1%8C%D1%82%D0%B5+%D0%B7%D0%B0%D1%87%D0%B5%D1%82+%D0%BC%D0%B5%D0%BC&client=firefox-b-d&sxsrf=ACYBGNT6YuN08TRB6Fyc6SG9Vq8hShJDpQ:1576911777600&tbm=isch&source=iu&ictx=1&fir=JbDrnmPMmTA10M%253A%252CFtSqn_3QaSopsM%252C_&vet=1&usg=AI4_-kQKpYleRan18iDy6CFgktGn0KNHaA&sa=X&ved=2ahUKEwjthuKSlsbmAhVN8aYKHZx7CFkQ9QEwAnoECAoQCA#imgrc=JbDrnmPMmTA10M:");
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openLinkIntent);
        } else {
            Log.d("Intent", "Проблема с намерением!");
        }
    }
}