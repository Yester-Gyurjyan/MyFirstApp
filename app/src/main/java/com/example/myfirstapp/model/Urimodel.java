package com.example.myfirstapp.model;

import android.net.Uri;

public class Urimodel {
    private Uri uri;
    public Urimodel(){};
    public Urimodel(Uri uri){
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
