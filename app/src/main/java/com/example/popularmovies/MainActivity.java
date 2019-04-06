package com.example.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.popularmovies.models.Movie;
import com.example.popularmovies.utilities.MovieDetailsJsonUtils;
import com.example.popularmovies.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterListItemClickListener {

    public static final String POPULAR_QUERY = "popular";
    public static final String TOP_RATED_QUERY = "top_rated";

    private RecyclerView mRecyclerView;
    private  MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private Movie[] mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressBar);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager LayoutManager = new GridLayoutManager(this, 2); //TODO: Fix the number

        mRecyclerView.setLayoutManager(LayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mMoviesAdapter);

        loadMovieData(POPULAR_QUERY);
    }

    private void loadMovieData(String query){
        showMoviesList();
        new FetchMovieTask().execute(query);
    }

    @Override
    public void onListItemClick(int item) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("Movie", mMovies[item]); // Send the movie Object as Parcelable
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_screen_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sort_most_popular){
            loadMovieData(POPULAR_QUERY);
        }else{
            loadMovieData(TOP_RATED_QUERY);
        }

        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            String searchQuery = strings[0];
            URL movieRequestURL = NetworkUtils.buildUrl(searchQuery);

            try {
                String jsonMovieResponse = NetworkUtils.
                        getResponseFromHttpUrl(movieRequestURL);

                mMovies = MovieDetailsJsonUtils.
                        getSimpleWeatherStringsFromJson(MainActivity.this, jsonMovieResponse);

                return mMovies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                showMoviesList();
                mMoviesAdapter = new MoviesAdapter(movies, MainActivity.this);
                mRecyclerView.setAdapter(mMoviesAdapter);
            } else {
                showErrorMessage();
            }
        }
    }

    private void showMoviesList() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
}
