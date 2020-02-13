package com.example.android912baseapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android912baseapp.R;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        public final TextView wordItemView2;
        final WordListAdapter mAdapter;
        private View.OnClickListener onCLick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mPosition = getLayoutPosition();

                String element = mWordList.get(mPosition);

                mWordList.set(mPosition, "Clicked! " + element);
                mAdapter.notifyDataSetChanged();
            }
        };

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            wordItemView2 = itemView.findViewById(R.id.word2);

            this.mAdapter = adapter;
            itemView.setOnClickListener(onCLick);
        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }


    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder,
                                 int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
        holder.wordItemView2.setText(mCurrent.toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
