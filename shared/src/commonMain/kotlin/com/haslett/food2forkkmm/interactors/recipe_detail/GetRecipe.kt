package com.haslett.food2forkkmm.interactors.recipe_detail

import com.haslett.food2forkkmm.datasource.cache.RecipeCache
import com.haslett.food2forkkmm.datasource.network.RecipeService
import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeCache: RecipeCache,
){
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())
            
            delay(2000)
            
            val recipe =  recipeCache.get(recipeId)
            
            emit(DataState.data(message = null, data = recipe))
            
        }catch (e: Exception){
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }
    
}
