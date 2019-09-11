package com.example.myfirstapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.presentor.OpenCameraandGallery;

import java.io.ByteArrayOutputStream;

public class ChoosePhotoPage extends AppCompatActivity implements BaseView.ChoosePhotoPage {
    public static final int GALLERY_CODE = 1;
    public static final int CAMERA_CODE = 2;
    private static final int STORAGE_PERMISSION = 1;

    private ImageButton camera;
    private ImageButton gallery;
    private OpenCameraandGallery getPhoto;
    private Intent intent;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();

    }
    private void initData() {
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        getPhoto = new OpenCameraandGallery(this);
        openCameraButton();
        openGalleryButton();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            sendGalleryPhoto(data);
        }
        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {
            sendCameraPhoto(data);
        }
    }
    private void sendGalleryPhoto(Intent data) {
        uri = data.getData();
        startNextPage();
    }
    private void sendCameraPhoto(Intent data) {
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        uri = getImageUri(this , bitmap);
        startNextPage();
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public static boolean isGrantExternalRW(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (context.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            ((Activity) context).requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, STORAGE_PERMISSION);
            return false;
        }
        return true;
    }
    @Override
    public void openCameraButton() {

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhoto.openCamera();
            }
        });
    }
    @Override
    public void openGalleryButton() {
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getPhoto.openGallery();

            }
        });
    }
    @Override
    public void startNextPage() {
        intent = new Intent(this, EditPage.class);
        intent.putExtra("picture", uri.toString());
        if (isGrantExternalRW(this)) {
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this , "Permission denied!" ,Toast.LENGTH_SHORT).show();
        }
    }
}


