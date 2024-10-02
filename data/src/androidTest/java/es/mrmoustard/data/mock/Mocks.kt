package es.mrmoustard.data.mock

import es.mrmoustard.data.source.local.database.dto.MovieStatus

internal val movieStatusMock = MovieStatus(
    id = 1,
    title = "title",
    backdropPath = "backdropPath",
    favourite = true,
    wannaWatchIt = true
)