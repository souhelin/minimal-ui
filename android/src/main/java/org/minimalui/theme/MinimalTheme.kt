package org.minimalui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Color(0xFF111111),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF606060),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF111111),
    surface = Color(0xFFF7F7F7),
    onSurface = Color(0xFF111111),
    surfaceVariant = Color(0xFFEAEAEA),
    onSurfaceVariant = Color(0xFF606060),
    outline = Color(0xFFD8D8D8),
    outlineVariant = Color(0xFFD8D8D8),
    error = Color(0xFFA32626),
    onError = Color(0xFFFFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    secondary = Color(0xFFA0A0A0),
    onSecondary = Color(0xFF000000),
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF111111),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF181818),
    onSurfaceVariant = Color(0xFFA0A0A0),
    outline = Color(0xFF303030),
    outlineVariant = Color(0xFF303030),
    error = Color(0xFFFF9A9A),
    onError = Color(0xFF000000)
)

private fun blend(foreground: Color, background: Color, amount: Float): Color {
    val ratio = amount.coerceIn(0f, 1f)
    return Color(
        red = foreground.red * ratio + background.red * (1f - ratio),
        green = foreground.green * ratio + background.green * (1f - ratio),
        blue = foreground.blue * ratio + background.blue * (1f - ratio),
        alpha = 1f
    )
}

private fun bestOnColor(background: Color): Color {
    fun contrast(foreground: Color): Float {
        val lighter = maxOf(foreground.luminance(), background.luminance())
        val darker = minOf(foreground.luminance(), background.luminance())
        return (lighter + 0.05f) / (darker + 0.05f)
    }
    return if (contrast(Color.Black) >= contrast(Color.White)) Color.Black else Color.White
}

private fun seededColorScheme(seed: Color, dark: Boolean): ColorScheme {
    val surface = if (dark) Color(0xFF101116) else Color(0xFFFCFCFF)
    val background = if (dark) Color(0xFF090A0F) else Color(0xFFFFFFFF)
    val primary = if (dark) blend(seed, Color.White, 0.78f) else blend(seed, Color.Black, 0.88f)
    val onPrimary = bestOnColor(primary)
    val primaryContainer = if (dark) blend(seed, Color.Black, 0.34f) else blend(seed, Color.White, 0.14f)
    val onPrimaryContainer = bestOnColor(primaryContainer)
    val secondary = if (dark) blend(seed, Color.White, 0.68f) else blend(seed, Color.Black, 0.62f)
    val secondaryContainer = if (dark) blend(seed, surface, 0.30f) else blend(seed, surface, 0.13f)
    val onSecondary = bestOnColor(secondary)
    val onSecondaryContainer = bestOnColor(secondaryContainer)
    val tertiary = if (dark) blend(seed, Color(0xFFFFD8EA), 0.28f) else blend(seed, Color(0xFF6E3A56), 0.38f)
    val tertiaryContainer = if (dark) blend(tertiary, surface, 0.28f) else blend(tertiary, surface, 0.12f)
    val onTertiary = bestOnColor(tertiary)
    val onTertiaryContainer = bestOnColor(tertiaryContainer)

    return if (dark) {
        darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = blend(seed, Color.Black, 0.72f),
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = Color(0xFFF4F1F8),
            surface = surface,
            onSurface = Color(0xFFF4F1F8),
            surfaceVariant = blend(seed, surface, 0.12f),
            onSurfaceVariant = Color(0xFFC7C2CE),
            inverseSurface = Color(0xFFE8E2EC),
            inverseOnSurface = Color(0xFF24212A),
            error = Color(0xFFFFB4AB),
            onError = Color(0xFF690005),
            errorContainer = Color(0xFF93000A),
            onErrorContainer = Color(0xFFFFDAD6),
            outline = Color(0xFF938E9A),
            outlineVariant = Color(0xFF494550),
            surfaceTint = primary
        )
    } else {
        lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = blend(seed, Color.White, 0.58f),
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = Color(0xFF19171D),
            surface = blend(seed, surface, 0.025f),
            onSurface = Color(0xFF19171D),
            surfaceVariant = blend(seed, surface, 0.09f),
            onSurfaceVariant = Color(0xFF494550),
            inverseSurface = Color(0xFF302D34),
            inverseOnSurface = Color(0xFFF7F0FA),
            error = Color(0xFFBA1A1A),
            onError = Color.White,
            errorContainer = Color(0xFFFFDAD6),
            onErrorContainer = Color(0xFF410002),
            outline = Color(0xFF79757F),
            outlineVariant = Color(0xFFCAC5D0),
            surfaceTint = primary
        )
    }
}

/**
 * Minimal theme with system dark mode, Android 12+ wallpaper colors,
 * and developer-provided color customization.
 *
 * Precedence: [colorScheme] > [seedColor] > Android dynamic colors > Minimal neutral palette.
 */
@Suppress("NewApi")
@Composable
fun MinimalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    seedColor: Color? = null,
    colorScheme: ColorScheme? = null,
    typography: Typography = minimalTypography(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val resolvedColors = remember(darkTheme, dynamicColor, seedColor, colorScheme, context) {
        when {
            colorScheme != null -> colorScheme
            seedColor != null -> seededColorScheme(seedColor, darkTheme)
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            darkTheme -> DarkColors
            else -> LightColors
        }
    }

    MaterialTheme(colorScheme = resolvedColors, typography = typography, content = content)
}