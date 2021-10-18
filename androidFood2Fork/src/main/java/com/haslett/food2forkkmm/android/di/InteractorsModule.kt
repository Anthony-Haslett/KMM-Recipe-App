package com.haslett.food2forkkmm.android.di

import com.haslett.food2forkkmm.datasource.cache.RecipeCache
import com.haslett.food2forkkmm.datasource.network.RecipeService
import com.haslett.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.haslett.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    
    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache,
    ): SearchRecipes{
        return SearchRecipes(
            recipeService = recipeService,
            recipeCache = recipeCache
        )
    }
    
    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe{
        return GetRecipe(recipeCache = recipeCache)
    }
}
