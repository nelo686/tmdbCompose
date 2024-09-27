package es.mrmoustard.data.source.remote

import es.mrmoustard.data.source.remote.dto.MovieDetailDto
import es.mrmoustard.data.source.remote.dto.WrapperDto
import es.mrmoustard.data.source.remote.api.TmdbApi
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: TmdbApi
): MoviesRemoteDataSource {
    override suspend fun getTopRated(page: Int, region: String): WrapperDto =
       api.getTopRated(page = page, region = region)

    override suspend fun getMovieDetails(movieId: Int): MovieDetailDto =
        api.getMovieDetails(movieId = movieId)
}