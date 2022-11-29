package com.apps.qurankarim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoaSetelahSholatAdapter extends RecyclerView.Adapter<DoaSetelahSholatAdapter.ExampleViewHolder> {
    private List<DoaSetelahSholatItem> mExampleList;

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

    public DoaSetelahSholatAdapter(List<DoaSetelahSholatItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public DoaSetelahSholatAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_doa_setelah_sholat, parent, false);
        DoaSetelahSholatAdapter.ExampleViewHolder evh = new DoaSetelahSholatAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(DoaSetelahSholatAdapter.ExampleViewHolder holder, int position) {
        DoaSetelahSholatItem currentItem = mExampleList.get(position);
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
