package com.example.myfirstapp.presentor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class PhotoSaveandShare extends AsyncTask<Bitmap, Void , Void > implements IntfPresentor.IPhotoSaveandShare {
    private Context context;
    private Bitmap bitmap;

    public PhotoSaveandShare(Context context , Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
    }
    @Override
    protected Void doInBackground(Bitmap... bitmaps) {
        File filePath = Environment.getExternalStorageDirectory();
        File myDir = new File(filePath.getAbsolutePath() + "/Camera/");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver() , bitmap , fname , null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void savePhotoGallery() {

        Log.d("tag" , "savePhotoGallery");
        doInBackground(bitmap);
    }
    @Override
    public void sharePhoto() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "download this image");
        intent.putExtra(Intent.EXTRA_STREAM,  intent.getData());
        intent.setType("image/*");
        context.startActivity(Intent.createChooser(intent, "Share image via..."));
    }

}
