package com.example.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mMovieYear;
    private TextView mMovieRating;
    private TextView mMovieDuration;
    private TextView mMovieDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMoviePoster = (ImageView) findViewById(R.id.details_poster);
        mMovieTitle = (TextView) findViewById(R.id.movie_title_tv);
        mMovieYear = (TextView) findViewById(R.id.details_year_tv);
        mMovieRating = (TextView) findViewById(R.id.details_rating_tv);
        mMovieDuration = (TextView) findViewById(R.id.details_duration_tv);
        mMovieDescription = (TextView) findViewById(R.id.details_description_tv);



        //TODO: Receive the intent from the MainActivity
    }


}
