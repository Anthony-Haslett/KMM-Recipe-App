package com.haslett.food2forkkmm.android.di

import com.haslett.food2forkkmm.android.BaseApplication
import com.haslett.food2forkkmm.datasource.cache.DriverFactory
import com.haslett.food2forkkmm.datasource.cache.RecipeCache
import com.haslett.food2forkkmm.datasource.cache.RecipeCacheImpl
import com.haslett.food2forkkmm.datasource.cache.RecipeDatabaseFactory
import com.haslett.food2forkkmm.datasource.datasource.RecipeDatabase
import com.haslett.food2forkkmm.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    
    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }
    
    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDataBase: RecipeDatabase
    ): RecipeCache {
        return RecipeCacheImpl(
            recipeDatabase = recipeDataBase,
            datetimeUtil = DatetimeUtil()
        )
    }
}
