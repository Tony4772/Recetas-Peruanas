package com.andeanculinary.app.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.model.Recipe
import com.andeanculinary.app.ui.theme.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingModeScreen(
    recipe: Recipe,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStepIndex by remember { mutableIntStateOf(0) }
    var isCompleted by remember { mutableStateOf(false) }

    val currentStep = recipe.steps[currentStepIndex]
    val totalSteps = recipe.steps.size

    // Step Timer state
    var secondsLeft by remember(currentStepIndex) {
        mutableStateOf(currentStep.timerDurationSeconds)
    }
    var isTimerRunning by remember(currentStepIndex) {
        mutableStateOf(false)
    }

    LaunchedEffect(isTimerRunning, secondsLeft) {
        if (isTimerRunning && secondsLeft != null && secondsLeft!! > 0) {
            delay(1000L)
            secondsLeft = secondsLeft!! - 1
            if (secondsLeft == 0) {
                isTimerRunning = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Modo Cocina Guiado",
                            style = MaterialTheme.typography.labelSmall,
                            color = AjiGoldPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = recipe.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onExit) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Salir")
                    }
                },
                actions = {
                    Surface(
                        shape = CircleShape,
                        color = MaizMoradoTertiary.copy(alpha = 0.1f),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Text(
                            text = "Paso ${currentStepIndex + 1} de $totalSteps",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaizMoradoTertiary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceBase)
            )
        },
        bottomBar = {
            if (!isCompleted) {
                Surface(
                    color = SurfaceBase,
                    tonalElevation = 6.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { if (currentStepIndex > 0) currentStepIndex-- },
                            enabled = currentStepIndex > 0,
                            shape = CircleShape,
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Anterior")
                        }

                        Button(
                            onClick = {
                                if (currentStepIndex < totalSteps - 1) {
                                    currentStepIndex++
                                } else {
                                    isCompleted = true
                                }
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = AjiGoldPrimary),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Text(
                                text = if (currentStepIndex == totalSteps - 1) "Finalizar" else "Siguiente",
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }
        },
        containerColor = SurfaceBase,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Linear Progress Indicator
            LinearProgressIndicator(
                progress = { (currentStepIndex + 1).toFloat() / totalSteps.toFloat() },
                color = AjiGoldPrimary,
                trackColor = SurfaceContainerHigh,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                if (!isCompleted) {
                    // Active Step Card (20dp radius, 2dp Aji Gold border)
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, AjiGoldPrimary, RoundedCornerShape(20.dp))
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(AjiGoldPrimary)
                                ) {
                                    Text(
                                        text = "${currentStep.stepNumber}",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Text(
                                    text = currentStep.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // Large Arm's length text
                            Text(
                                text = currentStep.instruction,
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            // Step Timer if available
                            if (secondsLeft != null) {
                                Spacer(modifier = Modifier.height(24.dp))
                                val mins = secondsLeft!! / 60
                                val secs = secondsLeft!! % 60
                                val formatted = "%02d:%02d".format(mins, secs)

                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = SurfaceBase,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Column {
                                            Text(
                                                text = currentStep.timerLabel ?: "Temporizador",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaizMoradoTertiary,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = formatted,
                                                style = MaterialTheme.typography.headlineMedium,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        }

                                        FilledTonalButton(
                                            onClick = { isTimerRunning = !isTimerRunning },
                                            shape = CircleShape,
                                            colors = ButtonDefaults.filledTonalButtonColors(
                                                containerColor = MaizMoradoTertiary,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Icon(
                                                imageVector = if (isTimerRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                                contentDescription = null
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(if (isTimerRunning) "Pausar" else "Iniciar")
                                        }
                                    }
                                }
                            }

                            // Chef tip
                            if (currentStep.chefSecret != null) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SurfaceContainerLow,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.LocalFireDepartment,
                                            contentDescription = null,
                                            tint = AnticuchoRustSecondary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Column {
                                            Text(
                                                text = "Secreto del Chef Criollo",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = AnticuchoRustSecondary,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = currentStep.chefSecret,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // Celebration Screen
                    Card(
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(32.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                                    .background(HuacatayHerbGreen.copy(alpha = 0.15f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = HuacatayHerbGreen,
                                    modifier = Modifier.size(40.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "¡Plato Listo para Servir!",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Has preparado ${recipe.title} con las técnicas tradicionales y el punto exacto de sazón.",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = onExit,
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(containerColor = AjiGoldPrimary),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Volver al Inicio", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
