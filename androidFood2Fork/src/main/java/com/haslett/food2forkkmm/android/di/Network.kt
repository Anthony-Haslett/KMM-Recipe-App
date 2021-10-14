package com.haslett.food2forkkmm.android.di

import com.haslett.food2forkkmm.datasource.network.KtorClientFactory
import com.haslett.food2forkkmm.datasource.network.RecipeService
import com.haslett.food2forkkmm.datasource.network.RecipeServiceImpl
import com.haslett.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }
    
    @Singleton
    @Provides
    fun providesRecipeService(
        httpClient: HttpClient
    ): RecipeService {
        return RecipeServiceImpl(
            httpClient = httpClient,
            baseUrl = BASE_URL
        )
        
    }
}
