import React, { useState } from 'react';
import { Copy, Check, FileCode, Folder, Download, Smartphone, Sparkles, Terminal } from 'lucide-react';

interface KotlinFile {
  name: string;
  path: string;
  category: 'core' | 'theme' | 'screens' | 'components' | 'config';
  code: string;
}

const KOTLIN_FILES: KotlinFile[] = [
  {
    name: 'MainActivity.kt',
    path: 'app/src/main/java/com/andeanculinary/app/MainActivity.kt',
    category: 'core',
    code: `package com.andeanculinary.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andeanculinary.app.ui.navigation.AndeanNavHost
import com.andeanculinary.app.ui.theme.AndeanCulinaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndeanCulinaryTheme {
                AndeanNavHost()
            }
        }
    }
}`
  },
  {
    name: 'Recipe.kt',
    path: 'app/src/main/java/com/andeanculinary/app/model/Recipe.kt',
    category: 'core',
    code: `package com.andeanculinary.app.model

data class NutritionalInfo(
    val calories: Int,       // kcal por porción
    val proteins: Int,       // gramos por porción
    val carbohydrates: Int,  // gramos por porción
    val fats: Int = 0        // gramos opcionales
)

data class Recipe(
    val id: String,
    val title: String,
    val quechuaOrLocalName: String? = null,
    val subtitle: String,
    val description: String,
    val region: Region,
    val category: Category,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val difficulty: String,
    val spiceLevel: Int,
    val defaultServings: Int = 4,
    val imageUrl: String,
    val tags: List<String> = emptyList(),
    val nutrition: NutritionalInfo,
    val pairing: Pairing,
    val culturalHistory: String,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>
)`
  },
  {
    name: 'Color.kt',
    path: 'app/src/main/java/com/andeanculinary/app/ui/theme/Color.kt',
    category: 'theme',
    code: `package com.andeanculinary.app.ui.theme

import androidx.compose.ui.graphics.Color

// Andean Culinary Material 3 Color Tokens
val AjiGoldPrimary = Color(0xFFD97706)
val AjiGoldPrimaryDark = Color(0xFF8D4B00)
val OnAjiGold = Color(0xFFFFFFFF)
val AjiGoldContainer = Color(0xFFB15F00)
val OnAjiGoldContainer = Color(0xFFFFF7ED)

val AnticuchoRustSecondary = Color(0xFFC2410C)
val AnticuchoRustDark = Color(0xFF9A3412)
val OnAnticuchoRust = Color(0xFFFFFFFF)
val AnticuchoRustContainer = Color(0xFFFD6B36)
val OnAnticuchoRustContainer = Color(0xFF5D1900)

val MaizMoradoTertiary = Color(0xFF581C87)
val MaizMoradoLight = Color(0xFF7940A9)
val OnMaizMorado = Color(0xFFFFFFFF)

val HuacatayHerbGreen = Color(0xFF15803D)
val SurfaceBase = Color(0xFFFEF9F1)
val SurfaceContainer = Color(0xFFF5F0E8)
val SurfaceContainerHigh = Color(0xFFECE8E0)
val OnSurfaceText = Color(0xFF1D1C17)
val OnSurfaceVariantText = Color(0xFF554336)
val OutlineWarm = Color(0xFF887364)`
  },
  {
    name: 'Theme.kt',
    path: 'app/src/main/java/com/andeanculinary/app/ui/theme/Theme.kt',
    category: 'theme',
    code: `package com.andeanculinary.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = AjiGoldPrimary,
    onPrimary = OnAjiGold,
    secondary = AnticuchoRustSecondary,
    onSecondary = OnAnticuchoRust,
    tertiary = MaizMoradoTertiary,
    onTertiary = OnMaizMorado,
    background = SurfaceBase,
    onBackground = OnSurfaceText,
    surface = SurfaceBase,
    onSurface = OnSurfaceText,
    surfaceVariant = SurfaceContainerHigh,
    onSurfaceVariant = OnSurfaceVariantText,
    outline = OutlineWarm
)

@Composable
fun AndeanCulinaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.surface.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AndeanTypography,
        content = content
    )
}`
  },
  {
    name: 'CookingModeScreen.kt',
    path: 'app/src/main/java/com/andeanculinary/app/ui/screens/CookingModeScreen.kt',
    category: 'screens',
    code: `package com.andeanculinary.app.ui.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    // Step Timer state with Coroutine
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
            if (secondsLeft == 0) isTimerRunning = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onExit) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Salir")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LinearProgressIndicator(
                progress = { (currentStepIndex + 1).toFloat() / totalSteps.toFloat() },
                color = AjiGoldPrimary,
                modifier = Modifier.fillMaxWidth()
            )

            // Active Step Card (20dp radius & 2dp Aji Gold border)
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                modifier = Modifier
                    .padding(16.dp)
                    .border(2.dp, AjiGoldPrimary, RoundedCornerShape(20.dp))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Paso \${currentStep.stepNumber}: \${currentStep.title}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = currentStep.instruction,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}`
  },
  {
    name: 'RecipeDetailScreen.kt',
    path: 'app/src/main/java/com/andeanculinary/app/ui/screens/RecipeDetailScreen.kt',
    category: 'screens',
    code: `package com.andeanculinary.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andeanculinary.app.model.Recipe
import com.andeanculinary.app.ui.components.QuantityStepper
import com.andeanculinary.app.ui.theme.*

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    isSaved: Boolean,
    onBack: () -> Unit,
    onToggleSave: () -> Unit,
    onStartCooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    var servings by remember { mutableIntStateOf(recipe.defaultServings) }
    var checkedIngredients by remember { mutableStateOf(setOf<String>()) }
    val scaleRatio = servings.toFloat() / recipe.defaultServings.toFloat()

    Scaffold(
        bottomBar = {
            Button(
                onClick = onStartCooking,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = AjiGoldPrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(52.dp)
            ) {
                Icon(Icons.Default.Restaurant, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Empezar a Cocinar", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    modifier = Modifier.fillMaxWidth().aspectRatio(16f / 10f)
                )
            }
            item {
                QuantityStepper(
                    servings = servings,
                    onServingsChange = { servings = it }
                )
            }
            items(recipe.ingredients) { ing ->
                val isChecked = checkedIngredients.contains(ing.id)
                // 24dp circular checkbox
                Row(modifier = Modifier.clickable {
                    checkedIngredients = if (isChecked) checkedIngredients - ing.id else checkedIngredients + ing.id
                }) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isChecked) HuacatayHerbGreen else Color.Transparent)
                            .border(2.dp, if (isChecked) HuacatayHerbGreen else OutlineWarm, CircleShape)
                    )
                    Text("\${ing.name} - \${(ing.baseAmount * scaleRatio)} \${ing.unit}")
                }
            }
        }
    }
}`
  },
  {
    name: 'app/build.gradle.kts',
    path: 'app/build.gradle.kts',
    category: 'config',
    code: `plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.andeanculinary.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.andeanculinary.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("io.coil-kt:coil-compose:2.7.0")
}`
  }
];

export const KotlinCodeViewer: React.FC = () => {
  const [selectedFile, setSelectedFile] = useState<KotlinFile>(KOTLIN_FILES[0]);
  const [copied, setCopied] = useState(false);

  const handleCopy = () => {
    if (navigator.clipboard) {
      navigator.clipboard.writeText(selectedFile.code);
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    }
  };

  return (
    <div className="space-y-6">
      {/* Banner */}
      <div className="bg-[#F5F0E8] rounded-3xl p-6 sm:p-8 border border-[#EDE6DC]">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
          <div>
            <div className="flex items-center gap-2 mb-1">
              <span className="px-2.5 py-0.5 rounded-full text-[11px] font-bold bg-[#15803D] text-white">
                Kotlin 2.0 + Jetpack Compose
              </span>
              <span className="text-xs text-[#887364] font-medium">
                Android SDK 35 (Android 15 Ready)
              </span>
            </div>
            <h1 className="font-display text-2xl sm:text-3xl font-extrabold text-[#1D1C17]">
              Arquitectura Kotlin Nativa & Código Fuente
            </h1>
            <p className="text-xs sm:text-sm text-[#554336] mt-1">
              Archivos Kotlin generados en <code className="px-1.5 py-0.5 bg-[#EDE6DC] rounded text-[#8D4B00] font-mono">/app/src/main/java/com/andeanculinary/app/</code> listos para abrir en Android Studio.
            </p>
          </div>

          <button
            type="button"
            onClick={handleCopy}
            className="py-2.5 px-5 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white text-xs font-bold shadow-md flex items-center gap-2 self-start sm:self-auto transition-all active:scale-95"
          >
            {copied ? <Check className="w-4 h-4" /> : <Copy className="w-4 h-4" />}
            <span>{copied ? '¡Copiado!' : 'Copiar Archivo Actual'}</span>
          </button>
        </div>
      </div>

      {/* Code Browser Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6 items-start">
        {/* File Tree List */}
        <div className="lg:col-span-4 bg-[#F5F0E8] rounded-3xl p-4 border border-[#EDE6DC] space-y-2">
          <div className="px-3 py-2 text-xs font-bold uppercase tracking-wider text-[#8D4B00] flex items-center gap-2">
            <Folder className="w-4 h-4" />
            <span>Módulos Android / Gradle</span>
          </div>

          <div className="space-y-1">
            {KOTLIN_FILES.map((file) => {
              const isSelected = selectedFile.name === file.name;
              return (
                <button
                  key={file.name}
                  type="button"
                  onClick={() => setSelectedFile(file)}
                  className={`w-full text-left p-3 rounded-2xl flex items-center justify-between text-xs transition-all ${
                    isSelected
                      ? 'bg-[#FEF9F1] font-bold text-[#8D4B00] shadow-sm border border-[#D97706]/30'
                      : 'text-[#554336] hover:bg-[#EDE6DC]'
                  }`}
                >
                  <div className="flex items-center gap-2 truncate">
                    <FileCode className={`w-4 h-4 shrink-0 ${isSelected ? 'text-[#D97706]' : 'text-[#887364]'}`} />
                    <span className="truncate">{file.name}</span>
                  </div>
                  <span className="text-[10px] px-2 py-0.5 rounded-full bg-[#EDE6DC] uppercase tracking-wider font-semibold text-[#887364]">
                    {file.category}
                  </span>
                </button>
              );
            })}
          </div>

          <div className="p-3 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC] text-[11px] text-[#554336] space-y-1 mt-4">
            <strong className="block text-[#1D1C17] font-semibold">Ubicación física:</strong>
            <p className="font-mono text-[10px] break-all text-[#8D4B00]">
              {selectedFile.path}
            </p>
          </div>
        </div>

        {/* Code View Canvas */}
        <div className="lg:col-span-8 bg-[#1D1C17] rounded-3xl overflow-hidden border border-[#554336]/40 shadow-2xl flex flex-col">
          {/* Code Window Header */}
          <div className="flex items-center justify-between px-5 py-3.5 bg-[#282420] border-b border-[#554336]/30">
            <div className="flex items-center gap-2">
              <span className="w-3 h-3 rounded-full bg-[#ba1a1a]" />
              <span className="w-3 h-3 rounded-full bg-[#D97706]" />
              <span className="w-3 h-3 rounded-full bg-[#15803D]" />
              <span className="text-xs font-mono text-[#DBC2B0] ml-2">
                {selectedFile.name}
              </span>
            </div>

            <button
              type="button"
              onClick={handleCopy}
              className="py-1 px-3 rounded-full bg-[#332E29] hover:bg-[#443D36] text-[#FEF9F1] text-[11px] font-semibold flex items-center gap-1.5 transition-colors"
            >
              {copied ? <Check className="w-3.5 h-3.5 text-[#15803D]" /> : <Copy className="w-3.5 h-3.5" />}
              <span>{copied ? 'Copiado' : 'Copiar'}</span>
            </button>
          </div>

          {/* Syntax text block */}
          <pre className="p-6 text-xs font-mono text-[#F5F0E8] overflow-x-auto leading-relaxed max-h-[580px] selection:bg-[#D97706]/40">
            <code>{selectedFile.code}</code>
          </pre>
        </div>
      </div>
    </div>
  );
};
