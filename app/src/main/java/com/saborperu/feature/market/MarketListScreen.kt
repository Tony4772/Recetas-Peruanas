package com.saborperu.feature.market

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.LunchDining
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.Scale
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.ShoppingBasket
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material.icons.rounded.TipsAndUpdates
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saborperu.data.MarketItem
import com.saborperu.data.RecipeRepository
import com.saborperu.ui.components.FilterPill
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AjiAmarilloLight
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.Huacatay
import com.saborperu.ui.theme.OnAjiAmarilloLight
import com.saborperu.ui.theme.OutlineVariant
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.TextSecondary
import java.util.Locale

private data class MarketGroup(
    val title: String,
    val subtitle: String,
    val imageRes: Int?,
    val icon: ImageVector?,
    val items: List<MarketItem>
)

@Composable
fun MarketListScreen(
    onOpenRecipe: (String) -> Unit,
    viewModel: MarketListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var showAddDialog by remember { mutableStateOf(false) }

    val groups = remember(state.items, state.groupByAisle) {
        if (state.groupByAisle) {
            state.items.groupBy { it.aisle }.map { (aisle, items) ->
                MarketGroup(
                    title = aisle,
                    subtitle = "${items.size} ${if (items.size == 1) "insumo" else "insumos"}",
                    imageRes = null,
                    icon = aisleIcon(aisle),
                    items = items
                )
            }
        } else {
            state.items.groupBy { it.recipeId }.map { (recipeId, items) ->
                val recipe = RecipeRepository.recipeById(recipeId)
                MarketGroup(
                    title = items.first().recipeName,
                    subtitle = recipe?.let { "${it.portions} porciones • ${it.difficulty}" }
                        ?: "${items.size} insumos",
                    imageRes = items.first().recipeImageRes.takeIf { it != 0 },
                    icon = if (items.first().recipeImageRes == 0) Icons.Rounded.AddShoppingCart else null,
                    items = items
                )
            }
        }
    }

    val recipeGroupCount = remember(state.items) { state.items.map { it.recipeId }.distinct().size }
    val aisleGroupCount = remember(state.items) { state.items.map { it.aisle }.distinct().size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        MarketHeader(
            pending = state.pendingCount,
            total = state.totalCount,
            onShare = { shareMarketList(context, state) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (state.completedCount > 0) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.clearCompleted() }
                                .padding(horizontal = 4.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.DoneAll,
                                contentDescription = null,
                                tint = Huacatay,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Limpiar completados",
                                style = MaterialTheme.typography.labelSmall,
                                color = Huacatay
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    Surface(
                        onClick = { shareMarketList(context, state) },
                        shape = CircleShape,
                        color = Huacatay.copy(alpha = 0.12f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = null,
                                tint = Huacatay,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "WhatsApp",
                                style = MaterialTheme.typography.labelSmall,
                                color = Huacatay
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterPill(
                        label = "Por Recetas ($recipeGroupCount)",
                        selected = !state.groupByAisle,
                        onClick = { viewModel.setGrouping(false) },
                        leadingIcon = Icons.Rounded.MenuBook,
                        modifier = Modifier.weight(1f)
                    )
                    FilterPill(
                        label = "Por Pasillos ($aisleGroupCount)",
                        selected = state.groupByAisle,
                        onClick = { viewModel.setGrouping(true) },
                        leadingIcon = Icons.Rounded.Storefront,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            if (state.items.isEmpty()) {
                item {
                    EmptyMarketState(onAdd = { showAddDialog = true })
                }
            } else {
                groups.forEach { group ->
                    item(key = "group-${group.title}") {
                        GroupHeader(group = group)
                    }
                    items(group.items, key = { it.id }) { item ->
                        MarketItemRow(
                            item = item,
                            showAisle = !state.groupByAisle,
                            onToggle = { viewModel.toggleItem(item.id) }
                        )
                    }
                }

                item {
                    Spacer(Modifier.height(10.dp))
                    SmartSummaryCard(state = state)
                }
            }

            item {
                Spacer(Modifier.height(12.dp))
                AddManualItemButton(onClick = { showAddDialog = true })
            }
        }
    }

    if (showAddDialog) {
        AddIngredientDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { label, aisle ->
                viewModel.addItem(label, aisle)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun MarketHeader(
    pending: Int,
    total: Int,
    onShare: () -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(AjiAmarillo),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingBasket,
                    contentDescription = null,
                    tint = SurfaceContainerLowest,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Lista del Mercado",
                    style = MaterialTheme.typography.headlineSmall,
                    color = EarthInk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$pending pendientes • $total en total",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            Surface(
                onClick = onShare,
                shape = CircleShape,
                color = Huacatay
            ) {
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Compartir lista por WhatsApp",
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GroupHeader(group: MarketGroup) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            group.imageRes != null -> {
                Image(
                    painter = painterResource(group.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            group.icon != null -> {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AjiAmarilloLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = group.icon,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = group.title,
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = group.subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
        Text(
            text = "${group.items.count { !it.checked }}/${group.items.size}",
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary
        )
    }
}

@Composable
private fun MarketItemRow(
    item: MarketItem,
    showAisle: Boolean,
    onToggle: () -> Unit
) {
    Surface(
        onClick = onToggle,
        shape = RoundedCornerShape(16.dp),
        color = if (item.checked) SurfaceContainer else SurfaceContainerLow,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = if (item.checked) Huacatay else SurfaceContainerLowest,
                border = if (item.checked) null else BorderStroke(1.5.dp, OutlineVariant),
                modifier = Modifier.size(24.dp)
            ) {
                if (item.checked) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "Comprado",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (item.checked) TextSecondary else EarthInk,
                    textDecoration = if (item.checked) TextDecoration.LineThrough else TextDecoration.None,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (showAisle) {
                        Icon(
                            imageVector = aisleIcon(item.aisle),
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = item.aisle,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary,
                            maxLines = 1
                        )
                    }
                    if (item.note != null) {
                        if (showAisle) {
                            Text(
                                text = "•",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                        Text(
                            text = item.note,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            if (item.estimatedPrice > 0.0) {
                Spacer(Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = String.format(Locale.US, "S/ %.2f", item.estimatedPrice),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (item.checked) TextSecondary else EarthInk
                    )
                    Text(
                        text = "aprox.",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary,
                        fontSize = 9.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun SmartSummaryCard(state: MarketUiState) {
    Surface(
        color = SurfaceContainerHigh,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Resumen Inteligente de Mercado",
                    style = MaterialTheme.typography.titleMedium,
                    color = EarthInk,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Unidades métricas",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SummaryStat(
                    icon = Icons.Rounded.Scale,
                    label = "Peso estimado",
                    value = String.format(Locale.US, "%.1f kg", state.estimatedWeight),
                    modifier = Modifier.weight(1f)
                )
                SummaryStat(
                    icon = Icons.Rounded.Payments,
                    label = "Gasto aprox.",
                    value = String.format(Locale.US, "S/ %.2f", state.estimatedPrice),
                    modifier = Modifier.weight(1f)
                )
                SummaryStat(
                    icon = Icons.Rounded.TaskAlt,
                    label = "Completitud",
                    value = "${state.completionPercent}%",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainer)
            ) {
                if (state.completionPercent > 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(state.completionPercent / 100f)
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(Huacatay)
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = "${state.completedCount} de ${state.totalCount} insumos listos",
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
            Spacer(Modifier.height(12.dp))
            Surface(
                color = AjiAmarilloLight,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Lightbulb,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Tip: Lleva bolsa térmica para la carne y la gallina fresca.",
                        style = MaterialTheme.typography.labelSmall,
                        color = OnAjiAmarilloLight
                    )
                }
            }
        }
    }
}

@Composable
private fun SummaryStat(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AjiAmarillo,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk,
                maxLines = 1
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun AddManualItemButton(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLowest,
        border = BorderStroke(1.dp, OutlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 13.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.AddShoppingCart,
                contentDescription = null,
                tint = AjiAmarillo,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Añadir ingrediente manual",
                style = MaterialTheme.typography.labelMedium,
                color = AjiAmarillo
            )
        }
    }
}

@Composable
private fun EmptyMarketState(onAdd: () -> Unit) {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(AjiAmarilloLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingBasket,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Tu lista está vacía",
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Añade tus recetas favoritas o escribe un ingrediente para empezar tus compras.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Surface(
                onClick = onAdd,
                shape = CircleShape,
                color = AjiAmarillo
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AddShoppingCart,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Añadir ingrediente",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun AddIngredientDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var label by remember { mutableStateOf("") }
    var aisle by remember { mutableStateOf("Abarrotes") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Añadir ingrediente", style = MaterialTheme.typography.headlineSmall) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Se agregará a tu lista del mercado como pendiente.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
                OutlinedTextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text("Ingrediente") },
                    placeholder = { Text("Ej. 1 kg de papa amarilla") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = aisle,
                    onValueChange = { aisle = it },
                    label = { Text("Pasillo") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(label, aisle) },
                enabled = label.isNotBlank()
            ) {
                Text("Añadir")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

private fun aisleIcon(aisle: String): ImageVector = when {
    aisle.contains("Carnicer", ignoreCase = true) -> Icons.Rounded.LunchDining
    aisle.contains("Verduler", ignoreCase = true) -> Icons.Rounded.Eco
    aisle.contains("Abarrote", ignoreCase = true) -> Icons.Rounded.Kitchen
    aisle.contains("Hierba", ignoreCase = true) -> Icons.Rounded.Spa
    else -> Icons.Rounded.ShoppingBasket
}

private fun shareMarketList(context: Context, state: MarketUiState) {
    if (state.items.isEmpty()) return
    val text = buildString {
        appendLine("Lista del Mercado - Sabor Perú")
        appendLine("${state.pendingCount} pendientes de ${state.totalCount} insumos")
        state.items.groupBy { it.recipeName }.forEach { (recipeName, items) ->
            appendLine()
            appendLine("$recipeName:")
            items.forEach { item ->
                val mark = if (item.checked) "[x]" else "[ ]"
                appendLine("$mark ${item.label}")
            }
        }
        appendLine()
        append(
            String.format(
                Locale.US,
                "Peso aprox. %.1f kg • Gasto aprox. S/ %.2f • %d%% completado",
                state.estimatedWeight,
                state.estimatedPrice,
                state.completionPercent
            )
        )
    }
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    try {
        context.startActivity(Intent.createChooser(sendIntent, "Compartir lista del mercado"))
    } catch (_: ActivityNotFoundException) {
        // Sin apps disponibles para compartir: no interrumpimos la pantalla.
    }
}
