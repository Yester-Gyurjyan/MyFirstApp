package com.example.myfirstapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myfirstapp.R;

public class StartPage extends AppCompatActivity implements BaseView.StartPage {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initData();
        startNextPage();
    }
    private void initData(){
        start = findViewById(R.id.start);
    }
    @Override
    public void startNextPage() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , ChoosePhotoPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
