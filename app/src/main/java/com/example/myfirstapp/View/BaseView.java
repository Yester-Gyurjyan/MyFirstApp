package com.example.myfirstapp.View;

public interface BaseView {
    interface  StartPage{
        void startNextPage();
    }
    interface  EditPage{
        void saveImageGallery();
        void sendImage();
        void putBitmapOnImageView();
    }
    interface  ChoosePhotoPage{
        void startNextPage();
        void openCameraButton();
        void openGalleryButton();
    }
}
