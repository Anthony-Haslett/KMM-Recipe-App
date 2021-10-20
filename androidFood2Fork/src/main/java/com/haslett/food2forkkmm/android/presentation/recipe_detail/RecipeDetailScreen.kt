package com.haslett.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.haslett.food2forkkmm.android.presentation.components.RecipeImage
import com.haslett.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.haslett.food2forkkmm.android.presentation.theme.AppTheme
import com.haslett.food2forkkmm.domain.model.Recipe

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text("Unable to get details of this recipe...")
        } else {
            RecipeCard(
                recipe = recipe,
                onClick = {}
            )
        }
    }
}
