package es.mrmoustard.tmdbco.mock

import es.mrmoustard.domain.model.Movie
import es.mrmoustard.domain.model.TopRatedWrapper

internal val movieMock = Movie(
    adult = false,
    backdropPath = "backdropPath",
    genreIds = listOf(1, 2, 3),
    id = 1,
    originalLanguage = "originalLanguage",
    originalTitle = "originalTitle",
    overview = "overview",
    popularity = 6.0,
    posterPath = "posterPath",
    releaseDate = "releaseDate",
    title = "title",
    video = false,
    voteAverage = 8.2,
    voteCount = 2400
)

internal val topRatedWrapperMock = TopRatedWrapper(
    page = 1,
    results = listOf(movieMock),
    totalPages = 1,
    totalResults = 1
)
