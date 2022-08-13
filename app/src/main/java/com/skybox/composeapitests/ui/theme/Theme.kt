package com.skybox.composeapitests.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val testDarkColorScheme = darkColorScheme(
    primary = testDarkPrimary,
    onPrimary = testDarkOnPrimary,
    primaryContainer = testDarkPrimaryContainer,
    onPrimaryContainer = testDarkOnPrimaryContainer,
    inversePrimary = testDarkPrimaryInverse,
    secondary = testDarkSecondary,
    onSecondary = testDarkOnSecondary,
    secondaryContainer = testDarkSecondaryContainer,
    onSecondaryContainer = testDarkOnSecondaryContainer,
    tertiary = testDarkTertiary,
    onTertiary = testDarkOnTertiary,
    tertiaryContainer = testDarkTertiaryContainer,
    onTertiaryContainer = testDarkOnTertiaryContainer,
    error = testDarkError,
    onError = testDarkOnError,
    errorContainer = testDarkErrorContainer,
    onErrorContainer = testDarkOnErrorContainer,
    background = testDarkBackground,
    onBackground = testDarkOnBackground,
    surface = testDarkSurface,
    onSurface = testDarkOnSurface,
    inverseSurface = testDarkInverseSurface,
    inverseOnSurface = testDarkInverseOnSurface,
    surfaceVariant = testDarkSurfaceVariant,
    onSurfaceVariant = testDarkOnSurfaceVariant,
    outline = testDarkOutline
)

private val testLightColorScheme = lightColorScheme(
    primary = testLightPrimary,
    onPrimary = testLightOnPrimary,
    primaryContainer = testLightPrimaryContainer,
    onPrimaryContainer = testLightOnPrimaryContainer,
    inversePrimary = testLightPrimaryInverse,
    secondary = testLightSecondary,
    onSecondary = testLightOnSecondary,
    secondaryContainer = testLightSecondaryContainer,
    onSecondaryContainer = testLightOnSecondaryContainer,
    tertiary = testLightTertiary,
    onTertiary = testLightOnTertiary,
    tertiaryContainer = testLightTertiaryContainer,
    onTertiaryContainer = testLightOnTertiaryContainer,
    error = testLightError,
    onError = testLightOnError,
    errorContainer = testLightErrorContainer,
    onErrorContainer = testLightOnErrorContainer,
    background = testLightBackground,
    onBackground = testLightOnBackground,
    surface = testLightSurface,
    onSurface = testLightOnSurface,
    inverseSurface = testLightInverseSurface,
    inverseOnSurface = testLightInverseOnSurface,
    surfaceVariant = testLightSurfaceVariant,
    onSurfaceVariant = testLightOnSurfaceVariant,
    outline = testLightOutline
)

@Composable
fun ComposeApiTestsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val testColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> testDarkColorScheme
        else -> testLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = testColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = testColorScheme,
        typography = testTypography,
        shapes = shapes,
        content = content
    )
}
