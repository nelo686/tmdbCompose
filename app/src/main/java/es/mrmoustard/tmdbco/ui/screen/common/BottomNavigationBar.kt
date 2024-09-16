package es.mrmoustard.tmdbco.ui.screen.common

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.mrmoustard.tmdbco.ui.navigation.NavItem

@Composable
fun BottomNavigationBar(onNavItemClick: (String) -> Unit) {
    val navItems = listOf(
        NavItem.HOME,
        NavItem.FAVOURITE,
        NavItem.WATCHLIST
    )

    BottomAppBar {
        NavigationBar {
            navItems.forEach { item ->
                val title = stringResource(id = item.title)
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavItemClick(item.navCommand.route) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = title
                        )
                    },
                    label = { Text(text = title) }
                )
            }
        }
    }
}
