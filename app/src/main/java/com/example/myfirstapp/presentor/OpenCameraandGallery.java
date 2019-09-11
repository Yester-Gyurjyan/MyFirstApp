package com.example.myfirstapp.presentor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import static com.example.myfirstapp.View.ChoosePhotoPage.CAMERA_CODE;
import static com.example.myfirstapp.View.ChoosePhotoPage.GALLERY_CODE;

public class OpenCameraandGallery implements IntfPresentor.IOpenCameraandGallery {

    private Context context;

    public OpenCameraandGallery(Context context){
        this.context = context;
    }
    @Override
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ((Activity)context).startActivityForResult(intent, CAMERA_CODE);

    }
    @Override
    public void openGallery() {
        Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        ((Activity)context).startActivityForResult(galleryintent, GALLERY_CODE);
    }

}
