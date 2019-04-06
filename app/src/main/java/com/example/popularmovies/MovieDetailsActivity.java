package com.example.popularmovies;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

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


        Intent intent = getIntent();
        Movie selectedMovie = intent.getParcelableExtra("Movie"); // Receive the Movie object as Parcelable

        populateUi(selectedMovie);
    }

    private void populateUi(Movie movie){

        String notAvailable = "N/A";

        if(movie.getMovieTitle() != null && !(movie.getMovieTitle().equals(""))){
            mMovieTitle.setText(movie.getMovieTitle());
        }else{
            mMovieTitle.setText(notAvailable);
        }

        if(movie.getMovieRelease() != null && !(movie.getMovieRelease().equals(""))){
            // substring to get the 4 first characters of the string. The year
            mMovieYear.setText(movie.getMovieRelease().substring(0, 4));
        }else{
            mMovieTitle.setText(notAvailable);
        }

        if(movie.getMovieRate() != null && !(movie.getMovieRate().equals(""))){
            mMovieRating.setText(movie.getMovieRate());
        }else{
            mMovieTitle.setText(notAvailable);
        }

        if(movie.getMovieOverview() != null && !(movie.getMovieOverview().equals(""))){
            mMovieDescription.setText(movie.getMovieOverview());
        }else{
            mMovieTitle.setText(notAvailable);
        }

        Picasso.get()
                .load(movie.getMoviePoster())
                .placeholder(R.drawable.movie_poster_placeholder_image)
                .error(R.drawable.not_found_poster_image)
                .into(mMoviePoster);
    }

}
