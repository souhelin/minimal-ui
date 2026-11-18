package org.minimalui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.minimalui.theme.MinimalTheme

class MinimalComponentsTest {
    @get:Rule
    val composeRule = createComposeRule()


    @Test
    fun minimalThemeAppliesMwsTypographyByDefault() {
        var bodyFontSize = 0f

        composeRule.setContent {
            MinimalTheme(false, false, null, null) {
                val appliedFontSize = MaterialTheme.typography.bodyLarge.fontSize.value
                SideEffect {
                    bodyFontSize = appliedFontSize
                }
            }
        }

        composeRule.runOnIdle { assertEquals(18f, bodyFontSize) }
    }

    @Test
    fun minimalThemeAllowsCustomTypography() {
        var bodyFontSize = 0f
        val customTypography = Typography(
            bodyLarge = TextStyle(fontSize = 22.sp)
        )

        composeRule.setContent {
            MinimalTheme(darkTheme = false, typography = customTypography) {
                val appliedFontSize = MaterialTheme.typography.bodyLarge.fontSize.value
                SideEffect {
                    bodyFontSize = appliedFontSize
                }
            }
        }

        composeRule.runOnIdle { assertEquals(22f, bodyFontSize) }
    }

    @Test
    fun radioOptionSelectsWhenLabelIsClicked() {
        var selected by mutableStateOf(false)

        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                MinimalRadioOption(
                    label = "Permitir notificações",
                    selected = selected,
                    onSelect = { selected = true }
                )
            }
        }

        composeRule.onNodeWithText("Permitir notificações").performClick()
        composeRule.runOnIdle { assertTrue(selected) }
    }

    @Test
    fun textFieldAcceptsInputAndReportsValue() {
        var value by mutableStateOf("")

        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                MinimalTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = "Nome",
                    modifier = Modifier.testTag("name-field")
                )
            }
        }

        composeRule.onNodeWithTag("name-field").performTextInput("Minimal")
        composeRule.runOnIdle { assertEquals("Minimal", value) }
    }

    @Test
    fun tabsKeepAValidSelectionWhenIndexIsOutOfRange() {
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                MinimalTabs(
                    labels = listOf("Primeiro", "Segundo"),
                    selectedIndex = 99,
                    onSelected = {}
                )
            }
        }

        composeRule.onNodeWithText("Segundo").assertIsSelected()
    }

    @Test
    fun navigationBarKeepsAValidSelectionWhenIndexIsOutOfRange() {
        composeRule.setContent {
            MinimalTheme(darkTheme = false) {
                MinimalNavigationBar(
                    labels = listOf("Início", "Configurações"),
                    selectedIndex = -1,
                    onSelected = {}
                )
            }
        }

        composeRule.onNodeWithText("Início").assertIsSelected()
        composeRule.onNodeWithText("Configurações").assertIsDisplayed()
    }
}
