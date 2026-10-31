package com.andeanculinary.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.ui.theme.SurfaceContainerHigh

@Composable
fun QuantityStepper(
    servings: Int,
    onServingsChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minServings: Int = 1,
    maxServings: Int = 16
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(CircleShape)
            .background(SurfaceContainerHigh)
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        IconButton(
            onClick = {
                if (servings > minServings) onServingsChange(servings - 1)
            },
            enabled = servings > minServings,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Disminuir porciones",
                modifier = Modifier.size(16.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "$servings porc.",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        IconButton(
            onClick = {
                if (servings < maxServings) onServingsChange(servings + 1)
            },
            enabled = servings < maxServings,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Aumentar porciones",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
