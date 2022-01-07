package io.wiwa.networkbasic.module.moviedetail

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import io.wiwa.networkbasic.data.model.MovieDetailModel
import io.wiwa.networkbasic.databinding.ActivityMovieDetailBinding
import io.wiwa.networkbasic.databinding.ActivityMovieListBinding
import io.wiwa.networkbasic.network.MovieApiWrapper

class MovieDetailActivity: AppCompatActivity() {
    private val TAG = this.javaClass.simpleName

    private lateinit var binding: ActivityMovieDetailBinding

    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra("movie_id", 0)

        binding.backBtn.setOnClickListener {
            finish()
        }

        requestMovieDetail()
    }

    private fun requestMovieDetail() {
        if (movieId > 0) {
            MovieApiWrapper.getMovieDetail(
                movieId,
                {
                    setMovieData(it.data.movie)
                }, {
                    // handle error
                }
            )
        }
    }

    private fun setMovieData(data: MovieDetailModel) {
        Glide.with(this)
            .load(data.large_cover_image)
            .transform(CenterInside())
            .into(binding.image)

        binding.titleText.text = data.title
        binding.yearText.text = "${data.year}"
        binding.descText.text = data.description_full
    }
}