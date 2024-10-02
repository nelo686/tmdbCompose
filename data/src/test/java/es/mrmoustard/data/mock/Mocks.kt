package es.mrmoustard.data.mock

import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.data.source.remote.dto.CollectionDto
import es.mrmoustard.data.source.remote.dto.GenreDto
import es.mrmoustard.data.source.remote.dto.MovieDetailDto
import es.mrmoustard.data.source.remote.dto.MovieDto
import es.mrmoustard.data.source.remote.dto.ProductionCompanyDto
import es.mrmoustard.data.source.remote.dto.ProductionCountryDto
import es.mrmoustard.data.source.remote.dto.SpokenLanguageDto
import es.mrmoustard.data.source.remote.dto.WrapperDto
import es.mrmoustard.domain.model.Collection
import es.mrmoustard.domain.model.Genre
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.ProductionCompany
import es.mrmoustard.domain.model.ProductionCountry
import es.mrmoustard.domain.model.SpokenLanguage
import es.mrmoustard.domain.model.TopRatedWrapper

internal val collectionDtoMock = CollectionDto(
    id = 1,
    name = "name",
    posterPath = "posterPath",
    backdropPath = "backdropPath"
)

internal val collectionMock = Collection(
    id = 1,
    name = "name",
    posterPath = "posterPath",
    backdropPath = "backdropPath"
)

internal val genreDtoMockList = buildList {
    add(GenreDto(id = 1, name = "Thriller"))
    add(GenreDto(id = 2, name = "Action"))
    add(GenreDto(id = 3, name = "Comedy"))
}

internal val genreMockList = buildList {
    add(Genre(id = 1, name = "Thriller"))
    add(Genre(id = 2, name = "Action"))
    add(Genre(id = 3, name = "Comedy"))
}

internal val movieDtoMock = MovieDto(
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

internal val productionCompanyDtoMockList = buildList {
    add(ProductionCompanyDto(id = 1, name = "name1", logoPath = "logoPath1", originCountry = "originCountry1"))
    add(ProductionCompanyDto(id = 2, name = "name2", logoPath = "logoPath2", originCountry = "originCountry2"))
    add(ProductionCompanyDto(id = 3, name = "name3", logoPath = null, originCountry = "originCountry3"))
}

internal val productionCompanyMockList = buildList {
    add(ProductionCompany(id = 1, name = "name1", logoPath = "logoPath1", originCountry = "originCountry1"))
    add(ProductionCompany(id = 2, name = "name2", logoPath = "logoPath2", originCountry = "originCountry2"))
    add(ProductionCompany(id = 3, name = "name3", logoPath = "", originCountry = "originCountry3"))
}

internal val productionCountryDtoMockList = buildList {
    add(ProductionCountryDto(iso31661 = "1", name = "name1"))
    add(ProductionCountryDto(iso31661 = "2", name = "name2",))
    add(ProductionCountryDto(iso31661 = "3", name = "name3"))
}

internal val productionCountryMockList = buildList {
    add(ProductionCountry(iso31661 = "1", name = "name1"))
    add(ProductionCountry(iso31661 = "2", name = "name2"))
    add(ProductionCountry(iso31661 = "3", name = "name3"))
}

internal val spokenLanguageDtoMockList = buildList {
    add(SpokenLanguageDto(iso6391 = "1", name = "name1"))
    add(SpokenLanguageDto(iso6391 = "2", name = "name2",))
    add(SpokenLanguageDto(iso6391 = "3", name = "name3"))
}

internal val spokenLanguageMockList = buildList {
    add(SpokenLanguage(iso6391 = "1", name = "name1"))
    add(SpokenLanguage(iso6391 = "2", name = "name2"))
    add(SpokenLanguage(iso6391 = "3", name = "name3"))
}

internal val movieDetailDtoMock = MovieDetailDto(
    adult = false,
    backdropPath = "backdropPath",
    belongsToCollection = collectionDtoMock,
    budget = 3000,
    genres = genreDtoMockList,
    homepage = "homepage",
    id = 1,
    imdbId = "imdbId",
    originalLanguage = "originalLanguage",
    originalTitle = "originalTitle",
    overview = "overview",
    popularity = 6.0,
    posterPath = "posterPath",
    productionCompanies = productionCompanyDtoMockList,
    productionCountries = productionCountryDtoMockList,
    releaseDate = "releaseDate",
    revenue = 2,
    runtime = 3,
    spokenLanguages = spokenLanguageDtoMockList,
    status = "status",
    tagline = "tagline",
    title = "title",
    video = false,
    voteAverage = 8.2,
    voteCount = 2400
)

internal val movieDetailMock = MovieDetail(
    adult = false,
    backdropPath = "backdropPath",
    belongsToCollection = collectionMock,
    budget = 3000,
    genres = genreMockList,
    homepage = "homepage",
    id = 1,
    imdbId = "imdbId",
    originalLanguage = "originalLanguage",
    originalTitle = "originalTitle",
    overview = "overview",
    popularity = 6.0,
    posterPath = "posterPath",
    productionCompanies = productionCompanyMockList,
    productionCountries = productionCountryMockList,
    releaseDate = "releaseDate",
    revenue = 2,
    runtime = 3,
    spokenLanguages = spokenLanguageMockList,
    status = "status",
    tagline = "tagline",
    title = "title",
    video = false,
    voteAverage = 8.2,
    voteCount = 2400
)

internal val wrapperDtoMock = WrapperDto(
    page = 1,
    results = listOf(movieDtoMock),
    totalPages = 1,
    totalResults = 1
)

internal val topRatedWrapperMock = TopRatedWrapper(
    page = 1,
    results = listOf(movieMock),
    totalPages = 1,
    totalResults = 1
)

internal val movieStatusMock = MovieStatus(
    id = 1,
    title = "title",
    backdropPath = "backdropPath"
)
