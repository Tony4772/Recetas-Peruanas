package com.saborperu.feature.cooking

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FormatListNumbered
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.ShoppingBasket
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.saborperu.R
import com.saborperu.data.CookingStep
import com.saborperu.data.Recipe
import com.saborperu.ui.components.PillTag
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AjiAmarilloLight
import com.saborperu.ui.theme.AndeanSurface
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.Huacatay
import com.saborperu.ui.theme.OutlineVariant
import com.saborperu.ui.theme.ScrimWarm
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.Terracotta
import com.saborperu.ui.theme.TerracottaBright
import com.saborperu.ui.theme.TerracottaFixed
import com.saborperu.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingModeScreen(
    recipeId: String,
    initialStepIndex: Int,
    onClose: () -> Unit,
    onFinish: () -> Unit,
    viewModel: CookingModeViewModel = viewModel(
        key = "$recipeId-$initialStepIndex",
        factory = viewModelFactory {
            initializer { CookingModeViewModel(recipeId, initialStepIndex) }
        }
    )
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var showSteps by remember { mutableStateOf(false) }
    val step = state.step
    val percent = (state.progress * 100).toInt()
    val minutes = state.remainingSeconds / 60
    val seconds = state.remainingSeconds % 60
    val timeText = String.format(java.util.Locale.US, "%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AndeanSurface)
    ) {
        CookingHeader(recipe = state.recipe, onClose = onClose)

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {
            item { VoiceAssistantBadge() }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PillTag(
                        text = "Paso ${state.stepIndex + 1} de ${state.recipe.steps.size}",
                        containerColor = AjiAmarilloLight,
                        contentColor = AjiAmarillo
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "$percent% completado",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Paso ${state.stepIndex + 1}",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { state.progress },
                    color = AjiAmarillo,
                    trackColor = SurfaceContainerHigh,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(6.dp)
                        .clip(CircleShape)
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .height(172.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(
                            if (state.recipe.id == "lomo-saltado") R.drawable.lomo_wok else state.recipe.imageRes
                        ),
                        contentDescription = step.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, ScrimWarm.copy(alpha = 0.75f)),
                                    startY = 80f
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                            .clip(CircleShape)
                            .background(ScrimWarm.copy(alpha = 0.5f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = null,
                            tint = SurfaceContainerLowest,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = step.fireLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = SurfaceContainerLowest
                        )
                    }
                }
            }

            item { StepFocusCard(step = step) }

            if (step.timerSeconds > 0) {
                item {
                    TimerCard(
                        timeText = timeText,
                        isRunning = state.isRunning,
                        remainingSeconds = state.remainingSeconds,
                        timerLength = step.timerSeconds,
                        onToggle = viewModel::toggleTimer,
                        onAddThirty = viewModel::addThirtySeconds,
                        onReset = viewModel::resetTimer
                    )
                }
            }

            if (step.ingredients.isNotEmpty()) {
                item {
                    StepIngredientsCard(
                        step = step,
                        checked = state.checkedIngredients,
                        onToggle = viewModel::toggleStepIngredient
                    )
                }
            }
        }

        Surface(
            color = SurfaceContainerLowest,
            shadowElevation = 12.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = viewModel::previousStep,
                        enabled = state.stepIndex > 0,
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SurfaceContainerHigh,
                            contentColor = EarthInk,
                            disabledContainerColor = SurfaceContainerLow,
                            disabledContentColor = TextSecondary.copy(alpha = 0.45f)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Anterior", style = MaterialTheme.typography.labelLarge, maxLines = 1)
                    }
                    Spacer(Modifier.width(10.dp))
                    Button(
                        onClick = {
                            if (state.isLastStep) onFinish() else viewModel.nextStep()
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TerracottaBright,
                            contentColor = SurfaceContainerLowest
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp),
                        modifier = Modifier
                            .weight(1.55f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = if (state.isLastStep) {
                                "Finalizar receta"
                            } else {
                                "Paso ${state.stepIndex + 2}: ${state.recipe.steps[state.stepIndex + 1].title}"
                            },
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = if (state.isLastStep) Icons.Rounded.Check else Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Surface(
                    onClick = { showSteps = true },
                    color = SurfaceContainerHigh,
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 9.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.FormatListNumbered,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Ver los ${state.recipe.steps.size} pasos de la receta",
                            style = MaterialTheme.typography.labelMedium,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }

    if (showSteps) {
        ModalBottomSheet(
            onDismissRequest = { showSteps = false },
            containerColor = AndeanSurface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .navigationBarsPadding()
            ) {
                Text(
                    text = "Los ${state.recipe.steps.size} pasos",
                    style = MaterialTheme.typography.headlineSmall,
                    color = EarthInk
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = state.recipe.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
                Spacer(Modifier.height(12.dp))
                state.recipe.steps.forEachIndexed { index, item ->
                    Surface(
                        onClick = {
                            viewModel.goToStep(index)
                            showSteps = false
                        },
                        color = if (index == state.stepIndex) SurfaceContainerHigh else Color.Transparent,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (index == state.stepIndex) AjiAmarilloLight else SurfaceContainerHigh
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (index == state.stepIndex) AjiAmarillo else TextSecondary
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = EarthInk,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "${item.minutes} min • ${item.technique}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                            }
                            if (index == state.stepIndex) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null,
                                    tint = AjiAmarillo,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun CookingHeader(recipe: Recipe, onClose: () -> Unit) {
    Surface(color = AndeanSurface) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onClose,
                    shape = CircleShape,
                    color = SurfaceContainerLow
                ) {
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Cerrar modo cocina",
                            tint = EarthInk,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(AjiAmarilloLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.RestaurantMenu,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(17.dp)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Modo Cocina",
                        style = MaterialTheme.typography.titleMedium,
                        color = EarthInk
                    )
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
private fun VoiceAssistantBadge() {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(TerracottaBright),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = null,
                    tint = SurfaceContainerLowest,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Modo Manos Libres",
                        style = MaterialTheme.typography.labelMedium,
                        color = EarthInk
                    )
                    Text(
                        text = " • ",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextSecondary
                    )
                    Text(
                        text = "Voz activa",
                        style = MaterialTheme.typography.labelMedium,
                        color = Terracotta
                    )
                }
                Text(
                    text = "Di \"Siguiente\" o \"Pausar\"",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            Icon(
                imageVector = Icons.Rounded.GraphicEq,
                contentDescription = null,
                tint = Terracotta,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun StepFocusCard(step: CookingStep) {
    Surface(
        color = SurfaceContainerHigh,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(Terracotta)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = step.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = EarthInk
                    )
                }
                Spacer(Modifier.width(8.dp))
                PillTag(
                    text = step.technique,
                    containerColor = SurfaceContainerLowest,
                    contentColor = TextSecondary
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = step.description,
                style = MaterialTheme.typography.bodyLarge,
                color = EarthInk
            )
            Spacer(Modifier.height(12.dp))
            Surface(
                color = SurfaceContainerLow,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Lightbulb,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(20.dp)
                    )
                    Column {
                        Text(
                            text = "Secreto del Chef:",
                            style = MaterialTheme.typography.titleSmall,
                            color = EarthInk
                        )
                        Text(
                            text = step.chefTip,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TimerCard(
    timeText: String,
    isRunning: Boolean,
    remainingSeconds: Int,
    timerLength: Int,
    onToggle: () -> Unit,
    onAddThirty: () -> Unit,
    onReset: () -> Unit
) {
    val status = when {
        isRunning -> "En curso"
        remainingSeconds <= 0 -> "Completado"
        remainingSeconds == timerLength -> "Listo"
        else -> "Pausado"
    }
    Surface(
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.Timer,
                    contentDescription = null,
                    tint = Terracotta,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Temporizador de cocina",
                    style = MaterialTheme.typography.titleMedium,
                    color = EarthInk,
                    modifier = Modifier.weight(1f)
                )
                PillTag(
                    text = status,
                    containerColor = if (status == "En curso") TerracottaFixed else SurfaceContainerHigh,
                    contentColor = if (status == "En curso") Terracotta else TextSecondary
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = timeText,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 46.sp,
                    lineHeight = 52.sp
                ),
                color = EarthInk,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onReset,
                    shape = CircleShape,
                    color = SurfaceContainerHigh,
                    modifier = Modifier.size(46.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.Replay,
                            contentDescription = "Reiniciar temporizador",
                            tint = TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Button(
                    onClick = onToggle,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AjiAmarillo,
                        contentColor = SurfaceContainerLowest
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = if (isRunning) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = if (isRunning) "Pausar" else "Iniciar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Surface(
                    onClick = onAddThirty,
                    shape = CircleShape,
                    color = SurfaceContainerHigh,
                    modifier = Modifier.size(46.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "+30s",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StepIngredientsCard(
    step: CookingStep,
    checked: Set<Int>,
    onToggle: (Int) -> Unit
) {
    Surface(
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingBasket,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Ingredientes para este paso",
                    style = MaterialTheme.typography.titleMedium,
                    color = EarthInk,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${step.ingredients.size} insumos",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            Spacer(Modifier.height(6.dp))
            step.ingredients.forEachIndexed { index, ingredient ->
                val isChecked = checked.contains(index)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggle(index) }
                        .padding(vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        onClick = { onToggle(index) },
                        shape = CircleShape,
                        color = if (isChecked) Huacatay else Color.Transparent,
                        border = BorderStroke(
                            width = 1.4.dp,
                            color = if (isChecked) Huacatay else OutlineVariant
                        ),
                        modifier = Modifier.size(22.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (isChecked) {
                                Icon(
                                    imageVector = Icons.Rounded.Check,
                                    contentDescription = null,
                                    tint = SurfaceContainerLowest,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = ingredient.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isChecked) TextSecondary.copy(alpha = 0.6f) else EarthInk,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = ingredient.detail,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isChecked) TextSecondary.copy(alpha = 0.5f) else Terracotta
                    )
                }
                if (index != step.ingredients.lastIndex) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(OutlineVariant.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}
