package com.apps.qurankarim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListDoaAdapter extends RecyclerView.Adapter<ListDoaAdapter.ExampleViewHolder> {

    private List<ListDoaItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textNo);
            mTextView2 = itemView.findViewById(R.id.judulDoa);
        }
    }

    public ListDoaAdapter(List<ListDoaItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ListDoaAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kumpulan_doa, parent, false);
        ListDoaAdapter.ExampleViewHolder evh = new ListDoaAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ListDoaAdapter.ExampleViewHolder holder, int position) {
        ListDoaItem currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
