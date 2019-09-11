package com.example.myfirstapp.presentor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.myfirstapp.model.Urimodel;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class GetPhotoFromIntent_uri implements IntfPresentor.IGetPhotoFromIntent_uri {
    private Context context;
    private Intent intent;
    private Bitmap bitmap;
    private Urimodel urimodel;
   public GetPhotoFromIntent_uri(Context context , Intent intent , Bitmap bitmap){
        this.context = context;
        this.intent = intent;
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap getPhotoFromIntent() {
        String uriString = intent.getStringExtra("picture");
        urimodel = new Urimodel( Uri.parse(uriString));
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(urimodel.getUri());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeStream(imageStream);
        return bitmap;
    }
}
