package es.mrmoustard.tmdbco.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.tmdbco.R
import es.mrmoustard.tmdbco.ui.screen.common.ErrorMessage
import es.mrmoustard.tmdbco.ui.screen.common.ProgressIndicator
import es.mrmoustard.tmdbco.ui.theme.CustomDarkGray
import es.mrmoustard.tmdbco.ui.theme.customGold
import java.util.Locale

@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel<MovieDetailViewModel>()) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    when {
        state.value.loading -> ProgressIndicator()
        else -> DetailScreen(
            movie = state.value.movie,
            onStatusChange = { viewModel.onStatusChange(it) }
        )
    }
}

@Composable
fun DetailScreen(
    movie: Result<MovieDetail?>,
    onStatusChange: (MovieStatus) -> Unit
) {
    movie.fold(
        { ErrorMessage(error = it) },
        { item ->
            item?.let {
                DetailScreen(
                    movie = it,
                    onStatusChange = onStatusChange
                )
            }
        }
    )
}

@Composable
fun DetailScreen(
    movie: MovieDetail,
    onStatusChange: (MovieStatus) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item { Header(movie, onStatusChange) }
        item { Title(movie.title) }

        if (movie.tagline.isNotEmpty()) {
            item { TagLine(movie.tagline) }
        }

        item { Highlights(movie = movie) }
        item {
            Section(
                title = stringResource(id = R.string.details_overview_header),
                content = movie.overview
            )
        }
    }
}

@Composable
private fun Header(
    movie: MovieDetail,
    onStatusChange: (MovieStatus) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val status = MovieStatus(
            id = movie.id,
            title = movie.title,
            backdropPath = movie.backdropPath,
            favourite = movie.favourite,
            wannaWatchIt = movie.wannaWatchIt
        )
        Card {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342${movie.backdropPath}",
                contentDescription =movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Row {
            IconButton(
                onClick = { onStatusChange(status.copy(favourite = movie.favourite.not())) }) {
                StatusIcon(
                    statusValue = movie.favourite,
                    selectedIcon = Icons.Filled.Star,
                    unselectedIcon = Icons.Outlined.StarOutline
                )
            }
            IconButton(
                onClick = { onStatusChange(status.copy(wannaWatchIt = movie.wannaWatchIt.not())) }
            ) {
                StatusIcon(
                    statusValue = movie.wannaWatchIt,
                    selectedIcon = Icons.Filled.Movie,
                    unselectedIcon = Icons.Outlined.Movie
                )
            }
        }
    }
}

@Composable
fun StatusIcon(
    statusValue: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector
) {
    Icon(
        imageVector = if (statusValue) {
            selectedIcon
        } else {
            unselectedIcon
        },
        contentDescription = null,
        tint = if (statusValue) {
            customGold
        } else {
            Color.DarkGray
        }
    )
}

@Composable
private fun Title(title: String) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.huge_spacer)))
    Text(
        text = title,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.padding_medium),
                dimensionResource(id = R.dimen.no_padding)
            )
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.big_spacer)))
}

@Composable
private fun TagLine(tagline: String) {
    Text(
        text = "\"$tagline\"",
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic,
        color = Color.DarkGray.copy(alpha = 0.5f),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.padding_medium),
                dimensionResource(id = R.dimen.no_padding)
            )
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.big_spacer)))
}

@Composable
private fun Highlights(movie: MovieDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomDarkGray)
            .padding(
                dimensionResource(id = R.dimen.padding_medium),
                dimensionResource(id = R.dimen.padding_big)
            )
    ) {
        MovieScore(movie.voteAverage, movie.voteCount)

        Bullet(stringResource(id = R.string.released), movie.releaseDate)
        Bullet(
            stringResource(id = R.string.genres),
            movie.genres.map { it.name }.joinToString(", ")
        )
        Bullet(stringResource(id = R.string.original_title), movie.originalTitle)
        Bullet(stringResource(id = R.string.original_language), movie.originalLanguage)
        Bullet(
            stringResource(id = R.string.spoken_languages),
            movie.spokenLanguages.map { it.name }.joinToString(", ")
        )
    }
}

@Composable
private fun MovieScore(voteAverage: Double, voteCount: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = String.format(Locale.getDefault(), "%.2f/10", voteAverage),
            color = Color.White,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "$voteCount votes",
            color = Color.White,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Bullet(header: String, value: String) {
    Text(
        text = header.plus(value),
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun Section(title: String, content: String) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.big_spacer)))
        Text(
            text = title,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.padding_medium),
                    dimensionResource(id = R.dimen.no_padding)
                )
        )
        HorizontalDivider(thickness = dimensionResource(id = R.dimen.header_line_thickness))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer)))
        Text(
            text = content,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.padding_medium),
                    dimensionResource(id = R.dimen.no_padding)
                )
        )
}
