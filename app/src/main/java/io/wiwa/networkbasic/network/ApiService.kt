package io.wiwa.networkbasic.network

import io.wiwa.networkbasic.common.AppLog
import io.wiwa.networkbasic.data.model.MovieDetailResponse
import io.wiwa.networkbasic.data.model.MovieListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object ApiService {
    private val TAG = this.javaClass.simpleName

    private val BASE_URL = "https://yts.mx/api/v2/"

    const val HEAD_AUTH = "Authorization"
    const val HEAD_BEARER = "Bearer "

    private val retrofitService: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(AppHttpClient.getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApiInterface by lazy {
        retrofitService.create(MovieApiInterface::class.java)
    }
}

class AppHttpClient {
    private val TAG = this.javaClass.simpleName

    companion object {
        private val HTTP_CONNECT_TIMEOUT: Long = 15
        private val HTTP_WRITE_TIMEOUT: Long = 60
        private val HTTP_READ_TIMEOUT: Long = 60

        fun getHttpClient(): OkHttpClient {
            var httpClientBuilder = OkHttpClient().newBuilder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)

            if (AppLog.isDebug) {
                var loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClientBuilder.addInterceptor(loggingInterceptor)
            }

            return httpClientBuilder.build()
        }
    }
}

@JvmSuppressWildcards
interface MovieApiInterface {
    @GET("list_movies.json")
    fun getMovieList(): Call<MovieListResponse>

    @GET("movie_details.json")
    fun getMovieDetail(@Query("movie_id") id: Int): Call<MovieDetailResponse>
}