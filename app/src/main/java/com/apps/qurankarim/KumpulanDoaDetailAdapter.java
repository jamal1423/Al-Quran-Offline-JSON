package com.apps.qurankarim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KumpulanDoaDetailAdapter extends RecyclerView.Adapter<KumpulanDoaDetailAdapter.ExampleViewHolder> {
    private List<KumpulanDoaDetailItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textNo);
            mTextView2 = itemView.findViewById(R.id.textArab);
            mTextView3 = itemView.findViewById(R.id.lafalIndo);
            mTextView4 = itemView.findViewById(R.id.artiIndo);
        }
    }

    public KumpulanDoaDetailAdapter(List<KumpulanDoaDetailItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public KumpulanDoaDetailAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kumpulan_doa_detail, parent, false);
        KumpulanDoaDetailAdapter.ExampleViewHolder evh = new KumpulanDoaDetailAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(KumpulanDoaDetailAdapter.ExampleViewHolder holder, int position) {
        KumpulanDoaDetailItem currentItem = mExampleList.get(position);
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
