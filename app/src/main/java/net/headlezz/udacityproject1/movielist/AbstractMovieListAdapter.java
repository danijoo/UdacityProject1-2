package net.headlezz.udacityproject1.movielist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.headlezz.udacityproject1.MovieNavigation;
import net.headlezz.udacityproject1.R;
import net.headlezz.udacityproject1.tmdbapi.Movie;
import net.headlezz.udacityproject1.tmdbapi.TMDBApi;

/**
 * Abstract class for shwoing a list of movie posters
 */
public abstract class AbstractMovieListAdapter extends RecyclerView.Adapter<AbstractMovieListAdapter.MovieViewHolder> {

    MovieNavigation mMovieNavigation;

    public AbstractMovieListAdapter(MovieNavigation mn) {
        mMovieNavigation = mn;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.setMovie(getMovieForPosition(position));
    }

    /**
     * @param position of the list
     * @return the movie displayed for the given position
     */
    protected abstract Movie getMovieForPosition(int position);

    /**
     * Viewholder for the movie items
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView;
            ivPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMovieNavigation.showDetailsFragment(movie);
                }
            });
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
            TMDBApi.loadPoster(ivPoster, movie);
        }
    }
}
