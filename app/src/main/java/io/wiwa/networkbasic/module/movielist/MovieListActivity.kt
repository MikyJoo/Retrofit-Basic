package io.wiwa.networkbasic.module.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.wiwa.networkbasic.network.MovieApiWrapper
import io.wiwa.networkbasic.data.model.MovieListItemModel
import io.wiwa.networkbasic.databinding.ActivityMovieListBinding
import io.wiwa.networkbasic.module.moviedetail.MovieDetailActivity

class MovieListActivity: AppCompatActivity() {
    private val TAG = this.javaClass.simpleName

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var mainAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainList.layoutManager = LinearLayoutManager(this)

        requestMovieList()
    }

    private fun requestMovieList() {
        MovieApiWrapper.getMovieList({
            initList(it.data.movies)
        }, {
            // handle error
        })
    }

    private fun initList(list: ArrayList<MovieListItemModel>) {
        mainAdapter = MovieListAdapter(this, list) {
            presentMovieDetail(it.id)
        }

        binding.mainList.adapter = mainAdapter
    }

    private fun presentMovieDetail(id: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", id)
        startActivity(intent)
    }
}