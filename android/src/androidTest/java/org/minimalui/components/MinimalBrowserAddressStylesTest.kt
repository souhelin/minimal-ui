package org.minimalui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
    fun addressLayoutDisplaysConsumerContentAndSubmitsValue() {
        var submitted = ""
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                val value = remember { mutableStateOf("example.com") }
                MinimalBrowserAddressLayout(
                    value = value.value,
                    onValueChange = { value.value = it },
                    onSubmit = { submitted = it },
                    style = MinimalBrowserAddressStyle.SINGLE_TOP
                ) {
                    Text("Página de conteúdo")
                }
            }
        }

        composeRule.onNodeWithText("Página de conteúdo").assertIsDisplayed()
        composeRule.onNodeWithText("Ir").performClick()
        composeRule.runOnIdle { assertEquals("example.com", submitted) }
    }
}
