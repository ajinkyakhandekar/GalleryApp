package com.storelab.codetest.presentation.home

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.storelab.codetest.R

/**
 * UI for bottom navigation bar which will display navigation Items declared in [Screens]
 */
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    navigateToDestination: (String) -> Unit
) {
    BottomAppBar(
        modifier = modifier
    ) {
        screens.forEach { screen ->

            val isSelected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true

            NavigationBarItem(
                modifier = Modifier.testTag(screen.tag),
                selected = isSelected,
                label = {
                    Text(text = stringResource(id = screen.title))
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(id = R.string.navigation_icon)
                    )
                },
                onClick = { navigateToDestination(screen.route) }
            )
        }
    }
}
