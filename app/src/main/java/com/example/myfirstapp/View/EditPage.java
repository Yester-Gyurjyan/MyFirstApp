package com.example.myfirstapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.presentor.PhotoSaveandShare;
import com.example.myfirstapp.presentor.GetPhotoFromIntent_uri;

public class EditPage extends AppCompatActivity implements BaseView.EditPage {

    private ImageView photo;
    private ImageButton save;
    private ImageButton send;

    private Bitmap bitmap;

    private PhotoSaveandShare photoGallery;
    private   GetPhotoFromIntent_uri getPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();
        putBitmapOnImageView();
    }
    private void sendPhotoButton() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImage();
            }
        });
    }
    private void savePhotoButton() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveImageGallery();
            }
        });
    }
    private void initData(){
        photo = findViewById(R.id.photo);
        save = findViewById(R.id.save);
        send = findViewById(R.id.send);
        savePhotoButton();
        sendPhotoButton();
    }
    public void sendImage(){
        photoGallery.sharePhoto();
    }

    @Override
    public void putBitmapOnImageView() {
        getPhoto = new GetPhotoFromIntent_uri(this , getIntent() , bitmap);
        bitmap = getPhoto.getPhotoFromIntent();
        photo.setImageBitmap(bitmap);
        photoGallery = new PhotoSaveandShare(this , bitmap);
    }
    public void saveImageGallery() {
         photoGallery.savePhotoGallery();
    }
}



