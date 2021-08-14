package com.kiyo.KyoMovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kiyo.KyoMovie.model.Result;
import com.bumptech.glide.Glide;
import com.kiyo.KyoMovie.R;

public class DetailFilmActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE ="extra_movie";
    String title, overview, image, release_date ;
    Float rating;
    ImageView imgDetail;
    TextView tvTitle, tvDetail,tvReleaseDate, tvRating;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        tvTitle = findViewById(R.id.tvJudulDetail);
        tvReleaseDate = findViewById(R.id.tvTglRilisDetail);
        tvRating = findViewById(R.id.tvRatingDetail);
        tvDetail = findViewById(R.id.tvStoryDesc);
        imgDetail = findViewById(R.id.imgDetail);

        result = getIntent().getParcelableExtra(EXTRA_MOVIE);

        title = result.getOriginalTitle();
        overview = result.getOverview();
        release_date = result.getReleaseDate();
        rating = result.getVoteAverage();
        image = result.getPosterPath();

        tvTitle.setText(title);
        tvReleaseDate.setText(release_date);
        tvRating.setText(String.valueOf(rating));
        tvDetail.setText(overview);

        tvDetail.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500" + image)
                .into(imgDetail);

    }
}