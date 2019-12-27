package ru.mirea.komanda.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    private static final int REQUEST_CODE_PERMISSION_WRITE = 1;
    final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 0;
    private boolean isWork = false;
    private boolean isWork2 = false;

    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    OutputStream outputstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bytearrayoutputstream = new ByteArrayOutputStream();
        imageView = findViewById(R.id.imageView);

        int permissionStatus = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ;
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_CAMERA) ;
        }



        int permissionStatus2 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ;
        if (permissionStatus2 == PackageManager.PERMISSION_GRANTED) {
            isWork2 = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_WRITE) ;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnailBitmap);
        }
    }
    public void imageViewOnClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isWork = true;
                } else {
                    isWork = false;
                }
                return;
            }
            case REQUEST_CODE_PERMISSION_WRITE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isWork2 = true;
                } else {
                    isWork2 = false;
                }
                return;
            }
        }
    }

    public void Save(View view) {
        BitmapDrawable drawable;
        Bitmap bitmap;
        drawable=(BitmapDrawable)imageView.getDrawable();
        bitmap=drawable.getBitmap();
        File cdcard = Environment.getExternalStorageDirectory();
        File dir = new File(cdcard.getAbsolutePath()+"/Demo/");
        dir.mkdir();
        String fileName = String.format("%d.jpg",System.currentTimeMillis());
        File outfile = new File (dir,fileName);
        Toast.makeText(this,"save",Toast.LENGTH_LONG).show();
        try {
            outputstream = new FileOutputStream(outfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputstream);
            outputstream.flush();
            outputstream.close();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(outfile));
            sendBroadcast(intent);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
