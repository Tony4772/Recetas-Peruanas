package com.andeanculinary.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.ui.theme.MaizMoradoTertiary
import com.andeanculinary.app.ui.theme.SurfaceBase

@Composable
fun KitchenTimerPill(
    label: String,
    totalSeconds: Int,
    remainingSeconds: Int,
    isRunning: Boolean,
    onToggleRunning: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timeFormatted = "%02d:%02d".format(minutes, seconds)
    val progress = if (totalSeconds > 0) (totalSeconds - remainingSeconds).toFloat() / totalSeconds else 1f

    Surface(
        shape = CircleShape,
        color = SurfaceBase,
        shadowElevation = 8.dp,
        tonalElevation = 4.dp,
        modifier = modifier
            .padding(16.dp)
            .shadow(12.dp, CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 12.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(28.dp)) {
                CircularProgressIndicator(
                    progress = { progress },
                    color = MaizMoradoTertiary,
                    trackColor = Color(0xFFEDE6DC),
                    strokeWidth = 3.dp,
                    modifier = Modifier.fillMaxSize()
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = MaizMoradoTertiary,
                    modifier = Modifier.size(14.dp)
                )
            }

            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaizMoradoTertiary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = timeFormatted,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = onToggleRunning,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaizMoradoTertiary)
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isRunning) "Pausar" else "Reanudar",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            IconButton(
                onClick = onClose,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
