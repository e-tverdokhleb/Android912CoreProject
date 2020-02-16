package com.example.android912baseapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android912baseapp.R;
import com.example.android912baseapp.model.Movie;
import com.example.android912baseapp.utils.L;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> moviesList;
    private final LayoutInflater mInflater;

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mYear;
        private MovieAdapter mAdapter;
        private View.OnClickListener onCLick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mPosition = getLayoutPosition();
                Log.d(L.D0, String.valueOf(mPosition));
                mAdapter.notifyDataSetChanged();
            }
        };

        MovieViewHolder(View itemView, MovieAdapter adapter) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.m_title);
            mYear = itemView.findViewById(R.id.m_year);

            this.mAdapter = adapter;
            itemView.setOnClickListener(onCLick);
        }
    }

    public MovieAdapter(Context context, List<Movie> items) {
        mInflater = LayoutInflater.from(context);
        this.moviesList = items;
    }

    public void setItems(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_movie_recycler, parent, false);
        return new MovieViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.mTitle.setText(movie.getTitle());
        holder.mYear.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
