package com.apps.qurankarim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SurahDetailAdapter extends RecyclerView.Adapter<SurahDetailAdapter.ExampleViewHolder> {
    private List<SurahDetailItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        //public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textNo);
            mTextView2 = itemView.findViewById(R.id.textArab);
            //mTextView3 = itemView.findViewById(R.id.lafalAyat);
            mTextView3 = itemView.findViewById(R.id.artiIndo);
        }
    }

    public SurahDetailAdapter(List<SurahDetailItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public SurahDetailAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_surah_detail, parent, false);
        SurahDetailAdapter.ExampleViewHolder evh = new SurahDetailAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(SurahDetailAdapter.ExampleViewHolder holder, int position) {
        SurahDetailItem currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText11());
        holder.mTextView2.setText(currentItem.getText22());
        holder.mTextView3.setText(currentItem.getText33());
        //holder.mTextView4.setText(currentItem.getText44());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
