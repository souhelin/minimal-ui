package org.minimalui.theme

import org.junit.Assert.assertEquals
import org.junit.Test

class MinimalTypographyTest {
    @Test
    fun exposesExpectedMwsBodyAndHeadingScale() {
        assertEquals(40f, MinimalTypography.displayLarge.fontSize.value, 0f)
        assertEquals(32f, MinimalTypography.headlineLarge.fontSize.value, 0f)
        assertEquals(24f, MinimalTypography.titleLarge.fontSize.value, 0f)
        assertEquals(18f, MinimalTypography.bodyLarge.fontSize.value, 0f)
        assertEquals(16f, MinimalTypography.bodyMedium.fontSize.value, 0f)
        assertEquals(14f, MinimalTypography.bodySmall.fontSize.value, 0f)
        assertEquals(12f, MinimalTypography.labelMedium.fontSize.value, 0f)
    }

    @Test
    fun bodyStylesUseReadableLineHeights() {
        assertEquals(27f, MinimalTypography.bodyLarge.lineHeight.value, 0f)
        assertEquals(24f, MinimalTypography.bodyMedium.lineHeight.value, 0f)
        assertEquals(21f, MinimalTypography.bodySmall.lineHeight.value, 0f)
    }
}
