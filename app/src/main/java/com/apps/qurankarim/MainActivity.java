package com.apps.qurankarim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressDialog progress;

    private RecyclerView rv;

    private List<SurahItem> surahList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        mRecyclerView = findViewById(R.id.rView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        surahList = new ArrayList<>();

        get_json_surah();
    }

    public void get_json_surah() {
        String json = null;
        try {
            InputStream is = getAssets().open("quran_id.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                String idSurah = obj.optString("id");
                String surahArab = obj.optString("name");
                String surahInd = obj.optString("transliteration");
                String groupSurat = obj.optString("type");
                String jumlahAyat = obj.optString("total_verses");

                //Toast.makeText(MainActivity.this, idSurah + " " + nameSurah, Toast.LENGTH_LONG).show();

                surahList.add(new SurahItem(idSurah, surahInd, groupSurat+" | "+jumlahAyat+" Ayat" , surahArab));

                mAdapter = new SurahAdapter(surahList);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                //progress.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                    }
                },1000);

            }

            /*perintah klik recyclerview*/
            mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                    public boolean onSingleTapUp(MotionEvent e){
                        return true;
                    }
                });

                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && gestureDetector.onTouchEvent(e)){
                        int position = rv.getChildAdapterPosition(child);
                        //Toast.makeText(getApplicationContext(), "Surat ke : " + surahList.get(position).getText1() + " ("+surahList.get(position).getText2()+")", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent (MainActivity.this, SurahDetail.class);
                        intent.putExtra("getIdSurah",surahList.get(position).getText1());
                        startActivity(intent);
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                }
                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}