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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class KumpulanDoa extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressDialog progress;

    private RecyclerView rv;

    private List<ListDoaItem> doaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumpulan_doa);

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        mRecyclerView = findViewById(R.id.rViewKumpulanDoa);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(KumpulanDoa.this);
        doaList = new ArrayList<>();

        get_json_list_doa();
    }

    public void get_json_list_doa() {
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
                String pesan = obj.optString("message");
                String status = obj.optString("status");
                String titleDoa = obj.optString("title");

                //Toast.makeText(MainActivity.this, idSurah + " " + nameSurah, Toast.LENGTH_LONG).show();

                doaList.add(new ListDoaItem(idDoa, titleDoa));

                mAdapter = new ListDoaAdapter(doaList);

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
                        //Toast.makeText(getApplicationContext(), "Doa ke : " + doaList.get(position).getText1() + " ("+doaList.get(position).getText2()+")", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent (KumpulanDoa.this, KumpulanDoaDetail.class);
                        intent.putExtra("getIdDoa",doaList.get(position).getText1());
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