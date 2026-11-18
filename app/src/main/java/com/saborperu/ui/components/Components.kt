package com.saborperu.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.saborperu.R
import com.saborperu.data.Recipe
import com.saborperu.data.Region
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.Huacatay
import com.saborperu.ui.theme.MaizMorado
import com.saborperu.ui.theme.MaizMoradoFixed
import com.saborperu.ui.theme.OnMaizMoradoFixed
import com.saborperu.ui.theme.OutlineVariant
import com.saborperu.ui.theme.ScrimWarm
import com.saborperu.ui.theme.StarAmber
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerHighest
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.Terracotta
import com.saborperu.ui.theme.TerracottaFixed
import com.saborperu.ui.theme.TextSecondary

fun regionColor(region: Region): Color = when (region) {
    Region.COSTA -> Terracotta
    Region.SIERRA -> AjiAmarillo
    Region.SELVA -> MaizMorado
}

@Composable
fun BrandTopBar(
    subtitle: String,
    onSearch: () -> Unit,
    onProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AjiAmarillo),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.RestaurantMenu,
                    contentDescription = null,
                    tint = SurfaceContainerLowest,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Sabor Perú",
                    style = MaterialTheme.typography.headlineSmall,
                    color = AjiAmarillo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            IconButton(onClick = onSearch) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Buscar recetas",
                    tint = TextSecondary
                )
            }
            IconButton(onClick = onProfile) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Perfil de usuario",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(2.dp, SurfaceContainerLow, CircleShape)
                )
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    icon: ImageVector,
    iconTint: Color = AjiAmarillo,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = EarthInk,
            modifier = Modifier.weight(1f)
        )
        if (actionText != null && onAction != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onAction)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = AjiAmarillo
                )
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun FilterPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    selectedColor: Color = AjiAmarillo
) {
    val background = if (selected) selectedColor else SurfaceContainerHigh
    val foreground = if (selected) SurfaceContainerLowest else TextSecondary
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = background,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = if (selected) foreground else selectedColor,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = foreground,
                maxLines = 1
            )
        }
    }
}

@Composable
fun PillTag(
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = containerColor,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            maxLines = 1
        )
    }
}

@Composable
fun MetaItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    tint: Color = AjiAmarillo
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            maxLines = 1
        )
    }
}

@Composable
fun SpiceGauge(
    level: Int,
    contentColor: Color = Terracotta,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(1.dp)) {
        repeat(3) { index ->
            Icon(
                imageVector = Icons.Rounded.LocalFireDepartment,
                contentDescription = null,
                tint = if (index < level) contentColor else OutlineVariant,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun RatingPill(
    rating: Double,
    reviews: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(ScrimWarm.copy(alpha = 0.68f))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = StarAmber,
            modifier = Modifier.size(15.dp)
        )
        Text(
            text = String.format(java.util.Locale.US, "%.1f", rating),
            style = MaterialTheme.typography.labelMedium,
            color = SurfaceContainerLowest,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "($reviews)",
            style = MaterialTheme.typography.labelSmall,
            color = SurfaceContainerHighest
        )
    }
}

@Composable
fun FavoriteCircleButton(
    favorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = SurfaceContainerLowest.copy(alpha = 0.92f),
        modifier = modifier.size(38.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = if (favorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = if (favorite) "Quitar de favoritos" else "Añadir a favoritos",
                tint = if (favorite) Terracotta else TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun PrimaryPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    containerColor: Color = AjiAmarillo,
    contentColor: Color = SurfaceContainerLowest
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(horizontal = 22.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
        if (icon != null) {
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun RecipeFeedCard(
    recipe: Recipe,
    favorite: Boolean,
    onOpen: () -> Unit,
    onFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onOpen,
        shape = RoundedCornerShape(24.dp),
        color = SurfaceContainerLow,
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f)
            ) {
                Image(
                    painter = painterResource(recipe.imageRes),
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, ScrimWarm.copy(alpha = 0.55f)),
                                startY = 120f
                            )
                        )
                )
                PillTag(
                    text = recipe.regionTag,
                    containerColor = regionColor(recipe.region),
                    contentColor = SurfaceContainerLowest,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                )
                FavoriteCircleButton(
                    favorite = favorite,
                    onClick = onFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )
                RatingPill(
                    rating = recipe.rating,
                    reviews = recipe.reviewCount,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )
                PillTag(
                    text = recipe.difficulty,
                    containerColor = Terracotta,
                    contentColor = SurfaceContainerLowest,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                )
            }

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = EarthInk,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MetaItem(Icons.Rounded.Schedule, "${recipe.prepMinutes} min")
                    MetaItem(Icons.Rounded.Group, "${recipe.portions} porciones")
                    MetaItem(
                        Icons.Rounded.LocalFireDepartment,
                        recipe.spiceLabel,
                        tint = Terracotta
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        recipe.tags.take(2).forEach { tag ->
                            PillTag(
                                text = tag,
                                containerColor = SurfaceContainerHigh,
                                contentColor = TextSecondary
                            )
                        }
                    }
                    Button(
                        onClick = onOpen,
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AjiAmarillo,
                            contentColor = SurfaceContainerLowest
                        ),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Text("Ver receta", style = MaterialTheme.typography.labelMedium)
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CompactRecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLow,
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.45f)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = EarthInk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = recipe.difficulty,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TipCard(
    number: Int,
    title: String,
    description: String,
    containerColor: Color,
    contentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun MacroStat(
    icon: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AjiAmarillo,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = EarthInk
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )
    }
}

@Composable
fun HerbPill(text: String) {
    PillTag(
        text = text,
        containerColor = Color(0xFFDCFCE7),
        contentColor = Huacatay
    )
}

@Composable
fun PurplePill(text: String) {
    PillTag(
        text = text,
        containerColor = MaizMoradoFixed,
        contentColor = OnMaizMoradoFixed
    )
}

@Composable
fun SectionLinkRow(
    title: String,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = EarthInk,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Ver todas ($count)",
            style = MaterialTheme.typography.labelSmall,
            color = AjiAmarillo,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = onClick)
                .padding(4.dp)
        )
    }
}

@Composable
fun DividerSoft(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(OutlineVariant.copy(alpha = 0.6f))
    )
}
