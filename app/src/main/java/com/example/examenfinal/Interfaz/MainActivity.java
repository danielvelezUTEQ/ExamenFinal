package com.example.examenfinal.Interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.examenfinal.R;

public class MainActivity extends AppCompatActivity {
ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // img=(ImageView)findViewById(R.id.logo);

    }
public void onclick(View view){
    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
    startActivity(intent);
}

}