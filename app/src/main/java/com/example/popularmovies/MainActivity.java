package com.example.popularmovies;

import android.content.Context;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterListItemClickListener {

    private RecyclerView mRecyclerView;
    private  MoviesAdapter mMoviesAdapter;
    private GridLayoutManager mLayoutManager;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.progressBar);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        //TODO: Fix the below part
//        mLayoutManager = new GridLayoutManager(this);
//        mMoviesAdapter = new MoviesAdapter(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMoviesAdapter);

        loadMovieData();
    }

    private void loadMovieData(){
        showMoviesList();
        new FetchMovieTask().execute(); //TODO: Fill execute with something
    }

    @Override
    public void onListItemClick(int item) {
        //TODO: Change the Toast to Intent call for the Details Screen
        Context context = this;
        Toast.makeText(context, "Item no: " + item + " has been clicked", Toast.LENGTH_SHORT)
                .show();
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
            //TODO: Sort most popular
        }else{
            //TODO: Sort highest rated
        }

        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... strings) { //TODO: Implement this
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] movies) {
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
