package com.haslett.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haslett.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.haslett.food2forkkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.haslett.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.haslett.food2forkkmm.android.presentation.theme.AppTheme
import com.haslett.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.haslett.food2forkkmm.presentation.recipe_detail.RecipeDetailState

@ExperimentalStdlibApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit
) {
    AppTheme(displayProgressBar = state.isLoading) {
        if (state.recipe == null && state.isLoading) {
            // Loading
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (state.recipe == null) {
            Text(
                text = "We were unable to retrieve the details for this recipe\nTry resetting the app",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )
        } else {
            RecipeView(recipe = state.recipe!!)
        }
    }
}