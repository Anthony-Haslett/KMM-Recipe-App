package com.haslett.food2forkkmm.interactors.recipe_list

import com.haslett.food2forkkmm.datasource.cache.RecipeCache
import com.haslett.food2forkkmm.datasource.network.RecipeService
import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow  {
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            // insert into cache
            recipeCache.insert(recipes)
            
            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        }catch (e: Exception){
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }
    }
}