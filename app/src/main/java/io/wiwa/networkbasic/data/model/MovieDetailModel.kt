package io.wiwa.networkbasic.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailResponse (
    var status: String = "",
    var status_message: String = "",
    var data: MovieDataModel = MovieDataModel(),
): Parcelable

@Parcelize
data class MovieDataModel (
    var movie: MovieDetailModel = MovieDetailModel()
):Parcelable


@Parcelize
data class MovieDetailModel (
    var id: Int = 0,
    var url: String = "",
    var title: String = "",
    var year: Int = 0,
    var rating: Float = 0F,
    var runtime: Int = 0,
    var large_cover_image: String = "",
    var description_full: String = "",
): Parcelable