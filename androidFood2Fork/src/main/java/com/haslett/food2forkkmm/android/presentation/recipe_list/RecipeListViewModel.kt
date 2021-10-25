package com.haslett.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.haslett.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.haslett.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes
) : ViewModel() {
    
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())
    
    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }
    
    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query)
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }
    
    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }
    
    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }
    
    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            
            state.value = state.value.copy(isLoading = dataState.isLoading)
            
            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }
            
            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }
    
    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(state.value.recipes)
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }
    
    private fun handleError(s: String) {
        TODO("Handle errors")
    }
}

