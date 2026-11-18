package com.saborperu.feature.cooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saborperu.data.CookingStep
import com.saborperu.data.Recipe
import com.saborperu.data.RecipeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CookingUiState(
    val recipe: Recipe,
    val stepIndex: Int,
    val isRunning: Boolean,
    val remainingSeconds: Int,
    val checkedIngredients: Set<Int>
) {
    val step: CookingStep get() = recipe.steps[stepIndex]
    val progress: Float get() = (stepIndex + 1).toFloat() / recipe.steps.size
    val isLastStep: Boolean get() = stepIndex == recipe.steps.lastIndex
}

class CookingModeViewModel(recipeId: String, initialStepIndex: Int) : ViewModel() {

    private val recipe: Recipe = RecipeRepository.recipeById(recipeId) ?: RecipeRepository.recipes.first()
    private val stepIndex = MutableStateFlow(
        initialStepIndex.coerceIn(0, recipe.steps.lastIndex)
    )
    private val remainingSeconds = MutableStateFlow(currentStep.timerSeconds)
    private val isRunning = MutableStateFlow(false)
    private val checkedIngredients = MutableStateFlow<Set<Int>>(emptySet())
    private var timerJob: Job? = null

    private val currentStep: CookingStep
        get() = recipe.steps[stepIndex.value]

    val uiState: StateFlow<CookingUiState> = combine(
        stepIndex,
        isRunning,
        remainingSeconds,
        checkedIngredients
    ) { index, running, remaining, checked ->
        CookingUiState(
            recipe = recipe,
            stepIndex = index,
            isRunning = running,
            remainingSeconds = remaining,
            checkedIngredients = checked
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CookingUiState(
            recipe = recipe,
            stepIndex = stepIndex.value,
            isRunning = false,
            remainingSeconds = currentStep.timerSeconds,
            checkedIngredients = emptySet()
        )
    )

    fun toggleTimer() {
        if (isRunning.value) {
            pauseTimer()
        } else {
            if (remainingSeconds.value <= 0) {
                remainingSeconds.value = currentStep.timerSeconds
            }
            if (remainingSeconds.value <= 0) return
            isRunning.value = true
            timerJob?.cancel()
            timerJob = viewModelScope.launch {
                while (remainingSeconds.value > 0) {
                    delay(1_000)
                    remainingSeconds.value -= 1
                }
                isRunning.value = false
            }
        }
    }

    fun addThirtySeconds() {
        remainingSeconds.value += 30
    }

    fun resetTimer() {
        timerJob?.cancel()
        isRunning.value = false
        remainingSeconds.value = currentStep.timerSeconds
    }

    fun nextStep() {
        if (stepIndex.value < recipe.steps.lastIndex) {
            goToStep(stepIndex.value + 1)
        }
    }

    fun previousStep() {
        if (stepIndex.value > 0) {
            goToStep(stepIndex.value - 1)
        }
    }

    fun goToStep(index: Int) {
        timerJob?.cancel()
        isRunning.value = false
        stepIndex.value = index.coerceIn(0, recipe.steps.lastIndex)
        remainingSeconds.value = currentStep.timerSeconds
        checkedIngredients.value = emptySet()
    }

    fun toggleStepIngredient(index: Int) {
        checkedIngredients.value = checkedIngredients.value.toMutableSet().apply {
            if (!add(index)) remove(index)
        }
    }

    private fun pauseTimer() {
        isRunning.value = false
        timerJob?.cancel()
    }
}
