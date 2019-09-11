package com.example.myfirstapp.presentor;

import android.graphics.Bitmap;

public interface IntfPresentor {
     interface IGetPhotoFromIntent_uri {
        Bitmap getPhotoFromIntent();
    }
    interface IOpenCameraandGallery {
        void openCamera();
        void openGallery();
    }
    interface IPhotoSaveandShare {
        void savePhotoGallery();
        void sharePhoto();
    }

}
