package com.kiyo.KyoMovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

        tvTitle = findViewById(R.id.tvTitleDetail);
        tvReleaseDate = findViewById(R.id.tvDateReleaseDetail);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.desc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                String text = tvTitle.getText().toString().trim();
                if (text.equals("")){
                    Toast.makeText(DetailFilmActivity.this, "Nothing to Share", Toast.LENGTH_SHORT).show();
                }else {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, tvTitle.getText().toString().trim());
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
        }
        return super.onOptionsItemSelected(item);

    }
}