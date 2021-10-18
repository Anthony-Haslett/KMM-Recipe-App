package com.haslett.food2forkkmm.datasource.cache

import com.haslett.food2forkkmm.domain.model.Recipe
import io.ktor.utils.io.errors.*

interface RecipeCache {
    fun insert(recipe: Recipe)
    
    fun insert(recipes: List<Recipe>)
    
    fun search(query: String, page: Int): List<Recipe>
    
    fun getAll(page: Int):List<Recipe>
    
    @Throws(NullPointerException::class)
    fun get(recipeId: Int): Recipe?
}
