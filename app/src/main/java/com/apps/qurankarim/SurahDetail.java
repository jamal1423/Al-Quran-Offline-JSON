package com.apps.qurankarim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SurahDetail extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textHeader;

    private ProgressDialog progress;

    private List<SurahDetailItem> ayatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail);

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        mRecyclerView = findViewById(R.id.rViewSurah);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(SurahDetail.this);
        ayatList = new ArrayList<>();

        textHeader = (TextView)findViewById(R.id.headerSurah);

        get_json_surah_detail();
    }

    public void get_json_surah_detail() {
        String getData = getIntent().getStringExtra("getIdSurah");
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

                if(idSurah.equals(getData))
                {
                    JSONArray jsnArray = obj.optJSONArray("verses");
                    //Toast.makeText(getBaseContext(), "Data : "+jsnArray, Toast.LENGTH_LONG).show();
                    for(int j =0; j<jsnArray.length();j++)
                    {
                        JSONObject jsonObjects = jsnArray.getJSONObject(j);
                        String idAyat = jsonObjects.optString("id");
                        String textArab = jsonObjects.optString("text");
                        String artiIndo = jsonObjects.optString("translation");

                        textHeader.setText(surahInd+" | "+groupSurat+" | "+jumlahAyat+" Ayat");

                        ayatList.add(new SurahDetailItem(idAyat, textArab, artiIndo));

                        mAdapter = new SurahDetailAdapter(ayatList);

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
                }
                //Toast.makeText(MainActivity.this, idSurah + " " + nameSurah, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}