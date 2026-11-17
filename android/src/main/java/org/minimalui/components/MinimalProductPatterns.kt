package org.minimalui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * UI building block for an AI chat composer. Model/network integration stays in the app.
 */
@Composable
fun MinimalAiComposer(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Escreva uma mensagem...",
    sendLabel: String = "Enviar",
    enabled: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(androidx.compose.ui.unit.dp(8)),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            placeholder = { Text(placeholder) },
            minLines = 2,
            maxLines = 4
        )
        Button(
            onClick = onSend,
            enabled = enabled && value.isNotBlank()
        ) {
            Text(sendLabel)
        }
    }
}

/**
 * Responsive address field for mobile-browser interfaces.
 * The caller owns navigation, history, tabs, and URL validation.
 */
@Composable
fun MinimalBrowserAddressBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
    onReload: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Endereço",
    submitLabel: String = "Ir",
    enabled: Boolean = true
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(androidx.compose.ui.unit.dp(8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            singleLine = true,
            label = { Text(label) }
        )
        TextButton(onClick = onReload, enabled = enabled) {
            Text("↻")
        }
        Button(
            onClick = { onSubmit(value) },
            enabled = enabled && value.isNotBlank()
        ) {
            Text(submitLabel)
        }
    }
}