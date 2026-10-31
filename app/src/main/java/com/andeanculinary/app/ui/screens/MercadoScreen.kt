package com.andeanculinary.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.ui.components.AjiHeatGauge
import com.andeanculinary.app.ui.theme.*

data class ChiliItem(
    val name: String,
    val scientific: String,
    val scoville: String,
    val heat: Int,
    val origin: String,
    val usage: String
)

val CHILIS_LIST = listOf(
    ChiliItem("Ají Amarillo", "Capsicum baccatum", "30,000 - 50,000 SHU", 3, "Costa central", "Causa, Ají de Gallina, Huancaína y Lomo Saltado."),
    ChiliItem("Ají Panca", "Capsicum chinense", "1,000 - 1,500 SHU", 1, "Valles de Ica", "Anticuchos, Carapulcra y marinadas a la brasa."),
    ChiliItem("Ají Limo", "Capsicum chinense", "30,000 - 60,000 SHU", 4, "Norte de Lambayeque", "Ceviches, tiraditos y choritos a la chalaca."),
    ChiliItem("Rocoto Andino", "Capsicum pubescens", "100,000 - 200,000 SHU", 5, "Arequipa y Andes", "Rocoto Relleno y crema de ají con huacatay.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MercadoScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mercado & Ajíes del Perú",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceBase)
            )
        },
        containerColor = SurfaceBase,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "BOTICA GASTRONÓMICA",
                            style = MaterialTheme.typography.labelSmall,
                            color = AjiGoldPrimaryDark,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Biodiversidad de Ajíes Nativos",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Los ajíes no solo aportan picor: son la estructura aromática que define la identidad de cada plato regional.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(CHILIS_LIST.size) { idx ->
                val chili = CHILIS_LIST[idx]
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = chili.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${chili.scientific} · ${chili.origin}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = chili.scoville,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = AnticuchoRustSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        AjiHeatGauge(level = chili.heat)

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Uso: ${chili.usage}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
