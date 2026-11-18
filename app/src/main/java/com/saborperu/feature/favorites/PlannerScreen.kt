package com.saborperu.feature.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Autorenew
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.saborperu.data.Recipe
import com.saborperu.data.RecipeCategory
import com.saborperu.data.RecipeRepository
import com.saborperu.ui.components.PillTag
import com.saborperu.ui.components.regionColor
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.Huacatay
import com.saborperu.ui.theme.MaizMorado
import com.saborperu.ui.theme.MaizMoradoFixed
import com.saborperu.ui.theme.OnMaizMoradoFixed
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.TextSecondary

private val plannerDays = listOf(
    "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
)

private val plannerSeedIds = listOf(
    "lomo-saltado",
    "ceviche-limeno",
    "rocoto-relleno",
    "pachamanca-olla",
    "trucha-frita",
    "tacacho",
    "suspiro-limena"
)

private const val TODAY_DAY = "Viernes"

@Composable
fun PlannerScreen(
    onOpenRecipe: (String) -> Unit
) {
    var planIds by remember { mutableStateOf(plannerSeedIds) }

    val plan = plannerDays.mapIndexed { index, day ->
        PlanEntry(
            day = day,
            recipe = RecipeRepository.recipeById(planIds[index])
        )
    }.filter { it.recipe != null }

    val totalMinutes = plan.mapNotNull { it.recipe }.sumOf { it.prepMinutes }
    val regionCount = plan.mapNotNull { it.recipe }.map { it.region }.distinct().size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Surface(color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaizMoradoFixed),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = null,
                        tint = OnMaizMoradoFixed,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Planificador Semanal",
                        style = MaterialTheme.typography.headlineSmall,
                        color = EarthInk,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Tu semana criolla, lista para cocinar",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                PlanSummaryCard(
                    recipeCount = plan.size,
                    totalMinutes = totalMinutes,
                    regionCount = regionCount,
                    onNewPlan = { planIds = rotatePlan(planIds) }
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Comidas de la semana",
                        style = MaterialTheme.typography.titleMedium,
                        color = EarthInk,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Toca un plato para ver la receta",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
            }

            items(plan, key = { it.day }) { entry ->
                val recipe = entry.recipe ?: return@items
                PlanDayCard(
                    day = entry.day,
                    recipe = recipe,
                    isToday = entry.day == TODAY_DAY,
                    onOpen = { onOpenRecipe(recipe.id) },
                    onSwap = {
                        planIds = swapDay(planIds, plannerDays.indexOf(entry.day))
                    }
                )
            }

            item {
                Surface(
                    color = SurfaceContainerLow,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = MaizMorado,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Tip: deja reposar la pachamanca y el juane un día antes para intensificar sus aromas.",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

private data class PlanEntry(
    val day: String,
    val recipe: Recipe?
)

@Composable
private fun PlanSummaryCard(
    recipeCount: Int,
    totalMinutes: Int,
    regionCount: Int,
    onNewPlan: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = SurfaceContainerHigh,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Plan de 7 días",
                        style = MaterialTheme.typography.titleMedium,
                        color = EarthInk
                    )
                    Text(
                        text = "Una receta para cada día, sin repetir región.",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
                Surface(
                    onClick = onNewPlan,
                    shape = CircleShape,
                    color = AjiAmarillo
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Autorenew,
                            contentDescription = null,
                            tint = SurfaceContainerLowest,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "Nuevo plan",
                            style = MaterialTheme.typography.labelSmall,
                            color = SurfaceContainerLowest
                        )
                    }
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PlanStat(
                    value = "$recipeCount",
                    label = "recetas",
                    modifier = Modifier.weight(1f)
                )
                PlanStat(
                    value = "$totalMinutes min",
                    label = "de cocina",
                    modifier = Modifier.weight(1f)
                )
                PlanStat(
                    value = "$regionCount",
                    label = "regiones",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun PlanStat(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk,
                maxLines = 1
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun PlanDayCard(
    day: String,
    recipe: Recipe,
    isToday: Boolean,
    onOpen: () -> Unit,
    onSwap: () -> Unit
) {
    Surface(
        onClick = onOpen,
        shape = RoundedCornerShape(20.dp),
        color = if (isToday) SurfaceContainer else SurfaceContainerLow,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(46.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day.take(3).uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isToday) Huacatay else TextSecondary
                )
                Spacer(Modifier.height(4.dp))
                if (isToday) {
                    Surface(shape = CircleShape, color = Huacatay) {
                        Text(
                            text = "Hoy",
                            style = MaterialTheme.typography.labelSmall,
                            color = SurfaceContainerLowest,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(regionColor(recipe.region))
                    )
                }
            }

            Image(
                painter = painterResource(recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(14.dp))
            )
            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = EarthInk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PillTag(
                        text = recipe.region.label,
                        containerColor = regionColor(recipe.region).copy(alpha = 0.14f),
                        contentColor = regionColor(recipe.region)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "${recipe.prepMinutes} min",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary,
                            maxLines = 1
                        )
                    }
                }
            }

            IconButton(onClick = onSwap, modifier = Modifier.size(36.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Autorenew,
                    contentDescription = "Cambiar receta del $day",
                    tint = AjiAmarillo,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

private fun rotatePlan(ids: List<String>): List<String> {
    val pool = RecipeRepository.recipes
        .filter { it.category != RecipeCategory.BEBIDA }
        .map { it.id }
    if (pool.isEmpty()) return ids
    val anchor = pool.indexOf(ids.firstOrNull()).coerceAtLeast(-1)
    return List(plannerDays.size) { offset ->
        pool[(anchor + 1 + offset) % pool.size]
    }
}

private fun swapDay(ids: List<String>, index: Int): List<String> {
    if (ids.isEmpty() || index !in ids.indices) return ids
    return ids.toMutableList().also { list ->
        list[index] = ids[(index + 1) % ids.size]
    }
}
