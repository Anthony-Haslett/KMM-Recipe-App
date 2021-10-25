package com.haslett.food2forkkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.haslett.food2forkkmm.android.presentation.components.RecipeList
import com.haslett.food2forkkmm.android.presentation.recipe_list.components.SearchAppBar
import com.haslett.food2forkkmm.android.presentation.theme.AppTheme
import com.haslett.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.haslett.food2forkkmm.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = false) {
        
        Scaffold(topBar = {
            SearchAppBar(
                query = state.query,
                onQueryChange = {
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                }
            )
        }) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }
}
