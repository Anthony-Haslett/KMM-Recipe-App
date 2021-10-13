package com.haslett.food2forkkmm.android.presentation.navigation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.haslett.food2forkkmm.android.di.Dummy
import com.haslett.food2forkkmm.android.di.DummyImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dummy: Dummy
) : ViewModel() {
    
    val recipeId: MutableState<Int?> = mutableStateOf(null)
    
    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            this.recipeId.value = recipeId
        }
        println("RecipeDetailViewModel: ${dummy.description()}")
    }
}
