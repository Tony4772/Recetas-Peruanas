package com.saborperu.feature.favorites

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
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saborperu.data.RecipeRepository
import com.saborperu.ui.components.PrimaryPillButton
import com.saborperu.ui.components.RecipeFeedCard
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.Terracotta
import com.saborperu.ui.theme.TerracottaFixed
import com.saborperu.ui.theme.TextSecondary
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AjiAmarilloLight
import com.saborperu.ui.theme.OnTerracottaFixed

@Composable
fun FavoritesScreen(
    onOpenRecipe: (String) -> Unit
) {
    val favorites by RecipeRepository.favorites.collectAsStateWithLifecycle()
    val recipes = RecipeRepository.recipes.filter { favorites.contains(it.id) }

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
                        .background(TerracottaFixed),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = null,
                        tint = OnTerracottaFixed,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Mis Favoritos",
                        style = MaterialTheme.typography.headlineSmall,
                        color = EarthInk
                    )
                    Text(
                        text = if (recipes.isEmpty()) {
                            "Guarda lo que más te antoje"
                        } else {
                            "${recipes.size} ${if (recipes.size == 1) "receta guardada" else "recetas guardadas"}"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                }
                Surface(
                    shape = CircleShape,
                    color = AjiAmarilloLight
                ) {
                    Text(
                        text = "${recipes.size}",
                        style = MaterialTheme.typography.titleMedium,
                        color = AjiAmarillo,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        if (recipes.isEmpty()) {
            EmptyFavorites(onDiscover = { onOpenRecipe("ceviche-limeno") })
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = "Toca el corazón de una receta para tenerla siempre a mano.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                items(recipes, key = { it.id }) { recipe ->
                    RecipeFeedCard(
                        recipe = recipe,
                        favorite = true,
                        onOpen = { onOpenRecipe(recipe.id) },
                        onFavorite = { RecipeRepository.toggleFavorite(recipe.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyFavorites(onDiscover: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(TerracottaFixed),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = Terracotta,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(Modifier.height(18.dp))
        Text(
            text = "Aún no tienes favoritos",
            style = MaterialTheme.typography.headlineSmall,
            color = EarthInk,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Explora ceviches, guisos de sierra y sabores de la selva; marca con un corazón las recetas que quieras cocinar pronto.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        Surface(
            color = SurfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint = Terracotta,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Consejo: guarda el Lomo Saltado para tu próxima cena criolla.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(Modifier.height(18.dp))
        PrimaryPillButton(
            text = "Descubrir recetas",
            onClick = onDiscover,
            icon = Icons.Rounded.ArrowForward,
            containerColor = Terracotta
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "También puedes revisar tu Lista del Mercado desde el inicio.",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}
