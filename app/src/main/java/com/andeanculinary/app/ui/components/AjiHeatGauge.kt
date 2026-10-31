package com.andeanculinary.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.ui.theme.AnticuchoRustSecondary
import com.andeanculinary.app.ui.theme.OutlineVariantWarm

@Composable
fun AjiHeatGauge(
    level: Int,
    showLabel: Boolean = true,
    modifier: Modifier = Modifier
) {
    val labels = mapOf(
        0 to "Sin picor",
        1 to "Suave · 1/5",
        2 to "Medio · 2/5",
        3 to "Moderado · 3/5",
        4 to "Intenso · 4/5",
        5 to "Volcánico · 5/5"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            for (i in 1..5) {
                val isFilled = i <= level
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = null,
                    tint = if (isFilled) AnticuchoRustSecondary else OutlineVariantWarm.copy(alpha = 0.4f),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        if (showLabel) {
            Text(
                text = labels[level] ?: "$level/5",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
