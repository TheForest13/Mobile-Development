package ru.mirea.a.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private String mHost = "time-a.nist.gov"; // или time-a.nist.gov or time-b.nist.gov
    private int mPort = 13;
    private final int MY_PERMISSIONS_REQUEST_INTERNET = 100;
    private boolean isWork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);


        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_INTERNET);
        }
    }


    public void onClick(View view) {
        GetTimeTask timeTask = new GetTimeTask();
        timeTask.execute();
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(mHost, mPort);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // игнорируем первую строку
                timeResult = reader.readLine(); // считываем вторую строку
                Log.d(TAG, timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mTextView.setText(result);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_INTERNET: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isWork = true;
                } else {
                    isWork = false;
                }
                return;
            }

        }
    }
}