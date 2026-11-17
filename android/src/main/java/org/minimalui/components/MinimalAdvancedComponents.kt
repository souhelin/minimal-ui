package org.minimalui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.animation.core.animateFloat
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.animation.ValueAnimator
import android.os.Build
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun MinimalDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmLabel: String = "Confirmar",
    dismissLabel: String = "Cancelar"
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = { TextButton(onClick = onConfirm) { Text(confirmLabel) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(dismissLabel) } },
        shape = MaterialTheme.shapes.extraLarge
    )
}

@Composable
fun MinimalBottomSheet(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) { content() }
    }
}

@Composable
fun MinimalDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    options: List<String>,
    onSelect: (String) -> Unit
) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismiss) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(option) },
                onClick = { onSelect(option); onDismiss() }
            )
        }
    }
}

@Composable
fun MinimalTabs(
    labels: List<String>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(labels.isNotEmpty()) { "MinimalTabs requires at least one tab." }
    val safeSelectedIndex = selectedIndex.coerceIn(labels.indices)
    TabRow(selectedTabIndex = safeSelectedIndex, modifier = modifier) {
        labels.forEachIndexed { index, label ->
            Tab(
                selected = safeSelectedIndex == index,
                onClick = { onSelected(index) },
                text = { Text(label) }
            )
        }
    }
}

@Composable
fun MinimalNavigationBar(
    labels: List<String>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(labels.isNotEmpty()) { "MinimalNavigationBar requires at least one item." }
    val safeSelectedIndex = selectedIndex.coerceIn(labels.indices)
    NavigationBar(modifier = modifier) {
        labels.forEachIndexed { index, label ->
            NavigationBarItem(
                selected = safeSelectedIndex == index,
                onClick = { onSelected(index) },
                icon = { Text(label.take(1), modifier = Modifier.clearAndSetSemantics { }) },
                label = { Text(label) }
            )
        }
    }
}

@Composable
fun MinimalRadioOption(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onSelect,
                enabled = enabled,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null, enabled = enabled)
        Text(label)
    }
}

@Composable
fun MinimalSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    require(valueRange.start.isFinite() && valueRange.endInclusive.isFinite()) {
        "MinimalSlider requires finite range endpoints."
    }
    require(value.isFinite()) { "MinimalSlider requires a finite value." }
    require(valueRange.start < valueRange.endInclusive) {
        "MinimalSlider requires a valueRange with start smaller than endInclusive."
    }
    Slider(
        value = value.coerceIn(valueRange),
        onValueChange = onValueChange,
        valueRange = valueRange,
        enabled = enabled,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinimalTooltip(
    message: String,
    content: @Composable () -> Unit
) {
    TooltipBox(
        positionProvider = androidx.compose.material3.TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = { PlainTooltip { Text(message) } },
        state = rememberTooltipState()
    ) { content() }
}

@Composable
fun MinimalSnackbarHost(hostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(hostState = hostState, modifier = modifier)
}

@Composable
fun MinimalLoading(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    label: String = "Carregando"
) {
    Column(
        modifier = modifier.semantics(mergeDescendants = true) { contentDescription = label },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (progress == null) {
            androidx.compose.material3.CircularProgressIndicator()
        } else {
            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(label, style = MaterialTheme.typography.labelMedium)
    }
}

@Suppress("NewApi")
@Composable
fun MinimalSkeleton(
    modifier: Modifier = Modifier,
    lines: Int = 3,
    shimmer: Boolean = true
) {
    val animationsEnabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
        ValueAnimator.areAnimatorsEnabled()
    val shouldShimmer = shimmer && animationsEnabled
    val transition = androidx.compose.animation.core.rememberInfiniteTransition(label = "minimal-skeleton")
    val progress = transition.animateFloat(
        initialValue = 0f,
        targetValue = if (shouldShimmer) 1f else 0f,
        animationSpec = if (shouldShimmer) {
            androidx.compose.animation.core.infiniteRepeatable(
                animation = androidx.compose.animation.core.tween(
                    durationMillis = 1200,
                    easing = androidx.compose.animation.core.LinearEasing
                )
            )
        } else {
            androidx.compose.animation.core.tween(durationMillis = 0)
        },
        label = "minimal-skeleton-shimmer"
    ).value
    val lineCount = lines.coerceAtLeast(1)
    val base = MaterialTheme.colorScheme.surfaceVariant
    val highlight = MaterialTheme.colorScheme.surface
    val shape = MaterialTheme.shapes.small
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(lineCount) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (index == lineCount - 1) 0.68f else 1f)
                    .height(14.dp)
                    .clip(shape)
                    .drawWithCache {
                        val width = size.width
                        val offset = width * 2f * progress
                        val brush = Brush.linearGradient(
                            colors = listOf(base, if (shouldShimmer) highlight else base, base),
                            start = Offset(-width + offset, 0f),
                            end = Offset(width + offset, 0f)
                        )
                        onDrawBehind { drawRect(brush) }
                    }
            )
        }
    }
}

@Composable
fun <T> MinimalList(
    items: List<T>,
    modifier: Modifier = Modifier,
    key: ((T) -> Any)? = null,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = items, key = key) { item -> itemContent(item) }
    }
}

@Composable
fun MinimalPagination(
    currentPage: Int,
    pageCount: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(pageCount > 0) { "pageCount must be greater than zero." }
    val page = currentPage.coerceIn(1, pageCount)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedButton(onClick = { onPageChange(page - 1) }, enabled = page > 1) {
            Text("Anterior")
        }
        Text("Página $page de $pageCount", modifier = Modifier.padding(top = 12.dp))
        Button(onClick = { onPageChange(page + 1) }, enabled = page < pageCount) {
            Text("Próxima")
        }
    }
}
