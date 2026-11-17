package org.minimalui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Renders any ImageVector with Minimal's inherited content color by default.
 * Apps can pass their own expressive icon pack without adding it to this library.
 */
@Composable
fun MinimalIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null
) {
    if (tint == null) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier
        )
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    }
}