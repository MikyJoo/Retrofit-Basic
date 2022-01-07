package io.wiwa.networkbasic.network

import io.wiwa.networkbasic.data.model.MovieDetailResponse
import io.wiwa.networkbasic.data.model.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieApiWrapper {
    companion object {
        private val TAG = this.javaClass.simpleName

        fun getMovieList(onSuccess: (MovieListResponse) -> Unit, onFail: (Throwable) -> Unit) {
            ApiService.movieApi.getMovieList().enqueue(object: Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    response.body()?.let {
                        onSuccess.invoke(it)
                    }
                }
                override fun onFailure(
                    call: Call<MovieListResponse>,
                    t: Throwable
                ) {
                    onFail.invoke(t)
                }
            })
        }

        fun getMovieDetail(id: Int, onSuccess: (MovieDetailResponse) -> Unit, onFail: (Throwable) -> Unit) {
            ApiService.movieApi.getMovieDetail(id).enqueue(object: Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    response.body()?.let {
                        onSuccess.invoke(it)
                    }
                }
                override fun onFailure(
                    call: Call<MovieDetailResponse>,
                    t: Throwable
                ) {
                    onFail.invoke(t)
                }
            })
        }
    }
}