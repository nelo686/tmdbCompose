package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import es.mrmoustard.domain.model.Movie
import es.mrmoustard.tmdbco.R

@Composable
fun TmdbListItem(
    item: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clickable { onClick() }
    ) {
        Card {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w342${item.backdropPath}",
                contentDescription =item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium),
                    vertical = dimensionResource(id = R.dimen.padding_big)
                )
        )
    }
}
