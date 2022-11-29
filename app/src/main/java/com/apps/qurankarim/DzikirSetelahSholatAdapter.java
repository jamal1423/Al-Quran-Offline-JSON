package com.apps.qurankarim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DzikirSetelahSholatAdapter extends RecyclerView.Adapter<DzikirSetelahSholatAdapter.ExampleViewHolder> {
    private List<DzikirSetelahSholatItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textNo);
            mTextView2 = itemView.findViewById(R.id.textArab);
            mTextView3 = itemView.findViewById(R.id.artiIndo);
            mTextView4 = itemView.findViewById(R.id.loopBaca);
        }
    }

    public DzikirSetelahSholatAdapter(List<DzikirSetelahSholatItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public DzikirSetelahSholatAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dzikir_setelah_sholat, parent, false);
        DzikirSetelahSholatAdapter.ExampleViewHolder evh = new DzikirSetelahSholatAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(DzikirSetelahSholatAdapter.ExampleViewHolder holder, int position) {
        DzikirSetelahSholatItem currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText11());
        holder.mTextView2.setText(currentItem.getText22());
        holder.mTextView3.setText(currentItem.getText33());
        holder.mTextView4.setText(currentItem.getText44());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
