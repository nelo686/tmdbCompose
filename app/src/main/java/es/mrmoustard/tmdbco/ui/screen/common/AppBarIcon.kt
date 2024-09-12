package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBarIcon(
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}
