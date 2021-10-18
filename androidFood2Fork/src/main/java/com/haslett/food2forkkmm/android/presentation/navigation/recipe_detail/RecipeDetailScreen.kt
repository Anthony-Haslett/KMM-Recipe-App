package com.haslett.food2forkkmm.android.presentation.navigation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.haslett.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {
    if (recipe == null) {
        Text("Unable to get details of this recipe...")
    } else {
        Text("${recipe.title}")
    }
}
