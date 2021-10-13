package com.haslett.food2forkkmm.android.presentation.navigation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
) {
    if (recipeId == null) {
        Text("ERROR")
    } else {
        Text("RecipeDetailScreen:  $recipeId")
    }
}
