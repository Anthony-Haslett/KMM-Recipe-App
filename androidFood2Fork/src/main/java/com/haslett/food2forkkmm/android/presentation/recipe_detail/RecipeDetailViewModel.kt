package com.haslett.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.haslett.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.haslett.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
): ViewModel() {
    
    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())
    
    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))
        }
    }
    
    fun onTriggerEvent(event: RecipeDetailEvents) {
        when(event) {
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }
    
    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipe ->
                println("RecipeDetailVM: recipe: ${recipe}")
                state.value = state.value.copy(recipe = recipe)
            }
            
            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }
    
    private fun handleError(errorMessage: String) {
        // TODO("handle errors")
    }
}

