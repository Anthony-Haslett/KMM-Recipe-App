package com.haslett.food2forkkmm.presentation.recipe_list

import com.haslett.food2forkkmm.domain.model.Recipe
import com.haslett.food2forkkmm.domain.util.Queue
import com.squareup.sqldelight.Query

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<String> = Queue(mutableListOf())
)
