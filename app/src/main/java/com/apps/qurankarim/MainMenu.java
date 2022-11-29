package com.apps.qurankarim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnBacaQuran = (Button) findViewById(R.id.bacaQuran);
        btnBacaQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button btnBacaDoaSetelahSholat = (Button) findViewById(R.id.doaSetelahSholat);
        btnBacaDoaSetelahSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, DoaSetelahSholat.class);
                startActivity(myIntent);
            }
        });

        Button btnKumpulanDoa = (Button) findViewById(R.id.doaSetelahSholat);
        btnKumpulanDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, DoaSetelahSholat.class);
                startActivity(myIntent);
            }
        });

        Button btnDzikir = (Button) findViewById(R.id.dzikirSetelahSholat);
        btnDzikir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, DzikirSetelahSholat.class);
                startActivity(myIntent);
            }
        });

        Button btnListDoa = (Button) findViewById(R.id.kumpulanDoa);
        btnListDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenu.this, KumpulanDoa.class);
                startActivity(myIntent);
            }
        });
    }
}