package org.minimalui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.minimalui.theme.MinimalTheme

class MinimalBrowserAddressStylesTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun exposesFifteenAddressLayouts() {
        assertEquals(15, MinimalBrowserAddressStyle.entries.size)
    }

    @Test
    fun everyAddressStyleRendersContentAndSubmitAction() {
        val selectedStyle = mutableStateOf(MinimalBrowserAddressStyle.SINGLE_TOP)
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                val value = remember { mutableStateOf("example.com") }
                MinimalBrowserAddressLayout(
                    value = value.value,
                    onValueChange = { value.value = it },
                    onSubmit = {},
                    style = selectedStyle.value
                ) {
                    Text("Página de conteúdo")
                }
            }
        }

        MinimalBrowserAddressStyle.entries.forEach { style ->
            composeRule.runOnIdle { selectedStyle.value = style }
            composeRule.onNodeWithText("Página de conteúdo").assertIsDisplayed()
            composeRule.onNodeWithText("Ir").assertIsDisplayed()
        }
    }

    @Test
    fun contextualStyleUsesDedicatedShareCallback() {
        var submitted = ""
        var shareCount = 0
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                val value = remember { mutableStateOf("example.com") }
                MinimalBrowserAddressLayout(
                    value = value.value,
                    onValueChange = { value.value = it },
                    onSubmit = { submitted = it },
                    onShare = { shareCount++ },
                    style = MinimalBrowserAddressStyle.CONTEXTUAL
                ) {
                    Text("Página de conteúdo")
                }
            }
        }

        composeRule.onNodeWithContentDescription("Compartilhar").performClick()
        composeRule.runOnIdle {
            assertEquals(1, shareCount)
            assertEquals("", submitted)
        }
    }

    @Test
    fun integratedTabsUseSeparateOpenAndNewTabCallbacks() {
        var openTabsCount = 0
        var newTabCount = 0
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                val value = remember { mutableStateOf("example.com") }
                MinimalBrowserAddressLayout(
                    value = value.value,
                    onValueChange = { value.value = it },
                    onSubmit = {},
                    onTabs = { openTabsCount++ },
                    onNewTab = { newTabCount++ },
                    style = MinimalBrowserAddressStyle.TABS_INTEGRATED
                ) {
                    Text("Página de conteúdo")
                }
            }
        }

        composeRule.onNodeWithText("Aba atual").performClick()
        composeRule.onNodeWithText("+ Nova aba").performClick()
        composeRule.runOnIdle {
            assertEquals(1, openTabsCount)
            assertEquals(1, newTabCount)
        }
    }
}
