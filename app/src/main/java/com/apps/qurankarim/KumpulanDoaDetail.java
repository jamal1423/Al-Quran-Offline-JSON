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

public class KumpulanDoaDetail extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textHeader;

    private ProgressDialog progress;

    private List<KumpulanDoaDetailItem> doaDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumpulan_doa_detail);

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        mRecyclerView = findViewById(R.id.rViewDetailDoa);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(KumpulanDoaDetail.this);
        doaDetailList = new ArrayList<>();

        textHeader = (TextView)findViewById(R.id.headerDoa);

        get_json_doa_detail();
    }

    public void get_json_doa_detail() {
        String getData = getIntent().getStringExtra("getIdDoa");
        String json = null;
        try {
            InputStream is = getAssets().open("data_doa.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                String idDoa = obj.optString("id");
                String status = obj.optString("status");
                String title = obj.optString("title");

                if(idDoa.equals(getData))
                {
                    JSONArray jsnArray = obj.optJSONArray("data");
                    //Toast.makeText(getBaseContext(), "Data : "+jsnArray, Toast.LENGTH_LONG).show();
                    for(int j =0; j<jsnArray.length();j++)
                    {
                        JSONObject jsonObjects = jsnArray.getJSONObject(j);
                        String idDoaDetail = jsonObjects.optString("id");
                        String textArab = jsonObjects.optString("arabic");
                        String textIndo = jsonObjects.optString("indo");
                        String artiIndo = jsonObjects.optString("translate");

                        textHeader.setText(title);

                        doaDetailList.add(new KumpulanDoaDetailItem(idDoaDetail, textArab, textIndo, artiIndo));

                        mAdapter = new KumpulanDoaDetailAdapter(doaDetailList);

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