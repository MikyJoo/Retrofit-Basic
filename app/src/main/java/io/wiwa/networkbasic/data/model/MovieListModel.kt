package io.wiwa.networkbasic.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResponse (
    var status: String = "",
    var status_message: String = "",
    var data: MovieListModel = MovieListModel(),
): Parcelable

@Parcelize
data class MovieListModel (
    var movie_count: Int = 0,
    var limit: Int = 0,
    var page_number: Int = 0,
    var movies: ArrayList<MovieListItemModel> = ArrayList()
): Parcelable

@Parcelize
data class MovieListItemModel (
    var id: Int = 0,
    var url: String = "",
    var title: String = "",
    var title_english: String = "",
    var title_long: String = "",
    var year: Int = 0,
    var rating: Float = 0F,
    var runtime: Int = 0,
    var small_cover_image: String = "",
    var medium_cover_image: String = "",
): Parcelable