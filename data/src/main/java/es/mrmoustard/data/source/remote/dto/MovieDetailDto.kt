package es.mrmoustard.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.domain.model.Collection
import es.mrmoustard.domain.model.MovieDetail

data class MovieDetailDto(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("belongs_to_collection") val belongsToCollection: CollectionDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDto>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)

fun MovieDetailDto.setStatus(status: MovieStatus): MovieDetailDto =
    copy(favourite = status.favourite, wannaWatchIt = status.wannaWatchIt)

fun MovieDetailDto.mapToDomain(): MovieDetail =
    MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection?.mapToDomain() ?: Collection(),
        budget = budget,
        genres = genres.mapToDomain(),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath ?: "",
        productionCompanies = productionCompanies.mapToDomain(),
        productionCountries = productionCountries.mapToDomain(),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.mapToDomain(),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        favourite = favourite,
        wannaWatchIt = wannaWatchIt
    )
