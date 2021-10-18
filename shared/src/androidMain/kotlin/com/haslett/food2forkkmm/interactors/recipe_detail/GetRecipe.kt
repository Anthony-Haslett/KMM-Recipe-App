package com.haslett.food2forkkmm.interactors.recipe_detail

import com.haslett.food2forkkmm.datasource.network.RecipeService
import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe (
    private val recipeService: RecipeService, // We will change this to cache later
){
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())
            
            val recipe =  recipeService.get(recipeId)
            
            emit(DataState.data(message = null, data = recipe))
            
        }catch (e: Exception){
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }
    
}
