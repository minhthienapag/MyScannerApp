package com.example.myscannertutorial;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scanlibrary.ScanConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCamera(android.view.View v)
    {
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_CAMERA;
        Intent intent = new Intent(this, com.scanlibrary.ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void openGallery(android.view.View v)
    {
        int REQUEST_CODE = 99;
        int nothing = 0;
        int nothing2 = 0;
        int preference = ScanConstants.OPEN_MEDIA;
        Intent intent = new Intent(this, com.scanlibrary.ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == android.app.Activity.RESULT_OK) {
            android.net.Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            android.graphics.Bitmap bitmap = null;
            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                getContentResolver().delete(uri, null, null);
            //    scannedImageView.setImageBitmap(bitmap);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}
