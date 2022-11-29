package com.apps.qurankarim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DzikirSetelahSholat extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textHeader;
    private ProgressDialog progress;

    private RecyclerView rv;

    private List<DzikirSetelahSholatItem> DzikirSetelahSholatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_setelah_sholat);

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        mRecyclerView = findViewById(R.id.rViewDzikir);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(DzikirSetelahSholat.this);
        DzikirSetelahSholatList = new ArrayList<>();

        textHeader = (TextView)findViewById(R.id.headerDzikir);

        get_json_dzikir_setelah_sholat();
    }

    public void get_json_dzikir_setelah_sholat() {
        String json = null;
        try {
            InputStream is = getAssets().open("dzikir.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                String status = obj.optString("status");

                if(status.equals("1")) {
                    JSONArray jsnArray = obj.optJSONArray("data");

                    for(int j =0; j<jsnArray.length();j++)
                    {
                        JSONObject jsonObjects = jsnArray.getJSONObject(j);
                        String idDoa = jsonObjects.optString("id");
                        String textArab = jsonObjects.optString("arabic");
                        String artiIndo = jsonObjects.optString("translate");
                        String loopBaca = jsonObjects.optString("ulang");

                        textHeader.setText("Dzikir Setelah Sholat");

                        DzikirSetelahSholatList.add(new DzikirSetelahSholatItem(idDoa, textArab, artiIndo, "Dibaca : "+loopBaca));

                        mAdapter = new DzikirSetelahSholatAdapter(DzikirSetelahSholatList);

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}