package org.minimalui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private val BrowserShape = RoundedCornerShape(14.dp)

@Composable
private fun BrowserSurface(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(modifier = modifier, shape = BrowserShape, color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) { content() }
}

/** 01. Device status area; provide actual status information from the host app. */
@Composable
fun MinimalBrowserStatusBar(time: String, indicators: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(time, style = MaterialTheme.typography.labelMedium)
        Text(indicators, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

/** 02. Editable address field; URL validation and navigation remain with the caller. */
@Composable
fun MinimalBrowserAddressField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier,
    onSubmit: (String) -> Unit = {}, leadingLabel: String = "HTTPS") {
    BrowserSurface(modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(leadingLabel, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(8.dp))
            TextField(value = value, onValueChange = onValueChange, modifier = Modifier.weight(1f),
                singleLine = true, textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface))
            Text("↵", modifier = Modifier.clickable { onSubmit(value) }.padding(10.dp),
                color = MaterialTheme.colorScheme.primary)
        }
    }
}

/** 03. Security state label; do not infer trust from the displayed label. */
@Composable
fun MinimalBrowserSecurityIndicator(label: String, modifier: Modifier = Modifier) {
    Row(modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("●", color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(6.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

/** 04. Search suggestion row. */
@Composable
fun MinimalBrowserSuggestion(title: String, supportingText: String? = null, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().clickable(onClick = onClick).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("⌕", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            supportingText?.let { Text(it, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant) }
        }
    }
}

/** 05. Home shortcut tile. */
@Composable
fun MinimalBrowserShortcut(title: String, symbol: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    BrowserSurface(modifier.clickable(onClick = onClick).padding(12.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            Text(symbol, style = MaterialTheme.typography.titleLarge)
            Text(title, style = MaterialTheme.typography.labelSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

/** 06. Tab switcher counter. */
@Composable
fun MinimalBrowserTabCounter(count: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    BrowserSurface(modifier.clickable(onClick = onClick)) {
        Text(count.coerceAtLeast(0).toString(), modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge)
    }
}

/** 07. Tab preview card. */
@Composable
fun MinimalBrowserTabCard(title: String, preview: @Composable () -> Unit, onSelect: () -> Unit,
    onClose: () -> Unit, modifier: Modifier = Modifier) {
    BrowserSurface(modifier) {
        Column(Modifier.clickable(onClick = onSelect).padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium)
                Text("×", Modifier.clickable(onClick = onClose).padding(6.dp))
            }
            Spacer(Modifier.height(8.dp))
            preview()
        }
    }
}

/** 08. Main browser navigation controls. */
@Composable
fun MinimalBrowserNavigationBar(onBack: () -> Unit, onForward: () -> Unit, onHome: () -> Unit,
    onNewTab: () -> Unit, onTabs: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        BrowserAction("‹", "Back", onBack); BrowserAction("›", "Forward", onForward)
        BrowserAction("⌂", "Home", onHome); BrowserAction("+", "New tab", onNewTab); BrowserAction("▢", "Tabs", onTabs)
    }
}

@Composable
private fun BrowserAction(symbol: String, label: String, onClick: () -> Unit) {
    Column(Modifier.clickable(onClick = onClick).padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(symbol, style = MaterialTheme.typography.titleLarge)
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

/** 09. Bookmark list row. */
@Composable
fun MinimalBrowserBookmarkItem(title: String, url: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    BrowserListItem("☆", title, url, onClick, modifier)
}

/** 10. History list row. */
@Composable
fun MinimalBrowserHistoryItem(title: String, detail: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    BrowserListItem("◷", title, detail, onClick, modifier)
}

@Composable
private fun BrowserListItem(symbol: String, title: String, detail: String, onClick: () -> Unit, modifier: Modifier) {
    Row(modifier.fillMaxWidth().clickable(onClick = onClick).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(symbol, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(detail, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

/** 11. Download status item. */
@Composable
fun MinimalBrowserDownloadItem(fileName: String, status: String, onOpen: () -> Unit, modifier: Modifier = Modifier) {
    BrowserListItem("↓", fileName, status, onOpen, modifier)
}

/** 12. Reader mode controls. */
@Composable
fun MinimalBrowserReaderControls(textScaleLabel: String, onDecrease: () -> Unit, onIncrease: () -> Unit,
    modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text("Modo leitura", style = MaterialTheme.typography.titleSmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            BrowserAction("A−", "Diminuir", onDecrease)
            BrowserAction("A+", "Aumentar", onIncrease)
            Text(textScaleLabel, style = MaterialTheme.typography.labelSmall)
        }
    }
}

/** 13. Find-in-page control. */
@Composable
fun MinimalBrowserFindBar(query: String, onQueryChange: (String) -> Unit, matchStatus: String,
    onPrevious: () -> Unit, onNext: () -> Unit, onClose: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        TextField(query, onQueryChange, modifier = Modifier.weight(1f), singleLine = true, label = { Text("Pesquisar na página") })
        Text(matchStatus, modifier = Modifier.padding(horizontal = 6.dp), style = MaterialTheme.typography.labelSmall)
        BrowserAction("↑", "Previous", onPrevious); BrowserAction("↓", "Next", onNext); BrowserAction("×", "Close", onClose)
    }
}

/** 14. Share action row; the app supplies platform share behavior. */
@Composable
fun MinimalBrowserShareActions(onCopy: () -> Unit, onShare: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        MinimalOutlinedButton("Copiar link", onCopy, Modifier.weight(1f))
        MinimalPrimaryButton("Compartilhar", onShare, Modifier.weight(1f))
    }
}

/** 15. Site permission prompt; permission policy and system APIs belong to the host app. */
@Composable
fun MinimalBrowserPermissionPrompt(title: String, description: String, onDismiss: () -> Unit,
    onAllow: () -> Unit, modifier: Modifier = Modifier) {
    MinimalCard(modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            MinimalOutlinedButton("Agora não", onDismiss, Modifier.weight(1f))
            MinimalPrimaryButton("Permitir", onAllow, Modifier.weight(1f))
        }
    }
}
