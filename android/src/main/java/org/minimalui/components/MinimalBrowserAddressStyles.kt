package org.minimalui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/** Fifteen structural styles for browser address/navigation chrome. */
enum class MinimalBrowserAddressStyle {
    SINGLE_TOP,
    SINGLE_BOTTOM,
    DOUBLE_TOP,
    DOUBLE_BOTTOM,
    TOP_WITH_BOTTOM_NAV,
    BOTTOM_WITH_TOP_NAV,
    FLOATING,
    COMPACT,
    EXPANDED,
    TABS_INTEGRATED,
    CENTERED_SEARCH,
    SIDE_CONTROLS,
    MINIMAL,
    ADAPTIVE,
    CONTEXTUAL
}

/**
 * Browser chrome composition only. The host application owns navigation, URL validation,
 * tab state, permissions, history, network requests and actual device status information.
 *
 * All callbacks are supplied by the consumer. The content slot remains between top and
 * bottom chrome so the same layout can be used in a browser screen or a design preview.
 */
@Composable
fun MinimalBrowserAddressLayout(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
    style: MinimalBrowserAddressStyle,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onForward: () -> Unit = {},
    onHome: () -> Unit = {},
    onReload: () -> Unit = {},
    onTabs: () -> Unit = {},
    onNewTab: () -> Unit = {},
    onShare: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val bottomAddress = style == MinimalBrowserAddressStyle.SINGLE_BOTTOM ||
        style == MinimalBrowserAddressStyle.DOUBLE_BOTTOM ||
        style == MinimalBrowserAddressStyle.BOTTOM_WITH_TOP_NAV
    val doubleRow = style == MinimalBrowserAddressStyle.DOUBLE_TOP ||
        style == MinimalBrowserAddressStyle.DOUBLE_BOTTOM
    val floating = style == MinimalBrowserAddressStyle.FLOATING
    val compact = style == MinimalBrowserAddressStyle.COMPACT ||
        style == MinimalBrowserAddressStyle.MINIMAL
    val minimal = style == MinimalBrowserAddressStyle.MINIMAL
    val expanded = style == MinimalBrowserAddressStyle.EXPANDED
    val tabsIntegrated = style == MinimalBrowserAddressStyle.TABS_INTEGRATED
    val centered = style == MinimalBrowserAddressStyle.CENTERED_SEARCH
    val sideControls = style == MinimalBrowserAddressStyle.SIDE_CONTROLS
    val adaptive = style == MinimalBrowserAddressStyle.ADAPTIVE
    val contextual = style == MinimalBrowserAddressStyle.CONTEXTUAL
    val topNavigation = style == MinimalBrowserAddressStyle.BOTTOM_WITH_TOP_NAV

    Column(modifier = modifier.fillMaxSize()) {
        if (!bottomAddress) {
            if (topNavigation) BrowserNavigationRow(onBack, onForward, onHome, onReload, onTabs, enabled)
            BrowserAddressSurface(floating, expanded) {
                BrowserAddressRow(
                    value, onValueChange, onSubmit, onBack, onReload, onTabs, enabled,
                    compact, centered, sideControls, adaptive, contextual, minimal, onShare
                )
                if (doubleRow) BrowserNavigationRow(onBack, onForward, onHome, onReload, onTabs, enabled)
                if (expanded) {
                    Text(
                        "Pesquisar ou digitar endereço",
                        modifier = Modifier.padding(start = 14.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (tabsIntegrated) BrowserTabsRow(onTabs, onNewTab, enabled)
            }
        }

        if (floating) Spacer(Modifier.height(8.dp))
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) { content() }

        if (bottomAddress) {
            if (doubleRow) BrowserNavigationRow(onBack, onForward, onHome, onReload, onTabs, enabled)
            BrowserAddressSurface(floating, expanded) {
                BrowserAddressRow(
                    value, onValueChange, onSubmit, onBack, onReload, onTabs, enabled,
                    compact, centered, sideControls, adaptive, contextual, minimal, onShare
                )
                if (expanded) {
                    Text(
                        "Pesquisar ou digitar endereço",
                        modifier = Modifier.padding(start = 14.dp, bottom = 10.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        if (style == MinimalBrowserAddressStyle.TOP_WITH_BOTTOM_NAV) {
            BrowserBottomNavigation(onBack, onForward, onHome, onTabs, enabled)
        }
    }
}

@Composable
private fun BrowserAddressSurface(
    floating: Boolean,
    expanded: Boolean,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (floating) 12.dp else 0.dp, vertical = if (floating) 8.dp else 0.dp),
        shape = RoundedCornerShape(if (floating) 24.dp else if (expanded) 20.dp else 14.dp),
        color = if (floating) MaterialTheme.colorScheme.surfaceContainerHigh
            else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        shadowElevation = if (floating) 2.dp else 0.dp
    ) { content() }
}

@Composable
private fun BrowserAddressRow(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
    onBack: () -> Unit,
    onReload: () -> Unit,
    onTabs: () -> Unit,
    enabled: Boolean,
    compact: Boolean,
    centered: Boolean,
    sideControls: Boolean,
    adaptive: Boolean,
    contextual: Boolean,
    minimal: Boolean,
    onShare: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = if (compact) 2.dp else 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (sideControls) BrowserAction("Voltar", "‹", enabled, onBack)
        if (!centered && !adaptive) {
            Text("●", modifier = Modifier.padding(horizontal = 5.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium)
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                textAlign = if (centered) TextAlign.Center else TextAlign.Start
            ),
            placeholder = {
                Text(
                    if (centered) "Pesquisar ou digitar URL" else "Endereço",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            shape = RoundedCornerShape(if (compact) 999.dp else 16.dp)
        )
        if (contextual) {
            BrowserAction("Compartilhar", "↗", enabled, onShare)
        } else if (adaptive) {
            BrowserAction("Abas", "▢", enabled, onTabs)
        } else if (!minimal) {
            BrowserAction("Recarregar", "↻", enabled, onReload)
        }
        BrowserAction("Ir", "Ir", enabled && value.isNotBlank()) { onSubmit(value) }
    }
}

@Composable
private fun BrowserNavigationRow(
    onBack: () -> Unit,
    onForward: () -> Unit,
    onHome: () -> Unit,
    onReload: () -> Unit,
    onTabs: () -> Unit,
    enabled: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrowserAction("Voltar", "‹", enabled, onBack)
        BrowserAction("Avançar", "›", enabled, onForward)
        BrowserAction("Início", "⌂", enabled, onHome)
        BrowserAction("Recarregar", "↻", enabled, onReload)
        BrowserAction("Abas", "▢", enabled, onTabs)
    }
}

@Composable
private fun BrowserBottomNavigation(
    onBack: () -> Unit,
    onForward: () -> Unit,
    onHome: () -> Unit,
    onTabs: () -> Unit,
    enabled: Boolean
) {
    Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BrowserAction("Voltar", "‹", enabled, onBack)
            BrowserAction("Avançar", "›", enabled, onForward)
            BrowserAction("Início", "⌂", enabled, onHome)
            BrowserAction("Abas", "▢", enabled, onTabs)
        }
    }
}

@Composable
private fun BrowserTabsRow(onTabs: () -> Unit, onNewTab: () -> Unit, enabled: Boolean) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 4.dp)) {
        TextButton(onClick = onTabs, enabled = enabled) { Text("Aba atual") }
        TextButton(onClick = onNewTab, enabled = enabled) { Text("+ Nova aba") }
    }
}

@Composable
private fun BrowserAction(label: String, symbol: String, enabled: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.semantics { contentDescription = label }
    ) {
        Text(symbol, style = MaterialTheme.typography.labelLarge)
    }
}
